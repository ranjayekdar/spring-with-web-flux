package com.webflux.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.dao.CustomerDao;
import com.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	public List<Customer> loadAllCustomer(){
		long startTime = System.currentTimeMillis();
		List<Customer> customers = customerDao.getCustomers();
		long endTime = System.currentTimeMillis();
		System.out.print(endTime-startTime);
		return customers;
	}
	
	
public Flux<Customer> loadAllCustomerStream(){
		
		long startTime = System.currentTimeMillis();
		Flux<Customer> customers = customerDao.getCustomersStream();
		long endTime = System.currentTimeMillis();
		System.out.print(endTime-startTime);
		return customers;
	}

}
