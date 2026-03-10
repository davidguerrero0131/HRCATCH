package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;


public interface ISystemHoja_vidaService {

	public List<SystemHoja_vida> listmttosys();
	public SystemHoja_vida findOne(Long id);
	public void save(SystemHoja_vida hv);
	public void delete(Long id);
	
	public int countHv();
	public SystemHoja_vida findHv(Long id);
	public void updateIPHv(long idEquipo, String iP);
}
