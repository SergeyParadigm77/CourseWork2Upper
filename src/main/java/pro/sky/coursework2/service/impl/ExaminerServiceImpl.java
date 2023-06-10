package pro.sky.coursework2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework2.exceptions.TooManyQuestionsException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.ExaminerService;
import pro.sky.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new TooManyQuestionsException();
        }
        final Collection<Question> questions = new HashSet<>();
        while (questions.size() != amount) {
           questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
