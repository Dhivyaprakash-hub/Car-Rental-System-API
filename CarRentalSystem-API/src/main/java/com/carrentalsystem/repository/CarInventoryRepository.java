package com.carrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalsystem.entity.CarInventory;

@Repository
public interface CarInventoryRepository extends JpaRepository<CarInventory, String> {

}
