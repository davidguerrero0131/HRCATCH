package com.HUSRTbdBiomedica.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.HUSRTbdBiomedica.app.entity.Authority;
import com.HUSRTbdBiomedica.app.entity.Usuario;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Usuario usuario;
	
	
	
	
	public CustomUserDetails(Usuario usuario) {

		this.usuario = usuario;
		
	}
	/*public static CustomUserDetails build(Usuario usuario) {
		List<GrantedAuthority> authorities = 
				usuario.getAuthority().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
		System.out.println(authorities);
		return new CustomUserDetails(usuario,authorities);
	}*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> roles = usuario.getAuthority();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for(Authority role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
        
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getCedula();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFullName() {
		return usuario.getNombre() + " " + usuario.getApellido();
	}
	

}
