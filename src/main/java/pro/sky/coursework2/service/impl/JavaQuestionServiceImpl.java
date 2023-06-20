package pro.sky.coursework2.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.exceptions.CollectionIsEmptyException;
import pro.sky.coursework2.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.repository.JavaQuestionRepositoryImpl;
import pro.sky.coursework2.service.QuestionRepository;
import pro.sky.coursework2.service.QuestionService;

import java.util.*;



@Service
public class JavaQuestionServiceImpl implements QuestionService {
    private final Random random = new Random();
    private final QuestionRepository questionRepository = new JavaQuestionRepositoryImpl();
    @PostConstruct
    public void initQuestions() {
        questionRepository.add(new Question("Map - это коллекция?", "Нет"));
        questionRepository.add(new Question("List - это интерфейс или класс?", "Интерфейс"));
        questionRepository.add(new Question("Возможно ли увелиение размера массива после его объявления?", "Нет"));
        questionRepository.add(new Question("Какая наиболее распространенная реализация интерфейса Set?", "HashSet"));
        questionRepository.add(new Question("В какой области памяти хранятся примитивы?", "В Stack"));
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
            int questionIndex = random.nextInt(questionRepository.getAll().size());
            int index = 0;
            for (Question question : questionRepository.getAll()) {
                if (questionIndex == index) {
                    return question;
                }
                index++;
            }
        }
        throw new CollectionIsEmptyException();//выбросить исключени  - коллекция пустая
    }
}