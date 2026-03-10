package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "reporte")
public class Reporte implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reporte")
	private Long id_Reporte;
	
	@Column(name = "nombre_equipo")
	private String Nombre_equipo;
	
	@Column(name = "numero_reporte")
	private String Numero_reporte;
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "placa_inventario")
	private String Placa_inventario;
	
	@Column(name = "servicio")
	private String Servicio;
	
	@Column(name = "ubicacion")
	private String Ubicacion;

	@Column(name = "fecha")    
    private Date Fecha;
    
    @Column(name = "hora_llamado")
    private Time Hora_llamado;
    
    @Column(name = "hora_inicio")
    private Time Hora_inicio;
    
    @Column(name = "hora_terminacion")
    private Time Hora_terminacion;

    @Column(name = "total_horas")
    private Time Total_horas;
    
    @Column(name = "tipo_mantenimiento")
    private int Tipo_mantenimiento;
    
    @Column(name = "tipo_falla")
    private String Tipo_falla;
    
    @Column(name = "trabajo_realizado")
    private String Trabajo_realizado;
    
    @Column(name = "repuesto_cambiado")
    private String Repuesto_cambiado;
    
    @Column(name = "comprobante_ingreso")
    private String Comprobante_ingreso;
    
    @Column(name = "autor_realizado")
    private String Autor_realizado;
    
    @Column(name = "autor_recibido")
    private String Autor_recibido;
    
    @Column(name = "observaciones")
    private String Observaciones;
    
    @Column(name = "comodato")
    private boolean Comodato;
    
    @Column(name = "mtto_propio")
    private boolean Mtto_propio;
    
    @Column(name = "tiempo_fuera_servicio")
    private int Tiempo_fuera_servicio;
    
    @Column(name = "rutapdf")
    private String Rutapdf;
    
    @Column(name = "motivo")
    private String Motivo;
    
    @JoinColumn(name ="id_equipo_fk",referencedColumnName ="id_Equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;
 
    
    /********************* GET Y SET *****************************/
    
    public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
    public String sumtoString(Long id_Reporte) {
    	String numr = Long.toString(id_Reporte+20000);
    	
    	
    	return numr;
    }
    

	@Override
	public String toString() {
		return "Reporte [id_Reporte=" + id_Reporte + "]";
	}
	public Long getId_Reporte() {
		return id_Reporte;
	}

	public void setId_Reporte(Long id_Reporte) {
		this.id_Reporte = id_Reporte;
	}

	public String getNumero_reporte() {
		return Numero_reporte;
	}

	public void setNumero_reporte(String numero_reporte) {
		Numero_reporte = numero_reporte;
	}

	
	public boolean isMtto_propio() {
		return Mtto_propio;
	}
	public void setMtto_propio(boolean mtto_propio) {
		Mtto_propio = mtto_propio;
	}
	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
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

	public Time getTotal_horas() {
		return Total_horas;
	}

	public void setTotal_horas(Time total_horas) {
		Total_horas = total_horas;
	}

	public int getTipo_mantenimiento() {
		return Tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(int tipo_mantenimiento) {
		Tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getTipo_falla() {
		return Tipo_falla;
	}

	public void setTipo_falla(String tipo_falla) {
		Tipo_falla = tipo_falla;
	}

	public String getTrabajo_realizado() {
		return Trabajo_realizado;
	}

	public void setTrabajo_realizado(String trabajo_realizado) {
		Trabajo_realizado = trabajo_realizado;
	}

	public String getRepuesto_cambiado() {
		return Repuesto_cambiado;
	}

	public void setRepuesto_cambiado(String repuesto_cambiado) {
		Repuesto_cambiado = repuesto_cambiado;
	}

	public String getComprobante_ingreso() {
		return Comprobante_ingreso;
	}

	public void setComprobante_ingreso(String comprobante_ingreso) {
		Comprobante_ingreso = comprobante_ingreso;
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

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getNombre_equipo() {
		return Nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		Nombre_equipo = nombre_equipo;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getSerie() {
		return Serie;
	}

	public void setSerie(String serie) {
		Serie = serie;
	}

	public String getPlaca_inventario() {
		return Placa_inventario;
	}

	public void setPlaca_inventario(String placa_inventario) {
		Placa_inventario = placa_inventario;
	}

	public String getServicio() {
		return Servicio;
	}

	public void setServicio(String servicio) {
		Servicio = servicio;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public boolean isComodato() {
		return Comodato;
	}

	public void setComodato(boolean comodato) {
		Comodato = comodato;
	}
	public int getTiempo_fuera_servicio() {
		return Tiempo_fuera_servicio;
	}
	public void setTiempo_fuera_servicio(int tiempo_fuera_servicio) {
		Tiempo_fuera_servicio = tiempo_fuera_servicio;
	}
	public String getRutapdf() {
		return Rutapdf;
	}
	public void setRutapdf(String rutapdf) {
		Rutapdf = rutapdf;
	}
	public String getMotivo() {
		return Motivo;
	}
	public void setMotivo(String motivo) {
		Motivo = motivo;
	}
	

	
    
}
