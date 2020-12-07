package com.verwaltungsplatform.service;

import com.verwaltungsplatform.dto.UserRegistrationDto;
import com.verwaltungsplatform.model.User;

public interface UserService {
	User save(UserRegistrationDto registrationDto);
}
