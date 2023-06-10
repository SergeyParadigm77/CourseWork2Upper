package pro.sky.coursework2.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.coursework2.exceptions.CollectionIsEmptyException;
import pro.sky.coursework2.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
@Service
public class JavaQuestionServiceImpl implements QuestionService {
    private final Random random = new Random();
    private final Collection<Question> questions = new HashSet<>();
    @PostConstruct
    public void initQuestions() {
        questions.add(new Question("Map - это коллекция?", "Нет"));
        questions.add(new Question("List - это интерфейс или класс?", "Интерфейс"));
        questions.add(new Question("Возможно ли увелиение размера массива после его объявления?", "Нет"));
        questions.add(new Question("Какая наиболее распространенная реализация интерфейса Set?", "HashSet"));
        questions.add(new Question("В какой области памяти хранятся примитивы?", "В Stack"));
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        } else {
            throw new QuestionNotFoundException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.size() != 0) {
            int questionIndex = random.nextInt(questions.size());
            int index = 0;
            for (Question question : questions) {
                if (questionIndex == index) {
                    return question;
                }
                index++;
            }
        }
        throw new CollectionIsEmptyException();//выбросить исключени  - коллекция пустая
    }
}
