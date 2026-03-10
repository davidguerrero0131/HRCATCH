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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sysequipo")
public class SystemEquipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sysequipo")
	private Long id_Sysequipo;
	
	@Column(name = "nombre_equipo")
	private String Nombre_equipo;
	
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "placa_inventario")
	private String Placa_inventario;
		
	@Column(name = "ubicacion")
	private String Ubicacion;
	
	@Column(name= "ubicacion_especifica")
	private String Ubicacion_especifica;
	
	@Column(name = "ubicacion_anterior")
	private String Ubicacion_anterior;
	
	@Column(name = "fecha_modificacion")
	private Date Fecha_modificacion;
	
	@Column(name = "periodicidad")
	private int Periodicidad;
	
	@Column(name = "dias_mantenimiento")
	private String Dias_mantenimiento;

	@Column(name = "ano_ingreso")
	private Date Ano_ingreso;
	
	@Column(name = "activo")
	private boolean Activo;
	
	@Column(name = "codigo")
	private String Codigo;
	
	//Propiedad mantenimiento
	@Column(name = "mtto")
	private int Mtto;
	
	//PREVENTIVO MPSOFTWARE
	@Column(name = "preventivo_s")
	private boolean Preventivo_s;
	
	@Column(name = "estado_baja")
	private boolean EstadoBaja;
	
	@Column(name = "administrable")
	private boolean Administrable;
	
	@Column(name = "numero_puertos")
	private int NumeroPuertos;
	
	@Column(name = "direccionamiento_Vlan")
	private String DireccionamientoVlan;
	
	@JoinColumn(name ="id_tipo_equipo_fk",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="id_servicio_fk",referencedColumnName ="id_Servicio")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Servicio servicio;
	
	@JoinColumn(name = "id_hospital_FK",referencedColumnName="id_Hospital")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private Hospital hospital;
	
	@JoinColumn(name ="Id_usuario_fk",referencedColumnName ="id_Usuario")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;	
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Sysequipo() {
		return id_Sysequipo;
	}

	public void setId_Sysequipo(Long id_Sysequipo) {
		this.id_Sysequipo = id_Sysequipo;
	}

	public String getNombre_equipo() {
		return Nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		Nombre_equipo = nombre_equipo;
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

	public String getPlaca_inventario() {
		return Placa_inventario;
	}

	public void setPlaca_inventario(String placa_inventario) {
		Placa_inventario = placa_inventario;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public String getUbicacion_especifica() {
		return Ubicacion_especifica;
	}

	public void setUbicacion_especifica(String ubicacion_especifica) {
		Ubicacion_especifica = ubicacion_especifica;
	}

	public int getPeriodicidad() {
		return Periodicidad;
	}

	public void setPeriodicidad(int periodicidad) {
		Periodicidad = periodicidad;
	}

	public String getDias_mantenimiento() {
		return Dias_mantenimiento;
	}

	public void setDias_mantenimiento(String dias_mantenimiento) {
		Dias_mantenimiento = dias_mantenimiento;
	}

	

	public Date getAno_ingreso() {
		return Ano_ingreso;
	}

	public void setAno_ingreso(Date ano_ingreso) {
		Ano_ingreso = ano_ingreso;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public Tipo_equipo getTipo_equipo() {
		return tipo_equipo;
	}

	public void setTipo_equipo(Tipo_equipo tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public boolean isPreventivo_s() {
		return Preventivo_s;
	}

	public void setPreventivo_s(boolean preventivo_s) {
		Preventivo_s = preventivo_s;
	}

	public int getMtto() {
		return Mtto;
	}

	public void setMtto(int mtto) {
		Mtto = mtto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUbicacion_anterior() {
		return Ubicacion_anterior;
	}

	public void setUbicacion_anterior(String ubicacion_anterior) {
		Ubicacion_anterior = ubicacion_anterior;
	}

	public Date getFecha_modificacion() {
		return Fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		Fecha_modificacion = fecha_modificacion;
	}

	public boolean isEstadoBaja() {
		return EstadoBaja;
	}

	public void setEstadoBaja(boolean estadoBaja) {
		EstadoBaja = estadoBaja;
	}

	public boolean isAdministrable() {
		return Administrable;
	}

	public void setAdministrable(boolean administrable) {
		Administrable = administrable;
	}

	public int getNumeroPuertos() {
		return NumeroPuertos;
	}

	public void setNumeroPuertos(int numeroPuertos) {
		NumeroPuertos = numeroPuertos;
	}

	public String getDireccionamientoVlan() {
		return DireccionamientoVlan;
	}

	public void setDireccionamientoVlan(String direccionamientoVlan) {
		DireccionamientoVlan = direccionamientoVlan;
	}
	
	
	
}
