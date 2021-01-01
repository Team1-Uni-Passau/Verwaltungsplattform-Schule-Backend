package com.verwaltungsplatform.repositories;



import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Family;



@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

	@Query("SELECT familyId FROM Family f WHERE f.userId = :userId")
	int findByUserId (@Param("userId") int userId);
	
	@Query("FROM Family f WHERE f.familyId = :familyId AND f.userId IN (:SchoolClass)")
	Family findByFamilyId (@Param("familyId") int familyId);
	
	@Query("SELECT familyId FROM Family f WHERE f.userId IN (:studentIds)")
	List<Integer> findFamilyIds (@Param("studentIds") List<Integer> studentIds);
	
	@Query("SELECT userId FROM Family f WHERE f.familyId IN (:familyIds) AND f.userId NOT IN (:SchoolClass)")
	List<Integer> getUserIdByFamilyId (@Param("familyIds") List<Integer> familyIds);
	
}


