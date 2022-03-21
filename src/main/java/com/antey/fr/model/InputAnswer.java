package com.antey.fr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter@Getter
public class InputAnswer {

    private Long question;
    private String answer;
}
