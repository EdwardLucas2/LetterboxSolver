This program solves the Letter Boxed puzzle in The New York Times: https://www.nytimes.com/puzzles/letter-boxed.

**Basic Operation:** \
The main function takes an optional argument which, if not null, contains the path to the puzzle to be solved.
If the argument is null a randomly generated puzzle is generated and (attempted to be) solved instead.

**Puzzle File Format**: \
A text file with 4 lines, each line must have 3 characters, containing the letters for each side.
An example file 'PuzzleInput.puz' is provided.

### Class Overview
**Main class:** \
Instantiates the solver class and word list class, and calls the overarching functions

**WordList:** \
Contains an array of words possible in a puzzle, and a hashmap of a characters to a list, with the key being the words starting letter.
It also contains helper functions for adding, removing, and validating words.

**Puzzle:** \
Contains all the information about a puzzle, the letters, their position, the remaining letters etc.
Contains helper functions for playing words etc.

**Solver:** \
Contains the logic used to solve a puzzle, it currently operates recursively.
It currently uses heuristics to select the best next word, and backtracks if a dead-end is reached.

**LetterFrequency:** \
Contains static variables used to configure the heuristics and weightings used in the solver.

### Heuristics
There are currently 3 different criteria used in a weighted average to calculate a words 'value'.
Each is a normalised float from 0.0-1.0.
1) Number of remaining letters in puzzle included in the word
2) Sum of the difficulty (100 - the frequency of a letter) of all the new letters in the word, excluding the last letter
3) The easiness of the last letter in the word (it's frequency)

#### TODO:
- Better solution reporting, maybe implemented by storing the played words in the puzzle class
- Changing the solver from a recursive implementation to an iterative one
- Better/new heuristics and weightings
- Better normalisation for heuristic values
- Better random puzzle generation - currently impossible puzzles can be generated (i.e. no vowels, a Q without a U, etc.)

##### References:
Letter frequency: \
https://www3.nd.edu/~busiforc/handouts/cryptography/letterfrequencies.html

Word List: \
https://github.com/first20hours/google-10000-english/