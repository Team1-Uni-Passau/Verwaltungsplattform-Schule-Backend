package com.verwaltungsplatform.repositories;

import java.util.List;
import java.util.Date;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.IllnessNotification;

@Repository
public interface IllnessNotificationRepository extends JpaRepository<IllnessNotification, Integer> {

	IllnessNotification findById(int id);
	
	
	@Query("FROM IllnessNotification i WHERE i.date = CURRENT_DATE() AND i.affectedUser= :affectedUser")
	IllnessNotification findByAffectedUser(@Param("affectedUser") int affectedUser);
	
	@Query("FROM IllnessNotification i WHERE i.date = CURRENT_DATE()")
	List<IllnessNotification> findAll();
	
	
	@Query("FROM IllnessNotification i WHERE i.confirmation = :confirmation")
	List<IllnessNotification> findByConfirmation(@Param("confirmation") int confirmation);
	

	@Modifying
	@Query("UPDATE IllnessNotification i SET i.confirmation = : true WHERE i.date = CURRENT_DATE() AND i.affectedUser= :affectedUser")
			void updateConfirmation(@Param("affectedUser") int affectedUser);

	
	@Query("DELETE FROM IllnessNotification i WHERE i.id = :illnessNotificationId")
	void deleteIllnessNotification(@Param("illnessNotificationId") int illnessNotificationId);

	@Modifying
	@Query("UPDATE IllnessNotification i SET i.date = :newDate WHERE i.id = :illnessNotId")
			void updateDate(@Param("illnessNotId") int illnessNotId, @Param("newDate") Date newDate);

	}
