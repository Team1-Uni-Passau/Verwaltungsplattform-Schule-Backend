package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query("SELECT id FROM Appointment a WHERE a.weekday = :day AND a.hour = :hour")	
		int findAppointment(@Param("day") String day, @Param("hour") int hour);

}
