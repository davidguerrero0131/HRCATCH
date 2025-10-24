package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.SystemRepuestos;


public interface ISystemRepuestosService{

	
	public List<SystemRepuestos> listrepuestossys();
	public SystemRepuestos findOne(Long id);
	public void save(SystemRepuestos repuesto);
	public void delete(Long id);
	

	public int countRepuestossys();
	public List<SystemRepuestos> listrepuestosbyequipo(Long id);
	public List<SystemRepuestos> listrespuestosavail();
}
