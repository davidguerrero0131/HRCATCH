package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Entity
@Table(name = "tipo_equipo")
public class Tipo_equipo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_equipo")
	private Long id_Tipo_equipo;
	
	@NotNull
	@Column(name = "nombre_tipo_equipo")
	private String nombre_Tipo_equipo;
	
	@Column(name  = "cantidad_equipo")
	private int Cantidad_equipo;
	
	@Column(name = "material_consumible")
	private String Material_consumible;
	
	@Column(name = "herramienta")
	private String Herramienta;
	
	@Column(name = "tiempo_minutos")
	private int Tiempo_minutos;
	
	@Column(name = "repuestos_minimos")
	private String Repuestos_minimos;
		
	@Column(name = "tipo")
	private int Tipo;
	
	@Column(name = "actividad")
	private String Actividad;
	
	/****************GET Y SET ***************/
	
	
	public Long getId_Tipo_equipo() {
		return id_Tipo_equipo;
	}
	public void setId_Tipo_equipo(Long id_Tipo_equipo) {
		this.id_Tipo_equipo = id_Tipo_equipo;
	}
	
	public String getNombre_Tipo_equipo() {
		return nombre_Tipo_equipo;
	}
	public void setNombre_Tipo_equipo(String nombre_Tipo_equipo) {
		this.nombre_Tipo_equipo = nombre_Tipo_equipo;
	}
	public int getCantidad_equipo() {
		return Cantidad_equipo;
	}
	public void setCantidad_equipo(int Cantidad_equipo) {
		this.Cantidad_equipo = Cantidad_equipo;
	}
	public String getMaterial_consumible() {
		return Material_consumible;
	}
	public void setMaterial_consumible(String material_consumible) {
		Material_consumible = material_consumible;
	}
	public String getHerramienta() {
		return Herramienta;
	}
	public void setHerramienta(String herramienta) {
		Herramienta = herramienta;
	}
	public int getTiempo_minutos() {
		return Tiempo_minutos;
	}
	public void setTiempo_minutos(int tiempo_minutos) {
		Tiempo_minutos = tiempo_minutos;
	}
	public String getRepuestos_minimos() {
		return Repuestos_minimos;
	}
	public void setRepuestos_minimos(String repuestos_minimos) {
		Repuestos_minimos = repuestos_minimos;
	}
	public int getTipo() {
		return Tipo;
	}
	public void setTipo(int tipo) {
		Tipo = tipo;
	}
	public String getActividad() {
		return Actividad;
	}
	public void setActividad(String actividad) {
		Actividad = actividad;
	}	
	
	
}