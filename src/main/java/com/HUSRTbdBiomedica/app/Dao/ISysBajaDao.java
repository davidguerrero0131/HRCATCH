package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.SystemBaja;

@Repository
public interface ISysBajaDao extends CrudRepository<SystemBaja, Long> {

	@Query("SELECT b from SystemBaja b")
    public List<SystemBaja> getAllBajas();
	
	@Query("SELECT b from SystemBaja b where b.idSysBaja = ?1")
    public SystemBaja getById(Long id);
	
}
