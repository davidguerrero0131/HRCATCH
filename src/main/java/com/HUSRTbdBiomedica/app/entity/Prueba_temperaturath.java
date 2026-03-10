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
@Table(name = "prueba_temperaturath")
public class Prueba_temperaturath implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_temperaturath")
	private Long id_Prueba_temperaturath;
	
	@Column(name = "temperatura_patron")    
    private float Temperatura_patron;
	  
	@Column(name = "temperatura_calibracion")
	private float Temperatura_calibracion;
	
	@Column(name = "uexp")
	private float Uexp;
	
	@Column(name = "factork")
	private float Factork;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;

	
	//Termohigrometro
		/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Prueba_temperaturath() {
		return id_Prueba_temperaturath;
	}

	public void setId_Prueba_temperaturath(Long id_Prueba_temperaturath) {
		this.id_Prueba_temperaturath = id_Prueba_temperaturath;
	}

	public float getTemperatura_patron() {
		return Temperatura_patron;
	}

	public void setTemperatura_patron(float temperatura_patron) {
		Temperatura_patron = temperatura_patron;
	}

	public float getTemperatura_calibracion() {
		return Temperatura_calibracion;
	}

	public void setTemperatura_calibracion(float temperatura_calibracion) {
		Temperatura_calibracion = temperatura_calibracion;
	}

	public float getUexp() {
		return Uexp;
	}

	public void setUexp(float uexp) {
		Uexp = uexp;
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
