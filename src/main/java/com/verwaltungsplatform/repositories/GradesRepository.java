package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.verwaltungsplatform.model.Grades;


public interface GradesRepository extends JpaRepository<Grades, Integer> {
	
	
	@Query("FROM noten n WHERE  n.nutzer_id = : userId")
	List<Grades> getStudentGrades(int userId);

}
