package ma.dxc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.dxc.model.AppUser;
import ma.dxc.service.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
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
