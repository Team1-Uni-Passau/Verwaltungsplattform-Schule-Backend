package com.verwaltungsplatform.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "pruefung")
public class Exam {
	
	/*
	 * @param id				PrüfungsID
	 * @param termin			Prüfungsdatum
	 * @param appointment		Wochentag und Stunde, in der die Prüfung abgehalten wird
	 * @param subject			Fach, in dem die Prüfung abgehalten wird
	 * @param tester			prüfender Lehrer
	 * @param testedClass		geprüfte SchoolClass
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name="lehrender_id")
	private int teacherId;
	
	@Column (name="klassen_id")
	private int classId;


	@Column (name = "termin")
	private int appointment;
	
	@Column (name = "datum")
	private Date date;
	
	@Column (name = "art")
	private String type;
	
//	public Exam(Date date, int hour, String subject, Teacher tester, SchoolClass testedClass, String type) {
//		// Erstellt eine neue Prüfung in einem Fach für eine bestimmte SchoolClass zu einem bestimmten Termin
//	}
//
	
	
	public Exam(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getAppointment() {
		return appointment;
	}

	public void setAppointment(int appointment) {
		this.appointment = appointment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
