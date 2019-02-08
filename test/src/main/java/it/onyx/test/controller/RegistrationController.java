package it.onyx.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.onyx.test.dao.UserDao;
import it.onyx.test.form.UserForm;
import it.onyx.test.repository.UserRepository;
import it.onyx.test.service.UserService;
import it.onyx.test.util.Util2;


@Controller
public class RegistrationController {
	
	//@Autowired 
	//UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao ux;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
@RequestMapping(value="/registration")
public String ShowRegister(@ModelAttribute UserForm userForm, Model model) {
	return "registration";
 }


@RequestMapping(value="/registration", method= RequestMethod.POST)
public String register(@ModelAttribute UserForm userForm, Model model) {

	
	if(Util2.emailExist(userForm.getEmail())){
		model.addAttribute("Message", "EMAIL ALREADY IN USE !");
		return "/registration";
	} else {
	
	String email = userForm.getEmail();
	String password = userForm.getPassword();
	String firstName = userForm.getFirstName();
	String lastName = userForm.getLastName();
	String number = userForm.getNumber();
	String id = userForm.getId_cliente();
	
	
	if(firstName != null && lastName != null && number != null && email != null && password != null ) {
		if (firstName != "" && lastName != "" && number  != "" && email  != "" && password  != "") {
			
			
			ux.setCognome(lastName);ux.setNome(firstName);ux.setEmail(email);ux.setNum_telefono(number);ux.setPassword(password);
			//if(Util2.createUser(ux)) {
				userService.create(ux);
				model.addAttribute("Message", "REGISTRATION SUCCESSFUL");
				return "/user";
			//}else {
			//	model.addAttribute("Message", "SOMETHING WENT WRONG... TRY AGAIN !");
			//	return "/registration";
			//}
			
		} else { 
			model.addAttribute("Message", "ALL FIELDS REQUIRED");
			return "/registration";
		}
		}else {
			model.addAttribute("Message", "ALL FIELDS REQUIRED");
			return "/registration";
		}
	 }
	}


}
