package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "venda")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonProperty("client")
	private Customer customer;
	
	@ManyToMany
    @JoinTable(name="venda_produto", 
               joinColumns=  @JoinColumn( name = "venda_id"), 
               inverseJoinColumns= @JoinColumn(name = "produto_id") )
	private List<Product> products;
	
	@Column(name = "valor")
	private Double amount;
	
	@Column(name = "dh_cad", updatable = false)
	private LocalDate dateTimeCreation;
	
	@Column(name = "dh_mod")
	private LocalDate dateTimeModification;
	
	@PrePersist
	public void prePersist() {
		setDateTimeCreation(LocalDate.now());
		setDateTimeModification(LocalDate.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDateTimeCreation() {
		return dateTimeCreation;
	}

	public void setDateTimeCreation(LocalDate dateTimeCreation) {
		this.dateTimeCreation = dateTimeCreation;
	}

	public LocalDate getDateTimeModification() {
		return dateTimeModification;
	}

	public void setDateTimeModification(LocalDate dateTimeModification) {
		this.dateTimeModification = dateTimeModification;
	}
}
