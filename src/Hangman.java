import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
/**********************************************************************
 * @file Hangman.java
 * @brief runs an interactive hangman game with the console and drawing panel
 * @author Blair Bishop, Morgan White, Jack Connolly
 * @date: 4/30/24
 ***********************************************************************/
public class Hangman {

    public static void main(String[] args) {
        //Create objects to make a scanner and randomize a country from the Word class
        Scanner scan = new Scanner(System.in);
        String country = Word.randomCountry().toLowerCase();

        //Create a character array to make answer as long as the letters in the country's name
        char[] answer = new char[country.length()];

        //Create a drawing panel to be drawn on and initialize g as a graphics variable
        DrawingPanel panel = new DrawingPanel(500, 500);
        Graphics g = panel.getGraphics();

        //This fills answer with underscores so the user cannot see what the letters are, but can see how many there are
        Arrays.fill(answer, '_');

        char input;
        boolean stillPlaying = true;
        int mistakeCounter = 0;
        int totalSimilarities = 0;

        //Create answer1 and answer2 to later help separate the previous guess from the new guess when analyzing if the user made a mistake or not
        char[] answer1 = new char[country.length()];
        char[] answer2;

        //Draw the initial hangman board and show the empty word (answer) as a string
        drawHangman(g,answer,mistakeCounter,country);

        //Allow the player to play until they win or lose based on the value of stillPlaying
        while (stillPlaying) {
            //Copies the previous value of answer to answer 1
            System.arraycopy(answer, 0, answer1, 0, answer.length);

            //Asks for and stores the user's response to the prompt
            System.out.print("Guess a letter: ");
            input = scan.nextLine().charAt(0);

            //Checks if the user's letter is in the country's name, but then changes the empty space in answer to the letter the user correctly guessed
            answer2 = Word.match(input,country,answer);

            //Checks if the previous answer1 and new answer2 match or not to see if the user successfully guessed the letter, failure means the total similarities will be the length of the country's name
            for (int i = 0; i < answer.length; i++) {
                if (answer1[i] == answer2[i]) {
                    totalSimilarities++;
                }
            }

            //If the similarities between answer1 and answer2 are the length of the country's name, then that means the user made a mistake and counts that under mistakeCounter
            if (totalSimilarities == answer.length) {
                mistakeCounter++;
            }

            //Sets the totalSimilarities to 0 so that the while loop can run again with answer1 and answer2 able to be compared without the previous round's similarities being reflected
            totalSimilarities = 0;

            //Draws the hangman setup again and this time puts in the correct letter or the body part depending on the output of drawHangman (true or false)
            if (drawHangman(g,answer2,mistakeCounter,country)) {
                stillPlaying = false;
            }
        }
        System.out.println(country);
    }

    /**
     * drawHangman() takes in these parameters and draws the hangman setup in the drawing panel using Graphics. Then, it
     * tests how many mistakes that the user has made to determine how many body parts to draw. Then, it will show the
     * user if they won or not depending on whether they guessed the word correctly or made 6 mistakes. It then returns over,
     * a variable that is true when an end-of-game decision is made.
     * @param g : Graphics,
     * @param answer : char[],
     * @param mistakeCounter : int,
     * @param country : String,
     * @return boolean
     */
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
            g.fillRect(0,310,500,500);
            g.setColor(Color.black);
            g.drawString("You Lose, The answer was \"" + country + "\"",50,350);
            over = true;
        }
        if (winOrLose(answer)) {
            g.setColor(Color.white);
            g.fillRect(0,310,500,500);
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