package com.example.accessing_data_mongodb.service;

import com.example.accessing_data_mongodb.controller.dto.CreateCustomerDTO;
import com.example.accessing_data_mongodb.controller.dto.CustomerDTO;
import com.example.accessing_data_mongodb.controller.dto.UpdateCustomerDTO;
import com.example.accessing_data_mongodb.core.entity.Customer;
import com.example.accessing_data_mongodb.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        var customerEntity = modelMapper.map(createCustomerDTO, Customer.class);
        var savedCustomerEntity = customerRepository.save(customerEntity);
        return modelMapper.map(savedCustomerEntity, CustomerDTO.class);
    }

    public List<CustomerDTO> findAllCustomers() {
        var customers = customerRepository.findAll();
        return customers.stream().map(c -> modelMapper.map(c, CustomerDTO.class)).toList();
    }

    public CustomerDTO findOneById(String id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public void removeCustomerById(String id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO updateCustomer(String id, UpdateCustomerDTO updateCustomerDTO) {

        var customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity == null) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }

        customerEntity.setFirstName(updateCustomerDTO.getFirstName());
        customerEntity.setLastName(updateCustomerDTO.getLastName());

        var savedCustomer = customerRepository.save(customerEntity);

        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }
}
