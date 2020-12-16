//package com.verwaltungsplatform.model;
//
//import java.sql.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//
//@Entity
//@Table (name = "pruefung")
//public class Exam {
//	
//	/*
//	 * @param id				PrüfungsID
//	 * @param termin			Prüfungsdatum
//	 * @param appointment		Wochentag und Stunde, in der die Prüfung abgehalten wird
//	 * @param subject			Fach, in dem die Prüfung abgehalten wird
//	 * @param tester			prüfender Lehrer
//	 * @param testedClass		geprüfte SchoolClass
//	 */
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	@Column (name = "termin")
//	private Appointment appointment;
//	
//	@Column (name = "datum")
//	private Date date;
//	
//	// benötigt wahrscheinlich noch ein Attribut in der Tabelle
//	//private String subject;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "lehrender_id")
//	 * private Teacher tester;
//	 */
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "klassen_id")
//	 * private SchoolClass testedClass;
//	 */
//	
//	@Column (name = "art")
//	private String type;
//	
////	public Exam(Date date, int hour, String subject, Teacher tester, SchoolClass testedClass, String type) {
////		// Erstellt eine neue Prüfung in einem Fach für eine bestimmte SchoolClass zu einem bestimmten Termin
////	}
////
//}
