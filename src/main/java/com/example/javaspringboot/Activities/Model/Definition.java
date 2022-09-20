package com.example.javaspringboot.Activities.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "definition")
public class Definition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    public String title;
    public String explaination;
    public float value;

    public Definition(String title, String explaination, float value) {
        this.title = title;
        this.explaination = explaination;
        this.value = value;
    }

    public void setGeneral(String title, String explaination, float value){
        this.title = title;
        this.explaination = explaination;
        this.value = value;
    }
}
