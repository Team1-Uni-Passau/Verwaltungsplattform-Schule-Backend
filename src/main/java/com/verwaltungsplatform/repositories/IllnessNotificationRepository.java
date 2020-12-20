package com.verwaltungsplatform.repositories;

import java.util.List;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.verwaltungsplatform.model.IllnessNotification;

public interface IllnessNotificationRepository extends JpaRepository<IllnessNotification, Integer> {

	IllnessNotification findById(int id);
	
	@Query("FROM krankmeldung k WHERE datum = :current_date() AND k.id= :userId")
	IllnessNotification findByIdToday(int userId);
	
	@Query("SELECT bestaetigung FROM krankmeldung k WHERE k.id= :userId AND datum = :current_date()")
	boolean getConfirmationToday(int userId);
	
	@Query("FROM krankmeldung k WHERE datum = :current_date()")
	List<IllnessNotification> getIllnessNotificationToday();
	
	@Modifying
	@Query("UPDATE krankmeldung k SET k.bestaetigung = true WHERE k.id= :userId AND datum = :current_date()")
			void updateConfirmation(int userId);
	

}
