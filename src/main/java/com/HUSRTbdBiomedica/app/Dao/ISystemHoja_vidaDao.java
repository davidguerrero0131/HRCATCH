package com.HUSRTbdBiomedica.app.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HUSRTbdBiomedica.app.entity.SystemHoja_vida;

@Repository
public interface ISystemHoja_vidaDao extends CrudRepository<SystemHoja_vida, Long> {
	
	@Query("SELECT COUNT(sh) from SystemHoja_vida sh")
    public int countAll();
	
	@Query("SELECT sh FROM SystemHoja_vida sh "
			+ "INNER JOIN SystemEquipo se ON sh.systemEquipo.id_Sysequipo=se.id_Sysequipo "
			+ "WHERE se.id_Sysequipo=?1")
	public SystemHoja_vida findHVbySystemEquipo(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE SystemHoja_vida hv SET hv.Ip = ?2 WHERE hv.systemEquipo.id_Sysequipo = ?1")
	public void UpdateIPEquipo(Long idEquipo, String iP);

}
