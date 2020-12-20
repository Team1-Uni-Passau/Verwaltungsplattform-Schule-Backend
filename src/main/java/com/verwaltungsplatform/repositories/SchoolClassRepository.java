package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.SchoolClass;


import java.util.List;


@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
	List<SchoolClass> findByIdKlasse(String idKlasse);
	
    @Query("FROM klasse k WHERE  k.idklassen = :idKlasse")
	List<Integer> getStudentIds(String idKlasse);  
     
	SchoolClass getOne(int lernender_id);
	
	String findBySchuelerId(int schueler_id);

	
}