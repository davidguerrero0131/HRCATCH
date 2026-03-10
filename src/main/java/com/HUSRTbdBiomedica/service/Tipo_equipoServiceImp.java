package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.ITipo_equipoDao;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;
import com.HUSRTbdBiomedica.app.entity.Tipo_equipo;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
@ComponentScan
public class Tipo_equipoServiceImp implements ITipo_equipoService{
	@Autowired
	private ITipo_equipoDao Tipo_equipoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tipo_equipo> ListTipo_equipo() {
		return (List<Tipo_equipo>)Tipo_equipoDao.listTiposBiomedica();
	}

	@Override
	@Transactional
	public Optional<Tipo_equipo> ListTipo_equipobyId(Long id) {
		return null;
	}
	@Override
	@Transactional
	public List<Equipo> findEquiposbyTipoEquipo(Long id){
		return (List<Equipo>)Tipo_equipoDao.findEquiposbyTipoEquipo(id);
	}
	@Override
	@Transactional
	public List<Tipo_equipo> findTipo_equipobyPeriodicidad(int id){
		return (List<Tipo_equipo>)Tipo_equipoDao.listTipo_equiposByPeriodicidad(id);
	}
	@Override
	@Transactional
	public int countEbyTipoEquipobyP(int perioid, Long id) {
		return Tipo_equipoDao.countEspecificPbyTipoEquipo(perioid, id);
	}
	
	
	@Override
	@Transactional
	public Tipo_equipo findOne(Long id) {
		return Tipo_equipoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Tipo_equipo tipo_equipo) {
		Tipo_equipoDao.save(tipo_equipo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Tipo_equipoDao.delete(findOne(id));
		
	}

	@Override
	public Tipo_equipo findbyName(String name) {
		return Tipo_equipoDao.findTipo_equipobyname(name);
	}

	@Override
	public List<Tipo_equipo> findTiposconEquipo() {
		return Tipo_equipoDao.listTiposconEquipos();
	}

	@Override
	public List<Tipo_equipo> findTiposCalval() {
		return Tipo_equipoDao.listTipoequipoCalVal();
	}

	@Override
	public List<Tipo_equipo> findTiposnoCalval() {
		return Tipo_equipoDao.listTipoequiponoCalVal();
	}

	@Override
	public List<Tipo_equipo> findbyBiomedic() {
		return Tipo_equipoDao.listTiposBiomedica();
	}

	@Override
	public List<Tipo_equipo> findbySystem() {
		return Tipo_equipoDao.listTiposSistemas();
	}

	@Override
	public List<SystemEquipo> findSystemEquiposbyTipoEquipo(Long id) {
		// TODO Auto-generated method stub
		return Tipo_equipoDao.findSystemEquiposbyTipoEquipo(id);
	}

	@Override
	public List<Tipo_equipo> findTiposconSystemEquipos() {
		// TODO Auto-generated method stub
		return Tipo_equipoDao.listTiposconSystemEquipos();
	}

	@Override
	public List<Tipo_equipo> getTipoEquipoConComodatos() {
		// TODO Auto-generated method stub
		return Tipo_equipoDao.getTipoEquipoConComodatos();
	}

	@Override
	public List<Tipo_equipo> getAllTiposEquipoSys() {
		return Tipo_equipoDao.getAllTiposSys();
		
	}
	

}
