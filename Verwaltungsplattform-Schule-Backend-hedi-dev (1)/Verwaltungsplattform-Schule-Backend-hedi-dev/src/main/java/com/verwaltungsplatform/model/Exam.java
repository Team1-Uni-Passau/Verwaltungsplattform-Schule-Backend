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
	@Column (name="idpruefung")
	private int id;
	
	@Column (name="lehrender_id")
	private int teacherId;
	
	@Column (name="klassen_id")
	private String classId;


	@Column (name = "termin")
	private int appointment;
	
	@Column (name = "datum")
	private Date date;
	
	@Column (name = "art")
	private String type;
	
	public Exam() {
		super();
	}
	
	public Exam(int teacherId, String classId, int appointment, Date date, String type) {
		super();
		this.teacherId = teacherId;
		this.classId = classId;
		this.appointment = appointment;
		this.date = date;
		this.type = type;
	}

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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
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
