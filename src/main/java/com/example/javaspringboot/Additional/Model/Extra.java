package com.example.javaspringboot.Additional.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "Extra")
public class Extra {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String summary;
    public String content;
    public String author;
    public LocalDate generatedDate;
    public String video;
    public Boolean hidden;

    public Extra(String title, String summary, String content, String author, LocalDate generatedDate, String video, boolean hidden) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
        this.generatedDate = generatedDate;
        this.video = video;
        this.hidden = hidden;
    }

}

