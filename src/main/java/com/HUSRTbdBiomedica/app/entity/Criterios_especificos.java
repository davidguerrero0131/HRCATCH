package com.HUSRTbdBiomedica.app.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "criterios_especificos")
public class Criterios_especificos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_criterios_especificos")
	private Long id_Criterios_especificos;
	
	@Column(name = "tipo_danos")
	private int Tipo_danos;
	
	@Column(name = "accesorios")
	private int Accesorios;
	
	@Column(name = "uso_personal")
	private int Uso_personal;
	
	@Column(name = "otros_tipo_equipo")
	private int Otros_tipo_equipo;
	
	@Column(name = "total_puntos_economicos")
	private int Total_puntos_Economicos;
	
	/********************* GET Y SET *****************************/
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public Long getId_Criterios_especificos() {
		return id_Criterios_especificos;
	}
	public void setId_Criterios_especificos(Long id_Criterios_especificos) {
		this.id_Criterios_especificos = id_Criterios_especificos;
	}
	public int getTipo_danos() {
		return Tipo_danos;
	}
	public void setTipo_danos(int Tipo_danos) {
		this.Tipo_danos = Tipo_danos;
	}
	public int getAccesorios() {
		return Accesorios;
	}
	public void setAccesorios(int Accesorios) {
		this.Accesorios = Accesorios;
	}
	public int getUso_personal() {
		return Uso_personal;
	}
	public void setUso_personal(int Uso_personal) {
		this.Uso_personal = Uso_personal;
	}
	public int getOtros_tipo_equipo() {
		return Otros_tipo_equipo;
	}
	public void setOtros_tipo_equipo(int Otros_tipo_equipo) {
		this.Otros_tipo_equipo = Otros_tipo_equipo;
	}
	public int getTotal_puntos_economicos() {
		return Total_puntos_Economicos;
	}
	public void setTotal_puntos_economicos(int Total_puntos_Economicos) {
		this.Total_puntos_Economicos = Total_puntos_Economicos;
	}

} 
