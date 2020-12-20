package com.verwaltungsplatform.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.verwaltungsplatform.model.Notification;


public interface NotificationRepository extends JpaRepository<Notification, Integer> {

@Query("FROM ankuendigung a WHERE a.sichtbarkeitRolle =: sichtbarkeitRolle OR"
		+ "a.sichtbarkeitRolle =: 'Alle' AND"
		+ "a.enddatum >= : current_date() >=: a.startdatum ORDER BY a.startdatum DESC")	
	List<Notification> findByRole(String sichtbarkeitRolle);

@Query("FROM ankuendigung a WHERE a.klassenId =: klassenId AND"
		+ "a.enddatum >= : current_date() >=: a.startdatum ORDER BY a.startdatum DESC")	
	List<Notification> findByKlassenId(String klassenId);

@Modifying
@Query("UPDATE ankuendigung a SET a.startdatum = newdate WHERE a.idankuendigung= :notId")
		void updateStartDate(int notId, Date newdate);

@Modifying
@Query("UPDATE ankuendigung a SET a.enddatum = newdate WHERE a.idankuendigung= :notId")
		void updateEndDate(int notId, Date newdate);


@Modifying
@Query("UPDATE ankuendigung a SET a.text = newText WHERE a.idankuendigung= :notId")
		void updateText(int notId, String newText);

@Modifying
@Query("UPDATE ankuendigung a SET a.sichtbarkeitRolle = newRole WHERE a.idankuendigung= :notId")
		void updateRolle(int notId, String newRole);

@Modifying
@Query("UPDATE ankuendigung a SET a.klassen_id = newClass WHERE a.idankuendigung= :notId")
		void updateKlasse(int notId, String newClass);

@Query("DELETE FROM ankuendigung a WHERE a.idankuendigung= :notId")
		void deleteNotification(int notId);
}


