package com.ms.carservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.carservice.repositories.CarRepository;
import com.ms.carservice.models.Car;

@Service
public class CarService {

    @Autowired
    CarRepository repository;

    public List<Car> all() {
        return repository.findAll();
    }

    public Car get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Car create(Car item) {
        return repository.save(item);
    }

    public List<Car> getByUserId(int userId) {
        return repository.findByUserId(userId);
    }
}
