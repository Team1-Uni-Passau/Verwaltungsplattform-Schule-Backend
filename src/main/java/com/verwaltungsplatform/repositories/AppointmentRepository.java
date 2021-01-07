package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Appointment;
import com.verwaltungsplatform.model.SchoolClass;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query("SELECT id FROM Appointment a WHERE a.weekday = :day AND a.hour = :hour")	
		int findId(@Param("day") String day, @Param("hour") int hour);
	
	Appointment findById(int id);
	
	@Query("SELECT id FROM Appointment a WHERE a.weekday = :day")
	List<Integer> findByDay(@Param("day") String day);

}
