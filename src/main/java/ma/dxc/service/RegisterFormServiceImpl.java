package ma.dxc.service;

import org.springframework.beans.factory.annotation.Autowired;

import ma.dxc.model.AppUser;
import ma.dxc.model.RegisterForm;

public class RegisterFormServiceImpl implements RegisterFormService{
	
	@Autowired
	private AccountService accountService;

	@Override
	public AppUser register(RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("Vous devez confirmer votre mot de passe.");
		AppUser user = accountService.findUserByUsername(userForm.getUsername());
		if(user!=null)
			throw new RuntimeException("Cet utilisateur existe déjà.");
		AppUser appUser = new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		return accountService.saveUser(appUser);
	}
	
	

}
