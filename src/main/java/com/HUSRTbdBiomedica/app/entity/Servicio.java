package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_servicio")
	private Long id_Servicio;

	@Column(name = "nombre_servicio")
	private String Nombre_servicio;
	
	@Column(name = "ubicacion_servicio")
	private String Ubicacion_servicio;
	
	@Column(name = "cantidad_equipos")
	private int Cantidad_equipos;
	
	@Column(name = "fmpp")
	private int Fmpp;

	@Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT true")
	private boolean activo;
	
	/********************* GET Y SET *****************************/
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}
	
	public Long getId_Servicio() {
		return id_Servicio;
	}

	public void setId_Servicio(Long id_Servicio) {
		this.id_Servicio = id_Servicio;
	}

	public String getNombre_servicio() {
		return Nombre_servicio;
	}

	public void setNombre_servicio(String nombre_servicio) {
		Nombre_servicio = nombre_servicio;
	}

	public String getUbicacion_servicio() {
		return Ubicacion_servicio;
	}

	public void setUbicacion_servicio(String ubicacion_servicio) {
		Ubicacion_servicio = ubicacion_servicio;
	}

	public int getCantidad_equipos() {
		return Cantidad_equipos;
	}

	public void setCantidad_equipos(int cantidad_equipos) {
		Cantidad_equipos = cantidad_equipos;
	}

	public int getFmpp() {
		return Fmpp;
	}

	public void setFmpp(int fmpp) {
		Fmpp = fmpp;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	
	
	
	
}
