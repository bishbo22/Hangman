import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Word word = new Word();
        String country = Word.randomCountry();
        char[] answer = new char[country.length()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = '_';
        }
        char input;
        boolean stillPlaying = true;
        int mistakeCounter = 0;
        int totalSimilarities = 0;
        char[] answer1 = new char[country.length()];
        char[] answer2 = new char[country.length()];
        while (stillPlaying) {
            answer1 = answer;
            System.out.print("Guess a letter: ");
            input = scan.nextLine().charAt(0);
            answer2 = Word.match(input,country,answer);
            for (int i = 0; i < answer.length; i++) {
                if (answer1[i] == answer2[i]) {
                    totalSimilarities++;
                }
            }
            if (totalSimilarities == answer.length) {
                mistakeCounter++;
            }
            if (mistakeCounter == 5) {
                stillPlaying = false;
            }
            totalSimilarities = 0;
        }
        System.out.println(country);
    }
}