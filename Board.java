/*
 * Activity 2.5.2
 *
 * A Board class the PhraseSolverGame
 */
import java.util.Scanner;
import java.io.File;

public class  Board
{
  private String solvedPhrase;
  private String phrase;
  private int currentLetterValue; 

  /* your code here - constructor(s) */ 
  public Board(){
    solvedPhrase = "";
    phrase = loadPhrase();
    currentLetterValue = Board.setLetterValue();
    System.out.println("Phrase: " + phrase); 

  }
  
  /* your code here - accessor(s) */
  /*gets the phrase
  returns the phrase*/
  public String getPhrase(){
    return phrase;
  }
  //gets partially solved phrase(returns it)
  public String getPartiallySolvedPhrase(){
    return solvedPhrase;
  }
  //gets the value of the letter(returns it)
  public int getLetterValue(){
    return currentLetterValue;
  }
  
  
  /* your code here - mutator(s)  */


  /* ---------- provided code, do not modify ---------- */
  public void setLetterValue()
  {
    int randomInt = (int) ((Math.random() * 10) + 1) * 100;    
    currentLetterValue = randomInt;
  }

  public boolean isSolved(String guess)
  {
    if (phrase.equals(guess))
    {
      return true;
    }
    return false;
  }

  private String loadPhrase()
  {
    String tempPhrase = "";
    
    int numOfLines = 0;
    try 
    {
      Scanner sc = new Scanner(new File("phrases.txt"));
      while (sc.hasNextLine())
      {
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch(Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
		int randomInt = (int) ((Math.random() * numOfLines) + 1);
    
    try 
    {
      int count = 0;
      Scanner sc = new Scanner(new File("phrases.txt"));
      while (sc.hasNextLine())
      {
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt)
        {
          tempPhrase = temp;
        }
      }
    } catch (Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
    for (int i = 0; i < tempPhrase.length(); i++)
    {
      if (tempPhrase.substring(i, i + 1).equals(" "))
      {
        solvedPhrase += "  ";
      }  
      else
      {
        solvedPhrase += "_ ";
      }
    }  
    
    return tempPhrase;
  }  

/* Checks if the guessed letter is in the phrase
*   Pre condition:
*      if the letter was not aldready found
*    Post condition:
*      guessed letter is added to a new string
*      rest are added with spaces.
*       This method returns immediately whether or not the guessed letter is there or not
*      @param guess A string of characters that encompasses the character's guess
*/
//defines guessLetter method
  public boolean guessLetter(String guess)
  {
    /*initializes a boolean variable to check if the letter is in the phrase and 
    defines a String that concatinates all the correct letters.*/
    boolean foundLetter = false;
    String newSolvedPhrase = "";
    //loop through the phrase to find the letter
    for (int i = 0; i < phrase.length(); i++)
    {
      //if the letter(substring) in the phrase is equal to the guess
      if (phrase.substring(i, i + 1).equals(guess))
      {
        //add it to the solved phrase string
        newSolvedPhrase += guess + " ";\
        //change the boolean variable
        foundLetter = true;
      }
      //if the letter is not found
      else
      {
        //copy the space and underscore to the solved Phrase statement.
        newSolvedPhrase += solvedPhrase.substring(i * 2, i * 2 + 1) + " ";  
      }
    }
    solvedPhrase = newSolvedPhrase;
    return foundLetter;
  } 
} 