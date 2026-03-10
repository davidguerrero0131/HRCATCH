package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Licencia;


public interface ILicenciaService {

	public List<Licencia>ListLicencia();
	public Licencia findOne(Long id);
	public void save(Licencia licencia);
	public void delete(Long id);
	public int countAllLicencia();
	
	public String lastIdLicencia();
}
