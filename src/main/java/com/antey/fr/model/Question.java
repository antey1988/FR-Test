package com.antey.fr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter@Getter
public class Question extends AbstractEntity {

    private String content;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "possible_answer", joinColumns = @JoinColumn(name = "question_id"))
    @BatchSize(size = 100)
    private List<PossibleAnswer> possibleAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Survey survey;
}
