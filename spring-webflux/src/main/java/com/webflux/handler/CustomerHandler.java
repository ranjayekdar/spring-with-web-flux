package com.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dao.CustomerDao;
import com.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request){
		Flux<Customer> customersList = customerDao.getCustomersList(request);
		return ServerResponse.ok().body(customersList, Customer.class);
	}

}
