package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.*;

@Entity
@Table(name = "hoja_vida")
public class Hoja_vida implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_hoja_vida")
	private Long id_Hoja_vida;
	
	@Column(name = "ano_fabricacion")
	private int Ano_fabricacion;
	
	@Column(name= "departamento")
	private String Departamento;
	
	@Column(name="municipio")
	private String Municipio;
	
	@Column(name="direccion")
	private  String Direccion;
	
	@Column(name="telefonoinstitucion")
	private String Telefonoinstitucion;
	
	@Column(name = "codinternacional")
	private String Codinternacional;
	
	@Column(name="emailinstitucion")
	private String Emailinstitucion;
	
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
	
	@Column(name="registro_invima")
	private String Registro_invima;
	
	@Column(name="fabricante")
	private String Fabricante;
	
	@Column(name="paisfabricante")
	private String Paisfabricante;
	
	@Column(name="proveedor")
	private String Proveedor;
	
	@Column(name="telefonoproveedor")
	private String Telefonoproveedor;
	
	@Column(name = "correoproveedor")
	private String Correoproveedor;
	
	@Column(name="ciudadproveedor")
	private String Ciudadproveedor;
	
	@Column(name="representante")
	private String Representante;
	
	@Column(name="telefonorepresentante")
	private String Telefonorepresentante;
	
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
	
	public boolean isFuenteagas() {
		return Fuenteagas;
	}



	public void setFuenteagas(boolean fuenteagas) {
		Fuenteagas = fuenteagas;
	}



	@Column(name="equipotipoportatil")
	private boolean Equipotipoportatil;
	
	@Column(name="usomedico")
	private boolean Usomedico;
	
	@Column(name="usobasico")
	private boolean Usobasico;
	
	@Column(name="usoapoyo")
	private boolean Usoapoyo;
	
	@Column(name="riesgoi")
	private boolean Riesgoi;
	
	@Column(name="riesgoiia")
	private boolean Riesgoiia;
	
	@Column(name = "riesgoiib")
	private boolean Riesgoiib;
	
	@Column(name="riesgoiii")
	private boolean Riesgoiii;
	
	@Column(name="riesgona")
	private boolean Riesgona;
	
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
	
	@Column(name="biomedicdiagnostico")
	private boolean Biomedicdiagnostico;
	
	@Column(name="biomedictratamiento")
	private boolean Biomedictratamiento;
	
	@Column(name="biomedicrehabilitacion")
	private boolean Biomedicrehabilitacion;
	
	@Column(name="biomedicprevencion")
	private boolean Biomedicprevencion;
	
	@Column(name="biomedicanalisis")
	private boolean Biomedicanalisis;
	
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
	
	@Column(name="requierecalibracion")
	private boolean Requierecalibracion;
	
	@Column(name="norequierecalibracion")
	private boolean Norequierecalibracion;
	
	@Column(name="pcalsemestral")
	private boolean Pcalsemestral;
	
	@Column(name="pcalanual")
	private boolean Pcalanual;
	
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
	
	/********************EVALUACION*****************/
	
	public int evmanual(boolean manualop,boolean manualtec) {
		int pmanuales =0;
		if(manualop && manualtec) {
			pmanuales=100;
			
			
		}
		else if(manualop && !manualtec) {
			pmanuales=60;
		}
		else if(!manualop && manualtec) {
			pmanuales=40;
		}
		else {
			pmanuales=0;
		}
		return pmanuales;
		
	}
	public int evgarantia(Date fechavencimiento) {
		LocalDate Current_Date = LocalDate.now();
		int current_year= Current_Date.getYear();
		
		
		int puntven = 0;
		if (fechavencimiento==null) {
			
			puntven=0;
			return puntven;
		}
		else {
			LocalDate fechav = fechavencimiento.toLocalDate();
			int ven_year = fechav.getYear();
			
			int difyears = ven_year-current_year;
			
			if(difyears<0) {
				puntven = 0;
				
			}
			else if(difyears==0) {
				puntven = 15;
				
			}
			else if(difyears>0 && difyears<=3) {
				puntven = 30;
			}
			else if (difyears>3 && difyears<=5) {
				puntven = 45;
			}
			else if (difyears>5 && difyears<=7) {
				puntven = 65;
			}
			else if (difyears>7 && difyears<=10) {
				puntven = 80;
			}
			else if(difyears>10) {
				puntven = 100;
			}
			else {
				puntven = 0;
			}
			
			return puntven;
			
		}
		
		
	}
	/********************* GET Y SET *****************************/

	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}



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



	public String getCodinternacional() {
		return Codinternacional;
	}



	public void setCodinternacional(String codinternacional) {
		Codinternacional = codinternacional;
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



	public String getProveedor() {
		return Proveedor;
	}



	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}



	public String getTelefonoproveedor() {
		return Telefonoproveedor;
	}



	public void setTelefonoproveedor(String telefonoproveedor) {
		Telefonoproveedor = telefonoproveedor;
	}



	public String getCorreoproveedor() {
		return Correoproveedor;
	}



	public void setCorreoproveedor(String correoproveedor) {
		Correoproveedor = correoproveedor;
	}



	public String getCiudadproveedor() {
		return Ciudadproveedor;
	}



	public void setCiudadproveedor(String ciudadproveedor) {
		Ciudadproveedor = ciudadproveedor;
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

	public boolean isMacomodato() {
		return Macomodato;
	}



	public void setMacomodato(boolean macomodato) {
		Macomodato = macomodato;
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



	public boolean isRiesgoi() {
		return Riesgoi;
	}



	public void setRiesgoi(boolean riesgoi) {
		Riesgoi = riesgoi;
	}



	public boolean isRiesgoiia() {
		return Riesgoiia;
	}



	public void setRiesgoiia(boolean riesgoiia) {
		Riesgoiia = riesgoiia;
	}



	public boolean isRiesgoiib() {
		return Riesgoiib;
	}



	public void setRiesgoiib(boolean riesgoiib) {
		Riesgoiib = riesgoiib;
	}



	public boolean isRiesgoiii() {
		return Riesgoiii;
	}



	public void setRiesgoiii(boolean riesgoiii) {
		Riesgoiii = riesgoiii;
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



	public boolean isBiomedicdiagnostico() {
		return Biomedicdiagnostico;
	}



	public void setBiomedicdiagnostico(boolean biomedicdiagnostico) {
		Biomedicdiagnostico = biomedicdiagnostico;
	}



	public boolean isBiomedictratamiento() {
		return Biomedictratamiento;
	}



	public void setBiomedictratamiento(boolean biomedictratamiento) {
		Biomedictratamiento = biomedictratamiento;
	}



	public boolean isBiomedicrehabilitacion() {
		return Biomedicrehabilitacion;
	}



	public void setBiomedicrehabilitacion(boolean biomedicrehabilitacion) {
		Biomedicrehabilitacion = biomedicrehabilitacion;
	}



	public boolean isBiomedicprevencion() {
		return Biomedicprevencion;
	}



	public void setBiomedicprevencion(boolean biomedicprevencion) {
		Biomedicprevencion = biomedicprevencion;
	}



	public boolean isBiomedicanalisis() {
		return Biomedicanalisis;
	}



	public void setBiomedicanalisis(boolean biomedicanalisis) {
		Biomedicanalisis = biomedicanalisis;
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



	public boolean isRequierecalibracion() {
		return Requierecalibracion;
	}



	public void setRequierecalibracion(boolean requierecalibracion) {
		Requierecalibracion = requierecalibracion;
	}



	public boolean isNorequierecalibracion() {
		return Norequierecalibracion;
	}



	public void setNorequierecalibracion(boolean norequierecalibracion) {
		Norequierecalibracion = norequierecalibracion;
	}



	public boolean isPcalsemestral() {
		return Pcalsemestral;
	}



	public void setPcalsemestral(boolean pcalsemestral) {
		Pcalsemestral = pcalsemestral;
	}



	public boolean isPcalanual() {
		return Pcalanual;
	}



	public void setPcalanual(boolean pcalanual) {
		Pcalanual = pcalanual;
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



	public Equipo getEquipo() {
		return equipo;
	}



	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}



	public String getObservaciones() {
		return Observaciones;
	}



	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}



	public boolean isRiesgona() {
		return Riesgona;
	}



	public void setRiesgona(boolean riesgona) {
		Riesgona = riesgona;
	}
	
	
}
