package GestionKitchease.gestKitchease.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import GestionKitchease.gestKitchease.entity.Plat;
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
	
	@GetMapping("/creation")
	public ModelAndView creation(Model model) {
		Iterable<Plat> plats= platService.getAllPlats();
		model.addAttribute("plats", plats);
	    return new ModelAndView("kitcheaseGestion/creation"); 
	}
	
	 @GetMapping("/modification")
	    public ModelAndView afficherPageRecherche() {
	        return new ModelAndView("kitcheaseGestion/modification");
	    }
	 
	 @GetMapping("/modifier")
	 public String afficherFormulaireModification(@RequestParam(required = false) String nom, Model model) {
	     if (nom != null && !nom.isEmpty()) {
	         try {
	             Plat plat = platService.getPlatByNom(nom);
	             model.addAttribute("platAModifier", plat);
	         } catch (RuntimeException e) {
	             model.addAttribute("erreur", e.getMessage());
	         }
	     }
	     return "kitcheaseGestion/modification"; 
	 }

	
	
	@PostMapping("/creerPlat")
	public String creerPlat(@RequestParam("nom") String nom,
            @RequestParam("prix") double prix, 
            @RequestParam("description") String description, 
            @RequestParam("image") MultipartFile image ) throws IOException {

	    platService.creerPlat(nom, prix, description, image);
	    
	    return "redirect:/kitcheaseGestion/creation";
	}
	
	
	
	@PostMapping("/modifierPlat")
	public String modifierPlat(@RequestParam("nom") String nom,
            @RequestParam("prix") double prix, 
            @RequestParam("description") String description, 
            @RequestParam("image") MultipartFile image)throws IOException {
	    platService.modifierPlat(nom, prix, description, image);
	    return "redirect:/kitcheaseGestion/modification"/*?nom=" + nom*/;
	}
	
	@PostMapping("/supprimerPlat")
	public RedirectView supprimerPlat() {
	    platService.supprimerPlat(null);
	    return new RedirectView("");
	}


	@GetMapping("/usermanagement")
    public ModelAndView usermanagement() {
        return new ModelAndView("kitcheaseGestion/usermanagement");
        // landing page view usermanagement.html
    }

    @GetMapping("/stockmanagement")
    public ModelAndView stockmanagement() {
        return new ModelAndView("kitcheaseGestion/stockmanagement"); 
        // landing page view stockmanagement.html
    }

}
