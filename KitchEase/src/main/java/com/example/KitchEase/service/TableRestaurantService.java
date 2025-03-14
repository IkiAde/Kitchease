package com.example.KitchEase.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KitchEase.entity.TableRestaurant;
import com.example.KitchEase.repository.TableRestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class TableRestaurantService {
	
	 	@Autowired
	    private TableRestaurantRepository tableRepository;

	    @Transactional
	    public void createRandomTables(int numberOfTables) {
	        Random random = new Random();
	        for (int i = 0; i < numberOfTables; i++) {
	            int capacite = random.nextInt(12) + 1; 
	            TableRestaurant table = new TableRestaurant(capacite);
	            tableRepository.save(table);
	        }
	    }
}
