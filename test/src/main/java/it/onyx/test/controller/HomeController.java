package it.onyx.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.onyx.test.dao.UserDao;
import it.onyx.test.form.UserForm;
import it.onyx.test.util.Util2;

@Controller
public class HomeController {
	
	
	@RequestMapping(value="/home")
	public String showHome(@ModelAttribute UserForm userForm, Model model, @ModelAttribute("ud") UserDao ud) {
		
		System.out.println("get di home");
		System.out.println(ud.getEmail());
		model.addAttribute("firstName", ud.getNome());
		model.addAttribute("lastName", ud.getCognome());
		model.addAttribute("email", ud.getEmail());
		model.addAttribute("number", ud.getNum_telefono());
		model.addAttribute("password", ud.getPassword());
		model.addAttribute("id", ud.getId_cliente());

			return "home";
		
	}

}
