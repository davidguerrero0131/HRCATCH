package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "mantenimiento_preventivo")
public class Mantenimiento_preventivo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mantenimiento_preventivo")
	private Long id_Mantenimiento_preventivo;
	
	@Column(name = "nombre_equipo")
	private String nombre_Equipo;
	
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "placa_inventario")
	private String Placa_inventario;
	
	@Column(name = "servicio")
	private String Servicio;
	
	@Column(name = "ubicacion")
	private String Ubicacion;
	
	@Column(name = "fecha_realizacion")
	private Date Fecha_realizacion;
	
	@Column(name = "dias")
	private String Dias;
	
	
	@Column(name = "mes")
	private int Mes;
	
	@Column(name = "ano")
	private int Ano;
	
	@Column(name = "tiempo_realizacion")
	private Time Tiempo_realizacion;
	
	@Column(name = "realizado")
	private boolean Realizado;
	
	@Column(name = "checkcode")
	private String Checkcode;
	
	@JoinColumn(name ="id_tipo_equipo",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
	
	@JoinColumn(name ="id_usuario_fk",referencedColumnName ="id_Usuario")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@JoinColumn(name ="id_reporte_fk ",referencedColumnName ="id_Reporte")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Reporte reporte;
	
	
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }


	public Long getId_Mantenimiento_preventivo() {
		return id_Mantenimiento_preventivo;
	}


	public void setId_Mantenimiento_preventivo(Long id_Mantenimiento_preventivo) {
		this.id_Mantenimiento_preventivo = id_Mantenimiento_preventivo;
	}


	public String getNombre_Equipo() {
		return nombre_Equipo;
	}


	public void setNombre_Equipo(String nombre_Equipo) {
		this.nombre_Equipo = nombre_Equipo;
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


	public String getServicio() {
		return Servicio;
	}


	public void setServicio(String servicio) {
		Servicio = servicio;
	}


	public String getUbicacion() {
		return Ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}


	public Date getFecha_realizacion() {
		return Fecha_realizacion;
	}


	public void setFecha_realizacion(Date fecha_realizacion) {
		Fecha_realizacion = fecha_realizacion;
	}

	

	public String getDias() {
		return Dias;
	}


	public void setDias(String dias) {
		Dias = dias;
	}


	public int getMes() {
		return Mes;
	}


	public void setMes(int mes) {
		Mes = mes;
	}
	

	public int getAno() {
		return Ano;
	}


	public void setAno(int ano) {
		Ano = ano;
	}


	public Time getTiempo_realizacion() {
		return Tiempo_realizacion;
	}


	public void setTiempo_realizacion(Time tiempo_realizacion) {
		Tiempo_realizacion = tiempo_realizacion;
	}


	public boolean isRealizado() {
		return Realizado;
	}


	public void setRealizado(boolean realizado) {
		Realizado = realizado;
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


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getCheckcode() {
		return Checkcode;
	}


	public void setCheckcode(String checkcode) {
		Checkcode = checkcode;
	}


	public Reporte getReporte() {
		return reporte;
	}


	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}
	
	
	
}
