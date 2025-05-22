package GestionKitchease.gestKitchease.service;

public interface PlatInterface {

	
	public void creerPlat(String nom, String description, Double prix, String image, int quantite);
	public void supprimerPlat(Long id);
	public void modifierPlat(Long id);
	public void updateStockPlat();
}
