import java.util.ArrayList;

public class Solver {
    private WordList wordList;
    private Puzzle puzzle;

    public Solver(WordList wordList, Puzzle puzzle) {
        this.wordList = wordList;
        this.puzzle = puzzle;
    }

    public void Preprocess() {
        wordList.PruneWords(puzzle.GetLetterList());
        wordList.GenerateStartingLetterHash();
    }

    //Heuristics for word choices
    //Returns a float between 0.0-1.0
    public float CalculateWordValue(String word) {
        //Core features of heuristic
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
        //Max letter difficulty - the 8 most difficult letters (summed/100) = 0.608876
        //Min letter difficulty - the 8 easiest letters (summed/100) = 0.0545475
        totalLetterDifficulty = (totalLetterDifficulty-0.0545475f)/(0.608876f-0.0545475f);

        //Normalise final letter difficulty
        //Max letter difficulty (/100): 0.1116
        //Min letter difficulty (/100): 0.001962
        finalLetterEasiness = (finalLetterEasiness-0.001962f)/(0.1116f-0.001962f);

        //Now we have normalised the values, lets do a weighted average to determine the
    }
}
