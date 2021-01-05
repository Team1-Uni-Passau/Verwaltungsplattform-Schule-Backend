package com.verwaltungsplatform.dto;

import java.sql.Date;


public class NotificationDto {

	private int idNotification;
	
	private String enddate;
	
	private String startdate;
	
	private String content;
	
	private String rolle;
	
	private String classId;

   
	// Default constructor
	public NotificationDto() {
		
	}


	public NotificationDto(String enddate, String startdate, String content, String rolle, String classId) {
		super();
		this.enddate = enddate;
		this.startdate = startdate;
		this.content = content;
		this.rolle = rolle;
		this.classId = classId;
	}


	public int getIdNotification() {
		return idNotification;
	}


	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}


