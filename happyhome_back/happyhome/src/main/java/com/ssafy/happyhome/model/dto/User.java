package com.ssafy.happyhome.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
	private String id;
	private String password;
	private String name;
	private String address;  
	private String phone;  
	private String email;
	private String salt;  


//	public User() {	}
//	
//	public User(String id, String pass, String name, String address, String phone) {
//		super();
//		this.id = id;
//		this.password = pass;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//	}
//	
//	public User(String id, String pass, String name, String address, String phone, String salt) {
//		super();
//		this.id = id;
//		this.password = pass;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//		this.salt = salt;
//	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
