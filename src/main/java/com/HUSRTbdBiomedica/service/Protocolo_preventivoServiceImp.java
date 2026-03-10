package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IProtocolo_preventivoDao;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;


@Service
public class Protocolo_preventivoServiceImp implements IProtocolo_preventivoService {

	@Autowired
	private IProtocolo_preventivoDao Protocolo_preventivoDao;
	
	@Override
	public List<Protocolo_preventivo> ListReportes() {
		return (List<Protocolo_preventivo>)Protocolo_preventivoDao.findAll();
	}

	@Override
	public Optional<Protocolo_preventivo> ListReportesbyId(Long id) {
		return null;
	}

	@Override
	public Protocolo_preventivo findOne(Long id) {
		return Protocolo_preventivoDao.findById(id).orElse(null);
	}

	@Override
	public void save(Protocolo_preventivo protocolo_preventivo) {
		Protocolo_preventivoDao.save(protocolo_preventivo);
		
	}

	@Override
	public void delete(Long id) {
		Protocolo_preventivoDao.delete(findOne(id));
		
	}

	@Override
	public List<Protocolo_preventivo> protocologeneral(Long id) {
		// TODO Auto-generated method stub
		return Protocolo_preventivoDao.encontrarprotocolosgeneral(id);
	}

	@Override
	public List<Protocolo_preventivo> protocoloexcepcion(Long id) {
		// TODO Auto-generated method stub
		return Protocolo_preventivoDao.encontrarprotocolosexcepciones(id);
	}

	@Override
	public List<Protocolo_preventivo> protocolobymtto(Long id) {
		// TODO Auto-generated method stub
		return Protocolo_preventivoDao.mostrarprotocoloasignadopormtto(id);
	}

}
