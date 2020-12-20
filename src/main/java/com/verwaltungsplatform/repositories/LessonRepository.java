package com.verwaltungsplatform.repositories;

import java.sql.Date;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verwaltungsplatform.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

	@Query("SELECT fach FROM unterrichtsstunde u WHERE  u.idklassen = :idKlasse AND termin =: terminId")
	String getSubject(String idKlasse, int terminId);
	
	@Query("SELECT art FROM unterrichtsstunde u WHERE  u.idklassen = :idKlasse AND termin =: terminId")
	String getType(String idKlasse, int terminId);
	
	@Modifying
	@Query("UPDATE unterrichtsstunde u SET u.fach = newSubject WHERE u.idunterrichtsstunde= :lessonId")
			void updateSubject(int lessonId, String newSubject);

	@Modifying
	@Query("UPDATE unterrichtsstunde u SET u.klassen_id = newClassId WHERE u.idunterrichtsstunde= :lessonId")
			void updateClassId(int lessonId, Date newClassId);

	@Modifying
	@Query("UPDATE unterrichtsstunde u SET u.termin = newAppointment WHERE u.idunterrichtsstunde= :lessonId")
			void updateAppointment(int lessonId, String newAppointment);

	@Modifying
	@Query("UPDATE unterrichtsstunde u SET u.lehrender_id = newTeacher WHERE u.idunterrichtsstunde= :lessonId")
			void updateTeacher(int lessonId, String newTeacher);

	@Query("DELETE FROM unterrichtsstunde u WHERE u.idunterrichtsstunde= :lessonId")
	void deleteLesson(int lessonId);
}
