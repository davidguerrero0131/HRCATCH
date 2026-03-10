package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
@Table(name = "sysmantenimiento")
public class SystemMantenimiento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sysmtto")
	private Long id_Sysmtto;
	
	@Column(name = "numero_reporte")
	private String Numero_reporte;
	
	@Column(name = "fecha")    
    private Date Fecha;

	@Column(name = "entega")
	private boolean Entrega;
	
	@Column(name = "mpsoftware")
	private boolean MPSoftware;
	
	@Column(name = "mphardware")
	private boolean MPHardware;
	
	@Column(name = "dano")
	private boolean Dano;
	
	@Column(name = "tipo_mantenimiento")
	private int Tipo_mantenimiento;
	
	@Column(name = "tipo_falla")
	private int Tipo_falla;
	
	@Column(name = "hora_llamado")
    private Time Hora_llamado;
    
    @Column(name = "hora_inicio")
    private Time Hora_inicio;
    
    @Column(name = "hora_terminacion")
    private Time Hora_terminacion;
    
    @Column(name = "rutinah")
    private String Rutinah;
    
    @Column(name = "rutinas")
    private String Rutinas; 
    
    @Column(name = "observacionesh")
    private String Observacionesh;
    
    @Column(name = "observacioness")
    private String Observacioness;
    
    @Column(name = "rutaentrega")
    private String Rutaentrega;
    
    @Column(name = "rutahardware")
    private String Rutahardware;
    
    @Column(name = "rutasoftware")
    private String Rutasoftware;
    
    @Column(name = "tiempo_fuera_servicio")
    private int Tiempo_fuera_servicio;
    
    @Column(name = "autor_realizado")
    private String Autor_realizado;
    
    @Column(name = "autor_recibido")
    private String Autor_recibido;
	
	@JoinColumn(name ="id_sysequipo_fk",referencedColumnName ="id_Sysequipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private SystemEquipo equipo;
	
	@JoinColumn(name ="id_sysusuario_fk",referencedColumnName ="id_Usuario")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;
	
	/********************* GET Y SET *****************************/
    
    public static long getSerialVersionUID() {
    	return serialVersionUID;
    }

	public Long getId_Sysmtto() {
		return id_Sysmtto;
	}

	public void setId_Sysmtto(Long id_Sysmtto) {
		this.id_Sysmtto = id_Sysmtto;
	}

	public String getNumero_reporte() {
		return Numero_reporte;
	}

	public void setNumero_reporte(String numero_reporte) {
		Numero_reporte = numero_reporte;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public boolean isEntrega() {
		return Entrega;
	}

	public void setEntrega(boolean entrega) {
		Entrega = entrega;
	}

	public boolean isMPSoftware() {
		return MPSoftware;
	}

	public void setMPSoftware(boolean mPSoftware) {
		MPSoftware = mPSoftware;
	}

	public boolean isMPHardware() {
		return MPHardware;
	}

	public void setMPHardware(boolean mPHardware) {
		MPHardware = mPHardware;
	}

	public int getTipo_mantenimiento() {
		return Tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(int tipo_mantenimiento) {
		Tipo_mantenimiento = tipo_mantenimiento;
	}

	public int getTipo_falla() {
		return Tipo_falla;
	}

	public void setTipo_falla(int tipo_falla) {
		Tipo_falla = tipo_falla;
	}

	public Time getHora_llamado() {
		return Hora_llamado;
	}

	public void setHora_llamado(Time hora_llamado) {
		Hora_llamado = hora_llamado;
	}

	public Time getHora_inicio() {
		return Hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		Hora_inicio = hora_inicio;
	}

	public Time getHora_terminacion() {
		return Hora_terminacion;
	}

	public void setHora_terminacion(Time hora_terminacion) {
		Hora_terminacion = hora_terminacion;
	}

	

	public int getTiempo_fuera_servicio() {
		return Tiempo_fuera_servicio;
	}

	public void setTiempo_fuera_servicio(int tiempo_fuera_servicio) {
		Tiempo_fuera_servicio = tiempo_fuera_servicio;
	}

	public String getAutor_realizado() {
		return Autor_realizado;
	}

	public void setAutor_realizado(String autor_realizado) {
		Autor_realizado = autor_realizado;
	}

	public String getAutor_recibido() {
		return Autor_recibido;
	}

	public void setAutor_recibido(String autor_recibido) {
		Autor_recibido = autor_recibido;
	}

	public SystemEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(SystemEquipo equipo) {
		this.equipo = equipo;
	}

	public String getRutinah() {
		return Rutinah;
	}

	public void setRutinah(String rutinah) {
		Rutinah = rutinah;
	}

	public String getRutinas() {
		return Rutinas;
	}

	public void setRutinas(String rutinas) {
		Rutinas = rutinas;
	}

	public String getObservacionesh() {
		return Observacionesh;
	}

	public void setObservacionesh(String observacionesh) {
		Observacionesh = observacionesh;
	}

	public String getObservacioness() {
		return Observacioness;
	}

	public void setObservacioness(String observacioness) {
		Observacioness = observacioness;
	}

	public String getRutaentrega() {
		return Rutaentrega;
	}

	public void setRutaentrega(String rutaentrega) {
		Rutaentrega = rutaentrega;
	}

	public String getRutahardware() {
		return Rutahardware;
	}

	public void setRutahardware(String rutahardware) {
		Rutahardware = rutahardware;
	}

	public String getRutasoftware() {
		return Rutasoftware;
	}

	public void setRutasoftware(String rutasoftware) {
		Rutasoftware = rutasoftware;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDano() {
		return Dano;
	}

	public void setDano(boolean dano) {
		Dano = dano;
	}
	
    
	
}
