public class Main {
    public static void main(String[] args) {
        //Generate word list
        WordList wordList = new WordList("./src/google-10000-english.txt");

        //Generate puzzle
        Puzzle puzzle;
        if(args.length > 0) {
            puzzle = new Puzzle(args[0]);
        } else {
            puzzle= new Puzzle((String) null);
        }

        System.out.println(puzzle.toString());

        //Generate solver
        Solver solver = new Solver();

        //Preprocessing
        solver.Preprocess(wordList, puzzle);

        //Print word list after preprocessing
        System.out.println("Finished preprocessing, word list: " + wordList.wordList);
        System.out.println(wordList.startingLetterHash);

        //Solve the puzzle
        solver.Solve(wordList, puzzle, 0, 6);
    }
}