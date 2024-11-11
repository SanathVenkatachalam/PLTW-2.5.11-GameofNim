/*
 * Activity 2.5.2
 *
 * A Player class the PhraseSolverGame
 */

import java.util.Scanner;

public class Player {
    /* your code here - attributes */
    String name;


    /* your code here - constructor(s) */
    public Player() {
        System.out.println("Enter player name: ");
        Scanner scanner = new Scanner(System.in);
        String newName = scanner.nextLine();

        name = newName;
        System.out.println("Hello and welcome to the game " + name);

    }

    public Player(String inputName) {
        name = inputName;
        System.out.println("Hello and welcome to the game " + name);
    }

    /* your code here - accessor(s) */
    //returns the player name
    public String getName() {
        return name;
    }


    /* your code here - mutator(s) */
  /*sets the player's name
  @param inputName a String containing characters that represents someone's name
  */
    public void setName(String inputName) {
        //changes the name variable to the input
        name = inputName;
    }
}
