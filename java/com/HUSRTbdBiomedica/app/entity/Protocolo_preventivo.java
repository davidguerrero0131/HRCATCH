package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "protocolo_preventivo")
public class Protocolo_preventivo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_protocolo_preventivo")
	private Long id_Procolo_preventivo;
	
	@Column(name = "paso")
	private String Paso;
	
	@Column(name = "cumplimiento")
	private boolean Cumplimiento;
	
	
	@JoinColumn(name ="id_tipo_equipo",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="id_mantenimiento_preventivo",referencedColumnName ="id_Mantenimiento_preventivo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Mantenimiento_preventivo mantenimiento_preventivo;

	/********************* GET Y SET *****************************/
	
	public Long getId_Procolo_preventivo() {
		return id_Procolo_preventivo;
	}

	public void setId_Procolo_preventivo(Long id_Procolo_preventivo) {
		this.id_Procolo_preventivo = id_Procolo_preventivo;
	}

	public String getPaso() {
		return Paso;
	}

	public void setPaso(String paso) {
		Paso = paso;
	}

	public boolean isCumplimiento() {
		return Cumplimiento;
	}

	public void setCumplimiento(boolean cumplimiento) {
		Cumplimiento = cumplimiento;
	}

	public Tipo_equipo getTipo_equipo() {
		return tipo_equipo;
	}

	public void setTipo_equipo(Tipo_equipo tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}

	public Mantenimiento_preventivo getMantenimiento_preventivo() {
		return mantenimiento_preventivo;
	}

	public void setMantenimiento_preventivo(Mantenimiento_preventivo mantenimiento_preventivo) {
		this.mantenimiento_preventivo = mantenimiento_preventivo;
	}
	
	
	
	
	
	
}
