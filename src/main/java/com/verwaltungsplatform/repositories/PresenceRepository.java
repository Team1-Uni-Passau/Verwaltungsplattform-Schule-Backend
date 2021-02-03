package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.IllnessNotification;
import com.verwaltungsplatform.model.Presence;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

	@Query("FROM Presence p WHERE p.userId = userId AND p.date <= CURRENT_DATE() AND p.date >= (CURRENT_DATE-7) ORDER BY p.date")
	List<Presence> findByUserId(@Param("userId") int userId);
	
	@Query("FROM Presence i WHERE i.date = CURRENT_DATE() AND i.userId= :userId")
	Presence findByUserIdToday(@Param("userId") int userId);
	
	@Query("FROM Presence i WHERE i.date = CURRENT_DATE() AND i.userId= :userId")
	Presence existsByUserId(@Param("userId") int userId);

}
