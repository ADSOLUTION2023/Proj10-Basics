package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

public class UserForm extends BaseForm{
	
	@NotEmpty(message = "First Name is Required")
	private String firstName;
	@NotEmpty(message = "Last Name is Required")
	private String lastName;
	@NotEmpty(message = "Login Id is Required")
	private String loginId;
	@NotEmpty(message = "Password is Required")
	private String password;
	@NotEmpty(message = "Roll Id is Required")
	private Long roleId;
	@NotEmpty(message = "Role Name is Required")
	private String roleName;
	private String imageId;
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
