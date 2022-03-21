package com.antey.fr.service;

import com.antey.fr.dto.InputAnswerRequestDto;
import com.antey.fr.dto.InputAnswerResponseDto;
import com.antey.fr.dto.SelectAnswerResponseDto;
import com.antey.fr.dto.UserAnswerResponseDto;
import com.antey.fr.model.*;
import com.antey.fr.repo.AnswerRepo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepo repo;

    public AnswerService(AnswerRepo repo) {
        this.repo = repo;
    }

    public void createUserAnswer(Long surveyId, Long userId, List<InputAnswerRequestDto> inputAnswerRequestDtos) {
        UserAnswer userAnswer = new UserAnswer();

        Survey survey = new Survey();
        survey.setId(surveyId);

        userAnswer.setSurvey(survey);
        userAnswer.setDateAnswer(LocalDateTime.now());
        userAnswer.setUser(userId);

        List<InputAnswer> inputAnswers = inputAnswerRequestDtos.stream().map(ia -> {
                InputAnswer inputAnswer = new InputAnswer();
                inputAnswer.setQuestion(ia.getQuestion());
                inputAnswer.setAnswer(createAnswerFromDto(ia));
                return inputAnswer;
            }).collect(Collectors.toList());
        userAnswer.setInputAnswers(inputAnswers);

        repo.save(userAnswer);
    }

    public List<UserAnswerResponseDto> getUserAnswers(Long userId) {
        List<UserAnswer> answers = repo.findAllByUser(userId);
        return answers.stream().map(a -> {
            UserAnswerResponseDto userAnswerResponseDto = new UserAnswerResponseDto();
            Survey survey = a.getSurvey();
            userAnswerResponseDto.setName(survey.getName());
            userAnswerResponseDto.setDescription(survey.getDescription());
            userAnswerResponseDto.setStartDate(survey.getStartDate());
            userAnswerResponseDto.setEndDate(survey.getEndDate());
            userAnswerResponseDto.setDateAnswer(a.getDateAnswer());
            userAnswerResponseDto.setInputAnswerResponseDtos(createAnswerFromModel(survey.getQuestions(), a.getInputAnswers()));
            return userAnswerResponseDto;
        }).collect(Collectors.toList());
    }

    private String createAnswerFromDto(InputAnswerRequestDto inputAnswer) {
        if (!Strings.isBlank(inputAnswer.getTextAnswer())) {
            return inputAnswer.getTextAnswer();
        }
        if (inputAnswer.getSelectAnswers() != null && !inputAnswer.getSelectAnswers().isEmpty()) {
            return inputAnswer.getSelectAnswers().stream().map(String::valueOf).collect(Collectors.joining(" "));
        }
        return null;
    }

    private List<InputAnswerResponseDto> createAnswerFromModel(List<Question> questions, List<InputAnswer> inputAnswers) {
        Map<Long, String> inputAnswer = inputAnswers.stream().collect(Collectors.toMap(InputAnswer::getQuestion, InputAnswer::getAnswer));
        return questions.stream().map(question -> {
            InputAnswerResponseDto inputAnswerResponseDto = new InputAnswerResponseDto();
            inputAnswerResponseDto.setQuestion(question.getContent());
            String answer = inputAnswer.get(question.getId());
            if (question.getAnswerType() == AnswerType.TEXT) {
                inputAnswerResponseDto.setTextAnswer(answer);
            } else {
                List<Integer> numbers = Arrays.stream(answer.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
                inputAnswerResponseDto.setSelectAnswers(question.getPossibleAnswers().stream().map(pa -> {
                    SelectAnswerResponseDto selectAnswerResponseDto = new SelectAnswerResponseDto();
                    selectAnswerResponseDto.setAnswer(pa.getAnswer());
                    selectAnswerResponseDto.setSelect(numbers.contains(pa.getNumber()));
                    return selectAnswerResponseDto;
                }).collect(Collectors.toList()));
            }
            return inputAnswerResponseDto;
        }).collect(Collectors.toList());
    }
}
