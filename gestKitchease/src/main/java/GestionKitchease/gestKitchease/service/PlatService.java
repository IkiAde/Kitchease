package GestionKitchease.gestKitchease.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import GestionKitchease.gestKitchease.entity.Plat;
import GestionKitchease.gestKitchease.repository.PlatRepository;

@Service
public class PlatService implements PlatInterface{
	
	@Autowired
	private PlatRepository platRepo;
	
	@Autowired
	private PlatProducer platProducer;
	
	@Override
	public void creerPlat(String nom, Double prix, String description, MultipartFile image) throws IOException{
		Optional<Plat> optPlat= platRepo.findbyNom(nom);
		if (optPlat.isPresent()) {	
	    	throw new RuntimeException("Ce plat existe déja");
		}
		else {
			var plat= new Plat();
			plat.setNom(nom);
			plat.setPrix(prix);
			plat.setDescription(description);
			plat.setImageData(image.getBytes());
			plat.setImageContentType(image.getContentType());
			platRepo.save(plat);
			//platProducer.sendPlatUpdate(plat);
			
		}
		
	}
	
	 /*private String convertToImageUrl(Plat plat) {
	        return "/kitcheaseGestion/" + plat.getIdPlat() + "/image";
	    }*/

	@Override
	public void supprimerPlat(Long id) {
		this.platRepo.deleteById(id);
		
	}

	@Override
	public void modifierPlat(String nom,Double newPrix, String newDescription, MultipartFile newImage) throws IOException {
		Optional<Plat> optPlat= platRepo.findbyNom(nom);
		if (optPlat.isPresent()) {	
			  Plat platToModify = optPlat.get();
			  platToModify.setPrix(newPrix);
			  platToModify.setDescription(newDescription);
			  platToModify.setImageData(newImage.getBytes());
			  platToModify.setImageContentType(newImage.getContentType());
			  
		}
		else {
			throw new RuntimeException("Ce plat n'existe pas");
		}
	}

	@Override
	public void updateStockPlat() {
		// TODO Auto-generated method stub
		
	}

	public Plat getPlatById(Long id) {
	
		return this.platRepo.findById(id).get();
	}

	public Iterable<Plat> getAllPlats() {
		return this.platRepo.findAll();
	}
	
	public Plat getPlatByNom(String nom) {
	    return platRepo.findbyNom(nom)
	                   .orElseThrow(() -> new RuntimeException("Ce plat n'existe pas"));
	}


}
