package com.example.javaspringboot.Additional.Model;

import com.example.javaspringboot.Games.Model.Hangman;
import com.example.javaspringboot.Games.Model.Match;
import com.example.javaspringboot.Games.Model.Quiz;
import com.example.javaspringboot.Games.Model.Swipe;
import com.example.javaspringboot.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @Nullable
    private String name;

    @ManyToMany
    @JoinTable(name="module_students",
       joinColumns = @JoinColumn(name="module_id"),
       inverseJoinColumns =  @JoinColumn(name = "user_id")
    )
    private Set<User> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_admins",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "user_id")
    )
    private Set<User> admins = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_quizzes",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "quiz_id")
    )
    private Set<Quiz> quizzes = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_hangmen",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "hangnman_id")
    )
    private Set<Hangman> hangmen = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_posts",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "post_id")
    )
    private Set<Extra> posts = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_matches",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "match_id")
    )
    private Set<Match> matches = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_feedbacks",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "feedback_id")
    )
    private Set<Feedback> feedbacks = new HashSet<>();

    @ManyToMany
    @JoinTable(name="module_swipes",
            joinColumns = @JoinColumn(name="module_id"),
            inverseJoinColumns =  @JoinColumn(name = "swipe_id")
    )
    private Set<Swipe> swipes = new HashSet<>();


    public Module(String code, String name, Set<User> students, Set<User> admins) {
        this.code = code;
        this.students = students;
        this.admins = admins;
        this.name = name;
    }

    public Module(String code, Set<User> students, Set<User> admins, Set<Quiz> quizzes, Set<Hangman> hangmen, Set<Extra> posts, Set<Match> matches, Set<Feedback> feedbacks, Set<Swipe> swipes, @Nullable String description) {
        this.code = code;
        this.students = students;
        this.admins = admins;
        this.quizzes = quizzes;
        this.hangmen = hangmen;
        this.posts = posts;
        this.matches = matches;
        this.feedbacks = feedbacks;
        this.swipes = swipes;
        this.name = name;
    }

    public Module(String code, String name, Set<Quiz> quizzes, Set<Hangman> hangmen, Set<Extra> posts, Set<Match> matches, Set<Feedback> feedbacks, Set<Swipe> swipes) {
        this.code = code;
        this.name = name;
        this.quizzes = quizzes;
        this.hangmen = hangmen;
        this.posts = posts;
        this.matches = matches;
        this.feedbacks = feedbacks;
        this.swipes = swipes;
    }

    public void addStudent(User user) { students.add(user);}

    public void addAdmin(User user) { admins.add(user); }

    public void removeStudent(User user) {students.remove(user);}

    public void removeAdmin(User user) {admins.remove(user);}

}