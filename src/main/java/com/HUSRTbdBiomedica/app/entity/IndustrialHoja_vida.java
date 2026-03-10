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
@Table(name = "indhoja_vida")
public class IndustrialHoja_vida implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_indhoja_vida")
	private Long id_Indhoja_vida;
	
	@Column(name= "departamento")
	private String Departamento;
	
	@Column(name="municipio")
	private String Municipio;
	
	@Column(name="direccion")
	private  String Direccion;
	
	@Column(name="nivelinstitucion")
	private String Nivelinstitucion;
	
	@Column(name="telefonoinstitucion")
	private String Telefonoinstitucion;
	
	@Column(name = "codinternacional")
	private String Codinternacional;
	
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
	
	@Column(name = "referencia")
	private String Referencia;
	
	@Column(name="fabricante")
	private String Fabricante;
	
	@Column(name="ciudadfabricante")
	private String Ciudadfabricante;
	
	@Column(name="representante")
	private String Representante;
	
	@Column(name="telefonorepresentante")
	private String Telefonorepresentante;
	
	@Column(name = "distribuidor")
	private String Distribuidor;
	
	@Column(name = "ciudadistribuidor")
	private String Ciudadistribuidor;
	
	@Column(name = "telefonodistribuidor")
	private String Telefonodistribuidor;
	
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
	
	@Column(name="fuenteaelectricidad")
	private boolean Fuenteaelectricidad;
	
	@Column(name="fuenteaenergiasolar")
	private boolean Fuenteaenergiasolar;
	
	@Column(name="fuenteaagua")
	private boolean Fuenteaagua;
	
	@Column(name = "fuenteaire")
	private boolean Fuenteaire;
	
	@Column(name="fuenteagas")
	private boolean Fuenteagas;
	
	@Column(name="fuenteavaporagua")
	private boolean Fuenteavaporagua;
	
	@Column(name="fuenteaderivadospetroleo")
	private boolean Fuenteaderivadospetroleo;
	
	@Column(name="fuenteaotros")
	private boolean Fuenteaotros;
	
	@Column(name="equipotipofijo")
	private boolean Equipotipofijo;
	
	@Column(name="equipotipoportatil")
	private boolean Equipotipoportatil;
	
	@Column(name="usomedico")
	private boolean Usomedico;
	
	@Column(name="usobasico")
	private boolean Usobasico;
	
	@Column(name="usoapoyo")
	private boolean Usoapoyo;
	
	@Column(name="riesgoalto")
	private boolean Riesgoalto;
	
	@Column(name="riesgomedio")
	private boolean Riesgomedio;
	
	@Column(name = "riesgobajo")
	private boolean Riesgobajo;
	
	@Column(name="claseelectrico")
	private boolean Claseelectrico;
	
	@Column(name="claseelectronico")
	private boolean Claseelectronico;
	
	@Column(name="clasemecanico")
	private boolean Clasemecanico;
	
	@Column(name="claseelectromecanico")
	private boolean Claseelectromecanico;
	
	@Column(name="clasehidraulico")
	private boolean Clasehidraulico;
	
	@Column(name="claseneumatico")
	private boolean Claseneumatico;
	
	@Column(name="clasevapor")
	private boolean Clasevapor;
	
	@Column(name="clasesolar")
	private boolean Clasesolar;
	
	@Column(name = "clasificacionplantas")
	private boolean Clasificacionplantas;
	
	@Column(name = "clasificacionlavanderia")
	private boolean Clasificacionlavanderia;
	
	@Column(name = "clasificacionbombas")
	private boolean Clasificacionbombas;
	
	@Column(name = "clasificacionautoclaves")
	private boolean Clasificacionautoclaves;
	
	@Column(name = "clasificacionrefrigeracion")
	private boolean Clasificacionrefrigeracion;
	
	@Column(name = "clasificacionapoyo")
	private boolean Clasificacionapoyo;
	
	@Column(name = "clasificacionotros")
	private boolean Clasificacionotros;
	
	@Column(name="mapropio")
	private boolean Mapropio;
	
	@Column(name="macontratado")
	private boolean Macontratado;
	
	@Column(name="macomodato")
	private boolean Macomodato;
	
	@Column(name="magarantia")
	private boolean Magarantia;
	
	@Column(name="prophospital")
	private boolean Prophospital;
	
	@Column(name="propproveedor")
	private boolean Propproveedor;

	@Column(name="propotro")
	private boolean Propotro;
	
	@Column(name="manual_operacion")
	private boolean Manual_operacion;
	
	@Column(name="manual_tecnico")
	private boolean Manual_tecnico;
	
	@Column(name = "foto")
	private String Foto;

	@Column(name = "fecha_update")
	private Date Fecha_update;
	
	@JoinColumn(name ="id_indequipo_FK",referencedColumnName ="id_Indequipo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private IndustrialEquipo industrialEquipo;	
	
	/********************* GET Y SET *****************************/

	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}

	public Long getId_Indhoja_vida() {
		return id_Indhoja_vida;
	}

	public void setId_Indhoja_vida(Long id_Indhoja_vida) {
		this.id_Indhoja_vida = id_Indhoja_vida;
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

	public String getNivelinstitucion() {
		return Nivelinstitucion;
	}

	public void setNivelinstitucion(String nivelinstitucion) {
		Nivelinstitucion = nivelinstitucion;
	}

	public String getTelefonoinstitucion() {
		return Telefonoinstitucion;
	}

	public void setTelefonoinstitucion(String telefonoinstitucion) {
		Telefonoinstitucion = telefonoinstitucion;
	}

	public String getCodinternacional() {
		return Codinternacional;
	}

	public void setCodinternacional(String codinternacional) {
		Codinternacional = codinternacional;
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

	public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

	public String getFabricante() {
		return Fabricante;
	}

	public void setFabricante(String fabricante) {
		Fabricante = fabricante;
	}

	public String getCiudadfabricante() {
		return Ciudadfabricante;
	}

	public void setCiudadfabricante(String ciudadfabricante) {
		Ciudadfabricante = ciudadfabricante;
	}

	public String getRepresentante() {
		return Representante;
	}

	public void setRepresentante(String representante) {
		Representante = representante;
	}

	public String getTelefonorepresentante() {
		return Telefonorepresentante;
	}

	public void setTelefonorepresentante(String telefonorepresentante) {
		Telefonorepresentante = telefonorepresentante;
	}

	public String getDistribuidor() {
		return Distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		Distribuidor = distribuidor;
	}

	public String getCiudadistribuidor() {
		return Ciudadistribuidor;
	}

	public void setCiudadistribuidor(String ciudadistribuidor) {
		Ciudadistribuidor = ciudadistribuidor;
	}

	public String getTelefonodistribuidor() {
		return Telefonodistribuidor;
	}

	public void setTelefonodistribuidor(String telefonodistribuidor) {
		Telefonodistribuidor = telefonodistribuidor;
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

	public boolean isFuenteaelectricidad() {
		return Fuenteaelectricidad;
	}

	public void setFuenteaelectricidad(boolean fuenteaelectricidad) {
		Fuenteaelectricidad = fuenteaelectricidad;
	}

	public boolean isFuenteaenergiasolar() {
		return Fuenteaenergiasolar;
	}

	public void setFuenteaenergiasolar(boolean fuenteaenergiasolar) {
		Fuenteaenergiasolar = fuenteaenergiasolar;
	}

	public boolean isFuenteaagua() {
		return Fuenteaagua;
	}

	public void setFuenteaagua(boolean fuenteaagua) {
		Fuenteaagua = fuenteaagua;
	}

	public boolean isFuenteaire() {
		return Fuenteaire;
	}

	public void setFuenteaire(boolean fuenteaire) {
		Fuenteaire = fuenteaire;
	}

	public boolean isFuenteagas() {
		return Fuenteagas;
	}

	public void setFuenteagas(boolean fuenteagas) {
		Fuenteagas = fuenteagas;
	}

	public boolean isFuenteavaporagua() {
		return Fuenteavaporagua;
	}

	public void setFuenteavaporagua(boolean fuenteavaporagua) {
		Fuenteavaporagua = fuenteavaporagua;
	}

	public boolean isFuenteaderivadospetroleo() {
		return Fuenteaderivadospetroleo;
	}

	public void setFuenteaderivadospetroleo(boolean fuenteaderivadospetroleo) {
		Fuenteaderivadospetroleo = fuenteaderivadospetroleo;
	}

	public boolean isFuenteaotros() {
		return Fuenteaotros;
	}

	public void setFuenteaotros(boolean fuenteaotros) {
		Fuenteaotros = fuenteaotros;
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

	public boolean isUsomedico() {
		return Usomedico;
	}

	public void setUsomedico(boolean usomedico) {
		Usomedico = usomedico;
	}

	public boolean isUsobasico() {
		return Usobasico;
	}

	public void setUsobasico(boolean usobasico) {
		Usobasico = usobasico;
	}

	public boolean isUsoapoyo() {
		return Usoapoyo;
	}

	public void setUsoapoyo(boolean usoapoyo) {
		Usoapoyo = usoapoyo;
	}

	public boolean isRiesgoalto() {
		return Riesgoalto;
	}

	public void setRiesgoalto(boolean riesgoalto) {
		Riesgoalto = riesgoalto;
	}

	public boolean isRiesgomedio() {
		return Riesgomedio;
	}

	public void setRiesgomedio(boolean riesgomedio) {
		Riesgomedio = riesgomedio;
	}

	public boolean isRiesgobajo() {
		return Riesgobajo;
	}

	public void setRiesgobajo(boolean riesgobajo) {
		Riesgobajo = riesgobajo;
	}

	public boolean isClaseelectrico() {
		return Claseelectrico;
	}

	public void setClaseelectrico(boolean claseelectrico) {
		Claseelectrico = claseelectrico;
	}

	public boolean isClaseelectronico() {
		return Claseelectronico;
	}

	public void setClaseelectronico(boolean claseelectronico) {
		Claseelectronico = claseelectronico;
	}

	public boolean isClasemecanico() {
		return Clasemecanico;
	}

	public void setClasemecanico(boolean clasemecanico) {
		Clasemecanico = clasemecanico;
	}

	public boolean isClaseelectromecanico() {
		return Claseelectromecanico;
	}

	public void setClaseelectromecanico(boolean claseelectromecanico) {
		Claseelectromecanico = claseelectromecanico;
	}

	public boolean isClasehidraulico() {
		return Clasehidraulico;
	}

	public void setClasehidraulico(boolean clasehidraulico) {
		Clasehidraulico = clasehidraulico;
	}

	public boolean isClaseneumatico() {
		return Claseneumatico;
	}

	public void setClaseneumatico(boolean claseneumatico) {
		Claseneumatico = claseneumatico;
	}

	public boolean isClasevapor() {
		return Clasevapor;
	}

	public void setClasevapor(boolean clasevapor) {
		Clasevapor = clasevapor;
	}

	public boolean isClasesolar() {
		return Clasesolar;
	}

	public void setClasesolar(boolean clasesolar) {
		Clasesolar = clasesolar;
	}

	public boolean isMapropio() {
		return Mapropio;
	}

	public void setMapropio(boolean mapropio) {
		Mapropio = mapropio;
	}

	public boolean isMacontratado() {
		return Macontratado;
	}

	public void setMacontratado(boolean macontratado) {
		Macontratado = macontratado;
	}

	public boolean isMacomodato() {
		return Macomodato;
	}

	public void setMacomodato(boolean macomodato) {
		Macomodato = macomodato;
	}

	public boolean isMagarantia() {
		return Magarantia;
	}

	public void setMagarantia(boolean magarantia) {
		Magarantia = magarantia;
	}

	public boolean isProphospital() {
		return Prophospital;
	}

	public void setProphospital(boolean prophospital) {
		Prophospital = prophospital;
	}

	public boolean isPropproveedor() {
		return Propproveedor;
	}

	public void setPropproveedor(boolean propproveedor) {
		Propproveedor = propproveedor;
	}

	public boolean isPropotro() {
		return Propotro;
	}

	public void setPropotro(boolean propotro) {
		Propotro = propotro;
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

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public Date getFecha_update() {
		return Fecha_update;
	}

	public void setFecha_update(Date fecha_update) {
		Fecha_update = fecha_update;
	}

	public IndustrialEquipo getIndustrialEquipo() {
		return industrialEquipo;
	}

	public void setIndustrialEquipo(IndustrialEquipo industrialEquipo) {
		this.industrialEquipo = industrialEquipo;
	}

	public boolean isClasificacionplantas() {
		return Clasificacionplantas;
	}

	public void setClasificacionplantas(boolean clasificacionplantas) {
		Clasificacionplantas = clasificacionplantas;
	}

	public boolean isClasificacionlavanderia() {
		return Clasificacionlavanderia;
	}

	public void setClasificacionlavanderia(boolean clasificacionlavanderia) {
		Clasificacionlavanderia = clasificacionlavanderia;
	}

	public boolean isClasificacionbombas() {
		return Clasificacionbombas;
	}

	public void setClasificacionbombas(boolean clasificacionbombas) {
		Clasificacionbombas = clasificacionbombas;
	}

	public boolean isClasificacionautoclaves() {
		return Clasificacionautoclaves;
	}

	public void setClasificacionautoclaves(boolean clasificacionautoclaves) {
		Clasificacionautoclaves = clasificacionautoclaves;
	}

	public boolean isClasificacionrefrigeracion() {
		return Clasificacionrefrigeracion;
	}

	public void setClasificacionrefrigeracion(boolean clasificacionrefrigeracion) {
		Clasificacionrefrigeracion = clasificacionrefrigeracion;
	}

	public boolean isClasificacionapoyo() {
		return Clasificacionapoyo;
	}

	public void setClasificacionapoyo(boolean clasificacionapoyo) {
		Clasificacionapoyo = clasificacionapoyo;
	}

	public boolean isClasificacionotros() {
		return Clasificacionotros;
	}

	public void setClasificacionotros(boolean clasificacionotros) {
		Clasificacionotros = clasificacionotros;
	}
	
	
}
