package com.antey.fr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class InputAnswerResponseDto {

    private String question;
    private String textAnswer;
    private List<SelectAnswerResponseDto> selectAnswers;
}
