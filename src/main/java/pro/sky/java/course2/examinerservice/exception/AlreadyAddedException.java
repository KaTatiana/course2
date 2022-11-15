package pro.sky.java.course2.examinerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.CONFLICT;
@ResponseStatus(value = CONFLICT)
public class AlreadyAddedException extends RuntimeException {
        public AlreadyAddedException(String message){
            super(message);
        }
    }
