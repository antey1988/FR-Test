package com.antey.fr.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Setter@Getter
public class UserAnswerResponseDto {

    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dateAnswer;
    private List<InputAnswerResponseDto> inputAnswerResponseDtos;
}
