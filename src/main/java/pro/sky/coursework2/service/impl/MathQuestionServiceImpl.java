package pro.sky.coursework2.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.exceptions.CollectionIsEmptyException;
import pro.sky.coursework2.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.repository.MathQuestionRepositoryImpl;
import pro.sky.coursework2.service.QuestionRepository;
import pro.sky.coursework2.service.QuestionService;

import java.util.*;

@Service
public class MathQuestionServiceImpl implements QuestionService {
    private final Random random = new Random();
    private final QuestionRepository questionRepository = new MathQuestionRepositoryImpl();
    @PostConstruct
    public void initQuestions() {
        questionRepository.add(new Question("Сколько будет два умножить на два?", "Четыре"));
        questionRepository.add(new Question("Сколько будет три умножить на три?", "Девять"));
        questionRepository.add(new Question("Сколько будет один умножить на один?", "Один"));
        questionRepository.add(new Question("Сколько будет четыре поделить на четыре?", "Один"));
        questionRepository.add(new Question("Сколько будет сто поделить на пять?", "Двадцать"));
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionRepository.getAll().size() != 0) {
            return (new ArrayList<>(questionRepository.getAll())).get(random.nextInt(questionRepository.getAll().size()));
        }
        throw new CollectionIsEmptyException();
    }
}
