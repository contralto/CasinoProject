//Name - Claudia Gonciulea
//Class - AP Comp Sci A
//Project - Probabilistic Games

import java.lang.Math;
import javax.swing.*;
import java.lang.Integer;
import javax.swing.JOptionPane;

public class DBFastMode {                //this is Fast Mode!
    private int card, earned;                     // card # and total earnings
    private int dicesum = 0;          // value of dice and their sum
    private int wcount, lcount, total, gc = 0;    // counts for wins and losses, total games played, game count (rounds)
    private double wperc;                         // percentage of wins

    // this is the method that begins the game; it welcomes the user, takes in the game count, and then runs the game method
    public void startGame() {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Welcome to fast mode! Fast mode will continue until you decide to exit. All results will be compiled.");
        String input;
        do {
            input = JOptionPane.showInputDialog("How many games (must be less than 1 billion) would you like to run? Press 0 to exit.");
            //System.out.println(input);
            gc = Integer.parseInt(input);
            total += gc;
            //System.out.println(total);
            for (int k = 1; k <= gc; k++) {
                //System.out.println("Game " + k);
                runGame();
            }
        } while (!(input.equals("0")));
        JOptionPane.showMessageDialog(frame, "Thanks for playing!");
    }

    //Runs all the game steps, all if statements check rules
    public void runGame() {
        getCard();

        if (card % 13 == 0 || card % 13 == 12) {
            wcount++;
            earned += 20;
        }

        else if (card % 13 >= 1 && card % 13 <= 5) {
            lcount++;
        }

        else {
            do {
                getDice();

                if (dicesum >= 2 && dicesum <= 7) {
                    wcount++;
                    earned += 20;
                }

                else if (dicesum == 9 || dicesum == 8) {
                    lcount++;
                }
            } while (dicesum >= 10 && dicesum <= 12);
        }
        calcWin();
        expValue();
    }


    //Card portion of the game!
    public void getCard() {
        card = (int)(Math.random() * 51);           // will pick a random number from 0 - 51
    }

    //Die portion of game!
    public void getDice() {
        int die1 = (int) (Math.random() * 6 + 1);
        int die2 = (int) (Math.random() * 6 + 1);
        dicesum = die1 + die2;
    }

    // calculates winning percentage (experimental probability of winning)
    public void calcWin() {
        wperc = 100 * (double)wcount / (wcount + lcount);
        wperc = Math.round(wperc * 10);
        wperc /= 10;
    }

    public void expValue() {
        if (wcount + lcount == total) {
            JFrame frame = new JFrame();
            double value = ((earned - ((double)total*10)) / (double)total);
            value = Math.round(value * 100);
            value /= 100;
            JOptionPane.showMessageDialog(frame, "Total games run: " + total + "\nAverage Winnings: " + value + "\nWinning Percentage: " + wperc + "%");
        }
    }
}