package com.verwaltungsplatform.model;

import java.sql.Date;

public class Parent extends User {

	/*
	 * @param kinds	Liste der Kinder eines Elternaccounts
	 */
	private int familyId;
	
//	public Parent() {
//		/*
//		 *  Konstruktor für SchoolClass Parent. 
//		 *  Wird durch den betreffenden Registrierungscode aufgerufen.
//		 */
//	}
	
	public void createIllnessNotification(Student kid, Date date) {
		// Erstellt eine Krankmeldung für ein Kind
	}
	
//	public WeeklySchedule getWochenplan(Student kind) {
//		// Gibt den WeeklySchedule eines Kindes aus
//	}
	
//	public boolean getAnwesenheit(Student kind) {
//		// Gibt Auskunft über die momentane Anwesenheit eines Kindes
//	}
	
//	public int[] getNoten(Student kind) {
//		// Gibt die Noten eines Kindes aus
//	}
//	
//	public GradingScheme getNotenschema(Student kind, String fach) {
//		/*
//		 * Gibt das GradingScheme für ein Fach eines Kindes aus
//		 * Vielleicht ist eine SchoolClass "Fach" sinnvoller
//		 */
//	}
//	
}
