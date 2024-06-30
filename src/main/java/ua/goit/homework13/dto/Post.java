package ua.goit.homework13.dto;

import lombok.*;
@Builder
@Data
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
