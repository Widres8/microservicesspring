package com.ms.carservice.controller;

import java.util.List;

import com.ms.carservice.models.Car;
import com.ms.carservice.services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cars")
public class CarsController {

    @Autowired
    CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> get() {
        var list = service.all();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> get(@PathVariable int id) {
        var item = service.get(id);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Car> post(@RequestBody Car model) {
        var item = service.create(model);

        if (item == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(item);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable int userId) {
        var list = service.getByUserId(userId);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }
}
