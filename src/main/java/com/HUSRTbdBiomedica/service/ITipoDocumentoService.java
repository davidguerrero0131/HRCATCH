package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.TipoDocumento;

public interface ITipoDocumentoService {

	public void addTipoDocumento(TipoDocumento tipoDocumento);
	public List<TipoDocumento> getAllTiposDocumento();
	public TipoDocumento getTipoDocumento(Long idTipoDocumento);
	public List<TipoDocumento> getDocumentosPEndientesEquipo(Long idEquipo);
}
