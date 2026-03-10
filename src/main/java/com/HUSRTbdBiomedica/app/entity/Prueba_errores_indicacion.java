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
@Table(name = "prueba_indicacion")
public class Prueba_errores_indicacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_errores")
	private Long id_Prueba_errores;
	
	@Column(name = "masa_ref")    
    private float Masa_ref;
	
	@Column(name = "indicacion")    
    private float Indicacion;
	
	@Column(name = "error")    
    private float Error;
	
	@Column(name = "incertidumbre_exp")    
    private float Incertidumbre_exp;
	
	@Column(name = "factork")    
    private float Factork;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;
	
	//Balanza
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }

	public Long getId_Prueba_errores() {
		return id_Prueba_errores;
	}

	public void setId_Prueba_errores(Long id_Prueba_errores) {
		this.id_Prueba_errores = id_Prueba_errores;
	}

	public float getMasa_ref() {
		return Masa_ref;
	}

	public void setMasa_ref(float masa_ref) {
		Masa_ref = masa_ref;
	}

	public float getIndicacion() {
		return Indicacion;
	}

	public void setIndicacion(float indicacion) {
		Indicacion = indicacion;
	}

	public float getError() {
		return Error;
	}

	public void setError(float error) {
		Error = error;
	}

	public float getIncertidumbre_exp() {
		return Incertidumbre_exp;
	}

	public void setIncertidumbre_exp(float incertidumbre_exp) {
		Incertidumbre_exp = incertidumbre_exp;
	}

	public float getFactork() {
		return Factork;
	}

	public void setFactork(float factork) {
		Factork = factork;
	}

	public Calibracion getCalibracion() {
		return calibracion;
	}

	public void setCalibracion(Calibracion calibracion) {
		this.calibracion = calibracion;
	}
	
	
	
	
	
}
