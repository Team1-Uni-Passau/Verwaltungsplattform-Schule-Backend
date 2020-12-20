package com.verwaltungsplatform.model;

import java.sql.Date;

public class Teacher extends User {
	
//	public Teacher() {
//		/*
//		 *  Konstruktor für SchoolClass Teacher. 
//		 *  Wird durch den betreffenden Registrierungscode aufgerufen.
//		 */
//	}
	
//	public Student[] getClassmember(SchoolClass klasse) {
//		// Gibt die Liste der Mitglieder einer SchoolClass aus
//	}
	
	public void setPresence(Student student, boolean presence) {
		// Trägt die Anwesenheit für einen bestimmten Schüler ein
	}
	
	public void setOralgrade(Student student, int grade) {
		// Trägt für einen Schüler eine mündliche Note ein
	}
//	
//	public WeeklySchedule getWeeklySchedule() {
//		// Gibt den WeeklySchedule des Lehrers aus
//	}
//	
	public void createIllnessNotification() {
		// Erstellt eine Krankmeldung für den Lehrer für das heutige Datum
	}
	
	public void createNotification(String titel, Date start, Date end, SchoolClass affectedClass, String content) {
		// Erstellt eine Ankündigung für eine SchoolClass
	}
	
	public void editNotification(Notification notification) {
		/*
		 * Ändert eine Ankündigung
		 * Kann in mehrere Methoden für die einzelnen Attribute aufgeteilt werden
		 */
	}
	
	public void deleteNotification(Notification notification) {
		// Löscht eine Ankündigung
	}
	
	public void createExam(Date date, int hour, String subject, SchoolClass affectedClass) {
		// Erstellt eine Prüfung für eine SchoolClass
	}
	
	public void setExamDate(Exam exam, Date newDate) {
		// Ändert den Termin einer Prüfung
	}
	
	public void deleteExam(Exam exam) {
		// Löscht eine Prüfung
	}
	
	public void setWrittenGrade() {
		// Trägt für einen Schüler eine schriftliche Note ein
	}
	
	public void setGradingScheme() {
		// Erstellt das GradingScheme für ein Fach
	}
		
}
