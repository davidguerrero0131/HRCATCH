package com.HUSRTbdBiomedica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IPlanActividadMetrologicaDao;
import com.HUSRTbdBiomedica.app.entity.PlanActividadMetrologica;

@Service
public class PlanActividadMetrologicaServiceImp implements IPlanActividaMEtrologicaService {

	@Autowired
	private IPlanActividadMetrologicaDao actividadMetrologicaDao;
	
	@Override
	public List<PlanActividadMetrologica> getPlanActividadMetrologica() {
		return actividadMetrologicaDao.findAll();
	}

	@Override
	public List<PlanActividadMetrologica> getActividadMetrologicaTipoEquipo(Long iDTipoEquipo) {
		// TODO Auto-generated method stub
		return actividadMetrologicaDao.getActividadMetrologicaTipoEquipo(iDTipoEquipo);
	}

	@Override
	public List<PlanActividadMetrologica> getActividadMetrologicaServicio(Long iDServicio) {
		// TODO Auto-generated method stub
		return actividadMetrologicaDao.getActividadMetrologicaServicio(iDServicio);
	}

	@Override
	public List<PlanActividadMetrologica> getActividadMetrologicaEquipo(Long idEquipo) {
		// TODO Auto-generated method stub
		return actividadMetrologicaDao.getActividadMetrologicaEquipo(idEquipo);
	}

	@Override
	public List<PlanActividadMetrologica> getActividadMetrologicaByMes(int mes, int año) {
		// TODO Auto-generated method stub
		return actividadMetrologicaDao.getActividadMetrologicaByMes(mes, año);
	}

	@Override
	public List<PlanActividadMetrologica> getActividadMetrologicaByMesAño(int mes, int año) {
		
		return actividadMetrologicaDao.getActividadMetrologicaByMesAño(mes, año);
	}

}
