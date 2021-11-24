package com.ms.userservice.feignclients;

import com.ms.userservice.viewmodels.Car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "car-service", url = "http://localhost:8002")
@RequestMapping("/api/cars")
public interface CarFeignClient {
    @PostMapping
    Car post(@RequestBody Car model);
}
