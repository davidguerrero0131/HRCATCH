package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

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
@Table(name = "sysrepuestos")
public class SystemRepuestos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sysrepuesto")
	private Long id_Sysrepuesto;
	
	@Column(name = "nombre_equipo")
	private String Nombre_repuesto;
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "observaciones")
	private String Observaciones;
	
	@Column(name = "activo")
	private boolean Activo;
	
	@Column(name = "ubicacion")
	private String Ubicacion;
	
	@JoinColumn(name ="id_sys_equipo_fk",referencedColumnName ="id_Sysequipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private SystemEquipo equipo;
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Sysrepuesto() {
		return id_Sysrepuesto;
	}

	public void setId_Sysrepuesto(Long id_Sysrepuesto) {
		this.id_Sysrepuesto = id_Sysrepuesto;
	}

	public String getNombre_repuesto() {
		return Nombre_repuesto;
	}

	public void setNombre_repuesto(String nombre_repuesto) {
		Nombre_repuesto = nombre_repuesto;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getSerie() {
		return Serie;
	}

	public void setSerie(String serie) {
		Serie = serie;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public SystemEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(SystemEquipo equipo) {
		this.equipo = equipo;
	}
	
	
}
