package com.HUSRTbdBiomedica.app.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private int idProveedor;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;

    @Column(name = "correo_proveedor")
    private String correoProveedor;

    @Column(name = "ciudad_proveedor")
    private String ciudadProveedor;

    @Column(name = "nombre_representante")
    private String nombreRepresentante;

    @Column(name = "telefono_representante")
    private String telefonoRespresentante;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(String ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getTelefonoRespresentante() {
        return telefonoRespresentante;
    }

    public void setTelefonoRespresentante(String telefonoRespresentante) {
        this.telefonoRespresentante = telefonoRespresentante;
    }

}
