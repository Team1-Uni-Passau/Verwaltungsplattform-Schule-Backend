package com.verwaltungsplatform.model;

import java.sql.Date;

public class SecretariatEmployee extends User {
	
//	public SecretariatEmployee() {
//		/*
//		 *  Konstruktor für SchoolClass SecretariatEmployee. 
//		 *  Wird durch den betreffenden Registrierungscode aufgerufen.
//		 */
//	}
	
	public void setStudent(User user) {
		// Ändert die Klasse eines Users in Student 
	}
	
	public void setParent(User user) {
		 // Ändert die Klasse eines Users in Parent
	}
	
	public void createNotification(String titel, Date start, Date end, String visibility, String content) {
		// Erstellt eine für bestimmte Gruppen sichtbare Ankündigung
	}
	
	public void editNotification(Notification notification) {
		/*
		 * Ändert eine Ankündigung
		 * Kann in mehrere Methoden für die einzelnen Attribute unterteilt werden
		 */
	}
	
	public void deleteNotification(Notification notification) {
		// Löscht eine Ankündigung
	}
	
	public void createWeeklySchedule(SchoolClass schoolclass, Teacher teacher) {
		/*
		 * Erstellt einen WeeklySchedule für eine SchoolClass ODER einen Lehrer
		 * Kann auch in zwei Methoden aufgeteilt werden
		 */
	}
	
	public void editWeeklySchedule(WeeklySchedule weeklySchedule) {
		/*
		 * Ändert einen WeeklySchedule
		 * Kann in mehrere Methoden für die einzelnen Attribute aufgeteilt werden
		 */
	}
	
	public void deleteWeeklySchedule(WeeklySchedule weeklySchedule) {
		// Löscht einen WeeklySchedule
	}
	
	public void createSchoolClass(String name) {
		// Erstellt eine neue SchoolClass ohne Mitglieder
	}
	
	public void editSchoolClass(SchoolClass schoolClass) {
		/*
		 * Ändert einen WeeklySchedule
		 * Kann in mehrere Methoden für die einzelnen Attribute aufgeteilt werden
		 */
	}
	
//	public IllnessNotification getIllnessNotification(IllnessNotification illnessNotification) {
//		// Zeigt eine Krankmeldung an
//	}
	
	public void confirmIllnessNotification(IllnessNotification illnessNotification) {
		// Bestätigt eine Krankmeldung
	}
	
	public void notifyParents(Student student) {
		// Benachrichtigt die Eltern eines Schülers
	}
	
}
