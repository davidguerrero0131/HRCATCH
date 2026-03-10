package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IMantenimiento_preventivoDao;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;

@Service
public class Mantenimiento_PreventivoServiceImp implements IMantenimiento_preventivoService {

	@Autowired
	private IMantenimiento_preventivoDao Mantenimiento_preventivoDao;
	
	@Override
	public List<Mantenimiento_preventivo> ListMantenimientos() {
		return (List<Mantenimiento_preventivo>)Mantenimiento_preventivoDao.findAll();
	}

	@Override
	public Optional<Mantenimiento_preventivo> Lismttobyid(Long id) {
		return null;
	}

	@Override
	public Mantenimiento_preventivo findOne(Long id) {
		return Mantenimiento_preventivoDao.findById(id).orElse(null);
	}

	@Override
	public void save(Mantenimiento_preventivo mantenimiento_prevenvitvo) {
		Mantenimiento_preventivoDao.save(mantenimiento_prevenvitvo);
		
	}
	

	@Override
	public void delete(Long id) {
		Mantenimiento_preventivoDao.delete(findOne(id));
	}

	@Override
	public List<Mantenimiento_preventivo> findBytecnico(Long id, int mes, int ano) {
		return Mantenimiento_preventivoDao.findbytecnicomonthandyear(id, mes, ano);
	}

	@Override
	public List<Mantenimiento_preventivo> findbyfecha(int mes, int ano) {
		return Mantenimiento_preventivoDao.findMesAnoinitial(mes, ano);
	}

	@Override
	public Mantenimiento_preventivo findbyreport(Long id) {
		return Mantenimiento_preventivoDao.findmttobyReporte(id);
	}

	@Override
	public List<Mantenimiento_preventivo> findbyEquipo(Long id) {
		// TODO Auto-generated method stub
		return Mantenimiento_preventivoDao.findmttobyequipo(id);
	}

}
