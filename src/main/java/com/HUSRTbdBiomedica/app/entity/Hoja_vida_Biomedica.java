package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hoja_vida_Biomedica")
public class Hoja_vida_Biomedica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_hoja_vida_Biomedica")
	private Long id_Hoja_vida;
	
	@Column(name = "ano_fabricacion")
	private int Ano_fabricacion;
	
	@Column(name = "codinternacional")
	private String Codinternacional;
	
	@Column(name="contrato")
	private String Contrato;
	
	@Column(name = "forma_adquisicion_fk")
	private int formaAdquisicion;
	
	@Column(name = "fecha_compra")
    private Date Fecha_compra;
	
	@Column(name = "fecha_instalacion")
    private Date Fecha_instalacion;
	
	@Column(name = "fecha_iniciooperacion")
    private Date Fecha_iniciooperacion;
	
	@Column(name = "fecha_vencimientogarantia")
    private Date Fecha_vencimientogarantia;
	
	@Column(name="costo_compra")
	private String Costo_compra;
	
	@Column(name="registro_invima")
	private String Registro_invima;
	
	@Column(name="fabricante")
	private String Fabricante;
	
	@Column(name="paisfabricante")
	private String Paisfabricante;
	
	@Column(name="id_proveedor_fk")
	private int proveedor;
	
	@Column(name="vmaxoperacion")
	private String Vmaxoperacion;
	
	@Column(name = "vminoperacion")
	private String Vminoperacion;
	
	@Column(name = "imaxoperacion")
	private String Imaxoperacion;
	
	@Column(name = "iminoperacion")
	private String Iminoperacion;
	
	@Column(name="wconsumida")
	private String Wconsumida;
	
	@Column(name="frecuencia")
	private String Frecuencia;
	
	@Column(name="presion")
	private String Presion;
	
	@Column(name="velocidad")
	private String Velocidad;
	
	@Column(name="temperatura")
	private String Temperatura;
	
	@Column(name="peso")
	private String Peso;
	
	@Column(name="capacidad")
	private String Capacidad;
	
	@Column(name="otrosdatostecnicos")
	private String Otrosdatostecnicos;
	
	@Column(name="fuente_alimentacion_fk")
	private int fuenteAlimentacion;
	
	@Column(name="equipotipofijo")
	private boolean Equipotipofijo;

	@Column(name="equipotipoportatil")
	private boolean Equipotipoportatil;
	
	@Column(name="tipo_uso")
	private char tipoUso;
	
	@Column(name="riesgo_fk")
	private int riesgo;
	
	@Column(name="clase_tecnologia_fk")
	private int claseTecnologia;
	
	@Column(name="clasificacion_biomedica_fk")
	private int clasificacionBiomedica;
	
	@Column(name="tipo_mantenimiento_actual_fk")
	private int tipoMantenimientoActual;
	
	@Column(name="id_propietario_fk")
	private int propiedad;
	
	@Column(name="manual_operacion")
	private boolean Manual_operacion;
	
	@Column(name="manual_tecnico")
	private boolean Manual_tecnico;
	
	@Column(name="requierecalibracion")
	private boolean requiereCalibracion;
	
	@Column(name="periodocalibracion")
	private int periodoCalibracion;
	
	@Column(name ="accesorio1")
	private String Accesorio1;
	
	@Column(name="accesorio2")
	private String Accesorio2;
	
	@Column(name="accesorio3")
	private String Accesorio3;
	
	@Column(name="accesorio4")
	private String Accesorio4;
	
	@Column(name="foto")
	private String Foto;
	
	@Column(name = "observaciones")
	private String Observaciones;
	
	@JoinColumn(name ="ID_equipo_FK",referencedColumnName ="id_Equipo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Equipo equipo;

	public Long getId_Hoja_vida() {
		return id_Hoja_vida;
	}

	public void setId_Hoja_vida(Long id_Hoja_vida) {
		this.id_Hoja_vida = id_Hoja_vida;
	}

	public int getAno_fabricacion() {
		return Ano_fabricacion;
	}

	public void setAno_fabricacion(int ano_fabricacion) {
		Ano_fabricacion = ano_fabricacion;
	}

	public String getCodinternacional() {
		return Codinternacional;
	}

	public void setCodinternacional(String codinternacional) {
		Codinternacional = codinternacional;
	}

	public String getContrato() {
		return Contrato;
	}

	public void setContrato(String contrato) {
		Contrato = contrato;
	}

	public int getFormaAdquisicion() {
		return formaAdquisicion;
	}

	public void setFormaAdquisicion(int formaAdquisicion) {
		this.formaAdquisicion = formaAdquisicion;
	}

	public Date getFecha_compra() {
		return Fecha_compra;
	}

	public void setFecha_compra(Date fecha_compra) {
		Fecha_compra = fecha_compra;
	}

	public Date getFecha_instalacion() {
		return Fecha_instalacion;
	}

	public void setFecha_instalacion(Date fecha_instalacion) {
		Fecha_instalacion = fecha_instalacion;
	}

	public Date getFecha_iniciooperacion() {
		return Fecha_iniciooperacion;
	}

	public void setFecha_iniciooperacion(Date fecha_iniciooperacion) {
		Fecha_iniciooperacion = fecha_iniciooperacion;
	}

	public Date getFecha_vencimientogarantia() {
		return Fecha_vencimientogarantia;
	}

	public void setFecha_vencimientogarantia(Date fecha_vencimientogarantia) {
		Fecha_vencimientogarantia = fecha_vencimientogarantia;
	}

	public String getCosto_compra() {
		return Costo_compra;
	}

	public void setCosto_compra(String costo_compra) {
		Costo_compra = costo_compra;
	}

	public String getRegistro_invima() {
		return Registro_invima;
	}

	public void setRegistro_invima(String registro_invima) {
		Registro_invima = registro_invima;
	}

	public String getFabricante() {
		return Fabricante;
	}

	public void setFabricante(String fabricante) {
		Fabricante = fabricante;
	}

	public String getPaisfabricante() {
		return Paisfabricante;
	}

	public void setPaisfabricante(String paisfabricante) {
		Paisfabricante = paisfabricante;
	}

	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public String getVmaxoperacion() {
		return Vmaxoperacion;
	}

	public void setVmaxoperacion(String vmaxoperacion) {
		Vmaxoperacion = vmaxoperacion;
	}

	public String getVminoperacion() {
		return Vminoperacion;
	}

	public void setVminoperacion(String vminoperacion) {
		Vminoperacion = vminoperacion;
	}

	public String getImaxoperacion() {
		return Imaxoperacion;
	}

	public void setImaxoperacion(String imaxoperacion) {
		Imaxoperacion = imaxoperacion;
	}

	public String getIminoperacion() {
		return Iminoperacion;
	}

	public void setIminoperacion(String iminoperacion) {
		Iminoperacion = iminoperacion;
	}

	public String getWconsumida() {
		return Wconsumida;
	}

	public void setWconsumida(String wconsumida) {
		Wconsumida = wconsumida;
	}

	public String getFrecuencia() {
		return Frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		Frecuencia = frecuencia;
	}

	public String getPresion() {
		return Presion;
	}

	public void setPresion(String presion) {
		Presion = presion;
	}

	public String getVelocidad() {
		return Velocidad;
	}

	public void setVelocidad(String velocidad) {
		Velocidad = velocidad;
	}

	public String getTemperatura() {
		return Temperatura;
	}

	public void setTemperatura(String temperatura) {
		Temperatura = temperatura;
	}

	public String getPeso() {
		return Peso;
	}

	public void setPeso(String peso) {
		Peso = peso;
	}

	public String getCapacidad() {
		return Capacidad;
	}

	public void setCapacidad(String capacidad) {
		Capacidad = capacidad;
	}

	public String getOtrosdatostecnicos() {
		return Otrosdatostecnicos;
	}

	public void setOtrosdatostecnicos(String otrosdatostecnicos) {
		Otrosdatostecnicos = otrosdatostecnicos;
	}

	public int getFuenteAlimentacion() {
		return fuenteAlimentacion;
	}

	public void setFuenteAlimentacion(int fuenteAlimentacion) {
		this.fuenteAlimentacion = fuenteAlimentacion;
	}

	public boolean isEquipotipofijo() {
		return Equipotipofijo;
	}

	public void setEquipotipofijo(boolean equipotipofijo) {
		Equipotipofijo = equipotipofijo;
	}

	public boolean isEquipotipoportatil() {
		return Equipotipoportatil;
	}

	public void setEquipotipoportatil(boolean equipotipoportatil) {
		Equipotipoportatil = equipotipoportatil;
	}

	public char getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(char tipoUso) {
		this.tipoUso = tipoUso;
	}

	public int getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(int riesgo) {
		this.riesgo = riesgo;
	}

	public int getClaseTecnologia() {
		return claseTecnologia;
	}

	public void setClaseTecnologia(int claseTecnologia) {
		this.claseTecnologia = claseTecnologia;
	}

	public int getClasificacionBiomedica() {
		return clasificacionBiomedica;
	}

	public void setClasificacionBiomedica(int clasificacionBiomedica) {
		this.clasificacionBiomedica = clasificacionBiomedica;
	}

	public int getTipoMantenimientoActual() {
		return tipoMantenimientoActual;
	}

	public void setTipoMantenimientoActual(int tipoMantenimientoActual) {
		this.tipoMantenimientoActual = tipoMantenimientoActual;
	}

	public int getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(int propiedad) {
		this.propiedad = propiedad;
	}

	public boolean isManual_operacion() {
		return Manual_operacion;
	}

	public void setManual_operacion(boolean manual_operacion) {
		Manual_operacion = manual_operacion;
	}

	public boolean isManual_tecnico() {
		return Manual_tecnico;
	}

	public void setManual_tecnico(boolean manual_tecnico) {
		Manual_tecnico = manual_tecnico;
	}

	public boolean isRequiereCalibracion() {
		return requiereCalibracion;
	}

	public void setRequiereCalibracion(boolean requiereCalibracion) {
		this.requiereCalibracion = requiereCalibracion;
	}

	public int getPeriodoCalibracion() {
		return periodoCalibracion;
	}

	public void setPeriodoCalibracion(int periodoCalibracion) {
		this.periodoCalibracion = periodoCalibracion;
	}

	public String getAccesorio1() {
		return Accesorio1;
	}

	public void setAccesorio1(String accesorio1) {
		Accesorio1 = accesorio1;
	}

	public String getAccesorio2() {
		return Accesorio2;
	}

	public void setAccesorio2(String accesorio2) {
		Accesorio2 = accesorio2;
	}

	public String getAccesorio3() {
		return Accesorio3;
	}

	public void setAccesorio3(String accesorio3) {
		Accesorio3 = accesorio3;
	}

	public String getAccesorio4() {
		return Accesorio4;
	}

	public void setAccesorio4(String accesorio4) {
		Accesorio4 = accesorio4;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
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

}
