package it.onyx.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.onyx.test.dao.UserDao;
import it.onyx.test.form.UserForm;
import it.onyx.test.util.Util2;

@Controller
public class RegistrationController {
	
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
			
			UserDao ux = new UserDao(firstName, lastName, number, email, password, "0");
			if(Util2.createUser(ux)) {
				model.addAttribute("Message", "REGISTRATION SUCCESSFUL");
				return "/user";
			}else {
				model.addAttribute("Message", "SOMETHING WENT WRONG... TRY AGAIN !");
				return "/registration";
			}
			
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
