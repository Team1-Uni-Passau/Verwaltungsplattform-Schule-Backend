package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Grades;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {
	
	
	@Query("FROM Grades g WHERE  g.userId = :userId")
	List<Grades> getStudentGrades(@Param("userId") int userId);

}
