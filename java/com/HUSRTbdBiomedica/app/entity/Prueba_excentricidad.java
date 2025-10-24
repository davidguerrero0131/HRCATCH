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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prueba_excentricidad")
public class Prueba_excentricidad implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_excentricidad")
	private Long id_Prueba_excentricidad;
	
	@Column(name = "carga_test")    
    private float Carga_test;
	
	@Column(name = "lectura_centro")
	private float Lectura_centro;
	
	@Column(name ="Lectura_i1")
	private float Lectura_i1;
	
	@Column(name ="Lectura_i2")
	private float Lectura_i2;
	
	@Column(name ="Lectura_i3")
	private float Lectura_i3;
	
	@Column(name ="Lectura_i4")
	private float Lectura_i4;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;
	
	//Balanza
/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }

	public Long getId_Prueba_excentricidad() {
		return id_Prueba_excentricidad;
	}
	
	public void setId_Prueba_excentricidad(Long id_Prueba_excentricidad) {
		this.id_Prueba_excentricidad = id_Prueba_excentricidad;
	}
	
	public float getCarga_test() {
		return Carga_test;
	}
	
	public void setCarga_test(float carga_test) {
		Carga_test = carga_test;
	}
	
	public float getLectura_centro() {
		return Lectura_centro;
	}
	
	public void setLectura_centro(float lectura_centro) {
		Lectura_centro = lectura_centro;
	}
	
	public float getLectura_i1() {
		return Lectura_i1;
	}
	
	public void setLectura_i1(float lectura_i1) {
		Lectura_i1 = lectura_i1;
	}
	
	public float getLectura_i2() {
		return Lectura_i2;
	}
	
	public void setLectura_i2(float lectura_i2) {
		Lectura_i2 = lectura_i2;
	}
	
	public float getLectura_i3() {
		return Lectura_i3;
	}
	
	public void setLectura_i3(float lectura_i3) {
		Lectura_i3 = lectura_i3;
	}
	
	public float getLectura_i4() {
		return Lectura_i4;
	}
	
	public void setLectura_i4(float lectura_i4) {
		Lectura_i4 = lectura_i4;
	}
	
	public Calibracion getCalibracion() {
		return calibracion;
	}
	
	public void setCalibracion(Calibracion calibracion) {
		this.calibracion = calibracion;
	}
		
		
}
