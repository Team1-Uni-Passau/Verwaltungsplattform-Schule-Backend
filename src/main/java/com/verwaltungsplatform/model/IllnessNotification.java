package com.verwaltungsplatform.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	private int id;
	
	/*
	 * Die Lösung für Fremdschlüssel ist noch unklar
	 * @Column (name = "nutzer_id")
	 * private User affectedUser;
	 */
	
	@Column (name = "datum")
	private Date date;
	
	@Column (name = "bestaetigung")
	private boolean confirmation;
	
	

	public IllnessNotification(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
		confirmation = false;
	}

	public IllnessNotification() {
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isConfirmation() {
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
