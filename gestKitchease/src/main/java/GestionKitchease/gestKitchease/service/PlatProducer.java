package GestionKitchease.gestKitchease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import GestionKitchease.gestKitchease.entity.Plat;



@Service
public class PlatProducer {

	
	

	    private final KafkaTemplate<String, Plat> kafkaTemplate;

	    @Autowired
	    public PlatProducer(KafkaTemplate<String, Plat> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	    public void sendPlatUpdate(Plat plat) {
	        kafkaTemplate.send("plats-updates", plat); // Envoie de la mise à jour à Kafka
	    }
	

}