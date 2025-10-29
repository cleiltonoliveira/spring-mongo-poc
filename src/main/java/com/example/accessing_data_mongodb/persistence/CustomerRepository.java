package com.example.accessing_data_mongodb.persistence;

import java.util.List;

import com.example.accessing_data_mongodb.core.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public Customer findByFirstName(String firstName);
  public List<Customer> findByLastName(String lastName);

}