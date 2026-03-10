package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Llamado;

public interface ILlamadoService {

	public List<Llamado>ListLlamado();
	public Optional<Llamado>ListLlamadobyId(Long id);
	public Llamado findOne(Long id);
	public void save(Llamado llamado);
	public void delete(Long id);
	
	//public void update();
	
	public int countbyDate(Date fecha1, Date fecha2);
	
	public List<Llamado> listllamadobyrange(Date fecha1, Date fecha2);
	public List<String> listllamadobytec(Date fecha1,Date fecha2);
	public List<String> listbytec(Date fecha1,Date fecha2);
	public List<String> listbyservices(Date fecha1,Date fecha2);	
	public List<String> listonlyservices(Date fecha1,Date fecha2);	
	public List<String> listPrioridad(Date fecha1,Date fecha2);	
	public List<String> listattPhone(Date fecha1,Date fecha2);	
	public List<String> listsnPhone(Date fecha1,Date fecha2);
	public int contarCall(Date fecha1,Date fecha2);
	public float countSecondTotal(Date fecha1,Date fecha2);
	public float countSecondRta(Date fecha1,Date fecha2);
	public int contarCallatt(Date fecha1,Date fecha2);
	public int contarCallsn(Date fecha1,Date fecha2);

	public List<String> listopEquipos(Date fecha1, Date fecha2);
}
