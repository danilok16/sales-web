package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerService {

	@Autowired
	private CustomerRepository customerReporsitory;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer save(@RequestBody @Valid Customer customer) {
		return customerReporsitory.save(customer);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@PathVariable("id") Long id, @RequestBody @Valid Customer customerUpdate) {
		customerReporsitory
			.findById(id)
			.map(customer -> {
				customerUpdate.setId(customer.getId());
				return customerReporsitory.save(customerUpdate);
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping("{id}")
	public Customer findById(@PathVariable("id") Long id) {
		return customerReporsitory
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		customerReporsitory
				.findById(id)
				.map( customer -> {
					customerReporsitory.delete(customer);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping
	public List<Customer> getAll(){
		return customerReporsitory.findAll();
	}
}
