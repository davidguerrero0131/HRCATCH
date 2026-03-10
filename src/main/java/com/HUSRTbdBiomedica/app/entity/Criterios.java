package com.HUSRTbdBiomedica.app.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "criterios")
public class Criterios implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_criterios")
	private Long id_Criterios;
	
	@Column(name = "total_puntos")
	private int Total_puntos;
	
	@Column(name = "fecha")
	private Date Fecha; 
	
    @JoinColumn(name ="equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
    
    @JoinColumn(name ="criterios_tecnicos_fk",referencedColumnName ="id_Criterios_tecnicos")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Criterios_tecnicos criterios_tecnicos;
    
    @JoinColumn(name ="criterios_economicos_fk",referencedColumnName ="id_Criterios_economicos")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Criterios_economicos criterios_economicos;
    
    @JoinColumn(name ="criterios_especificos_fk",referencedColumnName ="id_Criterios_especificos")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Criterios_especificos criterios_especificos;
    
    
    /********************* GET Y SET *****************************/   
    public static Long getSerialVersionUID() {
    	return serialVersionUID;
    }
    public Long getId_Criterios() {
    	return id_Criterios;
    }
    public void setId_Criterios(Long id_Criterios) {
    	this.id_Criterios = id_Criterios;
    }
    
    public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public int getTotal_puntos() {
    	return Total_puntos;
    }
    public void setTotal_puntos(int Total_puntos) {
    	this.Total_puntos = Total_puntos;
    }
    public Criterios_tecnicos getCriterios_tecnicos() {
    	return criterios_tecnicos;
    }
    public void setCriterios_tecnicos(Criterios_tecnicos criterios_tecnicos) {
    	this.criterios_tecnicos = criterios_tecnicos; 
    }
    public Criterios_economicos getCriterios_economicos() {
    	return criterios_economicos;
    }
    public void setCriterios_economicos(Criterios_economicos criterios_economicos) {
    	this.criterios_economicos = criterios_economicos;
    }
    public Criterios_especificos getCriterios_especificos() {
    	return criterios_especificos;
    }
    public void setCriterios_especificos(Criterios_especificos criterios_especificos) {
    	this.criterios_especificos = criterios_especificos;
    }
    
}