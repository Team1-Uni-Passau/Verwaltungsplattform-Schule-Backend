package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="registrierungscode")
public class Role_RegisterCode_Mapper {

	@Id
	@Column (name = "rolle", insertable= false, updatable= false)
	private String userRole;
	
	@Column (name = "registrierungscode")
	private int registrationCode;
	
	
	
	
	//default constructor
	public Role_RegisterCode_Mapper() {

	}

	

	public Role_RegisterCode_Mapper(String role, int registerCode) {
		super();
		this.userRole = role;
		this.registrationCode = registerCode;
	}
	
	
	
	
	
	
	public Role_RegisterCode_Mapper(String userRole) {
		super();
		this.userRole = userRole;
	}



	public String getRole() {
		return userRole;
	}

	public void setRole(String role) {
		this.userRole = role;
	}

	public int getRegisterCode() {
		return registrationCode;
	}

	public void setRegisterCode(int registerCode) {
		this.registrationCode = registerCode;
	}

}
