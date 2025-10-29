package com.example.accessing_data_mongodb.service;

import com.example.accessing_data_mongodb.controller.CustomerDTO;
import com.example.accessing_data_mongodb.core.entity.Customer;
import com.example.accessing_data_mongodb.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO) {

        var customerEntity = new Customer(customerDTO.getFirstName(), customerDTO.getLastName());
        return customerRepository.save(customerEntity);
    }
}
