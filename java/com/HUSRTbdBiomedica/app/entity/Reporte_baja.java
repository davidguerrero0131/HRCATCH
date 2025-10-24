package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reporte_baja")
public class Reporte_baja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reporte_baja")
	private Long id_Reporte_baja;
	
	@Column(name = "nombre_equipo_baja")
	private String Nombre_equipo_baja;
	
	@Column(name = "numero_reporte_baja")
	private String Numero_reporte_baja;
	
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

	@Column(name = "fecha_baja")    
    private Date Fecha_baja;
    
    @Column(name = "hora_llamado_baja")
    private Time Hora_llamado_baja;
    
    @Column(name = "hora_inicio_baja")
    private Time Hora_inicio_baja;
    
    @Column(name = "hora_terminacion_baja")
    private Time Hora_terminacion_baja;

    @Column(name = "total_horas_baja")
    private Time Total_horas_baja;
    
    @Column(name = "tipo_mantenimiento_baja")
    private int Tipo_mantenimiento_baja;
    
    @Column(name = "tipo_falla_baja")
    private String Tipo_falla_baja;
    
    @Column(name = "trabajo_realizado_baja")
    private String Trabajo_realizado_baja;
    
    @Column(name = "repuesto_cambiado_baja")
    private String Repuesto_cambiado_baja;
    
    @Column(name = "comprobante_egreso_baja")
    private String Comprobante_egreso_baja;
    
    @Column(name = "autor_realizado_baja")
    private String Autor_realizado_baja;
    
    @Column(name = "autor_recibido_baja")
    private String Autor_recibido_baja;
    
    @Column(name = "observaciones_baja")
    private String Observaciones_baja;
    
    @Column(name = "comodato_baja")
    private boolean Comodato_baja;
    
    @Column(name = "mtto_propio_baja")
    private boolean Mtto_propio_baja;
    
    @Column(name = "tiempo_fuera_servicio_baja")
    private int Tiempo_fuera_servicio_baja;
    
    @Column(name = "rutapdf_baja")
    private String Rutapdf_baja;
    
    @Column(name = "motivo_baja")
    private String Motivo_baja;
    
    @JoinColumn(name ="id_baja_fk",referencedColumnName ="id_Baja")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Baja baja;
 
    @JoinColumn(name ="id_mantenimiento_preventivo_fk",referencedColumnName ="id_Mantenimiento_preventivo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Mantenimiento_preventivo mantenimiento_preventivo;

    /********************* GET Y SET *****************************/
    public static long getSerialVersionUID() {
    	return serialVersionUID;
    }
    
	public Long getId_Reporte_baja() {
		return id_Reporte_baja;
	}

	public void setId_Reporte_baja(Long id_Reporte_baja) {
		this.id_Reporte_baja = id_Reporte_baja;
	}

	public String getNombre_equipo_baja() {
		return Nombre_equipo_baja;
	}

	public void setNombre_equipo_baja(String nombre_equipo_baja) {
		Nombre_equipo_baja = nombre_equipo_baja;
	}

	public String getNumero_reporte_baja() {
		return Numero_reporte_baja;
	}

	public void setNumero_reporte_baja(String numero_reporte_baja) {
		Numero_reporte_baja = numero_reporte_baja;
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

	public Date getFecha_baja() {
		return Fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		Fecha_baja = fecha_baja;
	}

	public Time getHora_llamado_baja() {
		return Hora_llamado_baja;
	}

	public void setHora_llamado_baja(Time hora_llamado_baja) {
		Hora_llamado_baja = hora_llamado_baja;
	}

	public Time getHora_inicio_baja() {
		return Hora_inicio_baja;
	}

	public void setHora_inicio_baja(Time hora_inicio_baja) {
		Hora_inicio_baja = hora_inicio_baja;
	}

	public Time getHora_terminacion_baja() {
		return Hora_terminacion_baja;
	}

	public void setHora_terminacion_baja(Time hora_terminacion_baja) {
		Hora_terminacion_baja = hora_terminacion_baja;
	}

	public Time getTotal_horas_baja() {
		return Total_horas_baja;
	}

	public void setTotal_horas_baja(Time total_horas_baja) {
		Total_horas_baja = total_horas_baja;
	}

	public int getTipo_mantenimiento_baja() {
		return Tipo_mantenimiento_baja;
	}

	public void setTipo_mantenimiento_baja(int tipo_mantenimiento_baja) {
		Tipo_mantenimiento_baja = tipo_mantenimiento_baja;
	}

	public String getTipo_falla_baja() {
		return Tipo_falla_baja;
	}

	public void setTipo_falla_baja(String tipo_falla_baja) {
		Tipo_falla_baja = tipo_falla_baja;
	}

	public String getTrabajo_realizado_baja() {
		return Trabajo_realizado_baja;
	}

	public void setTrabajo_realizado_baja(String trabajo_realizado_baja) {
		Trabajo_realizado_baja = trabajo_realizado_baja;
	}

	public String getRepuesto_cambiado_baja() {
		return Repuesto_cambiado_baja;
	}

	public void setRepuesto_cambiado_baja(String repuesto_cambiado_baja) {
		Repuesto_cambiado_baja = repuesto_cambiado_baja;
	}

	public String getComprobante_egreso_baja() {
		return Comprobante_egreso_baja;
	}

	public void setComprobante_egreso_baja(String comprobante_egreso_baja) {
		Comprobante_egreso_baja = comprobante_egreso_baja;
	}

	public String getAutor_realizado_baja() {
		return Autor_realizado_baja;
	}

	public void setAutor_realizado_baja(String autor_realizado_baja) {
		Autor_realizado_baja = autor_realizado_baja;
	}

	public String getAutor_recibido_baja() {
		return Autor_recibido_baja;
	}

	public void setAutor_recibido_baja(String autor_recibido_baja) {
		Autor_recibido_baja = autor_recibido_baja;
	}

	public String getObservaciones_baja() {
		return Observaciones_baja;
	}

	public void setObservaciones_baja(String observaciones_baja) {
		Observaciones_baja = observaciones_baja;
	}

	public boolean isComodato_baja() {
		return Comodato_baja;
	}

	public void setComodato_baja(boolean comodato_baja) {
		Comodato_baja = comodato_baja;
	}

	public boolean isMtto_propio_baja() {
		return Mtto_propio_baja;
	}

	public void setMtto_propio_baja(boolean mtto_propio_baja) {
		Mtto_propio_baja = mtto_propio_baja;
	}

	public Baja getBaja() {
		return baja;
	}

	public void setBaja(Baja baja) {
		this.baja = baja;
	}

	public Mantenimiento_preventivo getMantenimiento_preventivo() {
		return mantenimiento_preventivo;
	}

	public void setMantenimiento_preventivo(Mantenimiento_preventivo mantenimiento_preventivo) {
		this.mantenimiento_preventivo = mantenimiento_preventivo;
	}

	public int getTiempo_fuera_servicio_baja() {
		return Tiempo_fuera_servicio_baja;
	}

	public void setTiempo_fuera_servicio_baja(int tiempo_fuera_servicio_baja) {
		Tiempo_fuera_servicio_baja = tiempo_fuera_servicio_baja;
	}

	public String getRutapdf_baja() {
		return Rutapdf_baja;
	}

	public void setRutapdf_baja(String rutapdf_baja) {
		Rutapdf_baja = rutapdf_baja;
	}

	public String getMotivo_baja() {
		return Motivo_baja;
	}

	public void setMotivo_baja(String motivo_baja) {
		Motivo_baja = motivo_baja;
	}
    
    
    
    
}
