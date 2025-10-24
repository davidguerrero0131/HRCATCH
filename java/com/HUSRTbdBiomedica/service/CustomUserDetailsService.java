package com.HUSRTbdBiomedica.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IUsuarioDao;
import com.HUSRTbdBiomedica.app.entity.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao UsuarioDao;

	@Override
	public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
		Usuario usuario = UsuarioDao.findByCedula(cedula);
	
		return new CustomUserDetails(usuario);
	    
	}
	
	
	

	
	
	

}
