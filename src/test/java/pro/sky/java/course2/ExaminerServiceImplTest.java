package pro.sky.java.course2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.BadRequestException;
import pro.sky.java.course2.examinerservice.service.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    Question question1 = new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных");
    Question question2 = new Question("По каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными.");
    Set<Question> questions = Set.of(question1, question2);

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    @BeforeEach
    public void beforeEach(){
        Set<Question> questions = Set.of(question1, question2);
        when(questionService.getAll()).thenReturn(questions);
    }

    @ParameterizedTest
    @MethodSource("paramNegative")
    public void getQuestionsNegativeTest(int amount){
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(()->examinerServiceImpl.getQuestions(amount));
    }

    @ParameterizedTest
    @MethodSource("paramPositive")
    public void getQuestionsPositiveTest(int amount){
        when(questionService.getRandomQuestions(2)).thenReturn(questions);
        assertThat(examinerServiceImpl.getQuestions(amount)).isEqualTo(questions);
    }

    public static Stream<Arguments> paramNegative() {
        return Stream.of(
                Arguments.of(3));
    }

    public static Stream<Arguments> paramPositive() {
        return Stream.of(
                Arguments.of(2));
    }
}
