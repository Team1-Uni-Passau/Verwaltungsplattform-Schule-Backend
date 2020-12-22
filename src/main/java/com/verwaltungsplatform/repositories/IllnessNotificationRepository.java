package com.verwaltungsplatform.repositories;

import java.util.List;


import java.sql.Date;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.verwaltungsplatform.model.IllnessNotification;

@Repository
public interface IllnessNotificationRepository extends JpaRepository<IllnessNotification, Integer> {

	List<IllnessNotification> findById(int id);
		
	
	@Query("FROM IllnessNotification i WHERE i.date = CURRENT_DATE() AND i.affectedUser= :affectedUser")
	IllnessNotification findByAffectedUser(@Param("affectedUser") int affectedUser);
	
	List<IllnessNotification> findByDate(Date date);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE IllnessNotification i SET i.confirmation = true WHERE i.affectedUser = :affectedUser")
//			int updateAffectedUser(@Param("affectedUser") int affectedUser);
//	

}
