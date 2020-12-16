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
//@Table (name = "anwesenheit")
//public class Presence {
//	
//	/*
//	 * @param id					ID der Abwesenheit
//	 * @param affectedUser			Der abwesende Nutzer
//	 * @param date					Datum der Abwesenheit
//	 * @param illnessNotification	Sagt aus, ob eine Krankmeldung vorliegt
//	 * @param presence				Sagt aus, ob der Nutzer an- oder abwesend ist
//	 * @param lesson				Unterrichtsstunde der Abwesenheit
//	 */
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	/*
//	 * Die Lösung für Fremdschlüssel ist noch unklar
//	 * @Column (name = "nutzer_id")
//	 * private User affectedUser;
//	 */
//	
//	@Column (name = "datum")
//	private Date date;
//	
//	@Column (name = "krankmeldung")
//	private boolean illnessNotification;
//	
//	@Column (name = "anwesenheit")
//	private boolean presence;
//	
//	@Column (name = "unterrichtsstunde")
//	private Lesson lesson;
//
//}
