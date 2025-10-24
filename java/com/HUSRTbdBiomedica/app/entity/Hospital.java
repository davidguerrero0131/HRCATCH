package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_hospital")
	private Long id_Hospital;
	
	@Column(name = "nombre_hospital")
	private String Nombre_hospital;
	
	@Column(name = "direccion_hospital")
	private String Direccion_hospital;
	
	@Column(name = "nit_hospital")
	private String Nit_hospital;
	
	@Column(name = "ciudad")
	private String Ciudad;
	
	@Column(name = "nivel")
	private int Nivel;
	
	@Column(name = "departamento")
	private String Departamento;
	
	@Column(name = "estado")
	private boolean estado;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", fetch = FetchType.LAZY)
	private List<Equipo>equipo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", fetch = FetchType.LAZY)
	private List<Usuario>usuario;
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public List<Equipo> getEquipo() {
		return equipo;
	}

	public void setEquipo(List<Equipo> equipo) {
		this.equipo = equipo;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Long getId_Hospital() {
		return id_Hospital;
	}

	public void setId_Hospital(Long id_Hospital) {
		this.id_Hospital = id_Hospital;
	}

	public String getNombre_hospital() {
		return Nombre_hospital;
	}

	public void setNombre_hospital(String nombre_hospital) {
		Nombre_hospital = nombre_hospital;
	}

	public String getDireccion_hospital() {
		return Direccion_hospital;
	}

	public void setDireccion_hospital(String direccion_hospital) {
		Direccion_hospital = direccion_hospital;
	}

	public String getNit_hospital() {
		return Nit_hospital;
	}

	public void setNit_hospital(String nit_hospital) {
		Nit_hospital = nit_hospital;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getNivel() {
		return Nivel;
	}

	public void setNivel(int nivel) {
		Nivel = nivel;
	}

	

	
	
	

}
