package com.example.demo.respository;

import com.example.demo.entities.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, String> {

    Worker findAllByAccounts(String accounts);

}
