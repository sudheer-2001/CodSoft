import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            System.out.print("Your answer (1-" + question.getOptions().size() + "): ");
            
            int userAnswer = scanner.nextInt();
            processAnswer(question, userAnswer);
        }

        displayResult();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.getQuestion());

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void processAnswer(QuizQuestion question, int userAnswer) {
        if (userAnswer >= 1 && userAnswer <= question.getOptions().size()) {
            if (userAnswer - 1 == question.getCorrectOptionIndex()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " +
                        (question.getCorrectOptionIndex() + 1) + ". " +
                        question.getOptions().get(question.getCorrectOptionIndex()) + "\n");
            }
        } else {
            System.out.println("Invalid answer. Skipping to the next question.\n");
        }
    }

    private void displayResult() {
        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Sample quiz questions
        QuizQuestion q1 = new QuizQuestion("What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 2);

        QuizQuestion q2 = new QuizQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("Earth", "Mars", "Venus", "Jupiter"), 1);

        QuizQuestion q3 = new QuizQuestion("What is the largest mammal in the world?",
                Arrays.asList("Elephant", "Giraffe", "Blue Whale", "Hippopotamus"), 2);

        List<QuizQuestion> quizQuestions = Arrays.asList(q1, q2, q3);

        // Create a Quiz object with the list of questions
        Quiz quiz = new Quiz(quizQuestions);

        // Start the quiz
        quiz.startQuiz();
    }
}
