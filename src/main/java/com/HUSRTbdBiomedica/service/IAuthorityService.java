package com.HUSRTbdBiomedica.service;
import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Authority;


public interface IAuthorityService {
	
	public List<Authority>ListRols();
	
	public Authority findOne(Long id);
	public void save(Authority authority);
	public void delete(Long id);
	public int countAll();
	
	public List<Authority> listrauthorities();
	

}
