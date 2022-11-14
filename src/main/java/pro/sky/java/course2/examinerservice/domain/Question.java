package pro.sky.java.course2.examinerservice.domain;

public class Question {
    private final String question;
    private final String answer;
    private final int dnaCode;

    public Question(String question, String answer){
        this.question = question;
        this.answer = answer;
        String key = question + answer;
        this.dnaCode = key.hashCode();
    }
    public String getQuestion(){return question;}
    public String getAnswer(){return answer;}

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Question question = (Question) o;
        return  this.question.equals(question.getQuestion()) &&
                this.answer.equals(question.getAnswer());
    }

    @Override
    public int hashCode(){return dnaCode;}

    @Override
    public String toString(){return question + " - " + answer;}
}
