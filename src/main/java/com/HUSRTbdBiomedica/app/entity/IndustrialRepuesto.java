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
@Table(name = "indrepuesto")
public class IndustrialRepuesto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_indrepuesto")
	private Long id_Indrepuesto;
	
	@Column(name = "nombre_repuesto")
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
	
	@JoinColumn(name ="id_indequipo_fk",referencedColumnName ="id_Indequipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private IndustrialEquipo indequipo;
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Indrepuesto() {
		return id_Indrepuesto;
	}

	public void setId_Indrepuesto(Long id_Indrepuesto) {
		this.id_Indrepuesto = id_Indrepuesto;
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

	public IndustrialEquipo getIndequipo() {
		return indequipo;
	}

	public void setIndequipo(IndustrialEquipo indequipo) {
		this.indequipo = indequipo;
	}
	
	
}
