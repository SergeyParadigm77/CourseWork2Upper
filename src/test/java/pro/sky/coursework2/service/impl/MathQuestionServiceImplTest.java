package pro.sky.coursework2.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.coursework2.exceptions.CollectionIsEmptyException;
import pro.sky.coursework2.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MathQuestionServiceImplTest {
    private final QuestionService questionService = new MathQuestionServiceImpl();
    @Test
    public void shouldAddQuestion() {
        //given
        Question question = new Question("Сколько будет дважды два?", "Четыре");
        //when
        Question addedQuestion = questionService.add(question.getQuestion(), question.getAnswer());
        //then
        Assertions.assertEquals(question, addedQuestion);
    }

    @Test
    public void shouldAddQuestion2() {
        //given
        Question question = new Question("Сколько будет дважды два?", "Четыре");
        //when
        Question addedQuestion = questionService.add(question);
        //then
        Assertions.assertEquals(question, addedQuestion);
    }

    @Test
    public void shouldRemoveQuestion() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        questionService.add(question1);
        questionService.add(question2);

        Collection<Question> allQuestions2 = new HashSet<>() {};
        allQuestions2.add(question1);
        //when
        questionService.remove(question2);
        Collection<Question> allQuestions1 = new HashSet<>(questionService.getAll()) {};
        //then
        Assertions.assertEquals(allQuestions1, allQuestions2);
    }
    @Test
    public void shouldThrowNotFoundExceptionWhenQuestionIsNotFound() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        questionService.add(question1);
        //when
        // then
        Assertions.assertThrows(QuestionNotFoundException.class, () -> questionService.remove(question2));
    }
    @Test
    public void shouldThrowCollectionIsEmptyException(){
        //given
        //when
        //then
        Assertions.assertThrows(CollectionIsEmptyException.class, () -> questionService.getRandomQuestion());
    }
    @Test
    public void shouldReturnAll() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        Question question3 = new Question("Сколько будет трижды три?", "Девять");
        Question question4 = new Question("Сколько будет трижды три?", "Девять");
        questionService.add(question1);
        questionService.add(question2);
        questionService.add(question3);
        questionService.add(question4);

        Collection<Question> allQuestions2 = new HashSet<>() {};
        allQuestions2.add(question1);
        allQuestions2.add(question2);
        allQuestions2.add(question3);
        //when
        Collection<Question> allQuestions1 = new HashSet<>(questionService.getAll()) {};
        //then
        Assertions.assertEquals(allQuestions1, allQuestions2);
    }

    @Test
    public void shouldReturnRandomQuestion() {
        //given
        Question question = new Question("2 * 2 = ", "4");
        questionService.add(question);
        //when
        //then
        Assertions.assertEquals(question, questionService.getRandomQuestion());
    }
}
