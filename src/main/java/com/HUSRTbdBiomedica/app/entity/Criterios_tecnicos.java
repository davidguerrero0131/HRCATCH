package com.HUSRTbdBiomedica.app.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "criterios_tecnicos")
public class Criterios_tecnicos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_criterios_tecnicos")
	private Long id_Criterios_tecnicos;
	
	@Column(name = "intensidad_uso")
	private int Intensidad_uso;
	
	@Column(name = "edad")
	private int Edad;
	
	@Column(name = "numero_mantenimientos")
	private int Numero_mantenimientos;
	
	@Column(name = "tiempo_fuera_servicio")
	private int Tiempo_fuera_servicio;
	
	@Column(name = "manual_usuario_servicio")
	private int Manual_usuario_servicio;
	
	@Column(name = "seguridad")
	private int Seguridad;
	
	@Column(name = "garantia_repuestos")
	private int Garantia_repuestos;
	
	@Column(name = "total_puntos_tecnicos")
	private int Total_puntos_tecnicos;
	/********************* GET Y SET *****************************/	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public Long getId_Criterios_tecnicos() {
		return id_Criterios_tecnicos;
	}
	public void setId_Criterios_tecnicos(Long id_Criterios_tecnicos) {
		this.id_Criterios_tecnicos = id_Criterios_tecnicos;
	}
	public int getIntensidad_uso() {
		return Intensidad_uso;
	}
	public void setIntensidad_uso(int Intensidad_uso) {
		this.Intensidad_uso = Intensidad_uso;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int Edad) {
		this.Edad = Edad;
	}
	public int getNumero_mantenimientos() {
		return Numero_mantenimientos;
	}
	public void setNumero_mantenimientos(int Numero_mantenimientos) {
		this.Numero_mantenimientos = Numero_mantenimientos;
	}
	public int getTiempo_fuera_servicio() {
		return Tiempo_fuera_servicio;
	}
	public void setTiempo_fuera_servicio(int Tiempo_fuera_servicio) {
		this.Tiempo_fuera_servicio = Tiempo_fuera_servicio;
	}
	public int getManual_usuario_servicio() {
		return Manual_usuario_servicio;
	}
	public void setManual_usuario_servicio(int Manual_usuario_servicio) {
		this.Manual_usuario_servicio = Manual_usuario_servicio;
	}
	public int getSeguridad() {
		return Seguridad;
	}
	public void setSeguridad(int Seguridad) {
		this.Seguridad = Seguridad;
	}
	public int getGarantia_repuestos() {
		return Garantia_repuestos;
	}
	public void setGarantia_repuestos(int Garantia_repuestos) {
		this.Garantia_repuestos = Garantia_repuestos;
	}
	public int getTotal_puntos_tecnicos() {
		return Total_puntos_tecnicos;
	}
	public void setTotal_puntos_tecnicos(int Total_puntos_tecnicos) {
		this.Total_puntos_tecnicos = Total_puntos_tecnicos;
	}
}