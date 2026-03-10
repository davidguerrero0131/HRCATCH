package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "sysbaja")
public class SystemBaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sysbaja")
	private Long idSysBaja;
	
	@Column(name = "justificacion_Baja")
	private String justificacionBaja;		
	
	@Column(name = "accesorios_Reutilizables")
	private String accesoriosRecuperados;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fecha_Baja")
    private Date FechaBaja;
	
	@JoinColumn(name = "id_sysequipo_fk",referencedColumnName ="id_Sysequipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private SystemEquipo equipo;
	
	@JoinColumn(name = "id_sysusuario_fk",referencedColumnName ="id_Usuario")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;

	public Long getIdSysBaja() {
		return idSysBaja;
	}

	public void setIdSysBaja(Long idSysBaja) {
		this.idSysBaja = idSysBaja;
	}

	public String getJustificacionBaja() {
		return justificacionBaja;
	}

	public void setJustificacionBaja(String justificacionBaja) {
		this.justificacionBaja = justificacionBaja;
	}

	public String getAccesoriosRecuperados() {
		return accesoriosRecuperados;
	}

	public void setAccesoriosRecuperados(String accesoriosRecuperados) {
		this.accesoriosRecuperados = accesoriosRecuperados;
	}

	public Date getFechaBaja() {
		return FechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		FechaBaja = fechaBaja;
	}

	public SystemEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(SystemEquipo equipo) {
		this.equipo = equipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}
