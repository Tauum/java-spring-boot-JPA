package com.example.javaspringboot.Model.Games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    public String title;
    @OneToMany(fetch =  FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "quiz_questions",
            joinColumns = { @JoinColumn(name = "quiz_id")},
            inverseJoinColumns = { @JoinColumn(name = "question_id")})
    @Column(name = "questions")
    public Set<Question> questions;
    private LocalDate generatedDate;
    public int timeLimit;
    public float value;
    public boolean hidden;
    public String subject;

    public Quiz(String title, Set<Question> questions, LocalDate generatedDate, int timeLimit, float value, boolean hidden, String subject) {
        this.title = title;
        this.questions = questions;
        this.generatedDate = generatedDate;
        this.timeLimit = timeLimit;
        this.value = value;
        this.hidden = hidden;
        this.subject = subject;
    }

    public void setGeneral(String title, int timeLimit, float value, boolean hidden, String subject){
        this.title = title;
        this.timeLimit =timeLimit;
        this.value =value;
        this.hidden = hidden;
        this.subject =subject;
    }

}

