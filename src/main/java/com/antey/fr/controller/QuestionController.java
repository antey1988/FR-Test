package com.antey.fr.controller;

import com.antey.fr.model.Question;
import com.antey.fr.model.Survey;
import com.antey.fr.service.QuestionService;
import com.antey.fr.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping("/surveys/{id}/questions")
    public void addSurvey(@PathVariable Long id, @RequestBody List<Question> questions) {
        service.addQuestions(id, questions);
    }

    @PutMapping("/surveys/{id}/questions")
    public void updateSurvey(@PathVariable Long id,  @RequestBody List<Question> questions) {
        service.updateQuestions(id, questions);
    }

    @DeleteMapping("/surveys/{id}/questions")
    public void deleteSurvey(@PathVariable Long id, @RequestBody List<Long> ids) {
        service.deleteQuestions(id, ids);
    }
}
