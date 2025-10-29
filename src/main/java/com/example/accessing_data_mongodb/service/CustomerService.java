package com.example.accessing_data_mongodb.service;

import com.example.accessing_data_mongodb.controller.dto.CreateCustomerDTO;
import com.example.accessing_data_mongodb.controller.dto.CustomerResponseDTO;
import com.example.accessing_data_mongodb.controller.dto.UpdateCustomerDTO;
import com.example.accessing_data_mongodb.core.entity.Customer;
import com.example.accessing_data_mongodb.exception.ResourceNotFoundException;
import com.example.accessing_data_mongodb.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResponseDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        var customerEntity = modelMapper.map(createCustomerDTO, Customer.class);
        var savedCustomerEntity = customerRepository.save(customerEntity);

        return modelMapper.map(savedCustomerEntity, CustomerResponseDTO.class);
    }

    public List<CustomerResponseDTO> findAllCustomers() {
        var customers = customerRepository.findAll();
        return customers.stream().map(c -> modelMapper.map(c, CustomerResponseDTO.class)).toList();
    }

    public CustomerResponseDTO findOneById(String id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public void removeCustomerById(String id) {
        customerRepository.deleteById(id);
    }

    public CustomerResponseDTO updateCustomer(String id, UpdateCustomerDTO updateCustomerDTO) {

        var customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        customerEntity.setFirstName(updateCustomerDTO.getFirstName());
        customerEntity.setLastName(updateCustomerDTO.getLastName());

        var savedCustomer = customerRepository.save(customerEntity);

        return modelMapper.map(savedCustomer, CustomerResponseDTO.class);
    }
}
