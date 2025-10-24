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
@Table(name = "prueba_temperaturati")
public class Prueba_temperaturati implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_temperaturati")
	private Long id_Prueba_temperaturati;
	
	@Column(name = "temperatura_patron")    
    private float Temperatura_patron;
	  
	@Column(name = "temperatura_calibracion")
	private float Temperatura_calibracion;
	
	@Column(name = "lectura_termometro")
	private float Lectura_termometro;
	
	@Column(name = "sesgo")
	private float Sesgo;
	
	@Column(name = "correccion")
	private float Correcion;
	
	@Column(name = "dstd")
	private float Dstd;
	
	@Column(name = "medidamax")
	private float Medidamax;
	
	@Column(name = "medidamin")
	private float Medidamin;
	
	@Column(name = "uexp")
	private float Uexp;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;

	
	//Termometro infrarrojo
		/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Prueba_temperaturati() {
		return id_Prueba_temperaturati;
	}

	public void setId_Prueba_temperaturati(Long id_Prueba_temperaturati) {
		this.id_Prueba_temperaturati = id_Prueba_temperaturati;
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

	public float getLectura_termometro() {
		return Lectura_termometro;
	}

	public void setLectura_termometro(float lectura_termometro) {
		Lectura_termometro = lectura_termometro;
	}

	public float getSesgo() {
		return Sesgo;
	}

	public void setSesgo(float sesgo) {
		Sesgo = sesgo;
	}

	public float getCorrecion() {
		return Correcion;
	}

	public void setCorrecion(float correcion) {
		Correcion = correcion;
	}

	public float getDstd() {
		return Dstd;
	}

	public void setDstd(float dstd) {
		Dstd = dstd;
	}

	public float getMedidamax() {
		return Medidamax;
	}

	public void setMedidamax(float medidamax) {
		Medidamax = medidamax;
	}

	public float getMedidamin() {
		return Medidamin;
	}

	public void setMedidamin(float medidamin) {
		Medidamin = medidamin;
	}

	public float getUexp() {
		return Uexp;
	}

	public void setUexp(float uexp) {
		Uexp = uexp;
	}

	public Calibracion getCalibracion() {
		return calibracion;
	}

	public void setCalibracion(Calibracion calibracion) {
		this.calibracion = calibracion;
	}
	
	
	
	
}
