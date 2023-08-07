package com.mcheng.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CustomersMongoService {
	
	@Autowired
	CustomersMongoRepository mongoRepository;
	
	public void loadData() {
		Customer customer1 = new Customer("Miki", "m@email.com", "abc123");
		Customer customer2 = new Customer("Aaron", "a@email.com", "abc123456");
		Customer customer3 = new Customer("D'Artagnan", "d@email.com", "abc123321");
		mongoRepository.save(customer1);
		mongoRepository.save(customer2);
		mongoRepository.save(customer3);
	}
	
	public List<Customer> findAllCustomers() {
		return mongoRepository.findAll();
	}
	
	public Optional<Customer> findCustomerById(String id) {
		return mongoRepository.findById(id);
	}
	
	public List<Customer> getCustomerByName(String name) {
		
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", new GenericPropertyMatcher().startsWith()).withMatcher("name", new GenericPropertyMatcher().exact());
		Customer c1 = new Customer();
		c1.setName(name);
		Example<Customer> c = Example.of(c1, matcher);
		return mongoRepository.findAll(c);
		
	}
	
	public List<Customer> getCustomerByNamePost(Customer customer) {
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", new GenericPropertyMatcher().startsWith()).withMatcher("name", new GenericPropertyMatcher().exact());
		Example<Customer> c = Example.of(customer, matcher);
		return mongoRepository.findAll(c);
	}
	
	public void deleteCustomerById(String id) {
		mongoRepository.deleteById(id);
	}
	
	public void deleteCustomer(Customer customer) {
		mongoRepository.delete(customer);
	}
	
	public Customer saveOrUpdate(Customer customer) {
		customer = mongoRepository.save(customer);
		return customer;
	}
	
}
