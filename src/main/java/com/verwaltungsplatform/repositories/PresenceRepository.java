package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.Presence;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

}
