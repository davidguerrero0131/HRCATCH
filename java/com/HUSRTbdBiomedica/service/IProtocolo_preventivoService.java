package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;


public interface IProtocolo_preventivoService {
	
	public List<Protocolo_preventivo>ListReportes();
	public Optional<Protocolo_preventivo>ListReportesbyId(Long id);
	public Protocolo_preventivo findOne(Long id);
	public void save(Protocolo_preventivo protocolo_preventivo);
	public void delete(Long id);
	
	public List<Protocolo_preventivo> protocologeneral(Long id);
	public List<Protocolo_preventivo> protocoloexcepcion(Long id);
	public List<Protocolo_preventivo> protocolobymtto(Long id);

}
