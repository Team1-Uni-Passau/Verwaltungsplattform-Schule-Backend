//package com.verwaltungsplatform.service;
//
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import org.springframework.stereotype.Service;
//
//import com.verwaltungsplatform.model.Administrator;
//import com.verwaltungsplatform.repositories.AdministratorRepository;
//
//
//
//@Service 
//public class AdministratorServiceImpl {
//	private static final Logger LOGGER = Logger.getLogger(AdministratorServiceImpl.class.getName());
//	private AdministratorRepository adminRepository;
//
//	public AdministratorServiceImpl(AdministratorRepository adminRepository) { 
//		this.adminRepository = adminRepository;
//	}
//
//	public List<Administrator> findAll() {
//		return adminRepository.findAll();
//	}
//
//	public long count() {
//		return adminRepository.count();
//	}
//
//	public void delete(Administrator admin) {
//		adminRepository.delete(admin);
//	}
//
//	public void save(Administrator admin) {
//		if (admin == null) { 
//			LOGGER.log(Level.SEVERE,
//					"Admin is null. You can not save this object in the database");
//			return;
//		}
//		adminRepository.save(admin);
//	}
//	
//	
//}
//
//
