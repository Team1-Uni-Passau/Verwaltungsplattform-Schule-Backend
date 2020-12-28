package com.verwaltungsplatform.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Family;



@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

	int findByUserId (int nutzer_id);
	
	@Query("FROM Family f WHERE f.familyId = :familyId AND f.userId IN (:klasse)")
	Family findByFamilyId (@Param("familyId") int familyId);
	
}


