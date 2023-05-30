import java.util.Scanner;
/* Aedin Yu Lab 2 */
public abstract class Game {
    /**
      * Used to reset necessary starting values for object.
     * @return a starting message for the corresponding game.
     */
    protected abstract  String prepToPlay();
    /**
      * Checks whether a condition that indicates the corresponding game ending has been reached.
     * @return true : games over.
     * @return false : game continues.
     */
    protected abstract boolean isOver();
    /**
      * Used to validate a users move for the corresponding game.
      @param move a string that represents the user's move.
     * @return true : the move is valid and will be processed.s
     * @return false : the move is invalid and the user will be prompted to reenter.
     */
    protected abstract boolean isValid(String move);
    /**
      * Processes the move for the corresponding game.
      @param move a string that represents the user's move.
     * @return the current game state after the move has been processed.
     */
    protected abstract String processMove(String move);
    /**
      * Retrieve the final message for the corresponding game.
     * @return string that indicates the corresponding game has ended.
     */
    protected abstract String finalMessage();
    /**
      * Retrieves the name of the corresponding game.
     * @return string that represents the name of the corresponding game.
     */
    public abstract String getName();
    /**
      * Uses abstract methods to structure the typical game and run it for the user.
     * @param user a scanner object used to parse user input.
     */
    public void play(Scanner user) {
        System.out.println(prepToPlay());
        Boolean quit = false;
        while(!isOver()&&quit==false) {
            
           System.out.print("Enter Your Move or 'quit' to quit> ");
           String move = user.next();
        if(move.equals("quit")) {
            quit=true;
        }
           while(isValid(move)==false && !move.equals("quit")) {
               System.out.print("Invalid Move! try again> ");
               move=user.next();
           }
           if(quit==false){
               String processedMove = processMove(move);
               if(processedMove!=null){
           System.out.println(processedMove);
               }
           }

        }
        System.out.println(finalMessage());
    }
}
