// partnered with Nick Holt
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    private String fileName;
    private static HashSet<String> dict;
    private HashSet<Character> charactersGuessed;
    private String currentGuess;
    private int triesLeft;

    // reads the file
    Hangman(String fileName){
        this.fileName = fileName;
        dict = readFile(this.fileName);
        charactersGuessed = new HashSet<>();
        start();
    }

    // ask user what size word and how many tries you want
    private void start() {
        Scanner scan = new Scanner(System.in);

        try{
            System.out.print("Enter the size of the word you want to try and guess: ");
            int size = Integer.parseInt(scan.nextLine());
            System.out.println();

            System.out.print("Enter how many tries you want to have: ");
            triesLeft = Integer.parseInt(scan.nextLine());
            System.out.println();

            if(triesLeft <= 0 || size < 1)  {
                throw new Exception();
            }
            // set dictionary to word length
            this.wordSize(size);
            // sets up initial hangman of all blanks
            this.currentGuess = numGuesses(size);
        } catch(Exception e){
            System.out.println("Error, you entered in the wrong format, please try again...\n\n");
            dict = readFile(this.fileName);
            start();
        }
    }

    private void wordSize(int size){
        // http://www.java2s.com/example/java-api/java/util/hashset/removeif-1-0.html
            // taught how to use removeIf() function
        dict.removeIf(temp -> temp.length() != size);
    }

    private String getSomeWord(){
        return dict.toArray()[(int)(Math.random() * dict.size())].toString();
    }

    private String numGuesses(int size){
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < size; i++){
            temp.append('-');
        }
        return temp.toString();
    }

    // prints status and updates word as user guesses correct letters
    void startGame(){
        while(this.currentGuess.contains(String.valueOf('-')) && this.triesLeft > 0) {
            System.out.println("-----------------------------------------------------");
            System.out.printf("Current guess: " + this.currentGuess + "\tAttempts left: " + this.triesLeft + "\tGuessed letters: " + this.charactersGuessed + "\tSize: " + dict.size());
            Scanner scan = new Scanner(System.in);

            System.out.println();
            if(dict.size() == 2237) {
                System.out.println("Enter your first guess: ");
            }
            else {
                System.out.print("Enter your next guess: ");
            }
            String guess = scan.nextLine();
            System.out.println("-----------------------------------------------------");
            System.out.println();

            if (rightGuess(guess)) {
                Character charGuess = guess.charAt(0);
                if(charactersGuessed.contains(charGuess)){
                    System.out.println("You already guessed that letter, try again.");
                    continue;
                }
                updateWordFamilies(charGuess);
                if (!this.currentGuess.contains(String.valueOf('-'))) {
                    System.out.println("Congrats you won!!! The word was: " + getSomeWord());
                } else if (triesLeft == 0) {
                    System.out.println("Game Over, you lose. The word was: " + getSomeWord());
                }
                this.charactersGuessed.add(charGuess);
            } else {
                System.out.println("You entered an invalid guess, try again.");
            }
        }
    }

    private boolean rightGuess(String input){
        return input.replaceAll("[^a-zA-Z]", "").length() == 1;
    }

    // stores every index of words with character guessed
    private HashMap<ArrayList<Integer>, Integer> createFam(Character charGuess){
        HashMap<ArrayList<Integer>, Integer> families = new HashMap<>();
        for(String str : dict) {
            ArrayList<Integer> indices = new ArrayList<>();
            // if it doesn't exist, add -1 list
            if (!str.contains(String.valueOf(charGuess))) {
                indices.add(-1);
            }
            // else, add the index of every occurrence to the list
            else {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == charGuess) {
                        indices.add(i);
                    }
                }
            }
            // increment frequency occurred
            families.put(indices, families.getOrDefault(indices, 0) + 1);
        }
        return families;
    }

    // finds the biggest family of words
    private ArrayList<Integer> getBiggestFam(HashMap<ArrayList<Integer>, Integer> families){
        ArrayList<Integer> best = (ArrayList<Integer>) families.keySet().iterator().next();

        for(ArrayList<Integer> value : families.keySet()){
            if((families.get(value)) > (families.get(best))){
                best = value;
            }
        }
        return best;
    }

    // stores the user's guess
    private void updateCurrentGuess(ArrayList<Integer> best, Character charGuess){
        if(best.get(0) == -1){
            removeWordsWith(charGuess);
            return;
        }

        StringBuilder temp = new StringBuilder(this.currentGuess);
        for(Integer i : best){
            temp.setCharAt(i, charGuess);
        }

        this.currentGuess = temp.toString();
        fitDict(this.currentGuess, best);
    }

    // stores biggest word fam
    private void updateWordFamilies(Character charGuess){
        String before = this.currentGuess;
        HashMap<ArrayList<Integer>, Integer> families = createFam(charGuess);
        ArrayList<Integer> best = getBiggestFam(families);

        updateCurrentGuess(best, charGuess);

        if(before.equals(this.currentGuess)){
            this.triesLeft--;
        }
    }

    // iterating through words in file, removes words that don't work with the blanks/blanks and words combo
    private void fitDict(String currentGuess, ArrayList<Integer> best){
        Iterator<String> iter = dict.iterator();

        while(iter.hasNext()){
            String temp = iter.next();
            Boolean result = false;
            for(int i = 0; i < temp.length(); i++){
                if(temp.charAt(i) != currentGuess.charAt(i) && currentGuess.charAt(i) != '-'){
                    result = true;
                    break;
                } else {
                    for(Integer value : best){
                        if(temp.charAt(value) != currentGuess.charAt(value)){
                            result = true;
                            break;
                        }
                    }
                }

            }
            if(result){
                iter.remove();
            }
        }
    }

    private void removeWordsWith(Character charGuess){
        // http://www.java2s.com/example/java-api/java/util/hashset/removeif-1-0.html
            // taught how to use removeIf() function
        dict.removeIf(temp -> temp.contains(String.valueOf(charGuess)));
    }

    // adds all words from file to a set
    private HashSet<String> readFile(String fileName){
        HashSet<String> allWords = new HashSet<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                for(String word : words){
                    word = word.replaceAll("\\W", "").toLowerCase();
                    allWords.add(word);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e1) {
            System.out.println("Error, file wasn't found");
        }
        return allWords;
    }

    // plays a game of hangman then asks user if they want to play again
    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            // plays game of hangman
            Hangman game = new Hangman("words.txt");
            game.startGame();

            // asks to play again
            Scanner scan = new Scanner(System.in);
            System.out.println("Do you want to play again (Y/N): ");
            String input = scan.nextLine();
            if (input.equals("Y")) {
                playAgain = true;
                System.out.println();
            }
            else {
                playAgain = false;
            }
        }
        System.out.println("Game over.");
    }
}
