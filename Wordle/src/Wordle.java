import java.util.Scanner;
public class Wordle {
static String word = WordList.random();
static String guess = "";
static int turn = 0;
static String [][] grid = new String [6][5];
static boolean playing = false;
	public static void main(String[] args) 
	{
	playing = true;
	System.out.println("Guess a word!");
		while (playing)
		{
		guessWord();
		checkWin();
		displayBoard();
		}
	}
	private static void guessWord() 
	{
		Scanner userStringInput = new Scanner (System.in);
		guess = userStringInput.nextLine();
		guess = guess.toLowerCase();
			if (!WordList.isWord(guess))
			{
				System.out.println("Your word must be a valid 5-letter word.");
					if (guess.equals("cheater"))
					{
						System.out.println("The word is "+ word+ ", you filthy cheater.");
					}
				guessWord();	
			}
	}
	private static void checkWin() 
	{
		if (guess.equals(word))
		{
		gameWon();
		}
		else if (turn < 6)
		{
		evaluateGuess();
		}
		else
		{
		gameLost();
		}
	}
	public static void evaluateGuess()
	{
			if (turn == 0)
			{
			System.out.println("If a letter is between [] brackets, it is both in the word and in the correct spot.");
			System.out.println("If a letter is between () parantheses, it is in the word but in the incorrect spot.");
			System.out.println("If a letter is between {} brackets, it is not in the word.");
			}
		    for (int i = 0; i < 5; i++) 
		    {
		        String currentLetter = guess.substring(i, i + 1);
		        String targetLetter = word.substring(i, i + 1);
		        if (currentLetter.equals(targetLetter)) 
		        {
		            grid[turn][i] = "[" + currentLetter.toUpperCase() + "]";
		        } 
		        else if (word.contains(currentLetter)) 
		        {
		            grid[turn][i] = "(" + currentLetter + ")";
		        } 
		        else 
		        {
		            grid[turn][i] = " {" + currentLetter + "} ";
		        }
		    }
	}
	public static void displayBoard() 
	{
		System.out.println("| "+ grid[turn][0]+" | "+ grid[turn][1]+" | "+ grid[turn][2]+" | "+grid[turn][3]+" | "+grid[turn][4]);
		turn++;
	}
	public static void gameWon()
	{
	playing = false;
	turn++;
	System.out.println("| "+ grid[turn][0]+" | "+ grid[turn][1]+" | "+ grid[turn][2]+" | "+grid[turn][3]+" | "+grid[turn][4]);
		if (turn != 1)
		{
		System.out.println("You guessed the word "+ word+ " correctly in "+ turn+ "guesses!");
		}
		else
		{
		System.out.println("You guessed the word "+ word+ " correctly in 1 guess! Amazing!");
		}
	}
	public static void gameLost()
	{
	playing = false;
	System.out.println("| "+ grid[turn][0]+" | "+ grid[turn][1]+" | "+ grid[turn][2]+" | "+grid[turn][3]+" | "+grid[turn][4]);
	System.out.println("The word was "+ word+ ".");
	System.out.println("Better luck next time!");
	}
}
