public class Main {
    public static void main(String[] args) {
        //Generate word list
        WordList wordList = new WordList("./src/google-10000-english.txt");

        //Generate puzzle
        Puzzle puzzle = new Puzzle();
        System.out.println(puzzle.toString());

        //Generate solver
        Solver solver = new Solver(wordList, puzzle);

        //Preprocessing
        solver.Preprocess();

        //Print word list after preprocessing
        System.out.println("Finished preprocessing, word list: " + wordList.wordList);
        System.out.println(wordList.startingLetterHash);
    }
}