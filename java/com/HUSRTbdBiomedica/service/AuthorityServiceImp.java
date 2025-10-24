package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IAuthorityDao;
import com.HUSRTbdBiomedica.app.entity.Authority;

@Service
public class AuthorityServiceImp implements IAuthorityService{


	@Autowired
	private IAuthorityDao AuthorityDao;
	
	@Override
	public List<Authority> ListRols() {
		
		return (List<Authority>)AuthorityDao.findAll();
	}

	@Override
	public Authority findOne(Long id) {
		
		return AuthorityDao.findById(id).orElse(null);
	}

	@Override
	public void save(Authority authority) {
		
		AuthorityDao.save(authority);
		
	}

	@Override
	public void delete(Long id) {
		AuthorityDao.delete(findOne(id));
		
	}

	@Override
	public int countAll() {
		return AuthorityDao.countAll();
	}

	@Override
	public List<Authority> listrauthorities() {
		// TODO Auto-generated method stub
		return AuthorityDao.listRoles();
	}

	
}
