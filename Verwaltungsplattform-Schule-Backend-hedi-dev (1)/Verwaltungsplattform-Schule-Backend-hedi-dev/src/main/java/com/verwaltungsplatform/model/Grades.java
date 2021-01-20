  
package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "noten")
public class Grades {
	
	/*
	 * @param id		ID der Note
	 * @param exam		Die für die Note verantwortliche Prüfung
	 * @param grade		Die Note (1-6)
	 * @param student	Der geprüfte Schüler
	 */
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="idnoten")
	private int id;
	
	@Column (name = "pruefung_id")
	private int exam;

	@Column (name = "note")
	private int grade;
	
	@Column (name = "lernender_id")
	private int userId;
	
	

	public Grades() {
		super();
	}
	

	public Grades(int exam, int grade, int userId) {
		super();
		this.exam = exam;
		this.grade = grade;
		this.userId = userId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExam() {
		return exam;
	}

	public void setExam(int exam) {
		this.exam = exam;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	

}