package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.ILlamadoDao;
import com.HUSRTbdBiomedica.app.entity.Llamado;

@Service
public class LlamadoServiceImp implements ILlamadoService{

	@Autowired
	private ILlamadoDao LlamadoDao;
	
	@Override
	public List<Llamado> ListLlamado() {
		
		return (List<Llamado>)LlamadoDao.findAll();
	}

	@Override
	public Optional<Llamado> ListLlamadobyId(Long id) {
		return Optional.empty();
	}

	@Override
	public Llamado findOne(Long id) {
		return LlamadoDao.findById(id).orElse(null);
	}

	@Override
	public void save(Llamado llamado) {
		LlamadoDao.save(llamado);
	}

	@Override
	public void delete(Long id) {
		LlamadoDao.delete(findOne(id));
	}

	@Override
	public int countbyDate(Date fecha1, Date fecha2) {
		return LlamadoDao.countbyDate(fecha1, fecha2);
	}

	@Override
	public List<Llamado> listllamadobyrange(Date fecha1, Date fecha2) {
		return LlamadoDao.listbyrange(fecha1, fecha2);
	}

	@Override
	public List<String> listllamadobytec(Date fecha1, Date fecha2) {
		return LlamadoDao.lisybyatend(fecha1,fecha2);
	}

	@Override
	public List<String> listbytec(Date fecha1, Date fecha2) {
		return LlamadoDao.listtec(fecha1, fecha2);
	}

	@Override
	public List<String> listbyservices(Date fecha1, Date fecha2) {
		return LlamadoDao.listservices(fecha1, fecha2);
	}

	@Override
	public List<String> listonlyservices(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.listonlyservice(fecha1, fecha2);
	}

	@Override
	public List<String> listPrioridad(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.listbyprioridad(fecha1, fecha2);
	}

	@Override
	public List<String> listattPhone(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.listatencionphone(fecha1, fecha2);
	}

	@Override
	public List<String> listsnPhone(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.listconfirmatencionphone(fecha1, fecha2);
	}

	@Override
	public int contarCall(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.countCalls(fecha1, fecha2);
	}

	@Override
	public float countSecondTotal(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.countSecondsTtotal(fecha1, fecha2);
	}

	@Override
	 public float countSecondRta(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.countSecondsTrta(fecha1, fecha2);
	}
	
	@Override
	public int contarCallatt(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.countCallAt(fecha1, fecha2);
	}

	@Override
	public int contarCallsn(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.countCallSn(fecha1, fecha2);
	}

	@Override
	public List<String> listopEquipos(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return LlamadoDao.listtopequiposcall(fecha1, fecha2);
	}
	
	

}
