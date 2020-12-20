package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "unterrichtsstunde")
public class Lesson {
	
	/*
	 * @param id			ID der Unterrichtsstunde
	 * @param appointment	ID des Termins (Wochentag und Stunde)
	 * @param subject		Fach, das unterrichtet wird
	 * @param lehrer 		Lehrer, der das Fach unterrichtet
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="termin")
	private int appointment;
	

	@Column (name="klassen_id")
	private String classId;
	
	@Column (name="lehrender_id")
	private int teacherId;

	
	@Column (name = "fach")
	private String subject;

	
	public Lesson() {
		super();
	}
	
	
	public Lesson(int appointment, String classId, int teacherId, String subject) {
		super();
		this.appointment = appointment;
		this.classId = classId;
		this.teacherId = teacherId;
		this.subject = subject;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getAppointment() {
		return appointment;
	}

	public void setAppointment(int appointment) {
		this.appointment = appointment;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}



}
	
