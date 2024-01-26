import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Puzzle {
    char[][] box; //First index indicates the side 0 = bottom, 1 left, 2 top, 3 right
                        //Second index indicates the letter on the side (going left to right, bottom to top)
    Random ran;
    int currentLetterSide = -1, currentLetterIndex = -1;
    ArrayList<Character> remainingLetters;

    public Puzzle() {
        //Initialise the array and random
        this.box = new char[4][3];
        ran = new Random();

        //Add the letters
        //TODO improve generation (Q-U on different sides (if Q present), vowels, etc.
        for(int side = 0; side < 4; side++) {
            for(int i = 0; i < 3; i++) {
                //No repeating letters
                while(true) {
                    char newLetter = (char)(ran.nextInt(26) + 'a');
                    if(!this.ContainsLetter(newLetter)) {
                        this.box[side][i] = newLetter;
                        break;
                    }
                }
            }
        }

        remainingLetters = GetLetterList();
    }

    public Puzzle(Puzzle puzzle) {
        this.box = puzzle.box;
        this.currentLetterIndex = puzzle.currentLetterIndex;
        this.currentLetterSide = puzzle.currentLetterSide;
        this.remainingLetters = new ArrayList<>(puzzle.remainingLetters);
        this.ran = new Random();
    }

    public String toString() {
        String str = " |";

        //Top row
        for(int i = 0; i < 3; i++) {
            str += this.box[2][i] + "|";
        }

        //Middle
        for(int i = 0; i < 3; i ++) {
            str += "\n" + this.box[1][i] + "| | | |" + this.box[3][i];
        }

        //Bottom
        str += "\n |";
        for(int i = 0; i < 3; i++) {
            str += this.box[0][i] + "|";
        }

        return str;
    }

    public boolean ContainsLetter(char let) {
        for(int side = 0; side < 4; side++) {
            for(int in = 0; in < 3; in++) {
                if(box[side][in] == let)
                    return true;
            }
        }

        return false;
    }

    public ArrayList<Character> GetLetterList() {
        ArrayList<Character> res = new ArrayList<>();
        for(char[] charArr : this.box) {
            for(Character c : charArr) {
                res.add(c);
            }
        }

        return res;
    }

    //returns -1 if the letter isn't present, the side num else
    public int GetLetterSide(char letter) {
        for(int side = 0; side < 4; side++) {
            for(int i = 0; i < 3; i++) {
                if(this.box[side][i] == letter) {
                    return side;
                }
            }
        }

        return -1;
    }

    public void PlayWord(String word) {
        //remove the letter s in the word from the remaining letters list
        for(Character c : word.toCharArray()) {
            remainingLetters.remove(c);
        }

        //Update the current letter index
        currentLetterSide = GetLetterSide(word.charAt(word.length()-1));

        for(int i = 0; i < 3; i++) {
            if(box[currentLetterSide][i] == word.charAt(word.length()-1)) {
                currentLetterIndex = i;
                break;
            }
        }
    }

    public boolean PuzzleComplete() {
        return remainingLetters.isEmpty();
    }
}