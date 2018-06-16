package edu.usmp.proyeccion.mail;

import edu.usmp.proyeccion.mail.MailService;
import edu.usmp.proyeccion.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {

    @Autowired
    private MailService mailService;
    
//    @Autowired
//    private Person person;

    @GetMapping("/new")
    public String index(){
        return "send_mail_view";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, 
    		@RequestParam("subject") String subject, 
    		@RequestParam("body") String body){

        String message = body +"\n\n Datos de contacto: " + "\nNombre: " + name + "\nE-mail: " + mail;
        mailService.sendMail("correo@gmail.com","correodestino@gmail.com",subject,message);

        return "send_mail_view";
    }
}
