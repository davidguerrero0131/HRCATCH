package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IHoja_Vida_BiomedicaDao;
import com.HUSRTbdBiomedica.app.entity.Hoja_vida_Biomedica;

@Service
public class hoja_Vida_BiomedicaServiceImp implements IHoja_vida_BiomedicaService {

	@Autowired
	private IHoja_Vida_BiomedicaDao hoja_Vida_BiomedicaDao;
	
	@Override
	public List<Hoja_vida_Biomedica> getAllHVBiomedica() {
		// TODO Auto-generated method stub
		return (List<Hoja_vida_Biomedica>) hoja_Vida_BiomedicaDao.findAll();
	}

	@Override
	public void AddHoja_Vida_Biomedica(Hoja_vida_Biomedica hoja_vida_Biomedica) {

		hoja_Vida_BiomedicaDao.save(hoja_vida_Biomedica);
		
	}

}
