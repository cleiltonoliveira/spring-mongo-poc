package com.example.accessing_data_mongodb.controller;

import com.example.accessing_data_mongodb.controller.dto.CreateCustomerDTO;
import com.example.accessing_data_mongodb.controller.dto.CustomerResponseDTO;
import com.example.accessing_data_mongodb.controller.dto.UpdateCustomerDTO;
import com.example.accessing_data_mongodb.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CreateCustomerDTO customerDto) {
        var result = customerService.createCustomer(customerDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        var result = customerService.updateCustomer(id, updateCustomerDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        var customers = customerService.findAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getOneCustomer(@PathVariable String id) {
        var customer = customerService.findOneById(id);
        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> delete(@PathVariable String id) {
        customerService.removeCustomerById(id);
        return ResponseEntity.ok().build();
    }
}
