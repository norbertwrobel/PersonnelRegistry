package org.example.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;


}
