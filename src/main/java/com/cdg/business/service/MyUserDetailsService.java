package com.cdg.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdg.business.model.AppRole;
import com.cdg.business.model.AppUser;
import com.cdg.business.model.Permission;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	
	
	/**
	 * Cette fontion charge l'utilisateur en se basant sur son username, puis elle utilise la fonction getAuthorities()
	 * afin d'obtenir les privilèges de cet utilisateur, puis elle retourne un objet de type User qui contient le username,
	 * le mot de passe ainsi que les privilèges.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = accountService.findUserByUsername(username);
		if(user==null) throw new UsernameNotFoundException(username+"Not Found !");
		System.out.println(getAuthorities(user.getRoles()));
		return new User(user.getUsername(),user.getPassword(),getAuthorities(user.getRoles()));
	}
	
	/**
	 * Cette fonction prend comme paramètres les roles d'un utilisateur et retourne les privilèges de chaque
	 * role sous forme d'une collection de type GrantedAuthority.
	 * @param roles
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(
		      Collection<AppRole> roles) {
		  
		        return getGrantedAuthorities(getPrivileges(roles));
	}
	/**
	 * Cette fonction retourne la liste des privilèges des roles sous forme d'une liste des chaines de caractères.
	 * @param roles
	 * @return
	 */
	private List<String> getPrivileges(Collection<AppRole> roles) {
		  
        List<String> privileges = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (AppRole role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (Permission item : collection) {
            privileges.add(item.getPermissionName());
        }
        return privileges;
    }
	
	/**
	 * Cette fonction crée une nouvelle liste de type GrantedAuthority et ajoute tout les privilèges de l'utilisateur
	 * actuelle.
	 * @param privileges
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
