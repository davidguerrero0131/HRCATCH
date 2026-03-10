package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.MantenimientoMSV;

public interface IMantenimientoMSVService {

	
	public List<MantenimientoMSV> getAllMantenimientoMSV();
	
	public void addMantenimientoMSV(MantenimientoMSV mantenimientoMSV);
	
	public MantenimientoMSV getMantenimientoMSVEspec(Long idEquipo, Long idMantenimiento);
}
