//package com.verwaltungsplatform.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.verwaltungsplatform.model.Student;
//
//@Repository
//public interface StudentRepository extends JpaRepository<Student, Integer> {
//	Student findByStudentId(int studentId);
//    
//    @Query("FROM nutzer n WHERE  n.nutzer-id = :studentId")
//	Student getUser(int nutzerid);   
//}
//
