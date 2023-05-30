import java.util.Random;
/* Aedin Yu Lab 2 */
public class NumberGuesser extends Game {
    private Random randomOBJ;
    private int currentNumber;
    private int maxNumber;
    private int maxGuesses;
    private int currentGuesses;
    private int guess;
    /**
      * Contructs a NumberGuesser object.
      @param rng a Random object.
      @param maxNumber an int representing the max number for guessing.
      @param maxGuesses an int representing the allowed max guesses.
      @return none.
     */
    public NumberGuesser(Random rng, int maxNumber, int maxGuesses) { 
        randomOBJ = rng;
        this.maxGuesses = maxGuesses;
        this.maxNumber = maxNumber;
        
    }
    @Override
    /**
      * method returns the name of this game (NumberGuesser).
      @param none.
      @return "NumberGuesser" string.
     */
    public String getName() { 
        return "NumberGuesser";
    }
    @Override
    /**
      * method prepares the number guesser game to be played by reseting variables and picking a number using the parameters given. Returns new game details.
      @param none.
      @return "I've picked a number 1 to "+maxNumber+". You get "+maxGuesses+" guesses to guess it" string.
     */
    protected String prepToPlay() {
        currentGuesses = 0;
        guess =-1;
        int chosenNumber = randomOBJ.nextInt(maxNumber)+1;
        currentNumber=chosenNumber;
        return "I've picked a number 1 to "+maxNumber+". You get "+maxGuesses+" guesses to guess it";
    }
    @Override
    /**
      * method checks whether a game ending event has been reached like meeting maxGuesses or guessing number correctly.
      @param none.
      @return true : Games over. false : Games not over.
     */
    protected boolean isOver() {
        if(currentGuesses==maxGuesses) { 
            return true;
        }
        else if(guess==currentNumber) { 
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    /**
      * method checks whether a move is valid or not (must be a valid integer).
      @param move a string that represents the players move.
      @return true : moves valid. false : move is not valid.
     */
    protected boolean isValid(String move) {
        if(move.length()==0) {
            return false;
        }
        for(int i =0; i<move.length();i++){ 
            if(i==0){
                    if(move.charAt(0)=='1'||move.charAt(0)=='2'||move.charAt(0)=='3'||move.charAt(0)=='4'||move.charAt(0)=='5'||move.charAt(0)=='6'||move.charAt(0)=='7'||move.charAt(0)=='8'||move.charAt(0)=='9'){
                    }
                    else {
                        return false;
                    }
                }
                else 
                    if(move.charAt(i)=='0'||move.charAt(i)=='1'||move.charAt(i)=='2'||move.charAt(i)=='3'||move.charAt(i)=='4'||move.charAt(i)=='5'||move.charAt(i)=='6'||move.charAt(i)=='7'||move.charAt(i)=='8'||move.charAt(i)=='9'){
                    }
                    else {
                        return false;
                    }
            }
        return true;
    }
    @Override
    /**
      * method processes the move and counts guesses and return whether number is too high, low or correct.
      @param move a string that represents the players move.
      @return "Too Low", "Too High", "That's it!"
     */
    protected String processMove(String move) {
        if(currentNumber>Integer.parseInt(move)) {
            guess=Integer.parseInt(move);
            currentGuesses++;
            return "Too Low";
        }
        else if(currentNumber<Integer.parseInt(move)) { 
            currentGuesses++;
            guess=Integer.parseInt(move);
            return "Too High";
        }
        else { 
            guess=Integer.parseInt(move);
            return "That's it!";
        }
    }
    @Override
    /**
      * method returns the final message of the game showing the correct number.
      @param none.
      @return "The number was: "+currentNumber
     */
    protected String finalMessage() {
        return "The number was: "+currentNumber;
    }


}


