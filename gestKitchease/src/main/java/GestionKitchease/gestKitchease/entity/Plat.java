package GestionKitchease.gestKitchease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Plat {
	

	

		private String nom;
	    private Double prix;
	    private String description;
	    
	    @Lob
	    @Column(name = "image_data")
	    private byte[] imageData;
	    
	    private String imageContentType; 

	    
	    public Plat(){};
	    
	    public byte[] getImageData() {
			return imageData;
		}

		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
		}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idPlat;

	    public Long getIdPlat() {
			return idPlat;
		}
	    
	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public Double getPrix() {
	        return prix;
	    }

	    public void setPrix(Double prix) {
	        this.prix = prix;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }


		public String getImageContentType() {
			return imageContentType;
		}

		public void setImageContentType(String imageContentType) {
			this.imageContentType = imageContentType;
		}
}

