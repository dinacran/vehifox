package com.boot.SpringBoot.controller;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boot.SpringBoot.model.Vehicle;
import com.boot.SpringBoot.service.VehicleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@CrossOrigin
@RestController
public class VehicleController {

    VehicleService service;
    
    @Autowired
    public void setService(VehicleService service){
        this.service = service;
    }
    @GetMapping("/")
    public String greet() {
        return "Welcome to the app";
    }
    
    @GetMapping("vehicles")
    public List<Vehicle> getallVehicles() {
        return service.getVehicles();
    }

    @GetMapping("vehicle")
    public Vehicle getOne() {
        return service.getVehicles().get(0);
    }
    

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addObjects(@RequestBody List<Vehicle> obj) {
        service.insertMany(obj);

    }

    @GetMapping("/ids")
    public List<Integer> getMethodName() {
       return service.getIds();
    }

    
}
