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
@Table(name = "prueba_volumenmp")
public class Prueba_volumenmp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_volumenmp")
	private Long id_Prueba_volumenmp;
	
	@Column(name = "volumen_teorico")    
    private float Volumen_teorico;
	
	@Column(name = "volumen_calculado")    
    private float Volumen_calculado;
	
	@Column(name = "error_aleatorio")    
    private float Error_aleatorio;
	
	@Column(name = "incertidumbre_expandida")    
    private float Incertidumbre_expandida;
	
	@Column(name = "factork")    
    private float Factork;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;

	
	//Pipeta
		/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Prueba_volumenmp() {
		return id_Prueba_volumenmp;
	}

	public void setId_Prueba_volumenmp(Long id_Prueba_volumenmp) {
		this.id_Prueba_volumenmp = id_Prueba_volumenmp;
	}

	public float getVolumen_teorico() {
		return Volumen_teorico;
	}

	public void setVolumen_teorico(float volumen_teorico) {
		Volumen_teorico = volumen_teorico;
	}

	public float getVolumen_calculado() {
		return Volumen_calculado;
	}

	public void setVolumen_calculado(float volumen_calculado) {
		Volumen_calculado = volumen_calculado;
	}

	public float getError_aleatorio() {
		return Error_aleatorio;
	}

	public void setError_aleatorio(float error_aleatorio) {
		Error_aleatorio = error_aleatorio;
	}

	public float getIncertidumbre_expandida() {
		return Incertidumbre_expandida;
	}

	public void setIncertidumbre_expandida(float incertidumbre_expandida) {
		Incertidumbre_expandida = incertidumbre_expandida;
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
