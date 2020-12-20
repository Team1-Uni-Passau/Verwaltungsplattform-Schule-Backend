package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verwaltungsplatform.model.Grades;
import com.verwaltungsplatform.model.GradingScheme;

public interface GradingSchemeRepository extends JpaRepository<GradingScheme, Integer> {

	@Query("FROM notenschema n WHERE  n.klassen_id = : classId")
	List<GradingScheme> getGradingSchemes(String classId);

}

