package com.example.javaspringboot.Model.Submissions;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Data @NoArgsConstructor @AllArgsConstructor @Entity
//@Table(name = "Submitted_Propogate_Char")
public class SubmittedPropogateChar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    SubmittedPropagateCharType submittedPropagateCharType;

    char character;


}
