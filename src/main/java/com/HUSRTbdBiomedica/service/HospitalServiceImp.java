package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.Dao.IHospitalDao;
import com.HUSRTbdBiomedica.app.entity.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class HospitalServiceImp implements IHospital_Service {

	@Autowired
	private IHospitalDao HospitalDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Hospital> ListHospital() {
		// TODO Auto-generated method stub
		return (List<Hospital>)HospitalDao.findAll();
	}

	@Override
	@Transactional
	public Optional<Hospital> ListHospitalbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Hospital findOne(Long id) {
		// TODO Auto-generated method stub
		return HospitalDao.findById(id).orElse(null);
	}

	@Override
	public void save(Hospital hospital) {
		// TODO Auto-generated method stub
		HospitalDao.save(hospital);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		HospitalDao.delete(findOne(id));
	}

}
