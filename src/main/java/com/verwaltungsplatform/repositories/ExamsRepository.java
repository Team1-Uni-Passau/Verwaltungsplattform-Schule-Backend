package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verwaltungsplatform.model.Exam;

public interface ExamsRepository extends JpaRepository<Exam, Integer> {

	@Query("SELECT termin FROM pruefung p WHERE  p.id = :pruefungId")
	int getAppointment(int pruefungId);
}
