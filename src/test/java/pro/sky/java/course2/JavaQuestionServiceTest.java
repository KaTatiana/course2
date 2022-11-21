package pro.sky.java.course2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.AlreadyAddedException;
import pro.sky.java.course2.examinerservice.exception.NotFoundException;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @InjectMocks
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach(){
        javaQuestionService.add(new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных"));
        javaQuestionService.add(new Question("По каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными."));
    }

    @ParameterizedTest
    @MethodSource("paramsaddNegative")
    public void addNegativeTest(String question, String answer){
        assertThatExceptionOfType(AlreadyAddedException.class)
                .isThrownBy(()->javaQuestionService.add(question, answer));
    }

    @ParameterizedTest
    @MethodSource("paramsadd")
    public void addPositiveTest(String question, String answer){
        Question expected = new Question(question, answer);

        assertThat(javaQuestionService.add(question, answer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("paramsremovenegative")
    public void removeNegativeTest(Question question){
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(()->javaQuestionService.remove(question));
    }

    @ParameterizedTest
    @MethodSource("paramsremove")
    public void removePositiveTest(Question question){
        assertThat(javaQuestionService.remove(question)).isEqualTo(question);
    }

    @Test
    public void getAllPositiveTest(){
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных"));
        questions.add(new Question("По каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными."));

        assertThat(javaQuestionService.getAll()).isEqualTo(questions);
    }

    @Test
    public void getRandomQuestionPositiveTest(){
        Question question1 = new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных");
        Question question2 = new Question("По каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными.");

        assertThat(javaQuestionService.getRandomQuestion()).isIn(question1, question2);
    }
    @Test
    public void getRandomQuestionsPositiveTest(){
        Question question1 = new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных");
        Question question2 = new Question("По каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными.");

        assertThat(javaQuestionService.getRandomQuestions(2)).isEqualTo(Set.of(question1, question2));
    }
    public static Stream<Arguments> paramsaddNegative() {
        return Stream.of(
                Arguments.of("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных")
        );
    }
    public static Stream<Arguments> paramsadd() {
        return Stream.of(
                Arguments.of("aЧто такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных")
        );
    }
    public static Stream<Arguments> paramsremovenegative() {
        return Stream.of(
                Arguments.of(new Question("aПо каким параметрам переменные различаются «переменные»?","Переменные могут быть примитивными и непримитивными."))
        );
    }
    public static Stream<Arguments> paramsremove() {
        return Stream.of(
                Arguments.of(new Question("Что такое «переменная»?","Переменная — это некий контейнер, который имеет название (или слот в памяти компьютера) для хранения разных данных"))
        );
    }

}
