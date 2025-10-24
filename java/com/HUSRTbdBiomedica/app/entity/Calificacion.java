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
@Table(name = "calificacion")
public class Calificacion implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_calificacion")
	private Long id_Calificacion;
	
	@Column(name = "fecha_calificacion")    
    private Date Fecha_calificacion;
	
	@Column(name = "informe")
	private String Informe;
	
	@Column(name = "preparado_por")
	private String Preparado_por;
	
	@Column(name = "tecnico")
	private String Tecnico;
	
	@Column(name = "jefe_lab")
	private String Jefe_lab;
	
	@Column(name = "numero_certificado")
	private String Numero_certificado;
	
	@Column(name = "certificado")
	private String Certificado;
	
	@Column(name = "test_evaluados")
	private int Test_evaluados;
	
	@Column(name = "test_aprobados")
	private int Test_aprobados;
	
	@Column(name = "resumen")
	private String Resumen;
	
	@Column(name ="empresa")
	private String Empresa;
	
	@Column(name = "mes_programado")
	private int Mes_programado;
	
	@Column(name = "ano_programado")
	private int Ano_programado;
	
	@JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;


	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
	
	public Long getId_Calificacion() {
		return id_Calificacion;
	}


	public void setId_Calificacion(Long id_Calificacion) {
		this.id_Calificacion = id_Calificacion;
	}


	public Date getFecha_calificacion() {
		return Fecha_calificacion;
	}


	public void setFecha_calificacion(Date fecha_calificacion) {
		Fecha_calificacion = fecha_calificacion;
	}


	public String getInforme() {
		return Informe;
	}


	public void setInforme(String informe) {
		Informe = informe;
	}


	public String getPreparado_por() {
		return Preparado_por;
	}


	public void setPreparado_por(String preparado_por) {
		Preparado_por = preparado_por;
	}


	public String getTecnico() {
		return Tecnico;
	}


	public void setTecnico(String tecnico) {
		Tecnico = tecnico;
	}


	public String getJefe_lab() {
		return Jefe_lab;
	}


	public void setJefe_lab(String jefe_lab) {
		Jefe_lab = jefe_lab;
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


	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}


	public int getTest_evaluados() {
		return Test_evaluados;
	}


	public void setTest_evaluados(int test_evaluados) {
		Test_evaluados = test_evaluados;
	}


	public int getTest_aprobados() {
		return Test_aprobados;
	}


	public void setTest_aprobados(int test_aprobados) {
		Test_aprobados = test_aprobados;
	}


	public String getResumen() {
		return Resumen;
	}


	public void setResumen(String resumen) {
		Resumen = resumen;
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
