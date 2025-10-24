package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.Documento;

@Repository
public interface IDocumentoDao extends JpaRepository<Documento, Long> {

	@Query("select d from Documento d where d.equipo.id_Equipo = ?1")
	public List<Documento> documentosEquipo(Long idEquipo);
	
	@Query("select d from Documento d where d.tipoDocumento.idTipoDocumento = 10 and d.equipo.id_Equipo = ?1")
	public List<Documento> getGuiaEquipo(Long idEquipo);
}
