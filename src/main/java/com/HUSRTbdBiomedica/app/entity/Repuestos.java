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
@Table(name = "repuesto")
public class Repuestos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_repuesto")
	private Long id_Repuesto;
	
	@Column(name = "nombre_repuesto")
	private String Nombre_repuesto;
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	//grupo:27g disp medicos sin iva, von iva, mantenimiento
	@Column(name = "grupo")
	private String Grupo;
	
	@Column(name = "iva")
	private float IVA;
	
	@Column(name = "cantidad")
	private int Cantidad;
	
	@Column(name = "valor")
	private float Valor;
	
	@Column(name = "activo")
	private boolean Activo;
	
	@JoinColumn(name ="id_tipo_equipo_fk",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="ID_equipo_FK",referencedColumnName ="id_Equipo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
	
	/********************* GET Y SET *****************************/
    
    public static long getSerialVersionUID() {
    	return serialVersionUID;
    }

	public Long getId_Repuesto() {
		return id_Repuesto;
	}

	public void setId_Repuesto(Long id_Repuesto) {
		this.id_Repuesto = id_Repuesto;
	}

	public String getNombre_repuesto() {
		return Nombre_repuesto;
	}

	public void setNombre_repuesto(String nombre_repuesto) {
		Nombre_repuesto = nombre_repuesto;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getSerie() {
		return Serie;
	}

	public void setSerie(String serie) {
		Serie = serie;
	}

	public String getGrupo() {
		return Grupo;
	}

	public void setGrupo(String grupo) {
		Grupo = grupo;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public float getValor() {
		return Valor;
	}

	public void setValor(float valor) {
		Valor = valor;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}

	public Tipo_equipo getTipo_equipo() {
		return tipo_equipo;
	}

	public void setTipo_equipo(Tipo_equipo tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public float getIVA() {
		return IVA;
	}

	public void setIVA(float iVA) {
		IVA = iVA;
	}
	
    
    
}
