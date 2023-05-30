import java.util.Random;
/* Aedin Yu Lab 2 */
public class RPS extends Game {
    private Random randomOBJ;
    private int requiredWins;
    private int currWins;
    private int currLosses;
    private int maxLosses;
    /**
      * Constructor for the RPS object.
      @param rng a Random object.
      @param requiredWins an int that specifies how many wins needed for a user to win the round.
      @param maxLosses an int that specifies how many losses the user can take before they lose the round.
      @return none.
     */
    public RPS(Random rng, int requiredWins, int maxLosses) { 
        randomOBJ = rng;
        this.requiredWins = requiredWins;
        this.maxLosses = maxLosses;
        
    }
    @Override
    /**
      * a method that returns the name of the corresponding game.
      @param none.
      @return "Rock Paper Scissors"
     */
    public String getName() { 
        return "Rock Paper Scissors";
    }
    @Override
    /**
      * a method that resets the game and prepares the game to be ran again. Returns new game details.
      @param none.
      @return "Enter rock, paper, or scissors. Beat me " +requiredWins+" times before I win "+maxLosses+" times!"
     */
    protected String prepToPlay() {
        currLosses = 0;
        currWins=0;
        return "Enter rock, paper, or scissors. Beat me " +requiredWins+" times before I win "+maxLosses+" times!";
    }
    @Override
    /**
      * a method that checks whether any game ending events have occured (wins has reached required wins or losses has reached maxLosses).
      @param none.
      @return true : Game is over. false : game is not ober
     */
    protected boolean isOver() {
        if(currWins==requiredWins) { 
            return true;
        }
        else if(currLosses==maxLosses) { 
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    /**
      * a method that checks whether a given move is a valid move ("rock","paper",scissors).
    @param move a string that represents the users move.
      @return true : the move is valid. false : the move is invalid.
     */
    protected boolean isValid(String move) {
        if(move.equals("rock")||move.equals("scissors")||move.equals("paper")) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    /**
      * a method that picks a random AI move and processes the results and shows the result of the match.
    @param move a string that represents the users move.
      @return "you win", "you lose", "we tie".
     */
    protected String processMove(String move) {
        String returnString = "";
        int aiMove = randomOBJ.nextInt(3);
        if(aiMove==0) { 
            returnString+="scissors vs. " +move;
            if(move.equals("scissors")) {
                return returnString+" we tie";
            }
            else if(move.equals("rock")){ 
                currLosses++;
                return returnString+" you win";            }
            else {
                currWins++;
                return returnString+" you lose";
            }
        }
        else if(aiMove==1) { 
            returnString+="rock vs. " +move;
            if(move.equals("scissors")) {
                currLosses++;
                return returnString+" you lose";
            }
            else if(move.equals("rock")){ 
                return returnString+" we tie";
            }
            else {
                currWins++;
                return returnString+" you win";
            }
        }
        else { 
            returnString+="paper vs. " +move;
            if(move.equals("scissors")) {
                currWins++;
                return returnString+" you win";
            }
            else if(move.equals("rock")){ 
                currLosses++;
                return returnString+" you lose";
            }
            else {
                return returnString+" we tie";
            }
        }
    }
    @Override
    /**
      * a method that returns the final result of the set.
    @param none
      @return "You win the set", "You lose the set"
     */
    protected String finalMessage() {
        if(requiredWins==currWins) {
            return "You win the set.";
        }
        else {
            return "You lose the set.";
        }
    }
}
