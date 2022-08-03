package com.example.javaspringboot.Model.Submissions;


import com.example.javaspringboot.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity //needed for database mapping
@Table(name = "Submitted_Quiz")
public class SubmittedQuiz {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    private String quizTitle;
    private int quizValue;
    private int quizTimeLimit;
    public Long quizId;

    private float score;
    private LocalDate generatedDate;
    private int timeTaken;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Submitted_quiz_question",
            joinColumns = { @JoinColumn(name = "quiz_id")},
            inverseJoinColumns = { @JoinColumn(name = "submitted_question_id")})
    @Column(name = "submitted_questions")
    private Set<SubmittedQuestion> submittedQuestions = new HashSet<>();

    public SubmittedQuiz(User user, String quizTitle, int quizValue, int quizTimeLimit, Long quizId, float score, LocalDate generatedDate, int timeTaken, Set<SubmittedQuestion> submittedQuestions) {
        this.user = user;
        this.quizTitle = quizTitle;
        this.quizValue = quizValue;
        this.quizTimeLimit = quizTimeLimit;
        this.quizId = quizId;
        this.score = score;
        this.generatedDate = generatedDate;
        this.timeTaken = timeTaken;
        this.submittedQuestions = submittedQuestions;
    }
}


