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
//@Entity
//@Table (name = "ankuendigung")
//public class Notification {
//	
//	/*
//	 * @param id				AnkündigungsID
//	 * @param titel				Überschrift der Ankündigung
//	 * @param start				Startdatum der Ankündigung
//	 * @param end				Enddatum der Ankündigung
//	 * @param role				Rollen, für die die Ankündigung angezeigt wird
//	 * @param affectedClass		SchoolClass, für die die Ankündigung angezeigt wird
//	 * @param content			Inhalt der Ankündigung
//	 * 
//	 * Entweder die Rollensichtbarkeit ODER die Klassensichtbarkeit ist definiert
//	 */
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	/*
//	 * Attribut nicht im Datenmodell
//	 * private String titel;
//	 */
//	
//	/*
//	 * Attribut im Datenmodell. Was sagt es aus?
//	 * @Column (name = "nutzer_id")
//	 * privat int nutzerId
//	 */
//	
//	@Column (name = "startdatum")
//	private Date start;
//	
//	@Column (name = "enddatum")
//	private Date end;
//	
//	@Column (name = "sichtbarkeit_rolle")
//	private String role;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "klassen_id")
//	 * private SchoolClass affectedClass;
//	 */
//
//	@Column (name = "inhalt")
//	private String content;
//	
////	public Notification(String titel, Date start, Date end, String role, SchoolClass affectedClass, String content) {
////		// Erstellt eine neue Ankündigung
////	}
//	
//	public void setTitel(String newTitel) {
//		// Ändert den Titel der Ankündigung
//	}
//	
//	public void setStartdate(Date startdate) {
//		// Ändert das Startdatum der Ankündigung
//	}
//
//	public void setEnddate(Date enddate) {
//		// Ändert das Enddatum der Ankündigung
//	}
//	
//	public void setRolevisibility(String newRoles) {
//		// Ändert die Rollen, für die die Ankündigung angezeigt wird
//	}
//	
//	public void setClassvisibility(SchoolClass newClass) {
//		// Ändert die SchoolClass, für die die Ankündigung angezeigt wird
//	}
//	
//	public void delete() {
//		// Löscht die Ankündigung
//	}
//
//}
