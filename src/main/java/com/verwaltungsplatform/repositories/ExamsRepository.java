package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Exam;

@Repository
public interface ExamsRepository extends JpaRepository<Exam, Integer> {

	@Query("SELECT appointment FROM Exam e WHERE  e.id = :pruefungId")
	int getAppointment(@Param("pruefungId") int pruefungId);
}
