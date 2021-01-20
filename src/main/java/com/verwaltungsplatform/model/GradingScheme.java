package com.verwaltungsplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "notenschema")
public class GradingScheme {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="idnotenschema")
	private int id;
	
	@Column (name = "klassen_id")
	private String classId;
	
	@Column (name = "lehrender_id")
	private int teacherId;
	
	@Column (name = "wertung_schriftlich")
	private double writtenEvaluation;
	
	@Column (name = "wertung_muendlich")
	private double oralEvaluation;
	
	@Column (name = "anzahl_schriftlich")
	private int writtenNumber;
	
	@Column (name = "anzahl_muendlich")
	private int oralNumber;


	public GradingScheme(String classId, int teacherId, double writtenEvaluation, double oralEvaluation,
			int writtenNumber, int oralNumber) {
		super();
		this.classId = classId;
		this.teacherId = teacherId;
		this.writtenEvaluation = writtenEvaluation;
		this.oralEvaluation = oralEvaluation;
		this.writtenNumber = writtenNumber;
		this.oralNumber = oralNumber;
	}

	public GradingScheme() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
