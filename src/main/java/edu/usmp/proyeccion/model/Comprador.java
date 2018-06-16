package edu.usmp.proyeccion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comprador {

	@Id
	@GeneratedValue
	private Integer Id;

	@NotNull
	@Size(min = 2, max = 20)
	private String Nombre;

	@NotNull
	@Size(min = 2, max = 30)
	private String Estado;
	
	@NotNull
	@Size(min = 8, max = 8)
	private String Dni;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}
	
	

}
