package com.antey.fr.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter@Getter
public class SelectAnswerResponseDto {

    private String answer;
    private Boolean select;
}
