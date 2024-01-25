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
}
