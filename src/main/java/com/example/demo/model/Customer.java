package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity(name = "cliente")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String name;
	
	@Column(name = "documento")
	private String document;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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