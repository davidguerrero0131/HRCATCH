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
@Table(name = "fecha_mantenimiento_equipo")
public class FechaMantenimientoEquipos implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id_Registro;
	
	@Column(name = "dia1")
	private int dia1;
	
	@Column(name = "dia2")
	private int Dia2;
	
	@Column(name = "mes")
	private String mes;
	
	@Column(name = "año")
	private int año;

	@Column(name = "debe_realizar")
	private String debeRealizar;
	
	@JoinColumn(name ="ID_EQUIPO_FK",referencedColumnName ="id_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;

	private static final long serialVersionUID = 1L;

	public Long getId_Registro() {
		return id_Registro;
	}

	public void setId_Registro(Long id_Registro) {
		this.id_Registro = id_Registro;
	}

	public int getDia1() {
		return dia1;
	}

	public void setDia1(int dia1) {
		this.dia1 = dia1;
	}

	public int getDia2() {
		return Dia2;
	}

	public void setDia2(int dia2) {
		Dia2 = dia2;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDebeRealizar() {
		return debeRealizar;
	}

	public void setDebeRealizar(String debeRealizar) {
		this.debeRealizar = debeRealizar;
	}

	
}
