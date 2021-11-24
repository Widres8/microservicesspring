package com.ms.userservice.services;

import java.util.List;

import com.ms.userservice.feignclients.CarFeignClient;
import com.ms.userservice.models.User;
import com.ms.userservice.repositories.UserRepository;
import com.ms.userservice.viewmodels.Bike;
import com.ms.userservice.viewmodels.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    public List<User> all() {
        return repository.findAll();
    }

    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    public User create(User item) {
        return repository.save(item);
    }

    public List<Car> getCars(int userId) {
        var list = restTemplate.getForObject("http://car-service/api/cars/byUser/" + userId, Car[].class);
        return List.of(list);
    }

    public List<Bike> getBikes(int userId) {
        var list = restTemplate.getForObject("http://bike-service/api/bikes/byUser/" + userId, Bike[].class);
        return List.of(list);
    }

    public Car createCar(int userId, Car item) {
        item.setUserId(userId);
        return carFeignClient.post(item);
    }
}
