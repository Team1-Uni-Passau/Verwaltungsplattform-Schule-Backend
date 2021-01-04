package com.verwaltungsplatform.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ExamDto {

	private int examId;
	
	private int userId;
	
	private Date date;
	
	private String day;
	
	private int hour;
	
	private String subject;
	
	private String classId;
	
	private String type;
	   

   
	// Default constructor
	public ExamDto() {
		
	}

	public ExamDto(int examId, int userId, Date date, String day, int hour, String subject, String classId, String type) {
		super();
		this.examId = examId;
		this.userId = userId;
		this.date = date;
		this.day = day;
		this.hour = hour;
		this.subject = subject;
		this.classId = classId;
		this.type = type;
	}

	
	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	}