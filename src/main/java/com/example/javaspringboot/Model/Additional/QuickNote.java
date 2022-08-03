package com.example.javaspringboot.Model.Additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity //needed for database mapping
@Table(name = "note")
public class QuickNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long userId;
    public String userEmail;
    private String content;

    public QuickNote(Long userId, String userEmail, String content) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.content = content;
    }
}