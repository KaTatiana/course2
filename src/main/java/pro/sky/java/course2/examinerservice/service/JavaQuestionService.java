package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.AlreadyAddedException;
import pro.sky.java.course2.examinerservice.exception.NotFoundException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService(){
        questions = new HashSet<>();
        random = new Random();
    }
    @Override
    public Question add(String question, String answer) {
        Question item = new Question(question, answer);

        return add(item);
    }

    @Override
    public Question add(Question question) {
        if(!questions.contains(question)){
            questions.add(question);
        }
        else
        {
            throw new AlreadyAddedException("Вопрос уже присутствует в базе");
        }

        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questions.remove(question))
        {
            throw new NotFoundException("Вопрос отсутствует в базе");
        }

        return question;
    }

    @Override
    public Collection<Question> getAll() {

        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int size = questions.size();

        if (size > 0) {
            return getRandomQuestionSet(questions);
        }

        return null;
    }

    private Question getRandomQuestionSet(Collection<Question> questions) {
        int size = questions.size();
        int i = 0;
        int itemIndex = random.nextInt(size);
        for(Question object : questions) {
            if (i++ == itemIndex) {
                return object;
            }
        }
        return null;
    }
    @Override
    public Collection<Question> getRandomQuestions(int size) {
        Collection<Question> questions = getAll();
        Collection<Question> questionsNew = new HashSet<>();
        Question question;
        for(int i=0; i<size; i++){
            question = getRandomQuestionSet(questions);
            questionsNew.add(question);
            questions.remove(question);
        }
        return questionsNew;
    }
}
