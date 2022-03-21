package com.antey.fr.service;

import com.antey.fr.model.Survey;
import com.antey.fr.repo.SurveyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SurveyService {

    private final SurveyRepo repo;

    public SurveyService(SurveyRepo repo) {
        this.repo = repo;
    }


    public List<Survey> getAllSurveys() {
        return repo.findAll();
    }

    public Survey getSurveyById(Long id) {
        return find(id);
    }

    public void addSurvey(Survey survey) {
        survey.getQuestions().forEach(q -> {
            if (q.getPossibleAnswers() != null) {
                AtomicInteger number = new AtomicInteger();
                q.getPossibleAnswers().forEach(pa -> pa.setNumber(number.incrementAndGet()));
            }
        });
        repo.save(survey);
    }

    public Survey updateSurvey(Long id, Survey survey) {
        Survey old = find(id);
        update(old, survey);
        return repo.save(old);
    }

    public void deleteSurvey(Long id) {
        repo.deleteById(id);
    }


    public Survey find(Long id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    private void update(Survey oldValue, Survey newValue) {
        oldValue.setDescription(newValue.getDescription());
        oldValue.setName(newValue.getName());
        oldValue.setEndDate(newValue.getEndDate());
    }
}
