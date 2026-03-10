package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Responsable;


public interface IResponsableService {

	public List<Responsable>ListResponsables();
	public Responsable findOne(Long id);
	public void save(Responsable responsable);
	public void delete(Long id);
	public List<Responsable>listExternos();
	public List<Responsable>listResponsableComodato();
}
