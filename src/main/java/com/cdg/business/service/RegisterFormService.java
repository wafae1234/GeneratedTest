package com.cdg.business.service;

import com.cdg.business.model.AppUser;
import com.cdg.business.model.RegisterForm;

public interface RegisterFormService {
	public AppUser register( RegisterForm userForm);
}
