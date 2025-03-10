package com.example.KitchEase.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.KitchEase.entity.TableRestaurant;

import java.util.List;

public interface TableRepository extends CrudRepository<TableRestaurant, Long> {
    List<TableRestaurant> findByCapaciteGreaterThanEqual(int capacite);
}
