import java.util.Scanner;
public class Wordle {
static String word = WordList.random();
static String guess = "";
static int turn = 0;
static char [][] grid = new char [6][5];
	public static void main(String[] args) 
	{
	System.out.println("Guess a word!");
	GuessWord();
	EvaluateGuess();
	}
	private static void GuessWord() 
	{
		Scanner userStringInput = new Scanner (System.in);
		guess = userStringInput.nextLine();
		guess = guess.toLowerCase();
			if (!WordList.isWord(guess))
			{
				System.out.println("Your word must be a valid 5-letter word.");
				GuessWord();	
			}
	}
	private static void EvaluateGuess() 
	{

	}
}
