package com.HUSRTbdBiomedica.app.entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "criterios_economicos")
public class Criterios_economicos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_criterios_economicos")
	private Long id_Criterios_economicos;
	
	@Column(name = "valor_neto_actual")
	private int Valor_neto_actual;
	
	@Column(name = "costos_operacion")
	private int Costos_operacion;
	
	@Column(name = "costos_mantenimiento")
	private int Costos_mantenimiento;
	
	@Column(name = "costos_otros")
	private int Costos_otros;
	
	@Column(name = "total_puntos_economicos")
	private int Total_puntos_economicos;
	
	/********************* GET Y SET *****************************/
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public Long getId_Criterios_economicos(){
		return id_Criterios_economicos;
	}
	public void setId_Criterios_economicos(Long id_Criterios_economicos){
		this.id_Criterios_economicos = id_Criterios_economicos;
	}
	public int getValor_neto_actual() {
		return Valor_neto_actual;
	}
	public void setValor_neto_actual(int Valor_neto_actual) {
		this.Valor_neto_actual = Valor_neto_actual;
	}
	public int getCostos_operacion() {
		return Costos_operacion;
	}
	public void setCostos_operacion(int Costos_operacion) {
		this.Costos_operacion = Costos_operacion;
	}
	public int getCostos_mantenimiento() {
		return Costos_mantenimiento;
	}
	public void setCostos_mantenimiento(int Costos_mantenimiento) {
		this.Costos_mantenimiento = Costos_mantenimiento;
	}
	public int getCostos_otros() {
		return Costos_otros;
	}
	public void setCostos_otros(int Costos_otros) {
		this.Costos_otros = Costos_otros;
	}
	public int getTotal_puntos_economicos() {
		return Total_puntos_economicos;
	}
	public void setTotal_puntos_economicos(int Total_puntos_economicos) {
		this.Total_puntos_economicos = Total_puntos_economicos;
	}
	
}