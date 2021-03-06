package com.ms.userservice.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bike {

    private int id;
    private String brand;
    private String model;
    private int userId;

}
