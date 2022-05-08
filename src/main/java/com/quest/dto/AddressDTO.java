package com.quest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AddressDTO {
    private String pin;
    private String city;
    private String state;
    private String country;
    private String flatNumber;
    private String addressLine1;
    private String addressLine2;

    public AddressDTO(String pin, String city, String state, String country, String flatNumber, String addressLine1, String addressLine2) {
        this.pin = pin;
        this.city = city;
        this.state = state;
        this.country = country;
        this.flatNumber = flatNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }
}
