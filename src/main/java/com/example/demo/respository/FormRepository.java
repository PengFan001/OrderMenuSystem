package com.example.demo.respository;

import com.example.demo.entities.Form;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FormRepository extends CrudRepository<Form, Long> {
    ArrayList<Form> findFormByPhone(String phone);
    Form findFormByIdAndPhone(int id, String phone);
}
