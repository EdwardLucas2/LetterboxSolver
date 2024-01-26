import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

public class WordList {
    ArrayList<String> wordList = new ArrayList<String>();
    HashMap<Character, ArrayList> startingLetterHash = new HashMap<>();

    public WordList(String f) {
        //Read in the words from the file
        File myFile = new File(f);
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                wordList.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Remove short words (3 or fewer letters)
        wordList.removeIf(s -> s.length() < 4);
    }

    public WordList(WordList wordList) {
        this.wordList = new ArrayList<>(wordList.wordList);
        this.startingLetterHash = new HashMap<>(wordList.startingLetterHash);
    }
    public void PruneWords(ArrayList<Character> letterList, Puzzle puzzle) {
        System.out.println("Pruning words, letter list: " + letterList);
        //Iterate over every word in the word list
        ListIterator<String> iter = wordList.listIterator();
        while(iter.hasNext()) {
            String word = iter.next();

            //Check if the box contains all the letters for the word
            if(!ContainsCharacters(word, letterList)) {
                iter.remove();
            } else {
                //Box contains all the letters for the word, check if the word can be constructed
                if(!CanConstructWord(word, puzzle)) {
                    //Can;t construct the word
                    iter.remove();
                }
            }
        }
    }

    private boolean ContainsCharacters(String word, ArrayList<Character> letterList) {
        for(Character c : word.toCharArray()) {
            if(!letterList.contains(c)) {
                return false;
            }
        }

        return true;
    }
    //Assumes all letters for the word are present
    private boolean CanConstructWord(String word, Puzzle puzzle) {
        int lastLetterSide = -1;
        //iterate over every letter i n the word
        for(int i = 0; i < word.length(); i++) {
            //Get the side of the letter
            int letterSide = puzzle.GetLetterSide(word.charAt(i));
            // and compare it to the last letter side
            if(letterSide == lastLetterSide) {
                //Same side as last letter
                return false;
            } else {
                //Different side from last letter, update last letter side
                lastLetterSide = letterSide;
            }
        }

        return true;
    }

    public void GenerateStartingLetterHash() {
        //Initialise hashmap
        for(int ch = 0; ch <= 26; ch++) {
            startingLetterHash.put((char)('a' + ch), new ArrayList<>());
        }

        //Iterate over each word in the list
        for(String word: wordList) {
            startingLetterHash.get(word.charAt(0)).add(word);
        }
    }

    public void RemoveWord(String word) {
        wordList.remove(word);
        startingLetterHash.get(word.charAt(0)).remove(word);
    }
}
