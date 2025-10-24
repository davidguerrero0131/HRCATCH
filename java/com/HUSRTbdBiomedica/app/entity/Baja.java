package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "baja")
public class Baja implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_baja")
	private Long id_Baja;
	
	@Column(name = "nombre_baja")
	private String nombre_Baja;
	
	@Column(name = "marca_baja")
	private String Marca_baja;
	
	@Column(name = "modelo_baja")
	private String Modelo_baja;
	
	@Column(name = "serie_baja")
	private String Serie_baja;
	
	@Column(name = "placa_inventario_baja")
	private String Placa_inventario_baja;
	
	@Column(name = "servicio_baja")
	private String Servicio_baja;
	
	@Column(name = "ubicacion_baja")
	private String Ubicacion_baja;
	
	@Column(name = "ubicacion_especifica_baja")
	private String Ubicacion_especifica_baja;
	
	@Column(name = "ano_ingreso_baja")
	private int Ano_ingreso_baja;
	
	@Column(name = "codigo")
	private String Codigo;
	
	@Column(name = "causa")
	private String Causa;
	
	@Column(name = "guia")
	private String Guia;
	
	
	@JoinColumn(name = "id_hospital_FK",referencedColumnName="id_Hospital")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private Hospital hospital;
	
	
	@JoinColumn(name ="id_hoja_vida_fk",referencedColumnName ="id_Hoja_vida")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Hoja_vida hoja_vida;	

	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}	
	
	public Long getId_Baja() {
		return id_Baja;
	}

	public void setId_Baja(Long id_Baja) {
		this.id_Baja = id_Baja;
	}

	public String getNombre_Baja() {
		return nombre_Baja;
	}

	public void setNombre_Baja(String nombre_Baja) {
		this.nombre_Baja = nombre_Baja;
	}

	public String getMarca_baja() {
		return Marca_baja;
	}

	public void setMarca_baja(String marca_baja) {
		Marca_baja = marca_baja;
	}

	public String getModelo_baja() {
		return Modelo_baja;
	}

	public void setModelo_baja(String modelo_baja) {
		Modelo_baja = modelo_baja;
	}

	public String getSerie_baja() {
		return Serie_baja;
	}

	public void setSerie_baja(String serie_baja) {
		Serie_baja = serie_baja;
	}

	public String getPlaca_inventario_baja() {
		return Placa_inventario_baja;
	}

	public void setPlaca_inventario_baja(String placa_inventario_baja) {
		Placa_inventario_baja = placa_inventario_baja;
	}

	public String getServicio_baja() {
		return Servicio_baja;
	}

	public void setServicio_baja(String servicio_baja) {
		Servicio_baja = servicio_baja;
	}

	public String getUbicacion_baja() {
		return Ubicacion_baja;
	}

	public void setUbicacion_baja(String ubicacion_baja) {
		Ubicacion_baja = ubicacion_baja;
	}

	public String getUbicacion_especifica_baja() {
		return Ubicacion_especifica_baja;
	}

	public void setUbicacion_especifica_baja(String ubicacion_especifica_baja) {
		Ubicacion_especifica_baja = ubicacion_especifica_baja;
	}

	public int getAno_ingreso_baja() {
		return Ano_ingreso_baja;
	}

	public void setAno_ingreso_baja(int ano_ingreso_baja) {
		Ano_ingreso_baja = ano_ingreso_baja;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getCausa() {
		return Causa;
	}

	public void setCausa(String causa) {
		Causa = causa;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


	public Hoja_vida getHoja_vida() {
		return hoja_vida;
	}

	public void setHoja_vida(Hoja_vida hoja_vida) {
		this.hoja_vida = hoja_vida;
	}

	public String getGuia() {
		return Guia;
	}

	public void setGuia(String guia) {
		Guia = guia;
	}

	
	
	
	
	
}
