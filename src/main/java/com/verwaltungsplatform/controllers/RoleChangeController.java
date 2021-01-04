package com.verwaltungsplatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.model.Role_RegisterCode_Mapper;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.repositories.UserRepository;

@Controller
public class RoleChangeController {
	
	@Autowired
	private UserRepository userRepo;
	
	// Das Sekretariat kann einem bestimmten Nutzer die Rollen "Lernender" und "Eltern" zuweisen
	@PutMapping("/sekretariat/changerole")
	@ResponseBody
	public String changeRoleSekretariat(String eMail, String newRole) {
		
		User user = userRepo.findByEmail(eMail);
		String response;
		
		if(user == null) {
			response = "Es konnte kein Nutzer mit dieser E-Mail Adresse gefunden werden.";
			return response;
		}
		else {
			if(user.getRoleRegisterCodeMapper().getRole() == "Admin") {
				response = "Die Rolle des Nutzers konnte nicht geändert werden, da der Nutzer ein Administrator ist.";
				return response;
			}
			else if(user.getRoleRegisterCodeMapper().getRole() == "Sekretariat") {
				response = "Die Rolle des Nutzers konnte nicht geändert werden, da der Nutzer ein Mitglied des Sekretariats ist.";
				return response;
			}
			else if(user.getRoleRegisterCodeMapper().getRole() == "Lehrender") {
				response = "Die Rolle des Nutzers konnte nicht geändert werden, da der Nutzer ein Lehrender ist.";
				return response;
			}
			else {
				Role_RegisterCode_Mapper userRole = new Role_RegisterCode_Mapper(newRole);
				user.setRoleRegisterCodeMapper(userRole);
				userRepo.save(user);
				response = "Die Rolle des Nutzers wurde in " + newRole + " geändert.";
				return response;
			}
		}
	}
	
	// Ein Administrator kann einem bestimmten Nutzer jede Rolle zuweisen
	@PutMapping("/admin/changerole")
	@ResponseBody
	public String changeRoleAdmin(String eMail, String newRole) {
		
		User user = userRepo.findByEmail(eMail);
		String response;
		
		if(user == null) {
			response = "Es konnte kein Nutzer mit dieser E-Mail Adresse gefunden werden.";
			return response;
		}
		else {
			Role_RegisterCode_Mapper userRole = new Role_RegisterCode_Mapper(newRole);
			user.setRoleRegisterCodeMapper(userRole);
			userRepo.save(user);
			response = "Die Rolle des Nutzers wurde in " + newRole + " geändert.";
			return response;
		}
	}
	
	

}
