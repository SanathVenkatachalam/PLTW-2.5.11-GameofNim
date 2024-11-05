/*
 * Activity 2.5.2
 * 
 * A Player class the PhraseSolverGame
 */
import java.util.Scanner;

public class Player
{
  /* your code here - attributes */

  /* your code here - constructor(s) */ 
  public Player(){
    System.out.println("Enter player name: ");
    Scanner scanner = new Scanner(System.in);
    String newName = scanner.nextLine();

    String name = newName;
    System.out.println("Hello and welcome to the game " + name);
    int points = 0;

  }
  public Player(String inputName){
    String name = inputName;
    System.out.println("Hello and welcome to the game " + name);
    points = 0;
  }

  /* your code here - accessor(s) */ 
  //returns the player name
  public String getName(){
    return name;
  }
  //returns the amount of points a player has
  public int getPoints(){
    return points;
  }
  

  /* your code here - mutator(s) */ 
  /*sets the player's name
  @param inputName a String containing characters that represents someone's name 
  */
  public void setName(String inputName){
    //changes the name variable to the input
    name = inputName
  }
  /*adds the points for the score
  @param value a numerical value that is used to add points
  */
  public void addToPoints(int value){
    points+=value;
  }
}