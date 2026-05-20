package com.rays.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO {

	@Id
	@GenericGenerator(name = "ncsPk", strategy = "native")
	@GeneratedValue(generator = "ncsPk")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
