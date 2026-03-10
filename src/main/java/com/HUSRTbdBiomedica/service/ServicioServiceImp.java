package com.HUSRTbdBiomedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.HUSRTbdBiomedica.app.Dao.IServicioDao;
import com.HUSRTbdBiomedica.app.entity.Equipo;
import com.HUSRTbdBiomedica.app.entity.Servicio;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;

@Service
@ComponentScan
public class ServicioServiceImp implements IServicioService{

	@Autowired
	private IServicioDao ServicioDao;
	
	@Override
	public List<Servicio> ListServicio() {
		return (List<Servicio>)ServicioDao.getAllServicios();
	}

	@Override
	public Optional<Servicio> ListServiciobyId(Long id) {
		return null;
	}

	@Override
	public Servicio findOne(Long id) {
		return ServicioDao.findById(id).orElse(null);
	}

	@Override
	public void save(Servicio servicio) {
		ServicioDao.save(servicio);
		
	}

	@Override
	public void delete(Long id) {
		ServicioDao.delete(findOne(id));
		
	}

	@Override
	public int contarServicios() {
		return ServicioDao.countAll();
	}

	@Override
	public List<Equipo> findEquiposbyServicio(Long id) {
		return ServicioDao.findEquiposbyServicio(id);
	}

	@Override
	public int countEspecificbyServicio(Long id) {
		return ServicioDao.countEspecificPbyServicio(id);
	}

	@Override
	public List<Equipo> findEquiposbyServiciocuatrimestrales(Long id) {

		return ServicioDao.findEquiposCuatrimestralservicio(id);
	}

	@Override
	public List<Equipo> findEquiposbyServiciotrimestrales(Long id) {
		return ServicioDao.findEquiposTrimestralservicio(id);
	}

	@Override
	public List<Equipo> findEquiposbyServiciosemestrales(Long id) {
		return ServicioDao.findEquiposSemestralservicio(id);
	}

	@Override
	public List<Equipo> findEquiposbyServicioanuales(Long id) {
		return ServicioDao.findEquiposAnualservicio(id);
	}

	@Override
	public List<Servicio> findServicebyp(int id) {
		
		return ServicioDao.listServiciosByPeriodicidad(id);
	}

	@Override
	public int countEspecificbyServicionP(int period, Long id) {
		return ServicioDao.countEspecificPeriodicidadbyServicio(period, id);
	}

	@Override
	public Servicio findbyName(String name) {
		// TODO Auto-generated method stub
		return ServicioDao.findServiciobyname(name);
	}

	@Override
	public List<SystemEquipo> listSystemEquiposbyServicio(Long id) {
		// TODO Auto-generated method stub
		return ServicioDao.findSystemEquiposbyServicio(id);
	}

	@Override
	public List<Servicio> findServiciosconSystemEquipos() {
		// TODO Auto-generated method stub
		return ServicioDao.listServiciosconSystemEquipos();
	}

	@Override
	public List<Integer> countequiposbyServicio() {
		// TODO Auto-generated method stub
		return ServicioDao.countbyTiposServicios();
	}

	@Override
	public List<Servicio> ListServicioSys() {
		
		return ServicioDao.ListServicioSys();
	}

	@Override
	public List<Servicio> getServiciosConComodatos() {
		// TODO Auto-generated method stub
		return ServicioDao.getServiciosConComodatos();
	}

}
