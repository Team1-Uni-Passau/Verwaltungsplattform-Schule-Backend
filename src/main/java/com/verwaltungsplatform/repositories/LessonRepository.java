
package com.verwaltungsplatform.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

	@Query("SELECT subject FROM Lesson l WHERE  l.classId = :classId AND l.appointment = :appointment")
	String getSubject(@Param("classId") String classId, @Param("appointment") int appointment);
		
	@Modifying
	@Query("UPDATE Lesson l SET l.subject = :newSubject WHERE l.id = :lessonId")
			void updateSubject(@Param("lessonId") int lessonId, @Param("newSubject") String newSubject);

	@Modifying
	@Query("UPDATE Lesson l SET l.classId = :newClassId WHERE l.id = :lessonId")
			void updateClassId(@Param("lessonId") int lessonId, @Param("newClassId") Date newClassId);

	@Modifying
	@Query("UPDATE Lesson l SET l.appointment = :newAppointment WHERE l.id = :lessonId")
			void updateAppointment(@Param("lessonId") int lessonId, @Param("newAppointment") String newAppointment);

	@Modifying
	@Query("UPDATE Lesson l SET l.teacherId = :newTeacher WHERE l.id = :lessonId")
			void updateTeacher(@Param("lessonId") int lessonId, @Param("newTeacher") String newTeacher);

	@Query("DELETE FROM Lesson l WHERE l.id = :lessonId")
			void deleteLesson(@Param("lessonId") int lessonId);
	
	@Query("FROM Lesson l WHERE l.teacherId = :teacherId")
	List<Lesson> findWeeklyScheduleByIdLehrender(@Param("teacherId") int teacherId);

	@Query("FROM Lesson l WHERE l.classId = :classId")
	List<Lesson> findWeeklyScheduleByIdKlasse(@Param("classId") String classId);
}
