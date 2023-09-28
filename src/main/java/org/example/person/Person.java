package org.example.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.address.Address;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    private int index;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private Address address;
}
