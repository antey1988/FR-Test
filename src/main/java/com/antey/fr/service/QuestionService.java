package com.antey.fr.service;

import com.antey.fr.model.Question;
import com.antey.fr.model.Survey;
import com.antey.fr.repo.QuestionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepo repo;

    public QuestionService(QuestionRepo repo) {
        this.repo = repo;
    }

    public void addQuestions(Long id, List<Question> questions) {
        Survey survey = new Survey();
        survey.setId(id);
        questions.forEach(q -> {
            q.setSurvey(survey);
            if (q.getPossibleAnswers() != null) {
                AtomicInteger number = new AtomicInteger();
                q.getPossibleAnswers().forEach(pa -> pa.setNumber(number.incrementAndGet()));
            }
        });
        repo.saveAll(questions);
    }

    @Transactional
    public void updateQuestions(Long id, List<Question> questions) {
        Map<Long, Question> newValues = questions.stream().collect(Collectors.toMap(Question::getId, Function.identity()));
        List<Long> ids = questions.stream().map(Question::getId).collect(Collectors.toList());
        checkOwner(id, ids).forEach(q -> {
            Question newValue = newValues.get(q.getId());
            q.setAnswerType(newValue.getAnswerType());
            q.setContent(newValue.getContent());
            q.setPossibleAnswers(newValue.getPossibleAnswers());
            if (q.getPossibleAnswers() != null) {
                AtomicInteger number = new AtomicInteger();
                q.getPossibleAnswers().forEach(pa -> pa.setNumber(number.incrementAndGet()));
            }
        });
    }

    public void deleteQuestions(Long id, List<Long> ids) {
        checkOwner(id, ids);
        repo.deleteAllById(ids);
    }

    private List<Question> checkOwner(Long id, List<Long> ids) {
        List<Question> oldValues = repo.findAllById(ids);
        List<Question> alienQuestions = oldValues.stream().filter(q -> !q.getSurvey().getId().equals(id)).collect(Collectors.toList());
        if (!alienQuestions.isEmpty()) {
            throw new RuntimeException();
        }
        return oldValues;
    }
}
