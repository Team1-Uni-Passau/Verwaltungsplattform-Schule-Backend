//package com.verwaltungsplatform.repositories;
//
//import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
//
//import com.verwaltungsplatform.model.IllnessNotification;
//
//public interface IllnessNotificationRepository extends JpaRepository<IllnessNotification, Integer> {
//
//	IllnessNotification findById(int id);
//	@Query("SELECT bestaetigung FROM krankmeldung k WHERE k.id= :userid AND datum = :current_date()")
//	boolean getConfirmationToday(int userId);
//	
//}
