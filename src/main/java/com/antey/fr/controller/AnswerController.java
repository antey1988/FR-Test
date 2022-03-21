package com.antey.fr.controller;

import com.antey.fr.dto.InputAnswerRequestDto;
import com.antey.fr.dto.UserAnswerResponseDto;
import com.antey.fr.model.Question;
import com.antey.fr.service.QuestionService;
import com.antey.fr.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @PostMapping("/surveys/{id}/answers")
    public void assAnswer(@PathVariable Long id,
                          @RequestParam(required = false) Long userId,
                          @RequestBody List<InputAnswerRequestDto> answers) {
        service.createUserAnswer(id, userId, answers);
    }

    @GetMapping("/answers")
    public List<UserAnswerResponseDto> getAnswers(@RequestParam(required = false) Long userId) {
        return service.getUserAnswers(userId);
    }
}
