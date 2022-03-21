package com.antey.fr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Setter@Getter
public class PossibleAnswer{

    private Integer number;
    private String answer;
}
