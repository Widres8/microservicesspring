package com.ms.userservice.feignclients;

import com.ms.userservice.config.FeignMappingDefaultConfiguration;
import com.ms.userservice.viewmodels.Car;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// @FeignClient(name = "car-service", url = "http://localhost:8002")
// @RequestMapping()
@FeignClient(name = "car-service", configuration = FeignMappingDefaultConfiguration.class) // Eureka discover don´t need
                                                                                           // url
public interface CarFeignClient {
    @PostMapping("/api/cars")
    Car post(@RequestBody Car model);
}
