package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verwaltungsplatform.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Integer> {

}
