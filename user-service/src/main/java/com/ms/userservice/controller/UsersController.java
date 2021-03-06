package com.ms.userservice.controller;

import java.util.List;

import com.ms.userservice.models.User;
import com.ms.userservice.services.UserService;
import com.ms.userservice.viewmodels.Bike;
import com.ms.userservice.viewmodels.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> get() {
        var list = service.all();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        var item = service.get(id);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<User> get(@RequestBody User model) {
        var item = service.create(model);

        if (item == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(item);
    }

    @CircuitBreaker(name = "cars-cb", fallbackMethod = "fallbackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable int userId) {
        var item = service.get(userId);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        var list = service.getCars(userId);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @CircuitBreaker(name = "bikes-cb", fallbackMethod = "fallbackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable int userId) {
        var item = service.get(userId);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        var list = service.getBikes(userId);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable int userId, RuntimeException ex) {
        return new ResponseEntity("Error al conectar al servicio", HttpStatus.OK);
    }

    private ResponseEntity<List<Bike>> fallbackGetBikes(@PathVariable int userId, RuntimeException ex) {
        return new ResponseEntity("Error al conectar al servicio", HttpStatus.OK);
    }
}
