package com.example.javaspringboot.Model.Submissions;

import com.example.javaspringboot.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "Submitted_Match")
public class SubmittedMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    public String matchTitle;
    public float matchValue;
    public Long matchId;
    private int incorrect;
    private int correct;
    private LocalDate generatedDate;
    private int timeTaken;
    private float score;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User user;

    public SubmittedMatch(String matchTitle, float matchValue, Long matchId, int incorrect, int correct, LocalDate generatedDate, int timeTaken, float score, User user) {
        this.matchTitle = matchTitle;
        this.matchValue = matchValue;
        this.matchId = matchId;
        this.incorrect = incorrect;
        this.correct = correct;
        this.generatedDate = generatedDate;
        this.timeTaken = timeTaken;
        this.score = score;
        this.user = user;
    }

}
