/*
 * Activity 2.5.2
 *
 * A Board class the PhraseSolverGame
 */

import java.util.Scanner;


public class Board {
    /*attributes include: 
    numRocks( number of rocks to be picked),
    winner: winner of the game
    loser: loser of the game
    numPlayers: number of players currently playing
    maxPlayers: maximum number of players 
    maxRocksCanBePicked: maximum rocks that can be picked each turn
    currentPlayer: current player who is picking up the rocks
    nextPlayer: next player after the currentPlayer
     */
    
    public int numRocks;
    Player winner;
    Player loser;
    public int numPlayers;
    private int maxPlayers = 2;
    int maxRocksCanBePicked;
    Player currentPlayer;
    Player nextPlayer;

    public Board() {
    }

    public void displayBoard() {
        //display both number of rocks available and number of rocks player can pick at each turn.
        System.out.println("There are " + numRocks + " available");
        System.out.println("You can pick up at max " + maxRocksCanBePicked + " rocks at the first turn");


    }
    //outputs number of rocks
    public int getNumrocks() {
        //return the number of rocks
        return numRocks;
    }

    public void setNumrocks() {
        //sets a random value of rocks if the user opts out in imputting their own number.
        numRocks = (int) (Math.random() * (50 - 10) + 10);
        maxRocksCanBePicked = numRocks / 2;

    }

    public void setRocks(int rocks) {
        //user can set their own number of rocks.
        numRocks = rocks;
        maxRocksCanBePicked = numRocks / 2;
    }

    public void addPlayer() {
        // adds the player to the lobby.
        numPlayers += 1;
        if (numPlayers > maxPlayers) {
            //if more than 2 players join, then the lobby is overcrowded.
            System.out.println("Lobby Full!");
        }
    }

    public int getNumplayers() {
        //returns the number of players currently playing.
        return numPlayers;
    }
    /* pickupRocks: board removes the rocks from the "pile"
    
     @param rock: number of rocks user wants the remove. Value inputted using scanner.
    */
    public void pickupRocks(int rock) {
        
        if (rock > maxRocksCanBePicked) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Too many rocks!");
            System.out.print(getCurrentPlayer().getName() + " How many rocks do you want to pick?:");
            int rockNum = scanner.nextInt();
            pickupRocks(rockNum);

        } else {
            numRocks -= rock;
            maxRocksCanBePicked = numRocks / 2;
            if (numRocks == 1) {
                maxRocksCanBePicked = 1;
            }

        }
    }
    //counts the number of rocks that the user can remove each turn.
    public int countEligibleRocks() {
        return maxRocksCanBePicked;
    }

    //sets Winner. The person who does not take the last rock wins.
    public void setWinner(Player player) {
        winner = player;
    }
    //sets Loser. The person who takes the last rock loses.
    public void setLoser(Player player) {
        loser = player;
    }
    
    //shuts down the game.
    public void closeBoard() {
        System.out.println("Game Over");
    }

    //switches the turns to the next player
    //@param Player p: player who gets the turn
    public void setCurrentPlayer(Player p) {
        currentPlayer = p;

    }
    
    //sets the current player
    public void setCurrentPlayer() {
        Player tempPlayer = getNextPlayer();
        nextPlayer = currentPlayer;
        currentPlayer = tempPlayer;

    }
    
    
    //gets the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    //sets Next player turn
    //@param: Player p: player who is getting the next turn
    public void setNextPlayer(Player p) {
        nextPlayer = p;

    }
    //returns the next Player
    public Player getNextPlayer() {
        return nextPlayer;
    }
    
    //gets the winner of the game
    public String getWinner() {
        return winner.getName();
    }
    //gets the loser of the game.
    public String getLoser() {
        return loser.getName();
    }


}
