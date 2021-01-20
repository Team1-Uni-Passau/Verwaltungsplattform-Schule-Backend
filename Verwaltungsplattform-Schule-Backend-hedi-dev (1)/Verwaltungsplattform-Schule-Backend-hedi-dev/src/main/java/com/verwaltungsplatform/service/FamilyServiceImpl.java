package com.verwaltungsplatform.service;


import java.util.List;

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
		
		Family parentfamily = familyRepository.findByUserId(elternId);
		familyDto.setFamilyId(parentfamily.getFamilyId());
		
		List<Integer> schoolClass = schoolClassRepository.getAllStudent();
		Family studentfamily = familyRepository.findByFamilyId(parentfamily.getFamilyId(), schoolClass);
		
		familyDto.setStudentId(studentfamily.getUserId());
		familyDto.setClassId(schoolClassRepository.getOne(studentfamily.getUserId()).getName());
		return familyDto;
	}
	
	
}
