package com.verwaltungsplatform.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.SchoolClass;


import java.util.List;


@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
	List<SchoolClass> findByName(String name);
	
    @Query("SELECT student FROM SchoolClass s WHERE s.name = :name")
	List<Integer> getStudentIds(@Param("name") String name);  
   
    @Query("SELECT student FROM SchoolClass s WHERE s.name IN :name")
	List<Integer> getStudentIdsByClassIds(@Param("name") List<String> name);
    
	SchoolClass getOne(int lernender_id);

	@Query("SELECT name FROM SchoolClass s WHERE s.student = :student")
	String getName(@Param("student") int student);
	
	@Modifying
	@Query("UPDATE SchoolClass s SET s.name = :newName WHERE s.student= :student")
			void updateName(@Param("student") int student, @Param("newName") String newName);

	
}