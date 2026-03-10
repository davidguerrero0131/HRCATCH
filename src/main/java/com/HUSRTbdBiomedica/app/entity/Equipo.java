package com.HUSRTbdBiomedica.app.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.catalog.CatalogResolver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "equipo")
public class Equipo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_equipo")
	private Long id_Equipo;
	
	@Column(name = "nombre_equipo")
	private String nombre_Equipo;
	
	@Column(name = "marca")
	private String Marca;
	
	@Column(name = "modelo")
	private String Modelo;
	
	@Column(name = "serie")
	private String Serie;
	
	@Column(name = "placa_inventario")
	private String Placa_inventario;
	
	@Column(name = "servicio")
	private String Servicios;
	
	@Column(name = "ubicacion")
	private String Ubicacion;
	
	@Column(name= "ubicacion_especifica")
	private String Ubicacion_especifica;
	
	@Column(name = "periodicidad")
	private int Periodicidad;
	
	@Column(name = "dias_mantenimiento")
	private String Dias_mantenimiento;
	
	@Column(name = "meses_mantenimiento")
	private String Meses_mantenimiento;
	
	@Column(name = "ano_mantenimiento")
	private int Ano_mantenimiento;
	
	@Column(name = "enero_mantenimiento")
	private String Enero_mantenimiento;
	
	@Column(name = "febrero_mantenimiento")
	private String Febrero_mantenimiento;
	
	@Column(name = "marzo_mantenimiento")
	private String Marzo_mantenimiento;
	
	@Column(name = "abril_mantenimiento")
	private String Abril_mantenimiento;
	
	@Column(name = "mayo_mantenimiento")
	private String Mayo_mantenimiento;
	
	@Column(name  = "junio_mantenimiento")
	private String Junio_mantenimiento;
	
	@Column(name = "julio_mantenimiento")
	private String Julio_mantenimiento;
	
	@Column(name = "agosto_mantenimiento")
	private String Agosto_mantenimiento;
	
	@Column(name = "septiembre_mantenimiento")
	private String Septiembre_mantenimiento;
	
	@Column(name = "octubre_mantenimiento")
	private String Octubre_mantenimiento;
	
	@Column(name = "noviembre_mantenimiento")
	private String Noviembre_mantenimiento;
	
	@Column(name = "diciembre_mantenimiento")
	private String Diciembre_mantenimiento;
	
	@Column(name = "ano_ingreso")
	private int Ano_ingreso;
	
	@Column(name = "activo")
	private boolean Activo;
	
	@Column(name = "codigo")
	private String Codigo;
	
	@Column(name = "guia")
	private String Guia;
	
	@Column(name = "ficha_tecnica")
	private String Ficha_Tecnica;

	@Column(name = "calibracion")
	private int Calibracion;
	
	@Column(name = "validacion")
	private int Validacion;
	
	@Column(name = "calificacion")
	private int Califiacion;
	
	@Column(name = "estado_baja")
	private boolean EstadoBaja;
	
	@JoinColumn(name ="id_tipo_equipo_fk",referencedColumnName ="id_Tipo_equipo")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Tipo_equipo tipo_equipo;
	
	@JoinColumn(name ="id_servicio_fk",referencedColumnName ="id_Servicio")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Servicio servicio;
	
	@JoinColumn(name = "id_hospital_FK",referencedColumnName="id_Hospital")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private Hospital hospital;
	
	@JoinColumn(name = "id_responsable_fk",referencedColumnName="id_Responsable")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private Responsable responsable;
	
	/***********CONVERSION************/
	public ArrayList<String> divisiondias(String diasmantenimiento,int Periodicidad){
		String[] splitdias = null;
		if (diasmantenimiento==null) {
			if(Periodicidad == 4) {
				diasmantenimiento = "1-5,1-5,1-5";
				splitdias = diasmantenimiento.split(",");
			}
			else if(Periodicidad ==3) {
				diasmantenimiento = "1-5,1-5,1-5,1-5";
				splitdias = diasmantenimiento.split(",");
			}
			else if(Periodicidad==2) {
				diasmantenimiento = "1-5,1-5";
				splitdias = diasmantenimiento.split(",");
			}
			else if(Periodicidad ==1) {
				diasmantenimiento = "1-5";
				splitdias = diasmantenimiento.split(",");
			}
			else {
				diasmantenimiento = "1-5";
				splitdias = diasmantenimiento.split(",");
			}
			
		}
		else {
			splitdias = diasmantenimiento.split(",");
		}
		ArrayList<String> listdias = new ArrayList<String>(Arrays.asList(splitdias));
		
		return listdias;
	}
	public ArrayList<String> divisionmeses(String mesesmantenimiento,int Periodicidad) {
		String[] splitmeses = null;
		if (mesesmantenimiento==null) {
			if(Periodicidad==4) {
				mesesmantenimiento = "MARZO,JULIO,NOVIEMBRE";
				splitmeses = mesesmantenimiento.split(",");
			}
			else if(Periodicidad==3) {
				mesesmantenimiento = "FEBRERO,MAYO,AGOSTO,NOVIEMBRE";
				splitmeses = mesesmantenimiento.split(",");
			}
			else if(Periodicidad==2) {
				
				mesesmantenimiento = "ABRIL,OCTUBRE";
				splitmeses = mesesmantenimiento.split(",");
							
			}
			else if(Periodicidad==1) {
				mesesmantenimiento = "ENERO";
				splitmeses = mesesmantenimiento.split(",");
				
			}
			else {
				mesesmantenimiento = "ENERO";
				splitmeses = mesesmantenimiento.split(",");
				
			}
			
		}
		else {
			splitmeses = mesesmantenimiento.split(",");
		}
		ArrayList<String> listmeses = new ArrayList<String>(Arrays.asList(splitmeses));
		return listmeses;
	}
	public ArrayList<String> concaten(String diasmantenimiento,String mesesmantenimiento, int Periodicidad) {
		
		ArrayList<String> tabmeses = divisionmeses(mesesmantenimiento,Periodicidad);
		ArrayList<String> diasmeses = divisiondias(diasmantenimiento,Periodicidad);
		ArrayList<String> fechas = new ArrayList<String>();
		for(int indice=0;indice<tabmeses.size();indice++) {			
			fechas.add(tabmeses.get(indice)+':'+diasmeses.get(indice));			
		}
		return fechas;
	}
	
	public ArrayList<Integer> detectarmes_semana(ArrayList<String> fechas,int mes) {
		String Mes = null;
		if( mes==1) {
			Mes = "ENERO";
		}
		else if(mes==2) {
			Mes = "FEBRERO";
		}
		else if(mes==3) {
			Mes = "MARZO";	
		}
		else if(mes==4) {
			Mes = "ABRIL";
		}
		else if(mes==5) {
			Mes = "MAYO";
		}
		else if(mes==6) {
			Mes = "JUNIO";
		}
		
		else if(mes==7) {
			Mes = "JULIO";
		}
		else if(mes==8) {
			Mes = "AGOSTO";
		}
		else if(mes==9) {
			Mes = "SEPTIEMBRE";
		}
		else if(mes==10) {
			Mes = "OCTUBRE";
		}
		else if(mes==11) {
			Mes = "NOVIEMBRE";	
		}
		else if(mes==12) {
			Mes = "DICIEMBRE";
		}
		
		String[] dias = null;
		for(int indice=0;indice<fechas.size();indice++) {
			
			if(fechas.get(indice).contains(Mes)) {
				dias = fechas.get(indice).split(":");
				
			}
		}
		System.out.println("IdEquipo: " + id_Equipo + "Mes:" + mes + "Dias: " + dias);
		ArrayList<String> diasmtto = new ArrayList<String>(Arrays.asList(dias));
		String diastow = diasmtto.get(1);
		String[] diasseparate = diastow.split("-");
		ArrayList<String> diasitof = new ArrayList<String>(Arrays.asList(diasseparate));
		int diainicial = 1;
		int diafinal  = 30;
		if (diasitof.size()>1) {
			String diai = diasitof.get(0).trim();
			diainicial = Integer.parseInt(diai);
			String diaf = diasitof.get(1).trim();
			diafinal = Integer.parseInt(diaf);
		}
		else {
			String diai = diasitof.get(0).trim();
			diainicial = Integer.parseInt(diai);
			diafinal = diainicial;
		}
		
		ArrayList<Integer> diasinf = new ArrayList<Integer>();
		
		diasinf.add(diainicial);
		diasinf.add(diafinal);
		return diasinf;
		
		
	}
	/********************EVALUACION*****************/
	public int puntajeIntensidadUso(int periodicidad) {
		int intuso=0;
		if(periodicidad==4) {
			intuso=50;
		}
			
		else if(periodicidad==3) {
			intuso=25;			
		}
			
		else if(periodicidad==2) {
			intuso=75;
		}
		else if(periodicidad==1) {
			intuso=100;
		}
		else {
			intuso=0;
		}
		
		return intuso;
		
	}
	
	public int puntajeEdad(int ano_ingreso) {
		LocalDate current_date = LocalDate.now();
		int ano_actual = current_date.getYear();
		int edad=0;
		int pedad=0;
		if (ano_ingreso==0) {
			pedad=0;
		}
		else {
			edad = ano_actual-ano_ingreso;
		}
		
		if (edad<1.1 && edad>0) {
			pedad=100;
		}
		else if(edad>1 && edad<2.1) {
			pedad=80;
		}
		else if(edad>2 && edad<3.1) {
			pedad=60;
		}
		else if(edad>3 && edad<4.1) {
			pedad=40;
		}
		else if(edad>4 && edad<5.1) {
			pedad=30;
		}
		else if(edad>5 && edad<6.1) {
			pedad=15;
		}
		else {
			pedad=0;
		}
		return pedad;
	}
	public int puntajeFueraServicio(int diasfueraservicio) {
		int puntajefs = 0;
		if (diasfueraservicio<1.1) {
			puntajefs=100;
		}
		else if(diasfueraservicio>1 && diasfueraservicio<5.1) {
			puntajefs=90;
		}
		else if(diasfueraservicio>5 && diasfueraservicio<15.1) {
			puntajefs=75;
		}
		else if(diasfueraservicio>15 && diasfueraservicio<30.1) {
			puntajefs=60;
		}
		else if(diasfueraservicio>30 && diasfueraservicio<50.1) {
			puntajefs=45;
		}
		else if(diasfueraservicio>50 && diasfueraservicio<75.1) {
			puntajefs=30;
		}
		else if(diasfueraservicio>75 && diasfueraservicio<90.1) {
			puntajefs=30;
		}
		else if(diasfueraservicio>90) {
			puntajefs = 0;
		}
		return puntajefs;
		
	}
	public int puntajeSeguridad(Long id) {
		int puntajase = 0;
		List<Long> cat1 = Arrays.asList(61L);
		List<Long> cat2 = Arrays.asList(52L,70L,72L,73L,80L);
		List<Long> cat3 = Arrays.asList(9L,11L,12L,15L,16L,17L,21L,22L,23L,24L,26L,33L,36L,39L,40L,42L,43L,44L,45L,46L,65L,86L,90L,92L,97L,101L,102L,104L,110L);
		if(cat1.contains(id)) {
			puntajase = 60;
		}
		else if(cat2.contains(id)) {
			puntajase = 75;
		}
		else if(cat3.contains(id)) {
			puntajase = 90;
		}
		else {
			puntajase = 100;
		}
		return puntajase;
	}
	
	
	/********************* GET Y SET *****************************/
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}
	public String anotoString(int ano) {
		String anostring =Integer.toString(ano);
		return anostring;
	}

	public Long getId_Equipo() {
		return id_Equipo;
	}

	public void setId_Equipo(Long id_Equipo) {
		this.id_Equipo = id_Equipo;
	}

	public String getNombre_Equipo() {
		return nombre_Equipo;
	}

	public void setNombre_Equipo(String nombre_Equipo) {
		this.nombre_Equipo = nombre_Equipo;
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

	public String getServicios() {
		return Servicios;
	}

	public void setServicios(String servicios) {
		Servicios = servicios;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public int getPeriodicidad() {
		return Periodicidad;
	}

	public void setPeriodicidad(int periodicidad) {
		Periodicidad = periodicidad;
	}

	public String getDias_mantenimiento() {
		return Dias_mantenimiento;
	}

	public void setDias_mantenimiento(String dias_mantenimiento) {
		Dias_mantenimiento = dias_mantenimiento;
	}

	public String getMeses_mantenimiento() {
		return Meses_mantenimiento;
	}

	public void setMeses_mantenimiento(String meses_mantenimiento) {
		Meses_mantenimiento = meses_mantenimiento;
	}

	public int getAno_mantenimiento() {
		return Ano_mantenimiento;
	}

	public void setAno_mantenimiento(int ano_mantenimiento) {
		Ano_mantenimiento = ano_mantenimiento;
	}

	public int getAno_ingreso() {
		return Ano_ingreso;
	}

	public void setAno_ingreso(int ano_ingreso) {
		Ano_ingreso = ano_ingreso;
	}

	public Tipo_equipo getTipo_equipo() {
		return tipo_equipo;
	}

	public void setTipo_equipo(Tipo_equipo tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}


	public String getUbicacion_especifica() {
		return Ubicacion_especifica;
	}

	public void setUbicacion_especifica(String ubicacion_especifica) {
		Ubicacion_especifica = ubicacion_especifica;
	}

	

	public String getEnero_mantenimiento() {
		return Enero_mantenimiento;
	}

	public void setEnero_mantenimiento(String enero_mantenimiento) {
		Enero_mantenimiento = enero_mantenimiento;
	}

	public String getFebrero_mantenimiento() {
		return Febrero_mantenimiento;
	}

	public void setFebrero_mantenimiento(String febrero_mantenimiento) {
		Febrero_mantenimiento = febrero_mantenimiento;
	}

	public String getMarzo_mantenimiento() {
		return Marzo_mantenimiento;
	}

	public void setMarzo_mantenimiento(String marzo_mantenimiento) {
		Marzo_mantenimiento = marzo_mantenimiento;
	}

	public String getAbril_mantenimiento() {
		return Abril_mantenimiento;
	}

	public void setAbril_mantenimiento(String abril_mantenimiento) {
		Abril_mantenimiento = abril_mantenimiento;
	}

	public String getMayo_mantenimiento() {
		return Mayo_mantenimiento;
	}

	public void setMayo_mantenimiento(String mayo_mantenimiento) {
		Mayo_mantenimiento = mayo_mantenimiento;
	}

	public String getJunio_mantenimiento() {
		return Junio_mantenimiento;
	}

	public void setJunio_mantenimiento(String junio_mantenimiento) {
		Junio_mantenimiento = junio_mantenimiento;
	}

	public String getJulio_mantenimiento() {
		return Julio_mantenimiento;
	}

	public void setJulio_mantenimiento(String julio_mantenimiento) {
		Julio_mantenimiento = julio_mantenimiento;
	}

	public String getAgosto_mantenimiento() {
		return Agosto_mantenimiento;
	}

	public void setAgosto_mantenimiento(String agosto_mantenimiento) {
		Agosto_mantenimiento = agosto_mantenimiento;
	}

	public String getSeptiembre_mantenimiento() {
		return Septiembre_mantenimiento;
	}

	public void setSeptiembre_mantenimiento(String septiembre_mantenimiento) {
		Septiembre_mantenimiento = septiembre_mantenimiento;
	}

	public String getOctubre_mantenimiento() {
		return Octubre_mantenimiento;
	}

	public void setOctubre_mantenimiento(String octubre_mantenimiento) {
		Octubre_mantenimiento = octubre_mantenimiento;
	}

	public String getNoviembre_mantenimiento() {
		return Noviembre_mantenimiento;
	}

	public void setNoviembre_mantenimiento(String noviembre_mantenimiento) {
		Noviembre_mantenimiento = noviembre_mantenimiento;
	}

	public String getDiciembre_mantenimiento() {
		return Diciembre_mantenimiento;
	}

	public void setDiciembre_mantenimiento(String diciembre_mantenimiento) {
		Diciembre_mantenimiento = diciembre_mantenimiento;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public boolean isActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		Activo = activo;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public String getGuia() {
		return Guia;
	}
	public void setGuia(String guia) {
		Guia = guia;
	}
	public int getCalibracion() {
		return Calibracion;
	}
	public void setCalibracion(int calibracion) {
		Calibracion = calibracion;
	}
	public int getValidacion() {
		return Validacion;
	}
	public void setValidacion(int validacion) {
		Validacion = validacion;
	}
	public int getCalifiacion() {
		return Califiacion;
	}
	public void setCalifiacion(int califiacion) {
		Califiacion = califiacion;
	}
	public Responsable getResponsable() {
		return responsable;
	}
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	public boolean isEstadoBaja() {
		return EstadoBaja;
	}
	public void setEstadoBaja(boolean estadoBaja) {
		EstadoBaja = estadoBaja;
	}
	public String getFicha_Tecnica() {
		return Ficha_Tecnica;
	}
	public void setFicha_Tecnica(String ficha_Tecnica) {
		Ficha_Tecnica = ficha_Tecnica;
	}

}



