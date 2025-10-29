package com.example.accessing_data_mongodb.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerDTO {
    private String firstName;
    private String lastName;
}
