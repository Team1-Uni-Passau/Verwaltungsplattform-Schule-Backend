package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verwaltungsplatform.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
