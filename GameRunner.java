public class GameRunner {
    public static void main(String[] args) {
        //Welcome message
        System.out.println("Welcome to the Game of Nim!");
        //create instance of Game called nim(Get it? Because it's called Game of Nim.)
        Game nim = new Game();
        //called the play() function to start the game.
        nim.play();

    }
}
