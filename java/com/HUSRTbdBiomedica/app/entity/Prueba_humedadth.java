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
@Table(name = "prueba_humedadth")
public class Prueba_humedadth implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_humedadth")
	private Long id_Prueba_humedadth;

	@Column(name = "humedad_patron")    
    private float Humedad_patron;
	
	@Column(name = "humedad_calibracion")    
    private float Humedad_calibracion;
	
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


	public Long getId_Prueba_humedadth() {
		return id_Prueba_humedadth;
	}


	public void setId_Prueba_humedadth(Long id_Prueba_humedadth) {
		this.id_Prueba_humedadth = id_Prueba_humedadth;
	}


	public float getHumedad_patron() {
		return Humedad_patron;
	}


	public void setHumedad_patron(float humedad_patron) {
		Humedad_patron = humedad_patron;
	}


	public float getHumedad_calibracion() {
		return Humedad_calibracion;
	}


	public void setHumedad_calibracion(float humedad_calibracion) {
		Humedad_calibracion = humedad_calibracion;
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
