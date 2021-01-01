package com.verwaltungsplatform.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.repositories.FamilyRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.dto.FamilyDto;
import com.verwaltungsplatform.model.Family;



@Service
public class FamilyServiceImpl implements FamilyService {

	
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private FamilyRepository familyRepository;

	/*@param userId of parent
	 * @return FamilyDto with userId of parent and student, familyId and classId of student
	 */
	public FamilyDto getFamilyDto(int elternId) {
		FamilyDto familyDto = new FamilyDto(elternId);
		
		int familienId = familyRepository.findByUserId(elternId);
		familyDto.setFamilyId(familienId);
		
		Family family = familyRepository.findByFamilyId(familienId);
		
		familyDto.setStudentId(family.getUserId());
		familyDto.setClassId(schoolClassRepository.getOne(family.getUserId()).getName());
		return familyDto;
	}
	
	
}
