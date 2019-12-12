package hangmancmd;

import java.util.ArrayList;
import java.util.List;


public class Game {

    private List<String> board;
    private String word;
    private List<Character> letterList;
    private int mistakes;

    public Game(String word) {
        this.word = word;
        board = createNewBoard();
        this.mistakes = 0;
        this.letterList = breakWordToLetters(word);

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Character> getLetterList() {
        return letterList;
    }

    public void setLetterList(List<Character> letterList) {
        this.letterList = letterList;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void printBoard() {
        System.out.println(this.board);
    }

    public void printLetterList() {
        
        for (Character letter : this.letterList) {
            System.out.print(letter);
        }
        System.out.println("");
    }

    public static List<String> createNewBoard() {
        List<String> board = new ArrayList();
        board.add("+ +----+-+\n");
        board.add("+ +----+|+\n");
        board.add("| |      \n");
        board.add("| |       \n");
        board.add("| |       \n");
        board.add("| | 	   \n");
        board.add("| |       \n");
        board.add("| |\n");
        board.add("[_");

        return board;
    }

    public void reformatOnMistake() {
        switch (this.mistakes) {
            case 0:
                break;
            case 1:
                this.board.set(2, "| |     !\n");
                this.board.set(3, "| |    (@)\n");
                break;
            case 2:
                this.board.set(4, "| |     | \n");
                this.board.set(5, "| | 	  |\n");
                break;
            case 3:
                this.board.set(4, "| |     |/\n");
                break;
            case 4:
                this.board.set(4, "| |    \\|/\n");
                break;
            case 5:
                this.board.set(6, "| |      \\\n");
                break;
            case 6:
                this.board.set(6, "| |    / \\\n");
                break;

        }
    }

    public boolean checkIfComplete(List<Character> list) {
        String tempWord = "";
        for (Character character : list) {
            tempWord = tempWord + character;

        }
        if (tempWord.equalsIgnoreCase(word)) {
            return true;
        } else {
            return false;
        }

    }

    public List<Character> breakWordToLetters(String word) {
        List<Character> letterList = new ArrayList();
        for (int i = 0; i < word.length(); i++) {
            if (i == 0 || i == (word.length() - 1)) {
                letterList.add(word.charAt(i));
            } else {
                letterList.add('_');
            }
        }
        return letterList;
    }
}
