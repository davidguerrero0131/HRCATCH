package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "responsable")
public class Responsable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_responsable")
	private Long id_Responsable;
	
	@Column(name = "nombre_responsable")
	private String Nombre_responsable;
	
	@Column(name = "calificacion")
	private int Calificacion;
	
	@Column(name = "externo")
	private boolean Externo;
	
	@Column(name = "garantia")
	private boolean Garantia;
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Responsable() {
		return id_Responsable;
	}

	public void setId_Responsable(Long id_Responsable) {
		this.id_Responsable = id_Responsable;
	}

	public String getNombre_responsable() {
		return Nombre_responsable;
	}

	public void setNombre_responsable(String nombre_responsable) {
		Nombre_responsable = nombre_responsable;
	}

	public int getCalificacion() {
		return Calificacion;
	}

	public void setCalificacion(int calificacion) {
		Calificacion = calificacion;
	}

	public boolean isExterno() {
		return Externo;
	}

	public void setExterno(boolean externo) {
		Externo = externo;
	}

	public boolean isGarantia() {
		return Garantia;
	}

	public void setGarantia(boolean garantia) {
		Garantia = garantia;
	}
	
	

}
