package com.HUSRTbdBiomedica.service;

import java.util.List;

import com.HUSRTbdBiomedica.app.entity.Documento;

public interface IDocumentoService {

	public void addDocumento(Documento documento);
	public List<Documento> DocumentosEquipo(Long idEquipo);
	public Documento getdocumentoById(Long idDocumento);
	public void disabledocumento(Long idDocumento);
	public List<Documento> getDocumentoEquipo(Long idEquipo);
	
}
