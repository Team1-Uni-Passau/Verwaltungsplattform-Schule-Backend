package com.verwaltungsplatform.repositories;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.verwaltungsplatform.model.Family;




public interface FamilyRepository extends JpaRepository<Family, Integer> {

	int findByNutzerId (int nutzer_id);
	
	@Query("FROM familie f WHERE familien_id =: familien_id AND f.nutzer_id IN (:klasse)")
	Family findStudentByFamily (int familien_id);
}


