package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.BadRequestException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection <Question> allQuestions = questionService.getAll();
        Set<Question> questions = new HashSet<>();
        if (amount > allQuestions.size()) {
            throw new BadRequestException("Запрошено большее количество вопросов, чем хранится в сервисе");
        }
        int i = 0;
        while (i < amount) {
            Question newQuestion = questionService.getRandomQuestion();
            if(!questions.contains(newQuestion)) {
                questions.add(newQuestion);
                i++;
            }
        }

        return questions;
    }
}
