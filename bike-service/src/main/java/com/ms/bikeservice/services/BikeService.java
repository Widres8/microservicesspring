package com.ms.bikeservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.bikeservice.repositories.BikeRepository;
import com.ms.bikeservice.models.Bike;

@Service
public class BikeService {

    @Autowired
    BikeRepository repository;

    public List<Bike> all() {
        return repository.findAll();
    }

    public Bike get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Bike create(Bike item) {
        return repository.save(item);
    }

    public List<Bike> getByUserId(int userId) {
        return repository.findByUserId(userId);
    }
}
