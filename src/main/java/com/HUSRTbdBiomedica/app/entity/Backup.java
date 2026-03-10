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
import javax.persistence.Table;

@Entity
@Table(name = "reporte_backup")
public class Backup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_reprote_backup")
	private Long id_Backup;
	
	@Column(name = "tipo_recurso")
	private String tipo_recurso;
	
	@Column(name = "nombre_recurso")
	private String nombre_recurso;
	
	@Column(name = "fecha_backup")    
    private Date fecha_backup;
	
	@Column(name = "periodicidad")
	private String periodicidad;
	
	@Column(name = "medio")
	private String medio;
	
	@Column(name = "Destino")
	private String Destino;
	
	@Column(name = "autor_solicita")
	private String autor_solicita;
	
	@Column(name = "numero_caso_MS")
	private String caso_glpi;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "tamano")
	private String tamano;
	
	
	@JoinColumn(name = "id_autor_realizado_fk",referencedColumnName ="id_Usuario")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;

	public Long getId_Backup() {
		return id_Backup;
	}

	public void setId_Backup(Long id_Backup) {
		this.id_Backup = id_Backup;
	}

	public String getTipo_recurso() {
		return tipo_recurso;
	}

	public void setTipo_recurso(String tipo_recurso) {
		this.tipo_recurso = tipo_recurso;
	}

	public String getNombre_recurso() {
		return nombre_recurso;
	}

	public void setNombre_recurso(String nombre_recurso) {
		this.nombre_recurso = nombre_recurso;
	}

	public Date getFecha_backup() {
		return fecha_backup;
	}

	public void setFecha_backup(Date fecha_backup) {
		this.fecha_backup = fecha_backup;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public String getMedio() {
		return medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	public String getAutor_solicita() {
		return autor_solicita;
	}

	public void setAutor_solicita(String autor_solicita) {
		this.autor_solicita = autor_solicita;
	}

	public String getCaso_glpi() {
		return caso_glpi;
	}

	public void setCaso_glpi(String caso_glpi) {
		this.caso_glpi = caso_glpi;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	
}
