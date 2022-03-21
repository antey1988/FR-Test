package com.antey.fr.controller;

import com.antey.fr.model.Survey;
import com.antey.fr.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurveyController {

    private final SurveyService service;

    public SurveyController(SurveyService service) {
        this.service = service;
    }

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return service.getAllSurveys();
    }

    @GetMapping("/surveys/{id}")
    public Survey getSurvey(@PathVariable Long id) {
        return service.getSurveyById(id);
    }

    @PostMapping("/surveys")
    public void addSurvey(@RequestBody Survey survey) {
        service.addSurvey(survey);
    }

    @PutMapping("/surveys/{id}")
    public Survey updateSurvey(@PathVariable Long id, @RequestBody Survey survey) {
        return service.updateSurvey(id, survey);
    }

    @DeleteMapping("/surveys/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        service.deleteSurvey(id);
    }
}
