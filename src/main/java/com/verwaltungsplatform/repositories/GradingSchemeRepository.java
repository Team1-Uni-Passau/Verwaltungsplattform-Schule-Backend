package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Grades;
import com.verwaltungsplatform.model.GradingScheme;

@Repository
public interface GradingSchemeRepository extends JpaRepository<GradingScheme, Integer> {

	@Query("FROM GradingScheme g WHERE g.classId = :classId")
	List<GradingScheme> getGradingSchemes(@Param("classId") String classId);
	
	@Query("FROM GradingScheme g WHERE g.classId = :classId AND g.teacherId = :teacherId")
	GradingScheme getClassGradingScheme(@Param("classId") String classId, @Param("teacherId") int teacherId);

}

