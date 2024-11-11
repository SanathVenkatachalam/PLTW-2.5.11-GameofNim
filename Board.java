/*
 * Activity 2.5.2
 *
 * A Board class the PhraseSolverGame
 */
import java.util.Scanner;
import java.util.Random;


public class  Board
{
    public int numRocks;
    boolean isPlaying;
    Player winner;
    Player loser;
    public int numPlayers;
    private int maxPlayers = 2;
    int maxRockscanbepicked;
    Random rand = new Random();
    Player currentPlayer;
    Player nextPlayer;
    public Board(){
        isPlaying = true;
    }

    public void populate(){
        System.out.println("There are " + numRocks + " available");
        System.out.println("You can pick up at max " + maxRockscanbepicked + " rocks at the first turn");


    }

    public int getNumrocks(){
        return numRocks;
    }
    public void setNumrocks(){
        numRocks = (int)(Math.random()*(50-10) + 10);
        maxRockscanbepicked = numRocks/2;

    }
    public void setRocks(int rocks){
        numRocks = rocks;
        maxRockscanbepicked = numRocks/2;
    }
    public void addPlayer(){
        numPlayers+=1;
        if (numPlayers> 2){
            System.out.println("Lobby Full!");
        }
    }

    public int getNumplayers(){
        return numPlayers;
    }

    public void pickupRocks(int rock){
        if (rock > maxRockscanbepicked){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Too many rocks!");
            System.out.print(getCurrentPlayer().getName()+ " How many rocks do you want to pick?:");
            int rockNum = scanner.nextInt();
            pickupRocks(rockNum);

        }
        else{
            numRocks-=rock;
            maxRockscanbepicked = numRocks/2;
            if(numRocks == 1){
                maxRockscanbepicked = 1;
            }

        }
    }
    public int countEligibleRocks(){
        return maxRockscanbepicked;
    }
    public void setWinner(Player player){
        winner = player;
    }

    public void setLoser(Player player){
        loser = player;
    }
    public void closeBoard(){
        System.out.println("Game Over");
        isPlaying = false;
    }


    public void setCurrentPlayer(Player p){
        currentPlayer = p;

    }
    public void setCurrentPlayer(){
        Player tempPlayer = getNextPlayer();
        nextPlayer = currentPlayer;
        currentPlayer = tempPlayer;

    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }


    public void setNextPlayer(Player p){
        nextPlayer = p;

    }
    public Player getNextPlayer(){
        return nextPlayer;
    }

    public String getWinner(){
        return winner.getName();
    }
    public String getLoser(){
        return loser.getName();
    }



}
