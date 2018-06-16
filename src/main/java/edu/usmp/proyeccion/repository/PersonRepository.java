package edu.usmp.proyeccion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import edu.usmp.proyeccion.model.Person;

public interface PersonRepository extends Repository<Person, Integer> {

	void save(Person model);

	List<Person> findAll();

	Person findById(int id);

}
