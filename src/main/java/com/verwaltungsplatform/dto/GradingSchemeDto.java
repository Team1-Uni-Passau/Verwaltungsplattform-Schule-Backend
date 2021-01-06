package com.verwaltungsplatform.dto;

public class GradingSchemeDto {

	private String classId;
	
	private int teacherId;
   
	private double writtenEvaluation;
	
	private double oralEvaluation;
	
	private int writtenNumber;
	
	private int oralNumber;


   
	// Default constructor
	public GradingSchemeDto() {
		
	}
	
	public GradingSchemeDto(String classId, int teacherId, double writtenEvaluation, double oralEvaluation, int writtenNumber, int oralNumber) {
		super();
		this.classId = classId;
		this.teacherId = teacherId;
		this.writtenEvaluation = writtenEvaluation;
		this.oralEvaluation = oralEvaluation;
		this.writtenNumber = writtenNumber;
		this.oralNumber = oralNumber;
	}


	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}


	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	public double getWrittenEvaluation() {
		return writtenEvaluation;
	}


	public void setWrittenEvaluation(double writtenEvaluation) {
		this.writtenEvaluation = writtenEvaluation;
	}


	public double getOralEvaluation() {
		return oralEvaluation;
	}


	public void setOralEvaluation(double oralEvaluation) {
		this.oralEvaluation = oralEvaluation;
	}


	public int getWrittenNumber() {
		return writtenNumber;
	}


	public void setWrittenNumber(int writtenNumber) {
		this.writtenNumber = writtenNumber;
	}


	public int getOralNumber() {
		return oralNumber;
	}


	public void setOralNumber(int oralNumber) {
		this.oralNumber = oralNumber;
	}



	}