package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "familie")
public class Family {
	
	/*
	 * @param familyId	ID der Familie
	 * @param userId	ID des Schülers
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int familyId;
	
	
	@Column (name = "nutzer_id")
	private int userId;


	public int getFamilyId() {
		return familyId;
	}


	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

}
