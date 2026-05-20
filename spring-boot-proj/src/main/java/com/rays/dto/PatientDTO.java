package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;

public class PatientDTO extends BaseDTO{
	

	@Column(name = "PATIENT_NAME", length = 100)
	private String patientName;

	@Column(name = "PATIENT_ID", length = 50)
	private String patientId;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "ADDRESS", length = 255)
	private String address;
	
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
