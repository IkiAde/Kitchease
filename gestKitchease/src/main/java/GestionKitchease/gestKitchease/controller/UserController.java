package com.example.KitchEase.controller;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.KitchEase.entity.User;
import com.example.KitchEase.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public RedirectView createUser(
    @RequestParam String firstname,
    @RequestParam String lastname,
    @RequestParam String email,
    @RequestParam String password,
    @RequestParam String unit,
    @RequestParam String access,
    RedirectAttributes redirectAttributes,
    HttpSession session) {

        // create usernname format jane.doe
        String username = firstname.toLowerCase() + "." + lastname.toLowerCase();
        System.out.println("----------------------------------------------------------------" + username);

        // check if the email already exists in the database
        if(userService.existsById(username)){
            redirectAttributes.addFlashAttribute("errorMessage","Ce compte existe deja!");
            System.out.println("----------------------------------------------------------------");
            System.out.println("==========> Email already exist  " + username);
            return new RedirectView("/kitcheaseGestion/usermanagement");
        }

        userService.createUser(username, firstname, lastname, email, password, unit, access);
        redirectAttributes.addFlashAttribute("creationOk", "Compte creer avec succes");


        return new RedirectView("/user/userManagement");
    }


    @GetMapping("/createUser")
    public ModelAndView createUserForm(Model model) {
        // Check if the user is logged in
        return new ModelAndView("/kitcheaseGestion/userManagement");
    }

    @PostMapping("/login")
	public ModelAndView login(
		@RequestParam String username,
		@RequestParam String password,
		Model model,
		HttpSession session) {
			
		Optional<User> usr = userService.findByUserName(username);


		if(usr.isEmpty()) {
			model.addAttribute("error","username or password incorrect");
			return new ModelAndView("/kitcheaseGestion/home");
		}
		else {

			if(usr.isPresent() && usr.get().getPassword().equals(password)) {
                
                session.setAttribute("user_id", usr.get().getUserId());
				session.setAttribute("user_name", usr.get().getUserName());
				session.setAttribute("user_prenom", usr.get().getFirstName());
	
				model.addAttribute("success","Loggin successful");
	
				//printing user_name
				System.out.println("----------------------------------------------------------------");
				System.out.println("========> email logged in "+usr.get().getEmail());	
	
				//changement *******
				return new ModelAndView("redirect:/user/userManagement");
			}
			else {
				model.addAttribute("error","Email or password incorrect");
				return new ModelAndView("/kitcheaseGestion/home");
			
			}
		}

	
	}

    @GetMapping("/userManagement")
    public ModelAndView userManagement( HttpSession session) {
       

        // Check if the user is logged in
        if (session.getAttribute("user_name") == null) {
            return new ModelAndView("redirect:/kitcheaseGestion/home"); // Redirect to login page if not logged in
        }

        Iterable<User> user = userService.findAllUsers();
        Map<String, Object> model = Map.of("user", user);

        return new ModelAndView("/kitcheaseGestion/userManagement", model); // Return the user management view with the user data
    }

    //logout section
	@PostMapping("/logout")
	
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("kitcheaseGestion/home"); 
    }

    
   
}
