package hangmancmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HangmanCmd {

    public static void main(String[] args) throws InterruptedException {
        Game g = new Game(pickWord());
        playGame(g);

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Character getCharacterFromUser() {
        Pattern p = Pattern.compile("[a-zA-z]+");
        Scanner sc = new Scanner(System.in);
        Character c;
        boolean isOk = false;
        do {
            System.out.println("Please enter a letter!");
            while (!sc.hasNext(p)) {
                System.out.println("That's not a letter!");
                sc.next();
            }
            c = sc.next(p).charAt(0);
            isOk = true;
        } while (!isOk);
        return Character.toUpperCase(c);

    }

    public static boolean isIncluded(String word, Character c) {

        boolean result = word.contains(c.toString());
        return result;
    }

    public static List<Character> replaceCorrectLetters(String word, List<Character> list, Character c) {
        for (int i = 0; i < (word.length()); i++) {
            if (word.charAt(i) == c) {
                list.set(i, c);
            }
        }
        return list;
    }

    public static String pickWord() {
        List<String> wordList = new ArrayList();
        wordList.add("GIRAFFE");
        wordList.add("WHALE");
        wordList.add("HIPPOPOTAMUS");
        wordList.add("EAGLE");
        wordList.add("MONKEY");
        Random generator = new Random();
        int i = generator.nextInt(wordList.size()) + 1;
        return wordList.get(i);
    }

    public static void playRound(Game g) {
        clearScreen();
        g.printBoard();
        System.out.println("");
        g.printLetterList();
        Character c = getCharacterFromUser();
        if (isIncluded(g.getWord(), c)) {
            replaceCorrectLetters(g.getWord(), g.getLetterList(), c);

        } else {
            g.setMistakes(g.getMistakes() + 1);
            g.reformatOnMistake();
        }
    }

    public static void playGame(Game g) {
        while (true) {
            playRound(g);
            if (g.getMistakes() == 6) {
                clearScreen();
                g.reformatOnMistake();
                g.printBoard();
                System.out.println("YOU LOST!!! THE WORD WAS: " + g.getWord());
                break;
            }
            if (g.checkIfComplete(g.getLetterList())) {
                clearScreen();
                g.printBoard();
                System.out.println("");
                g.printLetterList();
                System.out.println("CONGRATULATIONS YOU WON!!!");
                break;
            }
        }
    }
}
