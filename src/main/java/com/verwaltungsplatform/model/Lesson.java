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
//@Table (name = "unterrichtsstunde")
//public class Lesson {
//	
//	/*
//	 * @param id			ID der Unterrichtsstunde
//	 * @param appointment	ID des Termins (Wochentag und Stunde)
//	 * @param subject		Fach, das unterrichtet wird
//	 * @param lehrer 		Lehrer, der das Fach unterrichtet
//	 */
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//
//	
//	@Column (name = "fach")
//	private String subject;
//	
//	@Column (name="klassen_id")
//	private String classid;
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "termin")
//	 * private Appointment appointment
//	 */
//	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getSubject() {
//		return subject;
//	}
//
//	public void setSubject(String subject) {
//		this.subject = subject;
//	}
//
//	public String getClassid() {
//		return classid;
//	}
//
//	public void setClassid(String classid) {
//		this.classid = classid;
//	}
//
//
//	
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "lehrender_id")
//	 * private Teacher teacher;
//	 */
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "klassen_id")
//	 * private SchoolClass class;
//	 */
//	
////	public Lesson(int appointment, String subject, Teacher teacher, SchoolClass class) {
////		// Erstellt eine neue Unterrichtsstunde
////	}
//
//}
