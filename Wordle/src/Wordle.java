import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Wordle {
    static String word = WordList.random();
    static String guess = "";
    static int turn = 0;
    static String[][] grid = new String[6][5];
    static boolean playing = false;
    static boolean isCheated = false;
    static ArrayList<String> incorrect = new ArrayList<String>();
    static ArrayList<String> correct = new ArrayList<String>();
    static Scanner userStringInput = new Scanner(System.in);
    public static void main(String[] args) 
    {
        playing = true;
        System.out.println("Guess a word!");
        while (playing) 
        {
            guessWord();
            evaluateGuess();
            checkWin();
        }
    }
    private static void guessWord() 
    {
        boolean valid = false;
        while (!valid) 
        {
            guess = userStringInput.nextLine().toLowerCase();
            if (guess.equals("cheater")) 
            {
                System.out.println("The word is " + word + ", you filthy cheater.");
                isCheated = true;
            } 
            if (guess.equals("a valid 5-letter word")||guess.equals("a valid 5-letter word."))
            {
            	System.out.println("You're not funny.");
            }
            if (!WordList.isWord(guess)) 
            {
                System.out.println("Your word must be a valid 5-letter word.");
            } 
            else 
            {
                valid = true;
            }
        }
    }
    public static void evaluateGuess() 
    {
        if (turn == 0) 
        {
            System.out.println("If a letter is between [] brackets, it is both in the word and in the correct spot.");
            System.out.println("If a letter is between () parentheses, it is in the word but in the incorrect spot.");
            System.out.println("If a letter is between {} brackets, it is not in the word.");
        }
        for (int i = 0; i < 5; i++) 
        {
            String currentLetter = guess.substring(i, i + 1);
            String targetLetter = word.substring(i, i + 1);
            if (currentLetter.equals(targetLetter)) 
            {
                grid[turn][i] = "[" + currentLetter.toUpperCase() + "]";
                if (!correct.contains(currentLetter)) 
                {
                    correct.add(currentLetter);
                }
            } 
            else if (word.contains(currentLetter)) 
            {
                grid[turn][i] = "(" + currentLetter + ")";
                if (!correct.contains(currentLetter)) 
                {
                    correct.add(currentLetter);
                }
            } else 
            {
                grid[turn][i] = "{" + currentLetter + "}";
                if (!incorrect.contains(currentLetter)) 
                {
                    incorrect.add(currentLetter);
                }
            }
        }
    }
    private static void checkWin() 
    {
        if (guess.equals(word)) 
        {
            gameWon();
        } 
        else if (turn < 5) 
        {
            displayBoard();
            turn++; 
        } 
        else 
        {
            gameLost();
        }
    }
    public static void displayBoard() 
    {
        Collections.sort(correct);
        Collections.sort(incorrect);
        System.out.println("turn " + (turn + 1) + " / 6:");
        System.out.println("| " + grid[turn][0] + " | " + grid[turn][1] + " | " + grid[turn][2] + " | " + grid[turn][3] + " | " + grid[turn][4]);
        System.out.println("You know the following letters are in the word:");
        for (int i = 0; i < correct.size(); i++) 
        {
            System.out.print(correct.get(i) + " ");
        }
        System.out.println("\nYou know the following letters are incorrect:");
        for (int i = 0; i < incorrect.size(); i++) 
        {
            System.out.print(incorrect.get(i) + " ");
        }
        System.out.println("\n");
    }
    public static void gameWon() 
    {
        playing = false;
        if (!isCheated) 
        {
            System.out.println("| " + grid[turn][0] + " | " + grid[turn][1] + " | " + grid[turn][2] + " | " + grid[turn][3] + " | " + grid[turn][4]);
            int totalGuesses = turn + 1;
            if (totalGuesses != 1) 
            {
                System.out.println("You guessed the word " + word + " correctly in " + totalGuesses + " guesses!");
            } 
            else 
            {
                System.out.println("You guessed the word " + word + " correctly in 1 guess! Amazing!");
            }
        } 
        else 
        {
            System.out.println("You won, but it doesn't count because you cheated. Shameful."); 
        }
    }
    public static void gameLost() 
    {
        playing = false;
        if (!isCheated) 
        {
            System.out.println("| " + grid[turn][0] + " | " + grid[turn][1] + " | " + grid[turn][2] + " | " + grid[turn][3] + " | " + grid[turn][4]);
            System.out.println("The word was " + word + ".");
            System.out.println("Better luck next time!");
        } else 
        {
            System.out.println("You cheated and still lost. How did you even accomplish this?");
        }
    }
}
