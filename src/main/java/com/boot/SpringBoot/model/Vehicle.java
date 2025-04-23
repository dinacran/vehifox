package com.boot.SpringBoot.model;


import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Component
@Data
@Entity
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String model;
    private String brand;
    private String displacement;
    private String power;
    private String bore;
    private String stroke;
    private String compressionRatio;
    private String price;
}
