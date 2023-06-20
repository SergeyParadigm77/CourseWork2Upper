package pro.sky.coursework2.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.exceptions.TooManyQuestionsException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.ExaminerService;
import pro.sky.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionServiceJava;
    private final QuestionService questionServiceMath;
    Random random = new Random();

    public ExaminerServiceImpl(@Qualifier("javaQuestionServiceImpl") QuestionService questionServiceJava,
                               @Qualifier("mathQuestionServiceImpl") QuestionService questionServiceMath) {
        this.questionServiceJava = questionServiceJava;
        this.questionServiceMath = questionServiceMath;
    }
    @Override
    public Collection<Question> getQuestions(int amount) {
        if ((questionServiceJava.getAll().size() + questionServiceMath.getAll().size()) < amount) {
            throw new TooManyQuestionsException();
        }
        final Collection<Question> questions = new HashSet<>();
        while (questions.size() != amount) {
            if (random.nextInt(2) == 0) {
                questions.add(questionServiceJava.getRandomQuestion());
            }
            else {
                questions.add(questionServiceMath.getRandomQuestion());
            }
        }
        return questions;
    }
}
