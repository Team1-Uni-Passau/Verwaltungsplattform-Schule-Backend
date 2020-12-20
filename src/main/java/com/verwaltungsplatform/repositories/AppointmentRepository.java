package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.verwaltungsplatform.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query("SELECT idtermin FROM termin t WHERE t.wochentag =: day AND t.stunde= : hour")	
		int findAppointment(String day, int hour);

}
