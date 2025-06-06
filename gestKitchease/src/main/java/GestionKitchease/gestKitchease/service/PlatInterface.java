package GestionKitchease.gestKitchease.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PlatInterface {

	
	public void creerPlat(String nom, Double prix, String description, MultipartFile image) throws IOException;
	public void supprimerPlat(Long id);
	public void modifierPlat(String nom,Double prix, String description, MultipartFile image)throws IOException;
	public void updateStockPlat();
}
