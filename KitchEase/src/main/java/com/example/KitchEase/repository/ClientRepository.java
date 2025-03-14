package com.example.KitchEase.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.KitchEase.entity.Client;


public interface ClientRepository extends CrudRepository <Client, Long> {

}
