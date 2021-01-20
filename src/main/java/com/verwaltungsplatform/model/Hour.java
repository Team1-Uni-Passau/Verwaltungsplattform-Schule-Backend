package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "stunde")
public class Hour {
	
	

	@Id
	@Column (name="idstunde")
	private int idHour;
	
	@Column (name = "startzeit")
	private String startTime;
	
	@Column (name = "endzeit")
	private String endTime;

	public Hour(int idHour, String startTime, String endTime) {
		super();
		this.idHour = idHour;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Hour() {
		super();
	}

	public int getIdHour() {
		return idHour;
	}

	public void setIdHour(int idHour) {
		this.idHour = idHour;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
