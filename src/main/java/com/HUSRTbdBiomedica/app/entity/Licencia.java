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
@Table(name = "licencia")
public class Licencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_licencia")
	private Long id_Licencia;
	
	@Column(name = "resolucion")
	private String Resolucion;
	
	@Column(name = "rutaformato")
	private String Rutaformato;
	
	@JoinColumn(name ="id_hospital_fk",referencedColumnName ="id_Hospital")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Hospital hospital;
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Licencia() {
		return id_Licencia;
	}

	public void setId_Licencia(Long id_Licencia) {
		this.id_Licencia = id_Licencia;
	}

	public String getResolucion() {
		return Resolucion;
	}

	public void setResolucion(String resolucion) {
		Resolucion = resolucion;
	}

	public String getRutaformato() {
		return Rutaformato;
	}

	public void setRutaformato(String rutaformato) {
		Rutaformato = rutaformato;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	
}
