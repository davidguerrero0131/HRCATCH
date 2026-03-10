package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.PlanActividadMetrologica;

public interface IPlanActividaMEtrologicaService {

	public List<PlanActividadMetrologica> getPlanActividadMetrologica();
	
	public List<PlanActividadMetrologica> getActividadMetrologicaTipoEquipo(Long iDTipoEquipo);
	
	public List<PlanActividadMetrologica> getActividadMetrologicaServicio(Long iDServicio);
	
	public List<PlanActividadMetrologica> getActividadMetrologicaEquipo(Long idEquipo);
	
	public List<PlanActividadMetrologica> getActividadMetrologicaByMes(int mes, int año);
	
	public List<PlanActividadMetrologica> getActividadMetrologicaByMesAño(int mes, int año);
}
