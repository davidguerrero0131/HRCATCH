package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mantenimiento_msv")
public class MantenimientoMSV implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_MantenimientoMSV")
	private Long id_MantenimientoMSV;

	@Column(name = "v65medidoSPO2")
	private Double v65medidoSPO2;
	
	@Column(name = "v75medidoSPO2")
	private Double v75medidoSPO2;
	
	@Column(name = "v100medidoSPO2")
	private Double v100medidoSPO2;
	
	@Column(name = "v45medidoECG")
	private Double v45medidoECG;
	
	@Column(name = "v60medidoECG")
	private Double v60medidoECG;
	
	@Column(name = "v120medidoECG")
	private Double v120medidoECG;
	
	@Column(name = "v180medidoECG")
	private Double v180medidoECG;
	
	@Column(name = "v120medidoSistolica")
	private Double v120medidoSistolica;
	
	@Column(name = "v80medidoDiastolica")
	private Double v80medidoDiastolica;
	
	@Column(name = "v92medidoMedia")
	private Double v92medidoMedia;
	
	@Column(name = "v200medidoSistolica")
	private Double v200medidoSistolica;
	
	@Column(name = "v150medidoDiastolica")
	private Double v150medidoDiastolica;
	
	@Column(name = "v167medidoMedia")
	private Double v167medidoMedia;
	
	@Column(name = "v60medidoSistolica")
	private Double v60medidoSistolica;
	
	@Column(name = "v30medidoDiastolica")
	private Double v30medidoDiastolica;
	
	@Column(name = "v40medidoMedia")
	private Double v40medidoMedia;
	
	@Column(name = "v80medidoSistolica")
	private Double v80medidoSistolica;
	
	@Column(name = "v50medidoDiastolica")
	private Double v50medidoDiastolica;
	
	@Column(name = "v60medidoMedia")
	private Double v60medidoMedia;
	
	@JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
	
	@JoinColumn(name ="id_mantenimeinto_preventivo_fk",referencedColumnName ="id_Mantenimiento_preventivo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Mantenimiento_preventivo mantenimiento;
	

	
	public Long getId_MantenimientoMSV() {
		return id_MantenimientoMSV;
	}

	public void setId_MantenimientoMSV(Long id_MantenimientoMSV) {
		this.id_MantenimientoMSV = id_MantenimientoMSV;
	}

	public Double getV65medidoSPO2() {
		return v65medidoSPO2;
	}

	public void setV65medidoSPO2(Double v65medidoSPO2) {
		this.v65medidoSPO2 = v65medidoSPO2;
	}

	public Double getV75medidoSPO2() {
		return v75medidoSPO2;
	}

	public void setV75medidoSPO2(Double v75medidoSPO2) {
		this.v75medidoSPO2 = v75medidoSPO2;
	}

	public Double getV100medidoSPO2() {
		return v100medidoSPO2;
	}

	public void setV100medidoSPO2(Double v100medidoSPO2) {
		this.v100medidoSPO2 = v100medidoSPO2;
	}

	public Double getV45medidoECG() {
		return v45medidoECG;
	}

	public void setV45medidoECG(Double v45medidoECG) {
		this.v45medidoECG = v45medidoECG;
	}

	public Double getV60medidoECG() {
		return v60medidoECG;
	}

	public void setV60medidoECG(Double v60medidoECG) {
		this.v60medidoECG = v60medidoECG;
	}

	public Double getV120medidoECG() {
		return v120medidoECG;
	}

	public void setV120medidoECG(Double v120medidoECG) {
		this.v120medidoECG = v120medidoECG;
	}

	public Double getV180medidoECG() {
		return v180medidoECG;
	}

	public void setV180medidoECG(Double v180medidoECG) {
		this.v180medidoECG = v180medidoECG;
	}

	public Double getV120medidoSistolica() {
		return v120medidoSistolica;
	}

	public void setV120medidoSistolica(Double v120medidoSistolica) {
		this.v120medidoSistolica = v120medidoSistolica;
	}

	public Double getV80medidoDiastolica() {
		return v80medidoDiastolica;
	}

	public void setV80medidoDiastolica(Double v80medidoDiastolica) {
		this.v80medidoDiastolica = v80medidoDiastolica;
	}

	public Double getV92medidoMedia() {
		return v92medidoMedia;
	}

	public void setV92medidoMedia(Double v92medidoMedia) {
		this.v92medidoMedia = v92medidoMedia;
	}

	public Double getV200medidoSistolica() {
		return v200medidoSistolica;
	}

	public void setV200medidoSistolica(Double v200medidoSistolica) {
		this.v200medidoSistolica = v200medidoSistolica;
	}

	public Double getV150medidoDiastolica() {
		return v150medidoDiastolica;
	}

	public void setV150medidoDiastolica(Double v150medidoDiastolica) {
		this.v150medidoDiastolica = v150medidoDiastolica;
	}

	public Double getV167medidoMedia() {
		return v167medidoMedia;
	}

	public void setV167medidoMedia(Double v167medidoMedia) {
		this.v167medidoMedia = v167medidoMedia;
	}

	public Double getV60medidoSistolica() {
		return v60medidoSistolica;
	}

	public void setV60medidoSistolica(Double v60medidoSistolica) {
		this.v60medidoSistolica = v60medidoSistolica;
	}

	public Double getV30medidoDiastolica() {
		return v30medidoDiastolica;
	}

	public void setV30medidoDiastolica(Double v30medidoDiastolica) {
		this.v30medidoDiastolica = v30medidoDiastolica;
	}

	public Double getV40medidoMedia() {
		return v40medidoMedia;
	}

	public void setV40medidoMedia(Double v40medidoMedia) {
		this.v40medidoMedia = v40medidoMedia;
	}

	public Double getV80medidoSistolica() {
		return v80medidoSistolica;
	}

	public void setV80medidoSistolica(Double v80medidoSistolica) {
		this.v80medidoSistolica = v80medidoSistolica;
	}

	public Double getV50medidoDiastolica() {
		return v50medidoDiastolica;
	}

	public void setV50medidoDiastolica(Double v50medidoDiastolica) {
		this.v50medidoDiastolica = v50medidoDiastolica;
	}

	public Double getV60medidoMedia() {
		return v60medidoMedia;
	}

	public void setV60medidoMedia(Double v60medidoMedia) {
		this.v60medidoMedia = v60medidoMedia;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Mantenimiento_preventivo getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(Mantenimiento_preventivo mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
}
