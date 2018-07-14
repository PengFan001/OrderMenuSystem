package com.example.demo.respository;

import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findAllByPhone(String phone);

}
