import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solver {
    private WordList wordList;
    private Puzzle puzzle;

    public Solver(WordList wordList, Puzzle puzzle) {
        this.wordList = wordList;
        this.puzzle = puzzle;
    }

    public void Preprocess() {
        wordList.PruneWords(puzzle.GetLetterList(), puzzle.box);
        wordList.GenerateStartingLetterHash();
    }

    //Heuristics for word choices
    //Returns a float between 0.0-1.0
    public float CalculateWordValue(String word) {
        //Core features of the heuristic:
        //Number of new letters in word
        //Difficulty of new letter in word
        //Easiness of the last letter in the word (the easier the better)

        //Number of new letters
        //Get letters in the word that haven't already been chosen (add them to an array)
        ArrayList<Character> newLetters = new ArrayList<>();

        for(Character c : word.toCharArray()) {
            if(puzzle.remainingLetters.contains(c)) {
                newLetters.add(c);
            }
        }

        int numNewLetters = newLetters.size();

        //Total difficulty of new letters - we want the highest number of infrequent (most difficult) letters (ignoring the last letter)
        float totalLetterDifficulty = 0;
        for(int i = 0; i < word.toCharArray().length - 1; i++) {
            totalLetterDifficulty += (100f - LetterFrequency.letterFrequency.get(word.charAt(i)));
        }

        totalLetterDifficulty /= 100f;

        //Final letter easiness - we want the most frequently occurring letter (easiest) to be last to make the next word easier to calculate
        float finalLetterEasiness = LetterFrequency.letterFrequency.get(word.charAt(0))/100f;

        //Normalise the values
        //Normalise newLetters by using min-max normalisation
        numNewLetters = (numNewLetters - 0)/(8 - 0);

        //Normalise total letter difficulty using min-max normalisation
        //The minimum value this can take will be with the easiest letters
        //Most frequent letter sum: 0.608876
        //Minimum value: 1.0-sum = 0.391124

        //The maximum value this can take is with the most difficult values
        //Least frequent value sum: 0.0545475
        //Max value: 1.0-sum = 0.945452
        totalLetterDifficulty = (totalLetterDifficulty-0.391124f)/(0.945452f-0.391124f);

        //Normalise final letter difficulty
        //Max letter difficulty (/100): 0.1116
        //Min letter difficulty (/100): 0.001962
        finalLetterEasiness = (finalLetterEasiness-0.001962f)/(0.1116f-0.001962f);

        //Now we have normalised the values, lets do a weighted average to determine the word value
        float weightedSum = numNewLetters * LetterFrequency.numLetterWeight +
                totalLetterDifficulty * LetterFrequency.totLetterDiffWeight +
                finalLetterEasiness * LetterFrequency.endLetterDiffWeight;

        //Return the sum
        return weightedSum;
    }

    public void Solve() {
        for(int step = 0; step < 6; step++) {
            String nextWord = CalculateNextWord();

        }
    }

    private String CalculateNextWord() {
        //Get all the possible words in a list
        ArrayList<String> possibleWords = new ArrayList<>();
        //Check if we have a first letter (choosing the first word or not)
        if(puzzle.currentLetterIndex < 0) {
            //Choosing the first word
            possibleWords.addAll(wordList.wordList);
        } else {
            //Not choosing the first word
            possibleWords.addAll(wordList.startingLetterHash.get(puzzle.box[puzzle.currentLetterSide][puzzle.currentLetterIndex]));
        }

        //Calculate the heuristic value for each possible word, and get the word with the highest value
        float maxVal = -1000f;
        int maxValWordIndex = -1;

        for(int i = 0; i < possibleWords.size(); i++) {
            float val = CalculateWordValue(possibleWords.get(i));
            if(val > maxVal) {
                maxVal = val;
                maxValWordIndex = i;
            }
        }

        //Return the word with the highest value
        return possibleWords.get(maxValWordIndex);
    }
}
