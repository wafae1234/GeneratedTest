package ma.dxc.service;

import ma.dxc.model.AppUser;
import ma.dxc.model.RegisterForm;

public interface RegisterFormService {
	public AppUser register( RegisterForm userForm);
}
