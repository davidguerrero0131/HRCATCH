package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.SystemBaja;


public interface ISystemBajaService {
	
	public List<SystemBaja> listBajas();
	public SystemBaja findOne(Long id);
	public void save(SystemBaja baja);
	public void delete(Long id);


}
