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
//@Table (name = "notenschema")
//public class GradingScheme {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "klassen_id")
//	 * private SchoolClass affectedClass;
//	 */
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "lehrender_id")
//	 * private Teacher teacher;
//	 */
//	
//	@Column (name = "wertung_schriftlich")
//	private double writtenEvaluation;
//	
//	@Column (name = "wertung_muendlich")
//	private double oralEvaluation;
//	
//	@Column (name = "anzahl_schriftlich")
//	private int writtenNumber;
//	
//	@Column (name = "anzahl_muendlich")
//	private int oralNumber;
//
//}
