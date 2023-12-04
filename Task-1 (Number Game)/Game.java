import java.util.Scanner;
import java.util.Random;

public class Game{
    static int minRange = 1;
    static int maxRange = 100;
    static int noOfAttempts = 10;
    static int rounds = 0;
    static int score = 0;
    static Scanner sc = new Scanner(System.in);

    public static void validate(){
        Random rand = new Random();
        int exactValue = rand.nextInt(maxRange - minRange + 1) + minRange;
        System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ".");
        int attempts = 0;
        while (attempts < noOfAttempts){
            System.out.print("Guess the number : ");
            int guess = sc.nextInt();
            attempts++;
            
            if(guess < exactValue){
                System.out.println("your guess is  too lower than exactValue! Try a higher number.");
            }else if(guess > exactValue){
                System.out.println("your guess is  too higher than exactValue! Try a lower number.");
            }else{
                System.out.println("Congratulations! You guessed the number " + exactValue + "in " + attempts +" attempts.");
                score++;
                break;
                }
            }
            if(attempts == noOfAttempts){
                 System.out.println("Sorry, you've reached the maximum number of attempts given. The number was " + exactValue + ".");
            }    
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to the guess the number game!!!");
        
        
        boolean nextAttempt = true;
        
        while(nextAttempt){
            validate();
            rounds++;
            System.out.print("Do you want to play again? (yes/no): ");
            nextAttempt = sc.next().equalsIgnoreCase("yes");
        }
        System.out.println("Game over! You played " + rounds + " rounds and your final score is " + score + ".");
        sc.close();
    }
}