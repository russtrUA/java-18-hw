package ua.goit.homework13.dto;

import lombok.*;

@Builder
@Data
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
