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
public class FirstController {
	

	@RequestMapping(value="index")
	public String getWelcome(Model model) {
		model.addAttribute("welcome", "Ciao Compa'");
		return "index";
		
	}
	@RequestMapping(value="/")
	public String getWelcome1( Model model) {
		model.addAttribute("saluti", "Ciao");
		model.addAttribute("welcome", "welcome to this webApp");
		return "index";
		
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String savePerson(@ModelAttribute UserForm userForm, Model model) {
//		model.addAttribute("userForm", userForm);
//		System.out.println(userForm.getName());
		System.out.println("sono nel get di user");
		return "user";
		
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String savePersonPost(@ModelAttribute UserForm userForm, Model model, RedirectAttributes redirectAttributes) {
		
		String email = userForm.getEmail();
		String password = userForm.getPassword();
		
		if( email != null && password != null ) {
			if (!email.equals("") && !password.equals("")) {
				if (Util2.emailExist(email)) {
					UserDao ud = Util2.login(email, password);
					redirectAttributes.addFlashAttribute("ud", ud);
					return "redirect:/home";
				} else {
					model.addAttribute("Message", "USER NOT FOUND");
					return "/user";					
			}
			}else {
				model.addAttribute("Message", "ALL FIELDS REQUIRED");
				return "/user";
			}
		} else {
			model.addAttribute("Message", "ALL FIELDS REQUIRED");
			return "/user";
		}
	
	}
	
	
}

