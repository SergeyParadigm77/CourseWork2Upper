package pro.sky.coursework2.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.coursework2.exceptions.QuestionNotFoundException;
import pro.sky.coursework2.model.Question;
import pro.sky.coursework2.service.QuestionRepository;


import java.util.Collection;
import java.util.HashSet;

public class JavaQuestionRepositoryImplTest {

    private final QuestionRepository questionRepository = new JavaQuestionRepositoryImpl();

    @Test
    public void shouldAddQuestion() {
        //given
        Question question = new Question("Сколько будет дважды два?", "Четыре");
        //when
        Question addedQuestion = questionRepository.add(question.getQuestion(), question.getAnswer());
        //then
        Assertions.assertEquals(question, addedQuestion);
    }

    @Test
    public void shouldAddQuestion2() {
        //given
        Question question = new Question("Сколько будет дважды два?", "Четыре");
        //when
        Question addedQuestion = questionRepository.add(question);
        //then
        Assertions.assertEquals(question, addedQuestion);
    }

    @Test
    public void shouldRemoveQuestion() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        questionRepository.add(question1);
        questionRepository.add(question2);

        Collection<Question> allQuestions2 = new HashSet<>() {};
        allQuestions2.add(question1);
        //when
        questionRepository.remove(question2);
        Collection<Question> allQuestions1 = new HashSet<>(questionRepository.getAll()) {};
        //then
        Assertions.assertEquals(allQuestions1, allQuestions2);
    }
    @Test
    public void shouldThrowNotFoundExceptionWhenQuestionIsNotFound() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        questionRepository.add(question1);
        //when
        // then
        Assertions.assertThrows(QuestionNotFoundException.class, () -> questionRepository.remove(question2));
    }
    @Test
    public void shouldReturnAll() {
        //given
        Question question1 = new Question("Сколько будет одинажды один?", "Один");
        Question question2 = new Question("Сколько будет дважды два?", "Четыре");
        Question question3 = new Question("Сколько будет трижды три?", "Девять");
        Question question4 = new Question("Сколько будет трижды три?", "Девять");
        questionRepository.add(question1);
        questionRepository.add(question2);
        questionRepository.add(question3);
        questionRepository.add(question4);

        Collection<Question> allQuestions2 = new HashSet<>() {};
        allQuestions2.add(question1);
        allQuestions2.add(question2);
        allQuestions2.add(question3);
        //when
        Collection<Question> allQuestions1 = new HashSet<>(questionRepository.getAll()) {};
        //then
        Assertions.assertEquals(allQuestions1, allQuestions2);
    }
}
