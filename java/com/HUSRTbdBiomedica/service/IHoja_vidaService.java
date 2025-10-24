package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida;

public interface IHoja_vidaService {
	public List<Hoja_vida> ObtainallHV();
	public int countAllHV();
	public Hoja_vida findOne(Long id);
	public Hoja_vida findHVbyEquipo(Long id);
	
	public void save(Hoja_vida hoja_vida);
	public void delete(Long id);
	
	public List<Hoja_vida> findHvbyVcto(Date fecha1, Date fecha2);


}
