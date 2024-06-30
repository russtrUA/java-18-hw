package ua.goit.homework13.dto;

import lombok.*;

@Data
@Builder
public class Task {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
