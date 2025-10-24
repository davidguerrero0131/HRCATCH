package com.HUSRTbdBiomedica.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.Dao.ISystemEquipoDao;
import com.HUSRTbdBiomedica.app.entity.SystemEquipo;

@Service
public class SystemEquipoServiceImp implements ISystemEquipoService{

	@Autowired
	private ISystemEquipoDao SystemEquipoDao;
	
	@Override
	public List<SystemEquipo> ListSystemEquipo() {
		// TODO Auto-generated method stub
		return (List<SystemEquipo>)SystemEquipoDao.findAll();
	}

	@Override
	public Optional<SystemEquipo> ListSystemEquipobyId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	@Transactional
	public SystemEquipo findOne(Long id) {
		// TODO Auto-generated method stub
		return SystemEquipoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(SystemEquipo equipo) {
		// TODO Auto-generated method stub
		SystemEquipoDao.save(equipo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		SystemEquipoDao.delete(findOne(id));
	}

	@Override
	public int countEquiposys() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.countAll();
	}

	@Override
	public List<String> listSySeries() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listasysseries();
	}

	@Override
	public List<SystemEquipo> findbySerie(String serie) {
		// TODO Auto-generated method stub
		return SystemEquipoDao.findsysEquipobySerie(serie);
	}

	@Override
	public List<SystemEquipo> findbyEnero() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyenero();
	}

	@Override
	public List<String> listnmmsp() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listasysnmmsp();
	}

	@Override
	public List<SystemEquipo> findbyFebrero() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyfebrero();
	}

	@Override
	public List<SystemEquipo> findbyMarzo() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbymarzo();
	}

	@Override
	public List<SystemEquipo> findbyAbril() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyabril();
	}

	@Override
	public List<SystemEquipo> findbyMayo() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbymayo();
	}

	@Override
	public List<SystemEquipo> findbyJunio() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyjunio();
	}

	@Override
	public List<SystemEquipo> findbyJulio() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyjulio();
	}

	@Override
	public List<SystemEquipo> findbyAgosto() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyagosto();
	}

	@Override
	public List<SystemEquipo> findbySeptiembre() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyseptiembre();
	}

	@Override
	public List<SystemEquipo> findbyOctubre() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyoctubre();
	}

	@Override
	public List<SystemEquipo> findbyNoviembre() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbynoviembre();
	}

	@Override
	public List<SystemEquipo> findbyDiciembre() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbydiciembre();
	}

	@Override
	public List<SystemEquipo> listbyAno(Date ano) {
		// TODO Auto-generated method stub
		return SystemEquipoDao.listbyano(ano);
	}

	@Override
	public List<SystemEquipo> orderbyTiponid() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.orderbytipo();
	}

	@Override
	public void bajaEquipo(Long idEquipo) {
		SystemEquipoDao.bajaEquipo(idEquipo);
		
	}

	@Override
	public void updateSwitchData(Long idEquipo, String Vlans, int numeroPuertos, Boolean administrable) {
		SystemEquipoDao.UpdateSwitchData(idEquipo, Vlans, numeroPuertos, administrable);
		
	}

	@Override
	public List<SystemEquipo> getAllActivo() {
		return SystemEquipoDao.getAllActivo();
	}

	@Override
	public List<SystemEquipo> getAllInactivos() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.getAllInactivos();
	}

	@Override
	public List<SystemEquipo> getAll() {
		
		return (List<SystemEquipo>) SystemEquipoDao.findAll();
	}

	@Override
	public int cantidadEquiposTipo(Long idTipoEquipo) {
		// TODO Auto-generated method stub
		return SystemEquipoDao.cantidadEquiposTipo(idTipoEquipo);
	}

	@Override
	public int cantidadEquiposSys() {
		// TODO Auto-generated method stub
		return SystemEquipoDao.countEquiposSys()
				;
	}



}
