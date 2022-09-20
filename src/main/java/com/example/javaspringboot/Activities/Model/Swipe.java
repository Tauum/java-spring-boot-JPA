package com.example.javaspringboot.Activities.Model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Entity //needed for database mapping
@Table(name = "swipe")
public class Swipe {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(fetch =  FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "swipe_game_cards",
            joinColumns = { @JoinColumn(name = "swipe_id")},
            inverseJoinColumns = { @JoinColumn(name = "card_id")})
    @Column(name = "swipe_cards")
    public Set<Swipe_Card> cards;
    private LocalDate generatedDate;

    public String subject;
    @Nullable
    private float value;

    public boolean hidden;

    public Swipe(String title, Set<Swipe_Card> cards, LocalDate generatedDate, String subject, float value, boolean hidden) {
        this.title = title;
        this.cards = cards;
        this.generatedDate = generatedDate;
        this.subject = subject;
        this.value = value;
        this.hidden = hidden;
    }

    public void setGeneral(String title, float value, boolean hidden, String subject){
        this.title = title;
        this.value =value;
        this.hidden = hidden;
        this.subject =subject;
    }
}
