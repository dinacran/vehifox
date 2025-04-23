package com.boot.SpringBoot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.SpringBoot.model.Vehicle;
import com.boot.SpringBoot.repository.VehicleRepo;

@Service
public class VehicleService {

    @Autowired
    VehicleRepo repo;

    public List<Vehicle> getVehicles() {
        return repo.findAll();
    }

    public Vehicle getVehicleById(int id) {
        return repo.findById(id).get();
    }


    public void insertOne(Vehicle obj) {
        repo.save(obj);
    }

    public void insertMany(List<Vehicle> obj) {
        repo.saveAll(obj);
    }

    public List<Integer> getIds() {
       return repo.findAll().stream().map(a -> a.getId()).collect(Collectors.toList());
    }

}
