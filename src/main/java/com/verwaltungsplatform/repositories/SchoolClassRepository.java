//package com.verwaltungsplatform.repositories;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.verwaltungsplatform.model.SchoolClass;
//
//
//@Repository
//public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
//	
//	@Query
//	List<SchoolClass> findByIdKlasse(String idKlasse);
//	
//    @Query("FROM klasse k WHERE  k.idklassen = :idKlasse")
//	List<Integer> getStudentIds(@Param("lernender_id") String idKlasse);  
//     
//    
//    @Query
//	SchoolClass getOne(int lernender_id);
//	
//    @Query
//	String findBySchuelerId(int schueler_id);
//	
//}