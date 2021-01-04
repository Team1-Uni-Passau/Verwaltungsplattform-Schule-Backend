package com.verwaltungsplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IllnessConfirmationDto {

	
	@NotNull
	@NotEmpty
	private int affectedUserId;
	
	private String firstName;
   
	private String lastName;
   
    private boolean confirmation;

   
	// Default constructor
	public IllnessConfirmationDto() {
		
	}
	
	
	public IllnessConfirmationDto(int affectedUserId) {
		super();
		this.affectedUserId = affectedUserId;
		this.firstName = "";
		this.lastName = "";
		this.confirmation = false;
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
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isConfirmation() {
		return confirmation;
	}
	

	
}
