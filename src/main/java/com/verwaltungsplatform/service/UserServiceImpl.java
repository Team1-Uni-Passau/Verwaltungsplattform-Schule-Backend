package com.verwaltungsplatform.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.verwaltungsplatform.dto.RegisterStudentDto;
import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.exceptions.UserAlreadyExistException;
import com.verwaltungsplatform.model.Family;
import com.verwaltungsplatform.model.Role_RegisterCode_Mapper;
import com.verwaltungsplatform.model.SchoolClass;
import com.verwaltungsplatform.model.User;
import com.verwaltungsplatform.repositories.FamilyRepository;
import com.verwaltungsplatform.repositories.RoleRegisterCodeMapperRepository;
import com.verwaltungsplatform.repositories.SchoolClassRepository;
import com.verwaltungsplatform.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRegisterCodeMapperRepository roleCodeMapperRepository;
	
	@Autowired
	private SchoolClassRepository schoolClassRepository;

	@Autowired
	private FamilyRepository familyRepository;
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

 
	// Method to persist a new registered user in the database
	@Override
	public ResponseEntity<String> save(UserRegistrationDto registrationDto) throws UserAlreadyExistException {
				
        //Let's check if user already registered
        if(checkIfUserExist(registrationDto.getEmail())){
            throw new UserAlreadyExistException("A user is already saved in the database with the following e-mail address: "+registrationDto.getEmail());
        }
        
        
        Role_RegisterCode_Mapper userRole = new Role_RegisterCode_Mapper(registrationDto.getRoleCodeMapping().getRole(), registrationDto.getRoleCodeMapping().getRegisterCode());
        
        //checks if user is in student list and if registration code matches
        if(schoolClassRepository.existsById(registrationDto.getUserId())) {
        	if(this.checkIfRegisterCodeMatchesRole(userRole.getRole(), userRole.getRegisterCode())) {
        		try {
    				// Updates the user
        			int id = registrationDto.getUserId();
                	userRepository.updateEmail(id, registrationDto.getEmail());
                	userRepository.updatePassword(id, registrationDto.getPassword());
    			    return new ResponseEntity<>(
    			    	      "User successfully saved in the database", HttpStatus.OK);
    			} catch (Exception e) {
    				e.printStackTrace(); 
    				return new ResponseEntity<>(
    			    	      "Error registering the user in the database", HttpStatus.BAD_REQUEST);
    			}
    		}
        	else {
    		    return new ResponseEntity<>(
    		    	      "The registration code is incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
    		}
        }
        
        else {
        //Creates a new user entity
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
								registrationDto.getEmail(), registrationDto.getPassword(), userRole);
			
		//If the register code matches the code saved in the registrierungscode table for the role chosen by the user
		if(this.checkIfRegisterCodeMatchesRole(userRole.getRole(), userRole.getRegisterCode())) {
			try {
				//checks if user is parent and adds entry to table family if yes
				if(userRole.getRegisterCode()==201) {
					createFamilyEntry(registrationDto);
				}
				// Persists the user entity in the database
				userRepository.save(user);
			    return new ResponseEntity<>(
			    	      "User successfully saved in the database", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace(); 
				return new ResponseEntity<>(
			    	      "Error registering the user in the database", HttpStatus.BAD_REQUEST);
			}
		} else {
		    return new ResponseEntity<>(
		    	      "The registration code is incorrect", HttpStatus.UNPROCESSABLE_ENTITY
		    	      );
		}
        }
				
	}
	
	
	//Method to verify if a user already exists in the users table
    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

	
	public String getUserRole(String email) {
		User user = userRepository.getUserRole(email);
		return user.getRoleRegisterCodeMapper().getRole();
	}


	@Override
	public boolean checkIfRegisterCodeMatchesRole(String role, int registrationCode) {	
		int registerCodeAssignedToRole = roleCodeMapperRepository.getRegistercodeWithRole(role).getRegisterCode();
		return registerCodeAssignedToRole == registrationCode;
	}

	public void createFamilyEntry(UserRegistrationDto registrationDto) {
		Family family = new Family(registrationDto.getFamilyId(), registrationDto.getUserId());
		familyRepository.save(family);
	}
	
	/* @param  RegisterStudentDto
	 * @return RegisterStudentDto with added userId and familyId
	 * saves a new student registered by secretary without email and password
	 */
	public RegisterStudentDto registerNewStudent(RegisterStudentDto studentDto) {
		Role_RegisterCode_Mapper role_mapper = new Role_RegisterCode_Mapper("Lernender", 200);
		User user = new User(studentDto.getFirstName(), studentDto.getLastName(), role_mapper);
		userRepository.save(user);
		
		SchoolClass schoolClass = new SchoolClass(studentDto.getClassId(), user.getId());
		schoolClassRepository.save(schoolClass);
		
		Family family = new Family(user.getId());
		familyRepository.save(family);
		
		studentDto.setUserId(user.getId());
		studentDto.setFamilyId(family.getFamilyId());
		
		return studentDto;
	}
}
