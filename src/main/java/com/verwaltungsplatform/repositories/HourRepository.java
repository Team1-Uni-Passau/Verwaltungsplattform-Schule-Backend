package com.verwaltungsplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Hour;


@Repository
public interface HourRepository extends JpaRepository<Hour, Integer> {
	
	@Query("FROM Hour h WHERE h.idHour = :idHour")	
		Hour findHour(@Param("idHour") int idHour);
	
	@Query("SELECT idHour FROM hour h WHERE h.startTime= :startTime")
	int findIdHour(@Param("startTime") String startTime);

}
