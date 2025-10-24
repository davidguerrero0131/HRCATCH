package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "hoja_vida_comodatos")
public class Hoja_vida_comodatos implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_hoja_vida_otros")
	private Long id_Hoja_vida_otros;
	
	@Column(name="serie_otros")
	private String Serie_otros;
	
	@Column(name="nombre_equipo_otros")
	private String Nombre_equipo_otros;
	
	@Column(name="marca_otros")
	private String Marca_otros;
	
	@Column(name="modelo_otros")
	private String Modelo_otros;
	
	@Column(name = "ano_fabricacion_otros")
	private String Ano_fabricacion_otros;
	
	@Column(name="placa_inventario_otros")
	private String Placa_inventario_otros;
	
	@Column(name= "departamento_otros")
	private String Departamento_otros;
	
	@Column(name="municipio_otros")
	private String Municipio_otros;
	
	@Column(name="direccion_otros")
	private  String Direccion_otros;
	
	@Column(name="telefonoinstitucion_otros")
	private String Telefonoinstitucion_otros;
	
	@Column(name="ubicacioninstitucion_otros")
	private String Ubicacioninstitucion_otros;
	
	@Column(name = "codinternacional_otros")
	private String Codinternacional_otros;
	
	@Column(name="servicioinstitucion_otros")
	private String Servicioinstitucion_otros;
	
	@Column(name="emailinstitucion_otros")
	private String Emailinstitucion_otros;
	
	@Column(name="contrato_otros")
	private String Contrato_otros;
	
	@Column(name="compraddirecta_otros")
	private boolean Compraddirecta_otros;
	
	@Column(name="convenio_otros")
	private boolean Convenio_otros;
	
	@Column(name="donado_otros")
	private boolean Donado_otros;
	
	@Column(name="asignadoporministerio_otros")
	private boolean Asignadoporministerio_otros;
	
	@Column(name="asignadoporgobernacion_otros")
	private boolean Asignadoporgobernacion_otros;
	
	@Column(name="comodato_otros")
	private boolean Comodato_otros;
	
	@Column(name = "fecha_compra_otros")
    private Date Fecha_compra_otros;
	
	@Column(name = "fecha_instalacion_otros")
    private Date Fecha_instalacion_otros;
	
	@Column(name = "fecha_iniciooperacion_otros")
    private Date Fecha_iniciooperacion_otros;
	
	@Column(name = "fecha_vencimientogarantia_otros")
    private Date Fecha_vencimientogarantia_otros;
	
	@Column(name="costo_compra_otros")
	private String Costo_compra_otros;
	
	@Column(name="registro_invima_otros")
	private String Registro_invima_otros;
	
	@Column(name="fabricante_otros")
	private String Fabricante_otros;
	
	@Column(name="paisfabricante_otros")
	private String Paisfabricante_otros;
	
	@Column(name="proveedor_otros")
	private String Proveedor_otros;
	
	@Column(name="telefonoproveedor_otros")
	private String Telefonoproveedor_otros;
	
	@Column(name = "correoproveedor_otros")
	private String Correoproveedor_otros;
	
	@Column(name="ciudadproveedor_otros")
	private String Ciudadproveedor_otros;
	
	@Column(name="representante_otros")
	private String Representante_otros;
	
	@Column(name="telefonorepresentante_otros")
	private String Telefonorepresentante_otros;
	
	@Column(name="vmaxoperacion_otros")
	private String Vmaxoperacion_otros;
	
	@Column(name = "vminoperacion_otros")
	private String Vminoperacion_otros;
	
	@Column(name = "imaxoperacion_otros")
	private String Imaxoperacion_otros;
	
	@Column(name = "iminoperacion_otros")
	private String Iminoperacion_otros;
	
	@Column(name="wconsumida_otros")
	private String Wconsumida_otros;
	
	@Column(name="frecuencia_otros")
	private String Frecuencia_otros;
	
	@Column(name="presion_otros")
	private String Presion_otros;
	
	@Column(name="velocidad_otros")
	private String Velocidad_otros;
	
	@Column(name="temperatura_otros")
	private String Temperatura_otros;
	
	@Column(name="peso_otros")
	private String Peso_otros;
	
	@Column(name="capacidad_otros")
	private String Capacidad_otros;
	
	@Column(name="otrosdatostecnicos_otros")
	private String Otrosdatostecnicos_otros;
	
	@Column(name="fuenteaelectricidad_otros")
	private boolean Fuenteaelectricidad_otros;
	
	@Column(name="fuenteaenergiasolar_otros")
	private boolean Fuenteaenergiasolar_otros;
	
	@Column(name="fuenteaagua_otros")
	private boolean Fuenteaagua_otros;
	
	@Column(name="fuenteagas_otros")
	private boolean Fuenteagas_otros;
	
	@Column(name="fuenteavaporagua_otros")
	private boolean Fuenteavaporagua_otros;
	
	@Column(name="fuenteaderivadospetroleo_otros")
	private boolean Fuenteaderivadospetroleo_otros;
	
	@Column(name="fuenteaotros_otros")
	private boolean Fuenteaotros_otros;
	
	@Column(name="equipotipofijo_otros")
	private boolean Equipotipofijo_otros;
	
	@Column(name="equipotipoportatil_otros")
	private boolean Equipotipoportatil_otros;
	
	@Column(name="usomedico_otros")
	private boolean Usomedico_otros;
	
	@Column(name="usobasico_otros")
	private boolean Usobasico_otros;
	
	@Column(name="usoapoyo_otros")
	private boolean Usoapoyo_otros;
	
	@Column(name="riesgoi_otros")
	private boolean Riesgoi_otros;
	
	@Column(name="riesgoiia_otros")
	private boolean Riesgoiia_otros;
	
	@Column(name = "riesgoiib_otros")
	private boolean Riesgoiib_otros;
	
	@Column(name="riesgoiii_otros")
	private boolean Riesgoiii_otros;
	
	@Column(name="claseelectrico_otros")
	private boolean Claseelectrico_otros;
	
	@Column(name="claseelectronico_otros")
	private boolean Claseelectronico_otros;
	
	@Column(name="clasemecanico_otros")
	private boolean Clasemecanico_otros;
	
	@Column(name="claseelectromecanico_otros")
	private boolean Claseelectromecanico_otros;
	
	@Column(name="clasehidraulico_otros")
	private boolean Clasehidraulico_otros;
	
	@Column(name="claseneumatico_otros")
	private boolean Claseneumatico_otros;
	
	@Column(name="clasevapor_otros")
	private boolean Clasevapor_otros;
	
	@Column(name="clasesolar_otros")
	private boolean Clasesolar_otros;
	
	@Column(name="biomedicdiagnostico_otros")
	private boolean Biomedicdiagnostico_otros;
	
	@Column(name="biomedictratamiento_otros")
	private boolean Biomedictratamiento_otros;
	
	@Column(name="biomedicrehabilitacion_otros")
	private boolean Biomedicrehabilitacion_otros;
	
	@Column(name="biomedicprevencion_otros")
	private boolean Biomedicprevencion_otros;
	
	@Column(name="biomedicanalisis_otros")
	private boolean Biomedicanalisis_otros;
	
	@Column(name="ptrimestral_otros")
	private boolean Ptrimestral_otros;
	
	@Column(name="pcuatrimestral_otros")
	private boolean Pcuatrimestral_otros;
	
	@Column(name="psemestral_otros")
	private boolean Psemestral_otros;
	
	@Column(name="panual_otros")
	private boolean Panual_otros;
	
	@Column(name="mapropio_otros")
	private boolean Mapropio_otros;
	
	@Column(name="macontratado_otros")
	private boolean Macontratado_otros;
	
	@Column(name="macomodato_otros")
	private boolean Macomodato_otros;
	
	@Column(name="magarantia_otros")
	private boolean Magarantia_otros;
	
	@Column(name="prophospital_otros")
	private boolean Prophospital_otros;
	
	@Column(name="propproveedor_otros")
	private boolean Propproveedor_otros;

	@Column(name="propotro_otros")
	private boolean Propotro_otros;
	
	@Column(name="manual_operacion_otros")
	private boolean Manual_operacion_otros;
	
	@Column(name="manual_tecnico_otros")
	private boolean Manual_tecnico_otros;
	
	@Column(name="requierecalibracion_otros")
	private boolean Requierecalibracion_otros;
	
	@Column(name="norequierecalibracion_otros")
	private boolean Norequierecalibracion_otros;
	
	@Column(name="pcalsemestral_otros")
	private boolean Pcalsemestral_otros;
	
	@Column(name="pcalanual_otros")
	private boolean Pcalanual_otros;
	
	@Column(name ="accesorio1_otros")
	private String Accesorio1_otros;
	
	@Column(name="accesorio2_otros")
	private String Accesorio2_otros;
	
	@Column(name="accesorio3_otros")
	private String Accesorio3_otros;
	
	@Column(name="accesorio4_otros")
	private String Accesorio4_otros;
	
	@Column(name="foto_otros")
	private String Foto_otros;
	
	@Column(name = "comodato_activo")
	private boolean ComodatoActivo;
	
	@JoinColumn(name ="ID_equipo_fk",referencedColumnName ="id_Equipo")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	private Equipo equipo_otros;	

	
	
	public static long getSerialVersionUID() {
    	return serialVersionUID;
    }


	public Long getId_Hoja_vida_otros() {
		return id_Hoja_vida_otros;
	}


	public void setId_Hoja_vida_otros(Long id_Hoja_vida_otros) {
		this.id_Hoja_vida_otros = id_Hoja_vida_otros;
	}


	public String getSerie_otros() {
		return Serie_otros;
	}


	public void setSerie_otros(String serie_otros) {
		Serie_otros = serie_otros;
	}


	public String getNombre_equipo_otros() {
		return Nombre_equipo_otros;
	}


	public void setNombre_equipo_otros(String nombre_equipo_otros) {
		Nombre_equipo_otros = nombre_equipo_otros;
	}


	public String getMarca_otros() {
		return Marca_otros;
	}


	public void setMarca_otros(String marca_otros) {
		Marca_otros = marca_otros;
	}


	public String getModelo_otros() {
		return Modelo_otros;
	}


	public void setModelo_otros(String modelo_otros) {
		Modelo_otros = modelo_otros;
	}


	public String getAno_fabricacion_otros() {
		return Ano_fabricacion_otros;
	}


	public void setAno_fabricacion_otros(String ano_fabricacion_otros) {
		Ano_fabricacion_otros = ano_fabricacion_otros;
	}


	public String getPlaca_inventario_otros() {
		return Placa_inventario_otros;
	}


	public void setPlaca_inventario_otros(String placa_inventario_otros) {
		Placa_inventario_otros = placa_inventario_otros;
	}


	public String getDepartamento_otros() {
		return Departamento_otros;
	}


	public void setDepartamento_otros(String departamento_otros) {
		Departamento_otros = departamento_otros;
	}


	public String getMunicipio_otros() {
		return Municipio_otros;
	}


	public void setMunicipio_otros(String municipio_otros) {
		Municipio_otros = municipio_otros;
	}


	public String getDireccion_otros() {
		return Direccion_otros;
	}


	public void setDireccion_otros(String direccion_otros) {
		Direccion_otros = direccion_otros;
	}

    
	
	public boolean isFuenteagas_otros() {
		return Fuenteagas_otros;
	}


	public void setFuenteagas_otros(boolean fuenteagas_otros) {
		Fuenteagas_otros = fuenteagas_otros;
	}


	public String getTelefonoinstitucion_otros() {
		return Telefonoinstitucion_otros;
	}


	public void setTelefonoinstitucion_otros(String telefonoinstitucion_otros) {
		Telefonoinstitucion_otros = telefonoinstitucion_otros;
	}


	public String getUbicacioninstitucion_otros() {
		return Ubicacioninstitucion_otros;
	}


	public void setUbicacioninstitucion_otros(String ubicacioninstitucion_otros) {
		Ubicacioninstitucion_otros = ubicacioninstitucion_otros;
	}


	public String getCodinternacional_otros() {
		return Codinternacional_otros;
	}


	public void setCodinternacional_otros(String codinternacional_otros) {
		Codinternacional_otros = codinternacional_otros;
	}


	public String getServicioinstitucion_otros() {
		return Servicioinstitucion_otros;
	}


	public void setServicioinstitucion_otros(String servicioinstitucion_otros) {
		Servicioinstitucion_otros = servicioinstitucion_otros;
	}


	public String getEmailinstitucion_otros() {
		return Emailinstitucion_otros;
	}


	public void setEmailinstitucion_otros(String emailinstitucion_otros) {
		Emailinstitucion_otros = emailinstitucion_otros;
	}


	public String getContrato_otros() {
		return Contrato_otros;
	}

    
	
	

	public boolean isMacomodato_otros() {
		return Macomodato_otros;
	}


	public void setMacomodato_otros(boolean macomodato_otros) {
		Macomodato_otros = macomodato_otros;
	}


	public void setContrato_otros(String contrato_otros) {
		Contrato_otros = contrato_otros;
	}


	public boolean isCompraddirecta_otros() {
		return Compraddirecta_otros;
	}


	public void setCompraddirecta_otros(boolean compraddirecta_otros) {
		Compraddirecta_otros = compraddirecta_otros;
	}


	public boolean isConvenio_otros() {
		return Convenio_otros;
	}


	public void setConvenio_otros(boolean convenio_otros) {
		Convenio_otros = convenio_otros;
	}


	public boolean isDonado_otros() {
		return Donado_otros;
	}


	public void setDonado_otros(boolean donado_otros) {
		Donado_otros = donado_otros;
	}


	public boolean isAsignadoporministerio_otros() {
		return Asignadoporministerio_otros;
	}


	public void setAsignadoporministerio_otros(boolean asignadoporministerio_otros) {
		Asignadoporministerio_otros = asignadoporministerio_otros;
	}


	public boolean isAsignadoporgobernacion_otros() {
		return Asignadoporgobernacion_otros;
	}


	public void setAsignadoporgobernacion_otros(boolean asignadoporgobernacion_otros) {
		Asignadoporgobernacion_otros = asignadoporgobernacion_otros;
	}


	public boolean isComodato_otros() {
		return Comodato_otros;
	}


	public void setComodato_otros(boolean comodato_otros) {
		Comodato_otros = comodato_otros;
	}


	public Date getFecha_compra_otros() {
		return Fecha_compra_otros;
	}


	public void setFecha_compra_otros(Date fecha_compra_otros) {
		Fecha_compra_otros = fecha_compra_otros;
	}


	public Date getFecha_instalacion_otros() {
		return Fecha_instalacion_otros;
	}


	public void setFecha_instalacion_otros(Date fecha_instalacion_otros) {
		Fecha_instalacion_otros = fecha_instalacion_otros;
	}


	public Date getFecha_iniciooperacion_otros() {
		return Fecha_iniciooperacion_otros;
	}


	public void setFecha_iniciooperacion_otros(Date fecha_iniciooperacion_otros) {
		Fecha_iniciooperacion_otros = fecha_iniciooperacion_otros;
	}


	public Date getFecha_vencimientogarantia_otros() {
		return Fecha_vencimientogarantia_otros;
	}


	public void setFecha_vencimientogarantia_otros(Date fecha_vencimientogarantia_otros) {
		Fecha_vencimientogarantia_otros = fecha_vencimientogarantia_otros;
	}


	public String getCosto_compra_otros() {
		return Costo_compra_otros;
	}


	public void setCosto_compra_otros(String costo_compra_otros) {
		Costo_compra_otros = costo_compra_otros;
	}


	public String getRegistro_invima_otros() {
		return Registro_invima_otros;
	}


	public void setRegistro_invima_otros(String registro_invima_otros) {
		Registro_invima_otros = registro_invima_otros;
	}


	public String getFabricante_otros() {
		return Fabricante_otros;
	}


	public void setFabricante_otros(String fabricante_otros) {
		Fabricante_otros = fabricante_otros;
	}


	public String getPaisfabricante_otros() {
		return Paisfabricante_otros;
	}


	public void setPaisfabricante_otros(String paisfabricante_otros) {
		Paisfabricante_otros = paisfabricante_otros;
	}


	public String getProveedor_otros() {
		return Proveedor_otros;
	}


	public void setProveedor_otros(String proveedor_otros) {
		Proveedor_otros = proveedor_otros;
	}


	public String getTelefonoproveedor_otros() {
		return Telefonoproveedor_otros;
	}


	public void setTelefonoproveedor_otros(String telefonoproveedor_otros) {
		Telefonoproveedor_otros = telefonoproveedor_otros;
	}


	public String getCorreoproveedor_otros() {
		return Correoproveedor_otros;
	}


	public void setCorreoproveedor_otros(String correoproveedor_otros) {
		Correoproveedor_otros = correoproveedor_otros;
	}


	public String getCiudadproveedor_otros() {
		return Ciudadproveedor_otros;
	}


	public void setCiudadproveedor_otros(String ciudadproveedor_otros) {
		Ciudadproveedor_otros = ciudadproveedor_otros;
	}


	public String getRepresentante_otros() {
		return Representante_otros;
	}


	public void setRepresentante_otros(String representante_otros) {
		Representante_otros = representante_otros;
	}


	public String getTelefonorepresentante_otros() {
		return Telefonorepresentante_otros;
	}


	public void setTelefonorepresentante_otros(String telefonorepresentante_otros) {
		Telefonorepresentante_otros = telefonorepresentante_otros;
	}


	public String getVmaxoperacion_otros() {
		return Vmaxoperacion_otros;
	}


	public void setVmaxoperacion_otros(String vmaxoperacion_otros) {
		Vmaxoperacion_otros = vmaxoperacion_otros;
	}


	public String getVminoperacion_otros() {
		return Vminoperacion_otros;
	}


	public void setVminoperacion_otros(String vminoperacion_otros) {
		Vminoperacion_otros = vminoperacion_otros;
	}


	public String getImaxoperacion_otros() {
		return Imaxoperacion_otros;
	}


	public void setImaxoperacion_otros(String imaxoperacion_otros) {
		Imaxoperacion_otros = imaxoperacion_otros;
	}


	public String getIminoperacion_otros() {
		return Iminoperacion_otros;
	}


	public void setIminoperacion_otros(String iminoperacion_otros) {
		Iminoperacion_otros = iminoperacion_otros;
	}


	public String getWconsumida_otros() {
		return Wconsumida_otros;
	}


	public void setWconsumida_otros(String wconsumida_otros) {
		Wconsumida_otros = wconsumida_otros;
	}


	public String getFrecuencia_otros() {
		return Frecuencia_otros;
	}


	public void setFrecuencia_otros(String frecuencia_otros) {
		Frecuencia_otros = frecuencia_otros;
	}


	public String getPresion_otros() {
		return Presion_otros;
	}


	public void setPresion_otros(String presion_otros) {
		Presion_otros = presion_otros;
	}


	public String getVelocidad_otros() {
		return Velocidad_otros;
	}


	public void setVelocidad_otros(String velocidad_otros) {
		Velocidad_otros = velocidad_otros;
	}


	public String getTemperatura_otros() {
		return Temperatura_otros;
	}


	public void setTemperatura_otros(String temperatura_otros) {
		Temperatura_otros = temperatura_otros;
	}


	public String getPeso_otros() {
		return Peso_otros;
	}


	public void setPeso_otros(String peso_otros) {
		Peso_otros = peso_otros;
	}


	public String getCapacidad_otros() {
		return Capacidad_otros;
	}


	public void setCapacidad_otros(String capacidad_otros) {
		Capacidad_otros = capacidad_otros;
	}


	public String getOtrosdatostecnicos_otros() {
		return Otrosdatostecnicos_otros;
	}


	public void setOtrosdatostecnicos_otros(String otrosdatostecnicos_otros) {
		Otrosdatostecnicos_otros = otrosdatostecnicos_otros;
	}


	public boolean isFuenteaelectricidad_otros() {
		return Fuenteaelectricidad_otros;
	}


	public void setFuenteaelectricidad_otros(boolean fuenteaelectricidad_otros) {
		Fuenteaelectricidad_otros = fuenteaelectricidad_otros;
	}


	public boolean isFuenteaenergiasolar_otros() {
		return Fuenteaenergiasolar_otros;
	}


	public void setFuenteaenergiasolar_otros(boolean fuenteaenergiasolar_otros) {
		Fuenteaenergiasolar_otros = fuenteaenergiasolar_otros;
	}


	public boolean isFuenteaagua_otros() {
		return Fuenteaagua_otros;
	}


	public void setFuenteaagua_otros(boolean fuenteaagua_otros) {
		Fuenteaagua_otros = fuenteaagua_otros;
	}


	public boolean isFuenteavaporagua_otros() {
		return Fuenteavaporagua_otros;
	}


	public void setFuenteavaporagua_otros(boolean fuenteavaporagua_otros) {
		Fuenteavaporagua_otros = fuenteavaporagua_otros;
	}


	public boolean isFuenteaderivadospetroleo_otros() {
		return Fuenteaderivadospetroleo_otros;
	}


	public void setFuenteaderivadospetroleo_otros(boolean fuenteaderivadospetroleo_otros) {
		Fuenteaderivadospetroleo_otros = fuenteaderivadospetroleo_otros;
	}


	public boolean isFuenteaotros_otros() {
		return Fuenteaotros_otros;
	}


	public void setFuenteaotros_otros(boolean fuenteaotros_otros) {
		Fuenteaotros_otros = fuenteaotros_otros;
	}


	public boolean isEquipotipofijo_otros() {
		return Equipotipofijo_otros;
	}


	public void setEquipotipofijo_otros(boolean equipotipofijo_otros) {
		Equipotipofijo_otros = equipotipofijo_otros;
	}


	public boolean isEquipotipoportatil_otros() {
		return Equipotipoportatil_otros;
	}


	public void setEquipotipoportatil_otros(boolean equipotipoportatil_otros) {
		Equipotipoportatil_otros = equipotipoportatil_otros;
	}


	public boolean isUsomedico_otros() {
		return Usomedico_otros;
	}


	public void setUsomedico_otros(boolean usomedico_otros) {
		Usomedico_otros = usomedico_otros;
	}


	public boolean isUsobasico_otros() {
		return Usobasico_otros;
	}


	public void setUsobasico_otros(boolean usobasico_otros) {
		Usobasico_otros = usobasico_otros;
	}


	public boolean isUsoapoyo_otros() {
		return Usoapoyo_otros;
	}


	public void setUsoapoyo_otros(boolean usoapoyo_otros) {
		Usoapoyo_otros = usoapoyo_otros;
	}


	public boolean isRiesgoi_otros() {
		return Riesgoi_otros;
	}


	public void setRiesgoi_otros(boolean riesgoi_otros) {
		Riesgoi_otros = riesgoi_otros;
	}


	public boolean isRiesgoiia_otros() {
		return Riesgoiia_otros;
	}


	public void setRiesgoiia_otros(boolean riesgoiia_otros) {
		Riesgoiia_otros = riesgoiia_otros;
	}


	public boolean isRiesgoiib_otros() {
		return Riesgoiib_otros;
	}


	public void setRiesgoiib_otros(boolean riesgoiib_otros) {
		Riesgoiib_otros = riesgoiib_otros;
	}


	public boolean isRiesgoiii_otros() {
		return Riesgoiii_otros;
	}


	public void setRiesgoiii_otros(boolean riesgoiii_otros) {
		Riesgoiii_otros = riesgoiii_otros;
	}


	public boolean isClaseelectrico_otros() {
		return Claseelectrico_otros;
	}


	public void setClaseelectrico_otros(boolean claseelectrico_otros) {
		Claseelectrico_otros = claseelectrico_otros;
	}


	public boolean isClaseelectronico_otros() {
		return Claseelectronico_otros;
	}


	public void setClaseelectronico_otros(boolean claseelectronico_otros) {
		Claseelectronico_otros = claseelectronico_otros;
	}


	public boolean isClasemecanico_otros() {
		return Clasemecanico_otros;
	}


	public void setClasemecanico_otros(boolean clasemecanico_otros) {
		Clasemecanico_otros = clasemecanico_otros;
	}


	public boolean isClaseelectromecanico_otros() {
		return Claseelectromecanico_otros;
	}


	public void setClaseelectromecanico_otros(boolean claseelectromecanico_otros) {
		Claseelectromecanico_otros = claseelectromecanico_otros;
	}


	public boolean isClasehidraulico_otros() {
		return Clasehidraulico_otros;
	}


	public void setClasehidraulico_otros(boolean clasehidraulico_otros) {
		Clasehidraulico_otros = clasehidraulico_otros;
	}


	public boolean isClaseneumatico_otros() {
		return Claseneumatico_otros;
	}


	public void setClaseneumatico_otros(boolean claseneumatico_otros) {
		Claseneumatico_otros = claseneumatico_otros;
	}


	public boolean isClasevapor_otros() {
		return Clasevapor_otros;
	}


	public void setClasevapor_otros(boolean clasevapor_otros) {
		Clasevapor_otros = clasevapor_otros;
	}


	public boolean isClasesolar_otros() {
		return Clasesolar_otros;
	}


	public void setClasesolar_otros(boolean clasesolar_otros) {
		Clasesolar_otros = clasesolar_otros;
	}


	public boolean isBiomedicdiagnostico_otros() {
		return Biomedicdiagnostico_otros;
	}


	public void setBiomedicdiagnostico_otros(boolean biomedicdiagnostico_otros) {
		Biomedicdiagnostico_otros = biomedicdiagnostico_otros;
	}


	public boolean isBiomedictratamiento_otros() {
		return Biomedictratamiento_otros;
	}


	public void setBiomedictratamiento_otros(boolean biomedictratamiento_otros) {
		Biomedictratamiento_otros = biomedictratamiento_otros;
	}


	public boolean isBiomedicrehabilitacion_otros() {
		return Biomedicrehabilitacion_otros;
	}


	public void setBiomedicrehabilitacion_otros(boolean biomedicrehabilitacion_otros) {
		Biomedicrehabilitacion_otros = biomedicrehabilitacion_otros;
	}


	public boolean isBiomedicprevencion_otros() {
		return Biomedicprevencion_otros;
	}


	public void setBiomedicprevencion_otros(boolean biomedicprevencion_otros) {
		Biomedicprevencion_otros = biomedicprevencion_otros;
	}


	public boolean isBiomedicanalisis_otros() {
		return Biomedicanalisis_otros;
	}


	public void setBiomedicanalisis_otros(boolean biomedicanalisis_otros) {
		Biomedicanalisis_otros = biomedicanalisis_otros;
	}


	public boolean isPtrimestral_otros() {
		return Ptrimestral_otros;
	}


	public void setPtrimestral_otros(boolean ptrimestral_otros) {
		Ptrimestral_otros = ptrimestral_otros;
	}


	public boolean isPcuatrimestral_otros() {
		return Pcuatrimestral_otros;
	}


	public void setPcuatrimestral_otros(boolean pcuatrimestral_otros) {
		Pcuatrimestral_otros = pcuatrimestral_otros;
	}


	public boolean isPsemestral_otros() {
		return Psemestral_otros;
	}


	public void setPsemestral_otros(boolean psemestral_otros) {
		Psemestral_otros = psemestral_otros;
	}


	public boolean isPanual_otros() {
		return Panual_otros;
	}


	public void setPanual_otros(boolean panual_otros) {
		Panual_otros = panual_otros;
	}


	public boolean isMapropio_otros() {
		return Mapropio_otros;
	}


	public void setMapropio_otros(boolean mapropio_otros) {
		Mapropio_otros = mapropio_otros;
	}


	public boolean isMacontratado_otros() {
		return Macontratado_otros;
	}


	public void setMacontratado_otros(boolean macontratado_otros) {
		Macontratado_otros = macontratado_otros;
	}


	public boolean isMagarantia_otros() {
		return Magarantia_otros;
	}


	public void setMagarantia_otros(boolean magarantia_otros) {
		Magarantia_otros = magarantia_otros;
	}


	public boolean isProphospital_otros() {
		return Prophospital_otros;
	}


	public void setProphospital_otros(boolean prophospital_otros) {
		Prophospital_otros = prophospital_otros;
	}


	public boolean isPropproveedor_otros() {
		return Propproveedor_otros;
	}


	public void setPropproveedor_otros(boolean propproveedor_otros) {
		Propproveedor_otros = propproveedor_otros;
	}


	public boolean isPropotro_otros() {
		return Propotro_otros;
	}


	public void setPropotro_otros(boolean propotro_otros) {
		Propotro_otros = propotro_otros;
	}


	public boolean isManual_operacion_otros() {
		return Manual_operacion_otros;
	}


	public void setManual_operacion_otros(boolean manual_operacion_otros) {
		Manual_operacion_otros = manual_operacion_otros;
	}


	public boolean isManual_tecnico_otros() {
		return Manual_tecnico_otros;
	}


	public void setManual_tecnico_otros(boolean manual_tecnico_otros) {
		Manual_tecnico_otros = manual_tecnico_otros;
	}


	public boolean isRequierecalibracion_otros() {
		return Requierecalibracion_otros;
	}


	public void setRequierecalibracion_otros(boolean requierecalibracion_otros) {
		Requierecalibracion_otros = requierecalibracion_otros;
	}


	public boolean isNorequierecalibracion_otros() {
		return Norequierecalibracion_otros;
	}


	public void setNorequierecalibracion_otros(boolean norequierecalibracion_otros) {
		Norequierecalibracion_otros = norequierecalibracion_otros;
	}


	public boolean isPcalsemestral_otros() {
		return Pcalsemestral_otros;
	}


	public void setPcalsemestral_otros(boolean pcalsemestral_otros) {
		Pcalsemestral_otros = pcalsemestral_otros;
	}


	public boolean isPcalanual_otros() {
		return Pcalanual_otros;
	}


	public void setPcalanual_otros(boolean pcalanual_otros) {
		Pcalanual_otros = pcalanual_otros;
	}


	public String getAccesorio1_otros() {
		return Accesorio1_otros;
	}


	public void setAccesorio1_otros(String accesorio1_otros) {
		Accesorio1_otros = accesorio1_otros;
	}


	public String getAccesorio2_otros() {
		return Accesorio2_otros;
	}


	public void setAccesorio2_otros(String accesorio2_otros) {
		Accesorio2_otros = accesorio2_otros;
	}


	public String getAccesorio3_otros() {
		return Accesorio3_otros;
	}


	public void setAccesorio3_otros(String accesorio3_otros) {
		Accesorio3_otros = accesorio3_otros;
	}


	public String getAccesorio4_otros() {
		return Accesorio4_otros;
	}


	public void setAccesorio4_otros(String accesorio4_otros) {
		Accesorio4_otros = accesorio4_otros;
	}


	public String getFoto_otros() {
		return Foto_otros;
	}


	public void setFoto_otros(String foto_otros) {
		Foto_otros = foto_otros;
	}


	public Equipo getEquipo_otros() {
		return equipo_otros;
	}


	public void setEquipo_otros(Equipo equipo_otros) {
		this.equipo_otros = equipo_otros;
	}


	public boolean isComodatoActivo() {
		return ComodatoActivo;
	}

	public void setComodatoActivo(boolean comodatoActivo) {
		ComodatoActivo = comodatoActivo;
	}

	
	
}
