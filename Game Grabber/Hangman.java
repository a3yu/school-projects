/* Aedin Yu Lab 2 */
public class Hangman extends Game {
    private int minimumWord;
    private int maximumWord;
    private int maximumGuess;
    private String currentState;
    private int currentGuesses;
    private WordsList wordLOBJ;
    private String currentWord;
    private int solvedChars;
    /**
      * Hangman object constructor.
     * @param words WordList object to use for finding a random word.
     * @param minWordLen integer to represent the lower bound of the word length desired.
     * @param maxWordLen integer to represent the upper bound of the word length desired.
     * @param maxGuesses integer to represent the maximum number of guesses allowed to user.
     */

    public Hangman(WordsList words, int minWordLen, int maxWordLen, int maxGuesses) { 
        minimumWord= minWordLen;
        maximumWord = maxWordLen;
        maximumGuess = maxGuesses;
        wordLOBJ = words;
    }
    @Override
    /**
      * Method to return the name of the game.
     * @return string of the name of the game ("Hangman").
     */
    public String getName() { 
        return "Hangman";
    }
    @Override
     /**
      * Resets all game variables and returns the new game details.
      *@param none
      *@return "I've picked a "+chosenWord.length()+" letter word. Guess letters you think are in the word. You get "+maximumGuess+" guesses."
     */
    protected String prepToPlay() {
        String chosenWord = wordLOBJ.getWord(minimumWord, maximumWord);
        currentWord=chosenWord;
        solvedChars=0;
        currentState="";
        currentGuesses = 0;
        for (int i = 0; i < chosenWord.length(); i++) {
            currentState+="_";
        }
        return "I've picked a "+chosenWord.length()+" letter word. Guess letters you think are in the word. You get "+maximumGuess+" guesses.";
    }
    @Override
     /**
      * Checks whether the game is concluded by either solving the word or guess limit has been reached.
     * @return true : max guesses or word has been solved. Thus, game ends.
     * @return false : none of the above has occured. Thus, game continues.
     */
    protected boolean isOver() {
        if(currentGuesses==maximumGuess) { 
            return true;
        }
        else if(solvedChars==currentWord.length()) { 
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    /**
      * Checks whether the move entered by the user is valid (1 lower-case letter).
     * @param move a string that represents the user's move.
     * @return true : the string is a one letter, lower case string.
     * @return false : the string is not one letter or lowercase.
     */
    protected boolean isValid(String move) {
        if(move.length()==1 && Character.isLetter(move.charAt(0))) {
            move.toLowerCase(); 
            return true;
        }
        return false;
    }
    @Override
     /**
      * Updates the currentState of the the string that represents the users progress in the game and iterates currentGuesses.
     * @param move a string that represents the user's move.
     * @return currentState which represents the user's progress in the game (currentWord with initially no characters and with all guessed letters filled in).
     */
    protected String processMove(String move) {
        String newWord = "";
        int solved = 0;
        currentGuesses+=1;
       for (int i = 0; i < currentWord.length(); i++) {
        if(currentWord.charAt(i)==move.charAt(0)) { 
            newWord+=currentWord.charAt(i);
            solved+=1;
        }   
        else if(currentState.charAt(i)!='_') {
               newWord+=currentState.charAt(i);
               solved+=1;
           }
           else {
               newWord+="_";
           }
       }
       currentState= newWord;
       solvedChars=solved;
       return currentState;
    }
    @Override
    /**
      * Method to return the final message of the game.
     * @return string that states currentWord indicatting it was the final word.
     */
    protected String finalMessage() {
    return "The word was: "+currentWord;
    }


}
