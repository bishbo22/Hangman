import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;


public class Hangman {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String country = Word.randomCountry();
        char[] answer = new char[country.length()];
        DrawingPanel panel = new DrawingPanel(500, 500);
        Graphics g = panel.getGraphics();
        Arrays.fill(answer, '_');
        char input;
        boolean stillPlaying = true;
        int mistakeCounter = 0;
        int totalSimilarities = 0;
        char[] answer1 = new char[country.length()];
        char[] answer2;
        drawHangman(g,answer,mistakeCounter,country);
        while (stillPlaying) {
            System.arraycopy(answer, 0, answer1, 0, answer.length);
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
            if (mistakeCounter == 6) {
                stillPlaying = false;
            }
            totalSimilarities = 0;
            if (drawHangman(g,answer2,mistakeCounter,country)) {
                stillPlaying = false;
            }
        }
        System.out.println(country);
    }

    public static boolean drawHangman(Graphics g,char[] answer,int mistakeCounter,String country) {
        boolean over = false;
        g.setColor(Color.white);
        g.fillRect(0,0,500,500);
        g.setColor(Color.black);
        g.drawLine(50,300,150,300);
        g.drawLine(100,300,100,50);
        g.drawLine(100,50,300,50);
        g.drawLine(300,50,300,75);
        g.drawString(Arrays.toString(answer),50,350);
        if (mistakeCounter > 0) {
            g.drawOval(275,75,50,50);
        }
        if (mistakeCounter > 1) {
            g.drawLine(300,125,300,225);
        }
        if (mistakeCounter > 2) {
            g.drawLine(300,140,275,135);
        }
        if (mistakeCounter > 3) {
            g.drawLine(300,140,325,135);
        }
        if (mistakeCounter > 4) {
            g.drawLine(300,225,275,250);
        }
        if (mistakeCounter > 5) {
            g.drawLine(300,225,325,250);
            g.setColor(Color.white);
            g.fillRect(0,300,500,500);
            g.setColor(Color.black);
            g.drawString("You Lose, The answer was \"" + country + "\"",50,350);
            over = true;
        }
        if (winOrLose(answer)) {
            g.setColor(Color.white);
            g.fillRect(0,300,500,500);
            g.setColor(Color.black);
            g.drawString("You Win",50,350);
            over = true;
        }
        return over;
    }

    public static boolean winOrLose(char[] answer) {
        int counter = 0;
        boolean win = false;
        for (char c : answer) {
            if (c == '_') {
                counter++;
            }
        }
        if (counter == 0) {
            win = true;
        }
        return win;
    }
}