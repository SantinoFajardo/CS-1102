package assignment1;

// Package used to scan the user input
import java.util.Scanner;


public class Assignment1 {
    // Create a class that represents a question
    static class Question {
        String question;
        String[] answers;
        String correctAnswer;

        Question(String question, String[] answers, String correctAnswer) {
            this.question = question;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }
    }

    // Create an array of questions
    private static final Question[] questions = {
        // Create a question with a question, answers, and correct answer
        new Question("What is the capital of France?", new String[] {"Paris", "London", "Berlin", "Madrid"}, "A"),
        new Question("What is the capital of Germany?", new String[] {"Paris", "Berlin", "London", "Madrid"}, "B"),
        new Question("What is the capital of Italy?", new String[] {"Paris", "London", "Rome", "Madrid"}, "C"),
        new Question("What is the capital of Spain?", new String[] {"Paris", "London", "Berlin", "Madrid"}, "D"),
        new Question("What is the capital of Portugal?", new String[] {"Lisbon", "London", "Berlin", "Madrid"}, "A"),
    };
    
   public static void main(String[] args) {
    int score = 0;
    for (Question question : questions) {
        // Print the question
        System.out.println(question.question);
        // Print the answers using labels A,B,C,D
       
        System.out.println("A) " + question.answers[0]);
        System.out.println("B) " + question.answers[1]);
        System.out.println("C) " + question.answers[2]);
        System.out.println("D) " + question.answers[3]);
        // Ask the user for the correct answer and scan the answer
        System.out.println("Enter the correct answer: ");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        boolean isValid = false;
        while(!isValid) {
            if(isValidAnswer(userAnswer)) isValid = true;
            else{
                System.out.println("Invalid answer, please enter A, B, C, or D");
                userAnswer = scanner.nextLine();
            }
        }
        // Check if the user answer is correct
        if (userAnswer.equalsIgnoreCase(question.correctAnswer)) {
            score++;
        }
    }
    System.out.println("Your score is: " + score + "/" + questions.length);
   }

   // Check if the user answer is in the range of A,B,C,D
   private static boolean isValidAnswer(String answer) {
    return answer.equalsIgnoreCase("A") || answer.equalsIgnoreCase("B") || answer.equalsIgnoreCase("C") || answer.equalsIgnoreCase("D");
   }
}