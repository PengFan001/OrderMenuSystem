package com.example.demo.respository;

import com.example.demo.entities.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FoodRepository extends CrudRepository<Food, Long> {

    ArrayList<Food> findAll();

    ArrayList<Food> findAllByType(String type);

    Food findAllById(int id);
}
