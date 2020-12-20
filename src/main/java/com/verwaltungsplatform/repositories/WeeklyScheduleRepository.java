package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.verwaltungsplatform.model.Lesson;
import com.verwaltungsplatform.model.WeeklySchedule;

public interface WeeklyScheduleRepository extends JpaRepository<WeeklySchedule, Integer> {

	@Query("FROM unterrichtsstunde u WHERE u.lehrender_id = :lehrenderId")
	List<Lesson> findByIdLehrender(int lehrender_id);

	@Query("FROM unterrichtsstunde u WHERE u.klassen_id =: klassen_id")
	List<Lesson> findByIdKlasse(String klassen_id);
}
