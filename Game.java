import java.util.Scanner;
import java.util.Random;

public class Game {
    Player player1;
    Player player2;
    Player bot;
    Scanner scanner;
    Board board;
    int rockNum;

    public Game(){
        scanner = new Scanner(System.in);
        board = new Board();
    }
    public void play(){
        addPlayers();
        if(board.getNumplayers() == 2){
            gamePlayer();
        }else{
            botPlayer();
        }
    }
    public void botPlayer(){
        boolean flag = true;
        System.out.println("Bot allows player to move first :)");
        System.out.println(board.getNumplayers() + " player(s) currently playing");
        board.setCurrentPlayer(player1);
        board.setNextPlayer(bot);
        while(flag) {
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            System.out.print(board.getCurrentPlayer().getName() + " How many rocks do you want to pick?:");
            rockNum = scanner.nextInt();
            board.pickupRocks(rockNum);
            if(board.getNumrocks()==0){
                board.setWinner(board.getNextPlayer());
                board.setLoser(board.getCurrentPlayer());
                System.out.println("Winner: " + board.getWinner());
                System.out.println("Loser: " + board.getLoser());
                board.closeBoard();
                System.out.print("Play again?(y/n):");
                String choice = scanner.next();
                if(choice.trim().equalsIgnoreCase("y")){
                    flag = true;
                    System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
                    board.setRocks(scanner.nextInt());
                }else{
                    flag = false;
                }

                continue;
            }

            board.setCurrentPlayer();
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            rockNum = (int)(Math.random()*(board.maxRockscanbepicked -1)*1);
            if(rockNum == 0){
                rockNum = (int)(Math.random()*(board.maxRockscanbepicked -1)*1)+1;
            }else if(board.maxRockscanbepicked ==1){
                rockNum = 1;
            }
            System.out.println("Bot picks up "+ rockNum + " rocks");
            board.pickupRocks(rockNum);

            if(board.getNumrocks()==0){
                board.setWinner(board.getNextPlayer());
                board.setLoser(board.getCurrentPlayer());
                System.out.println("Winner: " + board.getWinner());
                System.out.println("Loser: " + board.getLoser());
                board.closeBoard();
                System.out.print("Play again?(y/n):");
                String choice = scanner.next();
                if(choice.trim().equalsIgnoreCase("y")){
                    flag = true;
                    System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
                    board.setRocks(scanner.nextInt());
                }else{
                    flag = false;
                }
                continue;
            }
            board.setCurrentPlayer();

        }

    }
    public void gamePlayer(){
        boolean flag = true;
        coinToss();
        System.out.println(board.getNumplayers() + " player(s) currently playing");
        while(flag) {
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            System.out.print(board.getCurrentPlayer().getName() + " How many rocks do you want to pick?:");
            rockNum = scanner.nextInt();
            board.pickupRocks(rockNum);
            if(board.getNumrocks()==0){
                board.setWinner(board.getNextPlayer());
                board.setLoser(board.getCurrentPlayer());
                System.out.println("Winner: " + board.getWinner());
                System.out.println("Loser: " + board.getLoser());
                board.closeBoard();
                System.out.print("Play again?(y/n):");
                String choice = scanner.next();
                if(choice.trim().equalsIgnoreCase("y")){
                    flag = true;
                    System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
                    board.setRocks(scanner.nextInt());
                }else{
                    flag = false;
                }

                continue;
            }

            board.setCurrentPlayer();
            System.out.print("There are " + board.getNumrocks() + " rocks left.");
            System.out.println("You can pick at max " + board.countEligibleRocks() + " rocks this turn");
            System.out.print(board.getCurrentPlayer().getName()+ " How many rocks do you want to pick?:");
            rockNum = scanner.nextInt();
            board.pickupRocks(rockNum);

            if(board.getNumrocks()==0){
                board.setWinner(board.getNextPlayer());
                board.setLoser(board.getCurrentPlayer());
                System.out.println("Winner: " + board.getWinner());
                System.out.println("Loser: " + board.getLoser());
                board.closeBoard();
                System.out.print("Play again?(y/n):");
                String choice = scanner.next();
                if(choice.trim().equalsIgnoreCase("y")){
                    flag = true;
                    System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
                    board.setRocks(scanner.nextInt());
                }else{
                    flag = false;
                }
                continue;
            }
            board.setCurrentPlayer();

        }
    }


    private void addPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name:");
        String name = scanner.next();
        if( name == null) {
            player1 = new Player();
        }else{
            player1 = new Player(name);

        }
        board.addPlayer();
        System.out.print("Would you like to set the number of rocks(yes/no):");
        String ans = scanner.next();
        if(ans.toLowerCase().trim().equals("no")){
            board.setNumrocks();
        }else{
            System.out.print("Enter number of rocks wanted(10 to 50 inclusive):");
            board.setRocks(scanner.nextInt());
        }
        board.populate();
        System.out.print("Single or Co-op?(s for single. c for co-op):");
        String answer = scanner.next();
        if(answer.toLowerCase().trim().equals("s")){
            bot = new Bot();
        }else if(answer.toLowerCase().trim().equals("c")) {
            System.out.print("Enter player name:");
            String name2 = scanner.next();
            if(name2 == null) {
                player2 = new Player();
            }else{
                player2 = new Player(name2);
            }
            board.addPlayer();

        }


    }
    private void coinToss(){
        Random rand = new Random();
        System.out.println("0 means that " + player1.getName() + " starts");
        System.out.println("1 means that " + player2.getName() + " starts");
        System.out.println("Flipping coin...");
        int coin = rand.nextInt(2);
        //0 means player1 starts
        //1 means player2 starts
        if(coin == 0){
            System.out.println(player1.getName()+ " has won the toss and will start first");
            board.setCurrentPlayer(player1);
            board.setNextPlayer(player2);

        }
        else{
            System.out.println(player2.getName()+ " has won the toss and will start first");
            board.setCurrentPlayer(player2);
            board.setNextPlayer(player1);
        }

    }
}
