package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "klasse")
public class SchoolClass {
	
	/*
	 * @param name			Name der SchoolClass, z.B. 1A
	 * @param member		Liste der Schüler, die der SchoolClass zugeordnet sind
	 * @param teacher		Liste der Lehrer, die die SchoolClass unterrichten
	 */
	
	@Column (name = "idklassen")
	private String name;
	
	
	@Id
	@Column (name = "lernender_id")
	private int student;
	 
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudent() {
		return student;
	}

	public void setStudent(int student) {
		this.student = student;
	}

//	private Student[] member;
//	
//	private Teacher[] teacher;
	
//	public SchoolClass(String classname) {
//		// Erstellt eine neue, noch leere SchoolClass
//	}
	
//	public void addMitglied(Student student) {
//		// Fügt der SchoolClass einen neuen Schüler hinzu
//	}
//	
//	public void removeMitglied(Student student) {
//		// Entfernt einen Schüler aus der SchoolClass
//	}
	
	public void delete() {
		// Löscht die SchoolClass
	}
	
//	public void addTeacher(Teacher teacher) {
//		// Fügt der SchoolClass einen Lehrer hinzu
//	}
//	
}

