package com.verwaltungsplatform.repositories;

import java.util.List;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.IllnessNotification;

@Repository
public interface IllnessNotificationRepository extends JpaRepository<IllnessNotification, Integer> {

	IllnessNotification findById(int id);
	
//	@Query("FROM IllnessNotification i WHERE i.date = :current_date() AND i.id= :userId")
//	IllnessNotification findByIdToday(@Param("userId") int userId);
//	
//	@Query("SELECT confirmation FROM IllnessNotification i WHERE i.id = :userId AND i.date = :current_date()")
//	boolean getConfirmationToday(@Param("userId") int userId);
//	
//	@Query("FROM IllnessNotifiactation i WHERE i.date = :current_date()")
//	List<IllnessNotification> getIllnessNotificationToday();
	
//	@Modifying
//	@Query("UPDATE IllnessNotification i SET i.confirmation = true WHERE i.id = :userId AND i.date = :current_date()")
//			void updateConfirmation(@Param("userId") int userId);
	

}
