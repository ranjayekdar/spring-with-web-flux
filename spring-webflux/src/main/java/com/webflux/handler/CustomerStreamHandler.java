package com.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dao.CustomerDao;
import com.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomersStream(ServerRequest request){
		Flux<Customer> customersList = customerDao.getCustomersList(request);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customersList, Customer.class);
	}
	
	public Mono<ServerResponse> findByCustomerId(ServerRequest request){
		int customerId = Integer.parseInt(request.pathVariable("customerId"));
		Mono<Customer> customersList = customerDao.getCustomersList(request).filter(c->c.getId()==customerId).next();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customersList, Customer.class);
	}

}
