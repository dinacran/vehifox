package com.boot.SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.SpringBoot.model.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{

    
}
