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
@Table(name = "validacion")
public class Validacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_validacion")
	private Long id_Validacion;
	
	@Column(name ="numero_certificado")
	private String Numero_certificado;
	
	@Column(name = "certificado")
	private String Certificado;
	
	@Column(name = "fecha_proceso")
	private Date Fecha_proceso;
	
	@Column(name = "realizado_por")
	private String Realizado_por;
	
	@Column(name = "aprobado_por")
	private String Aprobado_por;
	
	@Column(name = "condiciones")
	private String Condiciones;
	
	@Column(name = "mes_programado")
	private int Mes_programado;
	
	@Column(name = "ano_programado")
	private int Ano_programado;
	
	@Column(name ="empresa")
	private String Empresa;
	
	@JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;

	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Validacion() {
		return id_Validacion;
	}

	public void setId_Validacion(Long id_Validacion) {
		this.id_Validacion = id_Validacion;
	}

	public String getNumero_certificado() {
		return Numero_certificado;
	}

	public void setNumero_certificado(String numero_certificado) {
		Numero_certificado = numero_certificado;
	}

	public String getCertificado() {
		return Certificado;
	}

	public void setCertificado(String certificado) {
		Certificado = certificado;
	}

	public Date getFecha_proceso() {
		return Fecha_proceso;
	}

	public void setFecha_proceso(Date fecha_proceso) {
		Fecha_proceso = fecha_proceso;
	}

	public String getRealizado_por() {
		return Realizado_por;
	}

	public void setRealizado_por(String realizado_por) {
		Realizado_por = realizado_por;
	}

	public String getAprobado_por() {
		return Aprobado_por;
	}

	public void setAprobado_por(String aprobado_por) {
		Aprobado_por = aprobado_por;
	}

	public String getCondiciones() {
		return Condiciones;
	}

	public void setCondiciones(String condiciones) {
		Condiciones = condiciones;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getMes_programado() {
		return Mes_programado;
	}

	public void setMes_programado(int mes_programado) {
		Mes_programado = mes_programado;
	}

	public int getAno_programado() {
		return Ano_programado;
	}

	public void setAno_programado(int ano_programado) {
		Ano_programado = ano_programado;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
		
	
}
