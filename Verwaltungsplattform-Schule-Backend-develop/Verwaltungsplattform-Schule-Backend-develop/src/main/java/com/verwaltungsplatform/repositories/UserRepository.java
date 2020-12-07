package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
