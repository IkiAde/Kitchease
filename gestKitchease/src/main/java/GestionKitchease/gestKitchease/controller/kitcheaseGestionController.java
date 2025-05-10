package GestionKitchease.gestKitchease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import GestionKitchease.gestKitchease.service.PlatService;

@Controller
@RequestMapping("/kitcheaseGestion")
public class kitcheaseGestionController {

	
	@Autowired
	private PlatService platService;
	
	@GetMapping("/home")
	public ModelAndView home(Model model) {
		
	    return new ModelAndView("kitcheaseGestion/home"); 
	}
	
	@GetMapping("/actions")
	public ModelAndView actions(Model model) {
		
	    return new ModelAndView("kitcheaseGestion/actions"); 
	}
	
	
	@PostMapping("/creerPlat")
	public RedirectView creerPlat() {
	    platService.creerPlat(null, null, null, null, 0);
	    return new RedirectView("");
	}
	
	@PostMapping("/modifierPlat")
	public RedirectView modifierPlat() {
	    platService.modifierPlat(null);
	    return new RedirectView("");
	}
	
	@PostMapping("/supprimerPlat")
	public RedirectView supprimerPlat() {
	    platService.supprimerPlat(null);
	    return new RedirectView("");
	}
}
