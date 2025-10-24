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
@Table(name = "prueba_presiont")
public class Prueba_presionT implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prueba_presiont")
	private Long id_Prueba_presiont;
	
	@Column(name = "presion_patron")    
    private float Presion_patron;
	
	@Column(name = "repetibilidad")    
    private float Repetibilidad;
	
	@Column(name = "histeresis")    
    private float Histeresis;
	
	@Column(name = "incertidumbre_expandida")    
    private float Incertidumbre_expandida;
	
	@Column(name = "factork")    
    private float Factork;
	
	@JoinColumn(name ="id_calibracion_fk",referencedColumnName ="id_Calibracion")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Calibracion calibracion;
	
	//Tensiometro
		/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }

	public Long getId_Prueba_presiont() {
		return id_Prueba_presiont;
	}

	public void setId_Prueba_presiont(Long id_Prueba_presiont) {
		this.id_Prueba_presiont = id_Prueba_presiont;
	}

	public float getPresion_patron() {
		return Presion_patron;
	}

	public void setPresion_patron(float presion_patron) {
		Presion_patron = presion_patron;
	}

	public float getRepetibilidad() {
		return Repetibilidad;
	}

	public void setRepetibilidad(float repetibilidad) {
		Repetibilidad = repetibilidad;
	}

	public float getHisteresis() {
		return Histeresis;
	}

	public void setHisteresis(float histeresis) {
		Histeresis = histeresis;
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
