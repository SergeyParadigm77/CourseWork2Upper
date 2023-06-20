package pro.sky.coursework2.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;
    @ExtendWith(MockitoExtension.class)
    public class ExaminerServiceImplTest {
        @Mock
        private QuestionService questionService;
        @InjectMocks
        private ExaminerServiceImpl examinerService;
        @Test
        public void shouldReturnCollectionOfRandomQuestions() {
            //given
            Collection<Question> questions = new HashSet<>();
            questions.add(new Question("Map - это коллекция?", "Нет"));
            questions.add(new Question("List - это интерфейс или класс?", "Интерфейс"));
            questions.add(new Question("Возможно ли увелиение размера массива после его объявления?", "Нет"));
            questions.add(new Question("Какая наиболее распространенная реализация интерфейса Set?", "HashSet"));
            questions.add(new Question("В какой области памяти хранятся примитивы?", "В Stack"));
            questions.add(new Question("Сколько будет два умножить на два?", "Четыре"));
            questions.add(new Question("Сколько будет три умножить на три?", "Девять"));
            questions.add(new Question("Сколько будет один умножить на один?", "Один"));
            questions.add(new Question("Сколько будет четыре поделить на четыре?", "Один"));
            questions.add(new Question("Сколько будет сто поделить на пять?", "Двадцать"));

            ArrayList<Question> questionsList = new ArrayList<>(questions);

            given(questionService.getAll()).willReturn(questions);
            given(questionService.getRandomQuestion()).willReturn(
                    questionsList.get(0),
                    questionsList.get(0),
                    questionsList.get(1),
                    questionsList.get(2),
                    questionsList.get(3),
                    questionsList.get(3),
                    questionsList.get(4),
                    questionsList.get(4),
                    questionsList.get(5),
                    questionsList.get(5),
                    questionsList.get(6),
                    questionsList.get(7),
                    questionsList.get(8),
                    questionsList.get(8),
                    questionsList.get(8),
                    questionsList.get(9)
            );
            //when
            Collection<Question> actualRandomQuestions = examinerService.getQuestions(10);
            //then
            Assertions.assertEquals(new HashSet<>(List.of(
                    questionsList.get(0),
                    questionsList.get(1),
                    questionsList.get(2),
                    questionsList.get(3),
                    questionsList.get(4),
                    questionsList.get(5),
                    questionsList.get(6),
                    questionsList.get(7),
                    questionsList.get(8),
                    questionsList.get(9))),actualRandomQuestions);
        }
    }
