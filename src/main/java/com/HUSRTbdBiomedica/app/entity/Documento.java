package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_documento")
	private Long idDocumento;
	
	@Column(name = "nombre_documento")
	private String nombreDocumento;
	
	@Column(name = "ruta_documento")
	private String rutaDocumento;
	
	@Column(name = "activo")
	private boolean activo;
	
    @JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
    
    @JoinColumn(name ="id_tipo_documento_fk",referencedColumnName ="id_tipo_documento")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private TipoDocumento tipoDocumento;

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getRutaDocumento() {
		return rutaDocumento;
	}

	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
    
    
}
