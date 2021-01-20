package com.verwaltungsplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.verwaltungsplatform.model.Role_RegisterCode_Mapper;
import com.verwaltungsplatform.model.User;

public interface RoleRegisterCodeMapperRepository extends JpaRepository<Role_RegisterCode_Mapper, String> {
    
	@Query("FROM Role_RegisterCode_Mapper u WHERE  u.userRole = :role")
	Role_RegisterCode_Mapper getRegistercodeWithRole(@Param("role") String role);

}
