package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.PatientDTO;
import com.rays.dto.UserDTO;

public class PatientRegistration extends BaseForm{
	

	@NotEmpty(message = "Patient Name is required")
	private String patientName;

	@NotEmpty(message = "Patient Id is required")
	private String patientId;
	
	@NotNull(message = "Date of birth is required")
	private Date dob;
	
	@NotEmpty(message = "Address is required")
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


	@Override
	public BaseDTO getDto() {
		PatientDTO dto = (PatientDTO) initDTO(new PatientDTO());
		dto.setPatientName(patientName);
		dto.setPatientId(patientId);
		dto.setDob(dob);
		dto.setAddress(address);
		return dto;
	}

}
