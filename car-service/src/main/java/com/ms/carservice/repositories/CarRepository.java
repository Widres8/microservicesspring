package com.ms.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ms.carservice.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByUserId(int userId);
}
