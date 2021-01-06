package com.verwaltungsplatform.repositories;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verwaltungsplatform.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    
    @Query("FROM User u WHERE  u.email = :email")
	User getUserRole(@Param("email") String email); 
    
    @Query("SELECT email FROM User u WHERE u.id IN (:parentIds)")
    List<String> getEmailByUserId(@Param("parentIds") List<Integer> parentIds);
    
    User findById(int id);
    
    @Query("SELECT firstName FROM User u WHERE u.id= :id")
    String getFirstName(@Param("id") int id);
    
    @Query("SELECT lastName FROM User u WHERE u.id= :id")
    String getLastName(@Param("id") int id);

    @Modifying
    @Query("UPDATE User u SET u.role = :newRole WHERE u.id = :id")
		void updateRole(@Param("id") int id, @Param("newRole") String newRole);
    
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
		void updateEmail(@Param("id") int id, @Param("email") String email);  

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
		void updatePassword(@Param("id") int id, @Param("password") String password);

  	}
