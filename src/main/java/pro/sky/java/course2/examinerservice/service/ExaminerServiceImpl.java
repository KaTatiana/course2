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
        if (amount > allQuestions.size()) {
            throw new BadRequestException("Запрошено большее количество вопросов, чем хранится в сервисе");
        }

        return questionService.getRandomQuestions(amount);
    }
}
