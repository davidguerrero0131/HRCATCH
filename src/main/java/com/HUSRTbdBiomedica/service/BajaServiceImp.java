package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IBajaDao;
import com.HUSRTbdBiomedica.app.entity.Baja;

@Service
public class BajaServiceImp implements IBajaService {

	@Autowired
	private IBajaDao BajaDao;
	
	@Override
	public List<Baja> listBajas() {
		return (List<Baja>)BajaDao.findAll();
	}

	@Override
	public Optional<Baja> ListEquipobyId(Long id) {
		return null;
	}

	@Override
	public Baja findOne(Long id) {
		return BajaDao.findById(id).orElse(null);
	}

	@Override
	public void save(Baja baja) {
		BajaDao.save(baja);
		
	}

	@Override
	public void delete(Long id) {
		BajaDao.delete(findOne(id));
		
	}

}
