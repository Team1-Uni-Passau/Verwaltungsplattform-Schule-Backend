package com.verwaltungsplatform.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.ExamsRepository;
import com.verwaltungsplatform.repositories.GradesRepository;
import com.verwaltungsplatform.repositories.GradingSchemeRepository;
import com.verwaltungsplatform.repositories.LessonRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.dto.GettingGradesDto;
import com.verwaltungsplatform.dto.GivingGradesDto;
import com.verwaltungsplatform.dto.GradingSchemeDto;
import com.verwaltungsplatform.model.Exam;
import com.verwaltungsplatform.model.Grades;
import com.verwaltungsplatform.model.GradingScheme;


@Service
public class GradesServiceImpl implements GradesService {

	
	@Autowired
	private GradesRepository gradesRepository;
	@Autowired
	private GradingSchemeRepository gradingSchemeRepository;
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private ExamsRepository examsRepository;
	@Autowired
	private FamilyServiceImpl familyServiceImpl;

	
	
	//saves new grade for student
		public void saveNewGrade (GivingGradesDto givingGradesDto) {

			Grades grades = new Grades(givingGradesDto.getExamId(), givingGradesDto.getGrade(), givingGradesDto.getAffectedUserId());
		
			gradesRepository.save(grades);
		}
		
	//saves new grading schema for teacher
		public void saveNewGradingScheme (GradingSchemeDto gradingSchemeDto) {

			GradingScheme gradingScheme = new GradingScheme(gradingSchemeDto.getClassId(), gradingSchemeDto.getTeacherId(),
					gradingSchemeDto.getWrittenEvaluation(), gradingSchemeDto.getOralEvaluation(), gradingSchemeDto.getWrittenNumber(), gradingSchemeDto.getOralNumber());
			
		
			gradingSchemeRepository.save(gradingScheme);
		}
		
		//@param userId
		//@return List of all grades with subject, grade and type
		//if userId is parent, students grades returned
		public List<GettingGradesDto> getAllGradesDto(int userId) {
			
			if (schoolClassRepository.existsById(userId)) {}
			else {
				FamilyDto familyDto = new FamilyDto();
				familyDto = familyServiceImpl.getFamilyDto(userId);
				userId = familyDto.getStudentId();
			}
				
			return ((List<Grades>) gradesRepository
					.getStudentGrades(userId))
					.stream()
					.map(this::convertToGradesDto).collect(Collectors.toList());
			
		}
		//method for getAllGradesDto
		private GettingGradesDto convertToGradesDto(Grades grades) {
			GettingGradesDto gradesDto = new GettingGradesDto();
			gradesDto.setAffectedUserId(grades.getUserId());
			String classId = schoolClassRepository.getName(grades.getUserId());
			Exam exam = examsRepository.getOneById(grades.getExam());
			int terminId = examsRepository.getAppointment(exam.getId());
			String subject = lessonRepository.getSubject(classId, terminId);
			gradesDto.setSubject(subject);
			gradesDto.setGrade(grades.getGrade());
			String type = exam.getType();
			gradesDto.setType(type);
			return gradesDto;
		}
 	
	
	//@param userID student or parent
	//@return List of all grading schemas of the class student is in
	
	public List<GradingSchemeDto> getAllGradeSchemesDto(int userId) {
		
		String classId;
		if (schoolClassRepository.existsById(userId)) {
			classId = schoolClassRepository.getName(userId);
		}
		else {
			FamilyDto familyDto = new FamilyDto();
			familyDto = familyServiceImpl.getFamilyDto(userId);
			classId = familyDto.getClassId();
		}
		
		return ((List<GradingScheme>) gradingSchemeRepository
				.getGradingSchemes(classId))
				.stream()
				.map(this::convertToGradingSchemaDto).collect(Collectors.toList());
	}
    
	//method for getAllGradeSchemesDto
	private GradingSchemeDto convertToGradingSchemaDto(GradingScheme gradingScheme) {
		GradingSchemeDto gradingSchemeDto = new GradingSchemeDto();
		gradingSchemeDto.setClassId(gradingScheme.getClassId());
		gradingSchemeDto.setTeacherId(gradingScheme.getTeacherId());
		gradingSchemeDto.setWrittenEvaluation(gradingScheme.getWrittenEvaluation());
		gradingSchemeDto.setOralEvaluation(gradingScheme.getOralEvaluation());
		gradingSchemeDto.setWrittenNumber(gradingScheme.getWrittenNumber());
		gradingSchemeDto.setOralNumber(gradingScheme.getOralNumber());
		return gradingSchemeDto;
	}
}





