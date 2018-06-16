package edu.usmp.proyeccion.web;

import java.util.List;
import java.util.Map;
import java.lang.Math;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.usmp.proyeccion.mail.MailService;
import edu.usmp.proyeccion.model.Comprador;
import edu.usmp.proyeccion.model.Person;
import edu.usmp.proyeccion.repository.CompradorRepository;
import edu.usmp.proyeccion.repository.PersonRepository;

@Controller
public class PersonController {
	

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private CompradorRepository compradorRepository;
	
	
	private Person calcularImpuesto(Person person)
	{
		double uit = 4150;
		double ingAnual = 0;
		double montoUit = 0;
		double rentaNeta = 0;


		double montoAfecto = 0;
		double impuesto = 0;
		
		double ant = 5 * uit;
		double ant1 = 20 * uit- 5*uit;
		double ant2 = 35 * uit- (ant + ant1);
		
		double i1=0;
		double i2=0;
		double i3=0;
		double i4=0;
		double i5=0;
		
		String infocorp=""; 
		double aleatorio = 0;
		//double aleatorio = 0;

		// if (person.getTipo() == "1") {
		// ingAnual = person.getSalario() * 12;
		// } else if(person.getTipo()=="2"){
		// ingAnual = person.getSalario() * 14;
		// }
		
	
		switch (person.getTipo()) {
		case "Independiente":
			ingAnual = person.getSalario() * 12;
			break;

		case "Dependiente":
			ingAnual = person.getSalario() * 14;
			break;
		}

		montoUit = 4150 * 7;
		rentaNeta = ingAnual - montoUit;
		person.setRentaNeta(rentaNeta);
		aleatorio = Math.round(Math.random()*1001);
		 System.out.print(aleatorio);
		
		if(aleatorio>400)
		{
			infocorp = "SI";
			person.setInfocorp(infocorp);
		}
		else
		{
			infocorp = "NO";
			person.setInfocorp(infocorp);
		}
        
        
		if (ingAnual <= montoUit) {

			person.setRentaNeta(0);
			person.setIngAnual(ingAnual);
			person.setImpuesto(0);

		} else {
			if (rentaNeta <= (5 * uit)) {

				montoAfecto = 5 * uit;
				//montoAfecto=rentaNeta;
				impuesto = Math.round(montoAfecto * 0.08);
				
				impuesto=5*uit*0.08;
				i1=5*uit*0.08;
				
				person.setIngAnual(ingAnual);
//				person.setImpuesto(i1);

			} else if (rentaNeta <= (20 * uit) && rentaNeta > (5 * uit)) {
//				double ant = 5 * uit;

				montoAfecto = 20 * uit - ant;

				impuesto = Math.round(montoAfecto * 0.14 );
				
				i2=5*uit*0.08 + (rentaNeta-5*uit)*0.14;
				
				person.setIngAnual(ingAnual);
//				person.setImpuesto(i2);

			} else if (rentaNeta <= (35 * uit) && rentaNeta > (20 * uit)) {

				montoAfecto = 35 * uit - (ant + ant1);

				impuesto = Math.round(montoAfecto * 0.17);
				i3=(5*uit*0.08) + ((20*uit-5*uit)*0.14) + ((rentaNeta-(20*uit-5*uit)-(5*uit))*0.17);
				

				person.setIngAnual(ingAnual);
//				person.setImpuesto(i3);
				
			} else if (rentaNeta <= (45 * uit) && rentaNeta > (35 * uit)) {


				montoAfecto = 45 * uit - (ant + ant1 + ant2);

				impuesto = Math.round(montoAfecto * 0.20);	
				i4=montoAfecto * 0.20;
				

				person.setIngAnual(ingAnual);
//				person.setImpuesto(i4);

			} else if (rentaNeta > (45 * uit)) {
				montoAfecto = rentaNeta - (45 * uit);

				impuesto = Math.round(montoAfecto * 0.30);
				
				i5=montoAfecto * 0.30;
				
				person.setIngAnual(ingAnual);
//				person.setImpuesto(i5);
			}

			// person.setRentaNeta(rentaNeta);
			// person.setIngAnual(ingAnual);
			person.setImpuesto(i1+i2+i3+i4+i5);

		}
		
		return person;
	}
	
	@GetMapping("/promociones")
	public String mostrar(Model model) {
		
		model.addAttribute("comprador", new Comprador());
		
		return "Promociones";
	}
	
	@PostMapping("/comprador/promociones")
	public String submitForm(@ModelAttribute Comprador comprador)
	{
		String estado = "Comprado";
		comprador.setEstado(estado);
        compradorRepository.save(comprador);
		
		return "redirect:/comprador/lista";
	}
	
	@GetMapping("/comprador/lista")
	public String listComprador(Map<String, Object> model) {
		List<Comprador> compradors = compradorRepository.findAll();
		model.put("compradors", compradors);
		return "listaComprador";
	}
	
	@GetMapping("/comprador/tablas")
	public String listTablas(Map<String, Object> model) {
		List<Comprador> compradors = compradorRepository.findAll();
		model.put("compradors", compradors);
		List<Person> persons = personRepository.findAll();
		model.put("persons", persons);
		
		return "listaTablas";
	}
	
	@GetMapping("/person/new")
	public String initCreationForm(Model model) {
		model.addAttribute("person", new Person());
		return "Form";
	}

	@PostMapping
	public String submitForm(@Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			return "Form";
		}
		
		person = calcularImpuesto(person);
		
		String body="Hola "+person.getNombre() + ",\n\nHemos evaluado su historial crediticio \r\n" + 
				"y le ofrecemos las siguientes promociones: " + 
				"\nhttp://localhost:8081/promociones";
		
		if(person.getInfocorp().equals("NO")) {
			mailService.sendMail("cambiarcorreo@gmail.com", person.getEmail(), "TRIBUTO", body);	
		}
		
		
		return "Impuesto";
	}
	
	
	
	@PostMapping("/person/impuesto")
	private String guardar(@ModelAttribute Person person) {

		personRepository.save(person);
		
		return "redirect:/person/lista";
	}
	
	
	@GetMapping("/person/lista")
	public String list(Map<String, Object> model) {
		List<Person> persons = personRepository.findAll();
		model.put("persons", persons);
		return "list";
	}
	
	
	
}
