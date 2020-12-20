package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "termin")
public class Appointment {
	
	/*
	 * @param id		ID des wöchentlichen Termins
	 * @param weekday	Wochentag des Termins
	 * @param hour		Stunde des Termins
	 */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "wochentag")
	private String weekday;
	
	@Column (name = "stunde")
	private int hour;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}


}
