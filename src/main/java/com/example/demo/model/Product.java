package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity(name = "produto")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	private String description;
	
	@Column(name = "preco")
	private Double price;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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