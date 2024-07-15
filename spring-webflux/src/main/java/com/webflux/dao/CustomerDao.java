package com.webflux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	public static void sleepExecution(int time) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers(){
		return IntStream.range(1, 51)
				.peek(CustomerDao::sleepExecution)
				.peek(System.out::println)
				.mapToObj(num->new Customer(num,"Customer"+num))
				.collect(Collectors.toList());
	}
	
	
	public Flux<Customer> getCustomersStream(){
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(System.out::println)
				.map(num->
				new Customer(num,"Customer"+num));
	}
	
	public Flux<Customer> getCustomersList(ServerRequest request){
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(System.out::println)
				.map(num->
				new Customer(num,"Customer"+num));
	}
	
}
