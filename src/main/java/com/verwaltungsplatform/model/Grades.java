//package com.verwaltungsplatform.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table (name = "noten")
//public class Grades {
//	
//	/*
//	 * @param id		ID der Note
//	 * @param exam		Die für die Note verantwortliche Prüfung
//	 * @param grade		Die Note (1-6)
//	 * @param student	Der geprüfte Schüler
//	 */
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "pruefung_id")
//	 * private Exam exam;
//	 */
//	
//	@Column (name = "note")
//	private int grade;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "lernender_id")
//	 * private Student student;
//	 */
//	
//	
//
//}
