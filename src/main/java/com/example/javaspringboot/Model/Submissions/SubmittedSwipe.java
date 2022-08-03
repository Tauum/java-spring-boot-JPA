package com.example.javaspringboot.Model.Submissions;

import com.example.javaspringboot.Model.Games.Swipe;
import com.example.javaspringboot.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity //needed for database mapping
@Table(name = "submitted_Swipe")
public class SubmittedSwipe {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="swipe_id")
    private Swipe swipe;
    private int timeTaken;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User user;

    private LocalDate generatedDate;

    public SubmittedSwipe(Swipe swipe, int timeTaken, User user, LocalDate generatedDate) {
        this.swipe = swipe;
        this.timeTaken = timeTaken;
        this.user = user;
        this.generatedDate = generatedDate;
    }
}
