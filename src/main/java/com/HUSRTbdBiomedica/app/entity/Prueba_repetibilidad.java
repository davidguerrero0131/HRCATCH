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
@Table(name = "prueba_repetibilidad")
public class Prueba_repetibilidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_repetibilidad")
	private Long id_Prueba_repetibilidad;
	
	@Column(name = "carga_test")    
    private float Carga_test;
	
	@Column(name ="carga_read")
	private float Carga_read;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;

	//Balanza
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Prueba_repetibilidad() {
		return id_Prueba_repetibilidad;
	}

	public void setId_Prueba_repetibilidad(Long id_Prueba_repetibilidad) {
		this.id_Prueba_repetibilidad = id_Prueba_repetibilidad;
	}

	public float getCarga_test() {
		return Carga_test;
	}

	public void setCarga_test(float carga_test) {
		Carga_test = carga_test;
	}

	public float getCarga_read() {
		return Carga_read;
	}

	public void setCarga_read(float carga_read) {
		Carga_read = carga_read;
	}

	public Calibracion getCalibracion() {
		return calibracion;
	}

	public void setCalibracion(Calibracion calibracion) {
		this.calibracion = calibracion;
	}
	
	
	
	
	
}
