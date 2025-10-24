package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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

import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "llamado")
public class Llamado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_llamado")
	private Long id_Llamado;
	
	//llamado
	@Column(name = "realizado")
	private String Realizado;
	
	@Column(name = "fecha")
	private Date Fecha;
	
	@Column(name = "hora_llamado")
	private Time Hora_llamado;
	
	@Column(name = "descripcion")
	private String Descripcion;
	
	@Column(name = "palabras_clave")
	private String Palabras_clave;
	
	
	
	@Column(name = "area")
	private int Area;
	
	@Column(name = "prioridad")
	private int Prioridad;
	
	
	//recepcion 	
	@Column(name = "fecha_r")
	private Date Fecha_r;
	
	@Column(name = "por_telefono")
	private boolean Por_telefono;
	
	@Column(name = "atendido")
	private String Atendido;

	@Column(name = "tipo")
	private int Tipo;
	
	@Column(name = "hora_respuesta")
	private Time Hora_respuesta;
	
	@Column(name = "ubicacion_exacta")
	private String Ubicacion_exacta;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "foto")
	private String Foto;
	
	@Column(name = "bitacora")
	private String Bitacora;
	
	//solucion
	@Column(name = "fecha_sn")
	private Date Fecha_sn;
	
	@Column(name = "confirmacion_telefono")
	private boolean Confirmacion_telefono;
	
	@Column(name = "atencion_prioridad")
	private int Atencion_prioridad;
	
	@Column(name = "solucion")
	private String Solucion;
	
	@Column(name = "hora_solucion")
	private Time Hora_solucion;
	
	@Column(name = "solucionado_por")
	private String Solucionado_por;
	
	//conclusion
	@Column(name = "calificacion")
	private int Calificacion;
	
	
	@Column(name = "estado")
	private int Estado;
	
	@Column(name = "afecta_paciente")
	private boolean afectaPaciente = false;
	
	@Column(name = "equipo_desabilitado")
	private boolean equipoDesabilitado = false;
	
	@Column(name = "tiempo_parada")
	private double tiempoParada;
	
	@JoinColumn(name ="tipo_equipo",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="id_serviciofk",referencedColumnName ="id_Servicio")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Servicio servicio;
	
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}


	public Long getId_Llamado() {
		return id_Llamado;
	}


	public void setId_Llamado(Long id_Llamado) {
		this.id_Llamado = id_Llamado;
	}


	public String getRealizado() {
		return Realizado;
	}


	public void setRealizado(String realizado) {
		Realizado = realizado;
	}


	public Time getHora_llamado() {
		return Hora_llamado;
	}


	public void setHora_llamado(Time hora_llamado) {
		Hora_llamado = hora_llamado;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	public String getPalabras_clave() {
		return Palabras_clave;
	}


	public void setPalabras_clave(String palabras_clave) {
		Palabras_clave = palabras_clave;
	}


	public int getArea() {
		return Area;
	}


	public void setArea(int area) {
		Area = area;
	}


	public int getPrioridad() {
		return Prioridad;
	}


	public void setPrioridad(int prioridad) {
		Prioridad = prioridad;
	}


	public String getAtendido() {
		return Atendido;
	}


	public void setAtendido(String atendido) {
		Atendido = atendido;
	}


	public Time getHora_respuesta() {
		return Hora_respuesta;
	}


	public void setHora_respuesta(Time hora_respuesta) {
		Hora_respuesta = hora_respuesta;
	}


	public int getAtencion_prioridad() {
		return Atencion_prioridad;
	}


	public void setAtencion_prioridad(int atencion_prioridad) {
		Atencion_prioridad = atencion_prioridad;
	}


	public String getSolucion() {
		return Solucion;
	}


	public void setSolucion(String solucion) {
		Solucion = solucion;
	}


	public int getCalificacion() {
		return Calificacion;
	}


	public void setCalificacion(int calificacion) {
		Calificacion = calificacion;
	}


	public int getEstado() {
		return Estado;
	}


	public void setEstado(int estado) {
		Estado = estado;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	public int getTipo() {
		return Tipo;
	}


	public void setTipo(int tipo) {
		Tipo = tipo;
	}


	public String getUbicacion_exacta() {
		return Ubicacion_exacta;
	}


	public void setUbicacion_exacta(String ubicacion_exacta) {
		Ubicacion_exacta = ubicacion_exacta;
	}


	public String getSerie() {
		return Serie;
	}


	public void setSerie(String serie) {
		Serie = serie;
	}


	public Tipo_equipo getTipo_equipo() {
		return tipo_equipo;
	}


	public void setTipo_equipo(Tipo_equipo tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}


	public Time getHora_solucion() {
		return Hora_solucion;
	}


	public void setHora_solucion(Time hora_solucion) {
		Hora_solucion = hora_solucion;
	}


	public boolean isPor_telefono() {
		return Por_telefono;
	}


	public void setPor_telefono(boolean por_telefono) {
		Por_telefono = por_telefono;
	}


	public boolean isConfirmacion_telefono() {
		return Confirmacion_telefono;
	}


	public void setConfirmacion_telefono(boolean confirmacion_telefono) {
		Confirmacion_telefono = confirmacion_telefono;
	}


	public Date getFecha_r() {
		return Fecha_r;
	}


	public void setFecha_r(Date fecha_r) {
		Fecha_r = fecha_r;
	}


	public Date getFecha_sn() {
		return Fecha_sn;
	}


	public void setFecha_sn(Date fecha_sn) {
		Fecha_sn = fecha_sn;
	}


	public String getFoto() {
		return Foto;
	}


	public void setFoto(String foto) {
		Foto = foto;
	}


	public String getBitacora() {
		return Bitacora;
	}


	public void setBitacora(String bitacora) {
		Bitacora = bitacora;
	}


	public String getSolucionado_por() {
		return Solucionado_por;
	}


	public void setSolucionado_por(String solucionado_por) {
		Solucionado_por = solucionado_por;
	}


	public boolean isAfectaPaciente() {
		return afectaPaciente;
	}


	public void setAfectaPaciente(boolean afectaPaciente) {
		this.afectaPaciente = afectaPaciente;
	}


	public boolean isEquipoDesabilitado() {
		return equipoDesabilitado;
	}


	public void setEquipoDesabilitado(boolean equipoDesabilitado) {
		this.equipoDesabilitado = equipoDesabilitado;
	}


	public double getTiempoParada() {
		return tiempoParada;
	}


	public void setTiempoParada(double tiempoParada) {
		this.tiempoParada = tiempoParada;
	}
	
	
}
