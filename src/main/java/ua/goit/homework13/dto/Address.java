package ua.goit.homework13.dto;

import lombok.*;

@Builder
@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoLocation geo;
}
