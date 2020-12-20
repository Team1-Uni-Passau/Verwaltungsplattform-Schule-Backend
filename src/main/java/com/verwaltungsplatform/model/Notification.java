package com.verwaltungsplatform.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "ankuendigung")
public class Notification {
	
	/*
	 * @param id				AnkündigungsID
	 * @param titel				Überschrift der Ankündigung
	 * @param start				Startdatum der Ankündigung
	 * @param end				Enddatum der Ankündigung
	 * @param role				Rollen, für die die Ankündigung angezeigt wird
	 * @param affectedClass		SchoolClass, für die die Ankündigung angezeigt wird
	 * @param content			Inhalt der Ankündigung
	 * 
	 * Entweder die Rollensichtbarkeit ODER die Klassensichtbarkeit ist definiert
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*
	 * Attribut nicht im Datenmodell
	 * private String titel;
	 */
	

	@Column (name = "nutzer_id")
	private int nutzerId;
	 
	
	@Column (name = "startdatum")
	private Date start;
	
	@Column (name = "enddatum")
	private Date end;
	
	@Column (name = "sichtbarkeit_rolle")
	private String role;
	
	
	@Column (name = "klassen_id")
	private String classId;
	

	@Column (name = "inhalt")
	private String content;
	
	public Notification(int nutzerId, Date start, Date end, String role, String content) {
		this.nutzerId = nutzerId;
		this.start = start;
		this.end = end;
		this.role = role;
		this.content = content;
	}
	
	public Notification(int nutzerId, String classId, String content, Date start, Date end) {
		this.nutzerId = nutzerId;
		this.start = start;
		this.end = end;
		this.classId = classId;
		this.content = content;
	}
	
	public void setRolevisibility(String newRoles) {
		// Ändert die Rollen, für die die Ankündigung angezeigt wird
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setClassvisibility(SchoolClass newClass) {
		// Ändert die SchoolClass, für die die Ankündigung angezeigt wird
	}
	
	public void delete() {
		// Löscht die Ankündigung
	}

}
