import java.util.Random;
import java.util.Scanner;

public class Game {
    /*attributes include:
    player1: player 1
    player2: player 2
    bot: the AI bot
    scanner: for input purposes
    board: the platform for the game
     */
    Player player1;
    Player player2;
    Bot bot;
    Scanner scanner;
    Board board;
    int rockNum;

    //set up the game.
    public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
    }

    // actually play the game
    public void play() {
        //adds the players to the game
        addPlayers();
        //if there are 2 players then it's a 1v1, else its human vs computer.
        if (board.getNumplayers() == 2) {
            //multiplayer
            gamePlayer();
        } else {
            //singleplayer
            botPlayer();
        }
    }

    public void botPlayer() {
        //singleplayer
        //flag is for the while loop
        boolean flag = true;
        //bot is nice, so it lets player move first :)
        System.out.println("Bot allows player to move first :)");
        System.out.println(board.getNumplayers() + " player(s) currently playing");
        //sets the current player
        board.setCurrentPlayer(player1);
        //sets the next player
        board.setNextPlayer(bot);
        while (flag) {
            //informs player that they can pick up set number of rocks.
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            System.out.print(board.getCurrentPlayer().getName() + " How many rocks do you want to pick?:");
            //user inputs number of rocks
            rockNum = scanner.nextInt();
            board.pickupRocks(rockNum);
            if (board.getNumrocks() == 0) {
                //endgame
                flag = endGame();

                continue;
            }
            //bot's turn
            board.setCurrentPlayer();
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            //Bot picks up random rocks.
            rockNum = (int) (Math.random() * (board.maxRocksCanBePicked - 1) * 1);
            if (rockNum == 0) {
                //in case of last rock(player is not controlling it)
                rockNum = (int) (Math.random() * (board.maxRocksCanBePicked - 1) * 1) + 1;
            } else if (board.maxRocksCanBePicked == 1) {
                rockNum = 1;
            }
            System.out.println("Bot picks up " + rockNum + " rocks");
            board.pickupRocks(rockNum);

            if (board.getNumrocks() == 0) {
                //endGame
                flag = endGame();
                continue;
            }
            board.setCurrentPlayer();

        }

    }

    public void gamePlayer() {
        boolean flag = true;
        //coin toss to randomly assign a starting player.
        coinToss();
        System.out.println(board.getNumplayers() + " player(s) currently playing");
        while (flag) {
            //player picks up chosen amount of rocks
            rockNum = getRocks();
            board.pickupRocks(rockNum);
            if (board.getNumrocks() == 0) {
                //end Game
                flag = endGame();
                continue;
            }
            //this oscillates between the two players
            //same as the first conditionals.
            board.setCurrentPlayer();
            rockNum = getRocks();
            board.pickupRocks(rockNum);
            if (board.getNumrocks() == 0) {
                flag = endGame();
                continue;
            }
            board.setCurrentPlayer();

        }
    }


    private void addPlayers() {
        //This function adds the players and the rocks in the game.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: ");
        //gets inputName
        String name = scanner.next();
        //adds player 1
        player1 = new Player(name);
        board.addPlayer();
        System.out.print("Would you like to set the number of rocks(yes/no): ");
        //player can enter number of rocks or random.
        String ans = scanner.next();
        if (ans.toLowerCase().trim().equals("no")) {
            board.setNumrocks();
        } else {
            setUpRocks();
        }
        board.displayBoard();
        //singlplayer/multiplayer selection
        setGameMode();

    }

    private void setUpRocks() {
        //This function allows the user to enter number of rocks they wanted.
        System.out.print("Enter number of rocks wanted (10 to 50 inclusive): ");
        int rocks = scanner.nextInt();
        //input validation
        if (rocks < 10 || rocks > 50) {
            System.out.println("Too many rocks!");
            setUpRocks();
        } else {//sets up the rocks
            board.setRocks(rocks);
        }
    }

    private void setGameMode() {
        //user can play against another user or bot
        System.out.print("Single or Co-op? (s for single. c for co-op): ");
        String answer = scanner.next();
        if (answer.toLowerCase().trim().equals("s")) {
            //adds a bot(basically a Player)
            bot = new Bot();
        } else if (answer.toLowerCase().trim().equals("c")) {
            //same process of player 1
            System.out.print("Enter player name:");
            String name2 = scanner.next();
            if (name2 == null) {
                player2 = new Player();
            } else {
                player2 = new Player(name2);
            }
            board.addPlayer();

        } else {
            //call back function if no good response is given.
            setGameMode();
        }
    }


    private void coinToss() {
        Random rand = new Random();
        //coin toss information
        System.out.println("0 means that " + player1.getName() + " starts");
        System.out.println("1 means that " + player2.getName() + " starts");
        System.out.println("Flipping coin...");
        int coin = rand.nextInt(2);
        //0 means player1 starts
        //1 means player2 starts
        if (coin == 0) {
            System.out.println(player1.getName() + " has won the toss and will start first");
            board.setCurrentPlayer(player1);
            board.setNextPlayer(player2);

        } else {
            System.out.println(player2.getName() + " has won the toss and will start first");
            board.setCurrentPlayer(player2);
            board.setNextPlayer(player1);
        }

    }

    private int getRocks() {
        System.out.print("There are " + board.getNumrocks() + " rocks left.");
        System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
        System.out.print(board.getCurrentPlayer().getName() + " How many rocks do you want to pick?:");
        return scanner.nextInt();

    }

    private boolean endGame() {
        //closes the game
        boolean flag = false;
        board.setWinner(board.getNextPlayer());
        board.setLoser(board.getCurrentPlayer());
        //declares winner
        System.out.println("Winner: " + board.getWinner());
        //declares loser
        System.out.println("Loser: " + board.getLoser());
        board.closeBoard();
        System.out.print("Play again?(y/n):");
        String choice = scanner.next();
        //restart/ play again clause
        if (choice.trim().equalsIgnoreCase("y")) {
            flag = true;
            System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
            board.setRocks(scanner.nextInt());
        } else {
            flag = false;
        }
        return flag;
    }
}
