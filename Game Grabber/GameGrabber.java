import java.util.Random;
import java.util.Scanner;
/* Aedin Yu Lab 2 */
public class GameGrabber {
    private Game[] games;
    private Scanner user;
    /**
      * Constructor for GameGrabber object.
     * @param games an array of game objects.
     * @param user a scanner object used throughout session.
     * @return none
     */
   public GameGrabber(Game[] games, Scanner user) { 
       this.games = games;
       this.user = user;
   } 
   /**
      * Method that pulls up game menu and allows user to select game, runs until user picks the quit option.
      @param none.
      @return none.
     */
   public void doMenu() {
       Boolean quit = false;
    while(!quit) {
        for (int i = 0; i < games.length; i++) {
            System.out.println(i+") "+games[i].getName());
        }
        System.out.println(games.length+") Quit");
        System.out.print("Pick a game (0-"+games.length+") ");
        Boolean validMove = false;
        while(!validMove){
        String input = user.next();
        try{
            int parseInt = Integer.parseInt(input);
            if(0<=parseInt && parseInt<=games.length){
                if(parseInt==games.length) { 
                    quit=true;
                }
                else {
                games[Integer.parseInt(input)].play(user);
                }
            validMove = true;
            }
            else {
                System.out.print("Pick a game (0-"+games.length+") ");
            }
        }
        catch(NumberFormatException error){
            System.out.print("Pick a game (0-"+games.length+") ");
        }
    }
    }
    System.out.print("goodbye");
   }
   /**
      * Creates a GameGrabber object and runs doMenu() using it.
      @param none.
      @return none.
     */
   public static void main(String[] args) { 
       Random rng = new Random();
       WordsList wl = new WordsList(rng);
       Scanner user = new Scanner(System.in);
       Game[] games = new Game[4];
       games[0] = new Hangman(wl, 1, 5, 10);
       games[1] = new NumberGuesser(rng, 200, 10);
       games[2] = new RPS(rng, 5, 2);
       games[3] = new WordJumble(wl, rng, 1, 5, 10);
       GameGrabber gameGrab = new GameGrabber(games, user);
       gameGrab.doMenu();
   }
}
