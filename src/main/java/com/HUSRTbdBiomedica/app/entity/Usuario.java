package com.HUSRTbdBiomedica.app.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long id_Usuario;
	
	@Column(name = "nombre")
	private String Nombre;
	
	@Column(name = "apellido")
	private String Apellido;
	
	@Column(name = "password")
	private String Password;
	
	@Column(name = "registro_invima")
	private  String Registro_invima;
	
	@Column(name = "cedula")
	private String Cedula;
	
	@Column(name = "tipo_cargo_usuario")
	private String Tipo_cargo_usuario;
	
	@Column(name = "estado")
	private boolean Estado;
	
	@Column(name = "area")
	private int Area;
	//Area biomedica 1
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",joinColumns = @JoinColumn(name = "id_usuario_fk"),inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> roles = new HashSet<>();
	
	@JoinColumn(name = "id_hospital_fk",referencedColumnName="id_Hospital")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private Hospital hospital;

	

	/********************* GET Y SET *****************************/
	
	
	public String getNombreCompleto() {
		return Nombre + " " + Apellido;
	}
	
	public static long getSerialVersionUID(){
		return serialVersionUID;
	}


	public Long getId_Usuario() {
		return id_Usuario;
	}


	public void setId_Usuario(Long id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	

	public String getApellido() {
		return Apellido;
	}


	public void setApellido(String apellido) {
		Apellido = apellido;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getRegistro_invima() {
		return Registro_invima;
	}


	public void setRegistro_invima(String registro_invima) {
		Registro_invima = registro_invima;
	}


	public String getCedula() {
		return Cedula;
	}


	public void setCedula(String cedula) {
		Cedula = cedula;
	}


	public String getTipo_cargo_usuario() {
		return Tipo_cargo_usuario;
	}


	public void setTipo_cargo_usuario(String tipo_cargo_usuario) {
		Tipo_cargo_usuario = tipo_cargo_usuario;
	}


	public boolean isEstado() {
		return Estado;
	}


	public void setEstado(boolean estado) {
		Estado = estado;
	}



	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Hospital getHospital() {
		return hospital;
	}


	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


	public Set<Authority> getAuthority() {
		return roles;
	}


	public void setAuthority(Set<Authority> roles) {
		this.roles = roles;
	}


	public int getArea() {
		return Area;
	}


	public void setArea(int area) {
		Area = area;
	}


	
	
}
