package com.example.accessing_data_mongodb.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
