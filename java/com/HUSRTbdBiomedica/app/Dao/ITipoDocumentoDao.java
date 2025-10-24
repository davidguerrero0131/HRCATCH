package com.HUSRTbdBiomedica.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HUSRTbdBiomedica.app.entity.TipoDocumento;

@Repository
public interface ITipoDocumentoDao extends JpaRepository<TipoDocumento, Long> {

	@Query("select t from TipoDocumento t where t.idTipoDocumento not in (select d.tipoDocumento.idTipoDocumento from Documento d where d.equipo.id_Equipo = ?1)")
	public List<TipoDocumento> getDocumentosPendientesEquipo(Long idEquipo);
	
}
