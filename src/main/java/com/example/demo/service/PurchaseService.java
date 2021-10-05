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

import com.example.demo.model.Purchase;
import com.example.demo.repository.PurchaseRepository;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin("http://localhost:4200")
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseReporsitory;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Purchase save(@RequestBody @Valid Purchase purchase) {
		purchase.setAmount(purchase.getProducts().stream().mapToDouble(x -> x.getPrice()).sum());
		return purchaseReporsitory.save(purchase);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@PathVariable("id") Long id, @RequestBody @Valid Purchase purchaseUpdate) {
		purchaseReporsitory
			.findById(id)
			.map(purchase -> {
				purchaseUpdate.setId(purchase.getId());
				return purchaseReporsitory.save(purchaseUpdate);
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
	}
	
	@GetMapping("{id}")
	public Purchase findById(@PathVariable("id") Long id) {
		return purchaseReporsitory
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		purchaseReporsitory
				.findById(id)
				.map(purchase -> {
					purchaseReporsitory.delete(purchase);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
	}
	
	@GetMapping
	public List<Purchase> getAll(){
		return purchaseReporsitory.findAll();
	}
}
