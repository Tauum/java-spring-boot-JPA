package com.example.javaspringboot.Submissions.Model;

import com.example.javaspringboot.Games.Model.Propagate;
import com.example.javaspringboot.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "Submitted_Propagate")
public class SubmittedPropagate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    @ManyToOne
    @JoinColumn(name="swipe_id")
    private Propagate propagate;
    private int timeTaken;

    private float score;

//    @OneToMany(fetch =  FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "submitted_propagate_chars",
//            joinColumns = { @JoinColumn(name = "submittedPropagate_id")},
//            inverseJoinColumns = { @JoinColumn(name = "char_id")})
//    @Column(name = "submitted_propagate_chars")
//    private Set<SubmittedPropogateChar> submittedPropogateCharSet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User user;

    private LocalDate generatedDate;

    public SubmittedPropagate(Propagate propagate, int timeTaken, User user, LocalDate generatedDate) {
        this.propagate = propagate;
        this.timeTaken = timeTaken;
        this.user = user;
        this.generatedDate = generatedDate;
    }

}
