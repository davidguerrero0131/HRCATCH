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
@Table(name = "syshoja_vida")
public class SystemHoja_vida implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_syshoja_vida")
	private Long id_Syshoja_vida;
	
	@Column(name= "departamento")
	private String Departamento;
	
	@Column(name="municipio")
	private String Municipio;
	
	@Column(name="direccion")
	private  String Direccion;
	
	@Column(name="telefonoinstitucion")
	private String Telefonoinstitucion;
	
	@Column(name="nivelinstitucion")
	private String Nivelinstitucion;
	
	@Column(name="emailinstitucion")
	private String Emailinstitucion;
	
	//numero de factura
	@Column(name="contrato")
	private String Contrato;
	
	@Column(name="compraddirecta")
	private boolean Compraddirecta;
	
	@Column(name="convenio")
	private boolean Convenio;
	
	@Column(name="donado")
	private boolean Donado;
	
	@Column(name="asignadoporministerio")
	private boolean Asignadoporministerio;
	
	@Column(name="asignadoporgobernacion")
	private boolean Asignadoporgobernacion;
	
	@Column(name="comodato")
	private boolean Comodato;
	
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
	
	@Column(name = "procesador")
	private String Procesador;
	
	@Column(name = "disco_duro")
	private String Disco_duro;
	
	@Column(name = "ram")
	private String Ram;
	
	@Column(name = "sistema_operativo")
	private String Sistema_operativo;
	
	@Column(name = "office")
	private String Office;
	
	@Column(name = "ip")
	private String Ip;
	
	@Column(name = "mac")
	private String mac;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "nombre_usuario")
	private String Nombre_usuario;
	
	@Column(name = "tonner")
	private String Tonner;
	
	@Column(name = "foto")
	private String Foto;

	@Column(name = "fecha_update")
	private Date Fecha_update;
	
	@Column(name = "tipo_uso")
	private String tipoUso;
	
	@Column(name = "vendedor")
	private String vendedor;
	
	@JoinColumn(name ="id_sysequipo_FK",referencedColumnName ="id_Sysequipo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private SystemEquipo systemEquipo;	
	
	/********************* GET Y SET *****************************/

	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Syshoja_vida() {
		return id_Syshoja_vida;
	}

	public void setId_Syshoja_vida(Long id_Syshoja_vida) {
		this.id_Syshoja_vida = id_Syshoja_vida;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefonoinstitucion() {
		return Telefonoinstitucion;
	}

	public void setTelefonoinstitucion(String telefonoinstitucion) {
		Telefonoinstitucion = telefonoinstitucion;
	}

	public String getNivelinstitucion() {
		return Nivelinstitucion;
	}

	public void setNivelinstitucion(String nivelinstitucion) {
		Nivelinstitucion = nivelinstitucion;
	}

	public String getEmailinstitucion() {
		return Emailinstitucion;
	}

	public void setEmailinstitucion(String emailinstitucion) {
		Emailinstitucion = emailinstitucion;
	}

	public String getContrato() {
		return Contrato;
	}

	public void setContrato(String contrato) {
		Contrato = contrato;
	}

	public boolean isCompraddirecta() {
		return Compraddirecta;
	}

	public void setCompraddirecta(boolean compraddirecta) {
		Compraddirecta = compraddirecta;
	}

	public boolean isConvenio() {
		return Convenio;
	}

	public void setConvenio(boolean convenio) {
		Convenio = convenio;
	}

	public boolean isDonado() {
		return Donado;
	}

	public void setDonado(boolean donado) {
		Donado = donado;
	}

	public boolean isAsignadoporministerio() {
		return Asignadoporministerio;
	}

	public void setAsignadoporministerio(boolean asignadoporministerio) {
		Asignadoporministerio = asignadoporministerio;
	}

	public boolean isAsignadoporgobernacion() {
		return Asignadoporgobernacion;
	}

	public void setAsignadoporgobernacion(boolean asignadoporgobernacion) {
		Asignadoporgobernacion = asignadoporgobernacion;
	}

	public boolean isComodato() {
		return Comodato;
	}

	public void setComodato(boolean comodato) {
		Comodato = comodato;
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

	public String getProcesador() {
		return Procesador;
	}

	public void setProcesador(String procesador) {
		Procesador = procesador;
	}

	public String getDisco_duro() {
		return Disco_duro;
	}

	public void setDisco_duro(String disco_duro) {
		Disco_duro = disco_duro;
	}

	public String getRam() {
		return Ram;
	}

	public void setRam(String ram) {
		Ram = ram;
	}

	public String getSistema_operativo() {
		return Sistema_operativo;
	}

	public void setSistema_operativo(String sistema_operativo) {
		Sistema_operativo = sistema_operativo;
	}

	public String getOffice() {
		return Office;
	}

	public void setOffice(String office) {
		Office = office;
	}

	public SystemEquipo getSystemEquipo() {
		return systemEquipo;
	}

	public void setSystemEquipo(SystemEquipo systemEquipo) {
		this.systemEquipo = systemEquipo;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public String getTonner() {
		return Tonner;
	}

	public void setTonner(String tonner) {
		Tonner = tonner;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public String getNombre_usuario() {
		return Nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		Nombre_usuario = nombre_usuario;
	}

	public Date getFecha_update() {
		return Fecha_update;
	}

	public void setFecha_update(Date fecha_update) {
		Fecha_update = fecha_update;
	}

	public String getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}
