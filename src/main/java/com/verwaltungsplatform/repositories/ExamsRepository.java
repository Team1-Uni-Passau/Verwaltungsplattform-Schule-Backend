package com.verwaltungsplatform.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Exam;

@Repository
public interface ExamsRepository extends JpaRepository<Exam, Integer> {

	@Query("SELECT appointment FROM Exam e WHERE  e.id = :pruefungId")
	int getAppointment(@Param("pruefungId") int pruefungId);
	
	@Query("FROM Exam e WHERE e.teacherId = :teacherId")
	List<Exam> getAllExam(@Param("teacherId") int teacherId);
	
	Exam getOneById(int examId);
	
	@Modifying
	@Query("UPDATE Exam e SET e.teacherId = :newTeacherId WHERE e.id= :examId")
			void updateTeacher(@Param("examId") int examId, @Param("newTeacherId") int newTeacherId);

	@Modifying
	@Query("UPDATE Exam e SET e.classId = :newClassId WHERE e.id= :examId")
			void updateClassId(@Param("examId") int examId, @Param("newClassId") String newClassId);
	
	@Modifying
	@Query("UPDATE Exam e SET e.appointment = :newAppointment WHERE e.id= :examId")
			void updateAppointment(@Param("examId") int examId, @Param("newAppointment") int newAppointment);
	
	@Modifying
	@Query("UPDATE Exam e SET e.date = :newDate WHERE e.id= :examId")
			void updateDate(@Param("examId") int examId, @Param("newDate") Date newDate);

	@Modifying
	@Query("UPDATE Exam e SET e.type = :newType WHERE e.id= :examId")
			void updateType(@Param("examId") int examId, @Param("newType") String newType);

}