package com.verwaltungsplatform.dto;

import java.sql.Date;


public class NotificationDto {

	
	private Date enddate;
	
	private Date startdate;
	
	private String content;
	

   
	// Default constructor
	public NotificationDto() {
		
	}



	public NotificationDto(Date enddate, Date startdate, String content) {
		super();
		this.enddate = enddate;
		this.startdate = startdate;
		this.content = content;
	}



	public Date getEnddate() {
		return enddate;
	}



	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}



	public Date getStartdate() {
		return startdate;
	}



	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}
	

	
	
}
