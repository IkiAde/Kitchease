package GestionKitchease.gestKitchease.controller;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import GestionKitchease.gestKitchease.entity.Employee;
import GestionKitchease.gestKitchease.service.EmployeeService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")

public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createUser")
    public RedirectView createUser(
    @RequestParam String firstname,
    @RequestParam String lastname,
    @RequestParam String email,
    @RequestParam String password,
    @RequestParam String status,
    @RequestParam String access,
    RedirectAttributes redirectAttributes,
    HttpSession session) {

        // create usernname format jane.doe
        String username = firstname.toLowerCase() + "." + lastname.toLowerCase();
        System.out.println("----------------------------------------------------------------" + username);


        // check if the email already exists in the database
        Optional<Employee> existingUser = employeeService.findByUserName(username);
    

        if (existingUser.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Ce compte existe deja!");
            System.out.println("==========> Username already exists: " + username);
            return new RedirectView("/user/userManagement");
        }


        employeeService.createUser(username, firstname, lastname, email, password, status, access);
        redirectAttributes.addFlashAttribute("success", "Compte creer avec succes");


        return new RedirectView("/user/userManagement");
    }


    @GetMapping("/employee")
    public ModelAndView createUserForm(Model model) {
      
        return new ModelAndView("/kitcheaseGestion/userManagement");
    }

    @PostMapping("/login")
	public ModelAndView login(
		@RequestParam String userName,
		@RequestParam String password,
		Model model,
		HttpSession session) {
			
		Optional<Employee> usr = employeeService.findByUserName(userName);
            System.out.println("---------------------------------->>>>>>>>>>>>>>"+usr);

		if(usr.isEmpty()) {
			model.addAttribute("error","username or password incorrect");
			return new ModelAndView("/kitcheaseGestion/home");
		}
		else {

			if(usr.isPresent() && usr.get().getPassword().equals(password)) {
                
                //Redirection upon user access type

                var sessUserId= usr.get().getEmployeeId();
                var sessUserName = usr.get().getUserName();
                var sessUserFirstName = usr.get().getFirstName();
                var sessUserLastName = usr.get().getLastName();
                var sessUserAccess = usr.get().getAccess();
                var sessUserStatus = usr.get().getStatus();
                
                session.setAttribute("sessUserId", sessUserId);
                session.setAttribute("sessUserAccess", sessUserAccess);
                session.setAttribute("sessUserName", sessUserName);
                session.setAttribute("sessUserFirstName", sessUserFirstName);
                session.setAttribute("sessUserLastName", sessUserLastName);
                session.setAttribute("sessUserStatus", sessUserStatus);

                if(sessUserStatus.equals("inactif")) {
                    model.addAttribute("errorNotActive","Account is inactive! please contact the administrator");
                    return new ModelAndView("/kitcheaseGestion/home");
                   
                }
                else{

                    // Redirect to the appropriate page based on user access level                   
                    if (sessUserAccess.equals("admin")) {
                        return new ModelAndView("redirect:/user/userManagement");
                        //changement *******
                    } else if (sessUserAccess.equals("serveur")) {

                        return new ModelAndView("kitcheaseGestion/serveur/home");

                    } else if (sessUserAccess.equals("cuisinier")) {

                        return new ModelAndView("kitcheaseGestion/cuisinier/home");
                        
                    }else{
                        
                        model.addAttribute("error","Access not authorized");
                        return new ModelAndView("/kitcheaseGestion/home");
                    }

                }
	
				
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
        if (session.getAttribute("sessUserName") == null) {
            return new ModelAndView("redirect:/kitcheaseGestion/home"); // Redirect to login page if not logged in
        }

        Iterable<Employee> employee = employeeService.findAll();
        Map<String, Object> model = Map.of("employeesList", employee);

        return new ModelAndView("/kitcheaseGestion/admin/usermanagement", model); // Return the admin user management view with the user data
    }


    // delete section 

    @GetMapping("/deleteEmployee/{employeeId}")
    public RedirectView deleteEmployee(
        @PathVariable Long employeeId,
        HttpSession session
    ){
        
        if(session.getAttribute("sessUserName") == null) {

            return new RedirectView("/kitcheaseGestion/home"); // Redirect to login page if not logged in
        }
        else if( !session.getAttribute("sessUserAccess").equals("admin") ) {
            // Check if the user is an admin
            return new RedirectView("/kitcheaseGestion/home"); // Redirect to login page if not admin         }
       
        }

        employeeService.deleteById(employeeId);
        System.out.println("check ======> employeeId: In deleted employee " + employeeId);

        return new RedirectView("/user/userManagement");
    } 


   @PostMapping("/updateEmployee/{employeeId}")
    public RedirectView updateEmployee(
        @PathVariable Long employeeId,
        @RequestParam String firstName,  
        @RequestParam String lastName,   
        @RequestParam String userName,   
        @RequestParam String email,
        @RequestParam(required = false) String password,  //  optional for updates
        @RequestParam String status,
        @RequestParam String access,
        HttpSession session,
        RedirectAttributes redirectAttributes  
    ) {
        
       
        if(session.getAttribute("sessUserName") == null) {
            return new RedirectView("/kitcheaseGestion/home");
        }
        
   
        if(!session.getAttribute("sessUserAccess").equals("admin")) {
            return new RedirectView("/kitcheaseGestion/home");
        }

        try {
           
            employeeService.updateUser(employeeId, userName, firstName, lastName, email, password, status, access);
            
            System.out.println("Successfully updated employee with ID: " + employeeId);
            
        
            redirectAttributes.addFlashAttribute("success", "Utilisateur modifié avec succès!");
            
        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
            e.printStackTrace();
            
          
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la modification de l'utilisateur: " + e.getMessage());
        }

        return new RedirectView("/user/userManagement");
    }



    //logout section
	@PostMapping("/logout")
	
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("kitcheaseGestion/home"); 
    }

    
   
}
