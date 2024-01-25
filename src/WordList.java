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

    public void PruneWords(ArrayList<Character> letterList) {
        System.out.println("Pruning words, letter list: " + letterList);
        //Iterate over every word in the word list
        ListIterator<String> iter = wordList.listIterator();
        while(iter.hasNext()) {
            //Iterate over every character in the word
            for(char c : iter.next().toCharArray()) {
                //Check if the puzzle contains the character
                if(!letterList.contains(c)) {
                    //It doesn't, remove the word
                    iter.remove();
                    break;
                }
            }
        }
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
}
