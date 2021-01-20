package com.verwaltungsplatform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@Table (name = "krankmeldung")
public class IllnessNotification {
	
	/*
	 * @param id			KrankmeldungsID
	 * @param affectedUser	Nutzer, für den die Krankmeldung gilt. Entweder Teacher oder Student
	 * @param date			Datum, für das die Krankmeldung gilt
	 * @param confirmation	Angabe über die Bestätigung der Krankmeldung durch das Sekretariat
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="idkrankmeldung")
	private int id;

	
	@Column (name = "nutzer_id")
	private int affectedUser;
	


	@Column (name = "datum")
	private Date date;
	
	@Column (name = "bestaetigung")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean confirmation;
	
	

	public IllnessNotification(int affectedUser, Date date) {
		super();
		this.affectedUser = affectedUser;
		this.date = date;
	}

	public IllnessNotification() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAffectedUser() {
		return affectedUser;
	}

	public void setAffectedUser(int affectedUser) {
		this.affectedUser = affectedUser;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}


	

//	
//	public void setconfirmation() {
//		// Bestätigt die Krankmeldung
//	}
//
}
