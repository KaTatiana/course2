package pro.sky.java.course2.examinerservice.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/exam/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(path = "/exam/java/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question item = new Question(question, answer);
        return questionService.remove(item);
    }

    @GetMapping(path = "/exam/java")
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
