package com.mcheng.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/customers")
public class CustomerGateway {

	@Autowired
	CustomersMongoService customerService;
	
	@GetMapping("/loaddata")
	@ResponseBody
	public ResponseEntity<?> loadData() {
		customerService.loadData();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}
	
	@GetMapping("/{id}")
	public Optional<Customer> findCustomerById(@PathVariable String id) {
		return customerService.findCustomerById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomerById(@PathVariable String id) {
		customerService.deleteCustomerById(id);
	}
	
	@GetMapping("/byname/{name}")
	public List<Customer> getCustomerByName(@PathVariable String name) {
		return customerService.getCustomerByName(name);
	}
	
	@PostMapping("/byname")
	public List<Customer> getCustomerByNamePost(@RequestBody Customer customer) {
		return customerService.getCustomerByName(customer.getName());
	}
	
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		customer = customerService.saveOrUpdate(customer);
		return customer;
	}
	
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customer = customerService.saveOrUpdate(customer);
		return customer;
	}
}
