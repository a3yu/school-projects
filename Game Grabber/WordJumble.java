import java.util.Random;
/* Aedin Yu Lab 2 */
public class WordJumble extends Game{
    private Random randomOBJ;
    private int minWordLen;
    private int maxWordLen;
    private WordsList wordsListOBJ;
    private int currGuesses;
    private int maxGuesses;
    private String currWord;
    private String guess;
    /**
      * constructor for the WordJumble object.
   * @param wl WordsList object
   * @param rng Random object
    *@param minWordLen smallest word to jumble
   * @param maxWordLean largest word to jumble
    *@param maxGuesses max allowed guesses.
     * @return none
     */
    public WordJumble(WordsList wl, Random rng, int minWordLen, int maxWordLen, int maxGuesses) { 
        randomOBJ = rng;
        this.minWordLen = minWordLen;
        this.maxWordLen = maxWordLen;
        this.maxGuesses = maxGuesses;
        this.wordsListOBJ = wl;
        
    }
    @Override
    /**
      * returns the name of the game.
   * @param none
     * @return "Word jumble"
     */
    public String getName() { 
        return "Word jumble";
    }
    @Override
    /**
      * resets all variables to allow users to play another game and picks out new word to junmble and jumbles it. Returns new game details.
    *@param none
     * @return "The following is a jumbled up word: "+newWord+" You get "+maxGuesses+ " guesses to guess it"
     */
    protected String prepToPlay() {
        String newWord = wordsListOBJ.getWord(minWordLen, maxWordLen);
        currWord = newWord;
        guess ="";
        currGuesses = 0;
        Boolean notScramble = true;
        while(notScramble){
         for(int i = newWord.length()-1; i>=1; i--) { 
            int j = randomOBJ.nextInt(i+1);
            char temp=newWord.charAt(i);
            newWord = newWord.substring(0,i)+newWord.charAt(j)+newWord.substring(i+1);
            newWord = newWord.substring(0,j)+temp+newWord.substring(j+1);
        }
        if(!newWord.equals(currWord)) {
            notScramble=false;
        }
    }
        return "The following is a jumbled up word: "+newWord+" You get "+maxGuesses+ " guesses to guess it";
    }
    @Override
    /**
      * checks whether a game ending event has been reached. (guesses or guessed correct)
   * @param none
     * @return true : game is over. false : game isnt over.
     */
    protected boolean isOver() {
        if(currGuesses == maxGuesses) { 
            return true;
        }
        else if(guess.equals(currWord)) { 
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    /**
      * All moves are valid so always returns true. counts guesses.
    *@param move represents the users input
      *@return true : valid move.
     */
    protected boolean isValid(String move) {
        currGuesses++;
        guess =move;
        return true;
    }
    @Override
    /**
      * processes the move by returning nothing if the guess is correct and returning prompt if not correct.
   * @param move represents the users input
     * @return null : move is correct. "Thats not it" : move is not correct.
     */
    protected String processMove(String move) {
        if(move.equals(currWord)) { 
            return null;
        }
        else {
            return "That's not it";
        }
    }
    @Override
    /**
      * returns the final message revealing word.
    *@param none
     * @return "The word was " +currWord
     */
    protected String finalMessage() {
        return "The word was " +currWord;
    }
}
