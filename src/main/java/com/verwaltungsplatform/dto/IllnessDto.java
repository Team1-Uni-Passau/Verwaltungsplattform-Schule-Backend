package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IllnessDto {

	
	
	@NotNull
	@NotEmpty
	private int affectedUserId;
	
	private int krankmeldungId;
	

	private String firstName;
   
	private String lastName;
   
    private String rolle;

   
	// Default constructor
	public IllnessDto() {
		
	}
	
	
	public IllnessDto(int affectedUserId) {
		super();
		this.affectedUserId = affectedUserId;
		this.firstName = "";
		this.lastName = "";
		this.rolle = "";
	}
	

	public int getKrankmeldungId() {
		return krankmeldungId;
	}


	public void setKrankmeldungId(int krankmeldungId) {
		this.krankmeldungId = krankmeldungId;
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
	public int getAffectedUserId() {
		return affectedUserId;
	}
	public void setAffectedUserId(int affectedUserId) {
		this.affectedUserId = affectedUserId;
	}


	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	
}
