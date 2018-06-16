package edu.usmp.proyeccion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(min = 8, max = 8)
	private String dni;

	@NotNull
	@Size(min = 2, max = 30)
	private String nombre;

	@NotNull
	private String tipo;
	private String infocorp;
	
	@NotNull
	private String ubicacion;
	
	@NotNull
	private String hobbie;
	
	@NotNull
	private String genero;
	
	@NotNull
	private String email;

	@NotNull
	private double salario;

	private double uit;
	private double montoUit;
	
	private double rentaNeta;
	private double ingAnual;
	private double impuesto;
	
	
	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public double getUit() {
		return uit;
	}

	public void setUit(double uit) {
		this.uit = uit;
	}

	public double getMontoUit() {
		return montoUit;
	}

	public void setMontoUit(double montoUit) {
		this.montoUit = montoUit;
	}

	public double getRentaNeta() {
		return rentaNeta;
	}

	public void setRentaNeta(double rentaNeta) {
		this.rentaNeta = rentaNeta;
	}

	public double getIngAnual() {
		return ingAnual;
	}

	public void setIngAnual(double ingAnual) {
		this.ingAnual = ingAnual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getHobbie() {
		return hobbie;
	}

	public void setHobbie(String hobbie) {
		this.hobbie = hobbie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfocorp() {
		return infocorp;
	}

	public void setInfocorp(String infocorp) {
		this.infocorp = infocorp;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	


}
