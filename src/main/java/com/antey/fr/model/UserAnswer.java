package com.antey.fr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter@Getter
public class UserAnswer extends AbstractEntity {

    private Long user;
    private LocalDateTime dateAnswer;
    @ManyToOne
    private Survey survey;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "input_answer", joinColumns = @JoinColumn(name = "input_id"))
    private List<InputAnswer> inputAnswers;

}
