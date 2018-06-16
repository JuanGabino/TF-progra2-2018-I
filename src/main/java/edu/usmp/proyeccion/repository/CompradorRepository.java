package edu.usmp.proyeccion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import edu.usmp.proyeccion.model.Comprador;

public interface CompradorRepository extends Repository<Comprador, Integer> {

	void save(Comprador model);

	List<Comprador> findAll();

	Comprador findById(int id);
}
