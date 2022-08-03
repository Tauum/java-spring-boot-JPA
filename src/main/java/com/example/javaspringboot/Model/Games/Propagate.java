package com.example.javaspringboot.Model.Games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "Propagate")
public class Propagate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Long id;

    public String title;
    public String explaination;
    public String answer;
    public float value;
    private LocalDate generatedDate;
    public boolean hidden;
    public String subject;

    public Propagate(String title, String explaination, String answer, float value, LocalDate generatedDate, boolean hidden, String subject) {
        this.title = title;
        this.explaination = explaination;
        this.answer = answer;
        this.value = value;
        this.generatedDate = generatedDate;
        this.hidden = hidden;
        this.subject = subject;
    }

    public void setGeneral(String title, String explaination, float value, boolean hidden, String subject, String answer){
        this.title = title;
        this.explaination = explaination;
        this.value = value;
        this.hidden = hidden;
        this.subject = subject;
        this.answer = answer;
    }
}
