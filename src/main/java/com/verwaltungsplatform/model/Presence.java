package com.verwaltungsplatform.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table (name = "anwesenheit")
public class Presence {
	
	/*
	 * @param id					ID der Abwesenheit
	 * @param affectedUser			Der abwesende Nutzer
	 * @param date					Datum der Abwesenheit
	 * @param illnessNotification	Sagt aus, ob eine Krankmeldung vorliegt
	 * @param presence				Sagt aus, ob der Nutzer an- oder abwesend ist
	 * @param lesson				Unterrichtsstunde der Abwesenheit
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="idanwesenheit")
	private int id;
	
	@Column (name="nutzer_id")
	private int userId;
	
	/*
	 * Die Lösung für Fremdschlüssel ist noch unklar
	 * @Column (name = "nutzer_id")
	 * private User affectedUser;
	 */
	
	@Column (name = "datum")
	private Date date;
	
	@Column (name = "krankmeldung")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean confirmation;
	
	@Column (name = "anwesenheit")
	private boolean presence;
	
	@Column (name = "unterrichtsstunde")
	private  int lesson;

	public Presence(int userId) {
		super();
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lesson) {
		this.lesson = lesson;
	}

	
}
