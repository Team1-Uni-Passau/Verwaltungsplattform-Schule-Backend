package com.verwaltungsplatform.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

@Query("FROM Notification n WHERE n.role = :role AND n.end >= CURRENT_DATE() AND n.start <= CURRENT_DATE()")	
	List<Notification> findByRole(@Param("role") String role);

@Query("FROM Notification n WHERE n.classId = :klassenId AND n.end >= CURRENT_DATE() AND n.start <= CURRENT_DATE()")	
	List<Notification> findByClassId(@Param("klassenId") String klassenId);

@Query("FROM Notification n WHERE n.end >= CURRENT_DATE() AND n.start <= CURRENT_DATE() AND n.role IS NULL AND n.classId IS NULL ")
	List<Notification> findByRoleIsNullAndClassIdIsNull();

@Modifying
@Query("UPDATE Notification n SET n.start = :newStart WHERE n.idankuendigung= :notId")
		void updateStartDate(@Param("notId") int notId, @Param("newStart") Date newStart);

@Modifying
@Query("UPDATE Notification n SET n.end = :newEnd WHERE n.idankuendigung= :notId")
		void updateEndDate(@Param("notId") int notId, @Param("newEnd") Date newEnd);


@Modifying
@Query("UPDATE Notification n SET n.content = :newText WHERE n.idankuendigung= :notId")
		void updateText(@Param("notId") int notId, @Param("newText") String newText);

@Modifying
@Query("UPDATE Notification n SET n.role = :newRole WHERE n.idankuendigung= :notId")
		void updateRole(@Param("notId") int notId, @Param("newRole") String newRole);

@Modifying
@Query("UPDATE Notification n SET n.classId = :newClass WHERE n.idankuendigung= :notId")
		void updateClassId(@Param("notId") int notId, @Param("newClass") String newClass);

@Query("DELETE FROM Notification n WHERE n.idankuendigung = :notId")
		void deleteNotification(@Param("notId") int notId);

List<Notification> findByClassIdIsNull();
}

