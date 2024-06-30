package ua.goit.homework13.dto;

import lombok.*;

@Builder
@Data
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
