package com.antey.fr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class InputAnswerRequestDto {

    private Long question;
    private String textAnswer;
    private List<Long> selectAnswers;
}
