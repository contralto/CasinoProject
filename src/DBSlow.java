//Name - Claudia Gonciulea
//Class - AP Comp Sci A
//Project - Probabilistic Games

import java.awt.Graphics;                   //LOTS of imports for methods needed, used for graphics
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Event;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class DBSlow extends JPanel {                  //https://javatutorial.net/display-text-and-graphics-java-jframe
    private int card, earned;
    private int bt;
    private String suit, cNumber, cInfo;                // cNumber = A - K, cInfo = card info
    private int die1, die2, dicesum = 0;
    private double gc, wcount, lcount, total = 0;
    private double wperc;
    private int cState;

    //creates "board" (green bg and size of window)
    public DBSlow() {
        setSize(800,600);
        setBackground(Color.GREEN.darker());
        setVisible(true);
    }

    //draws out all the shapes needed
    public void paint(Graphics window)
    {
        board(window);
        displayInfo(window);
    }

    //designs all the shapes so they can be painted
    public void board(Graphics window) {

        window.setColor(Color.RED.darker());              //card
        window.fillRect(100,170,150,250);

        window.setColor(Color.WHITE);                     //die 1
        window.fillRect(305,180,100,100);

        window.setColor(Color.WHITE);                     //die 2
        window.fillRect(420,300,100,100);

        window.setColor(Color.GRAY.darker());             //title box
        window.fillRect(0,25,500,75);

        window.setColor(Color.GRAY.darker());             //winning % box
        window.fillRect(600,470,200,40);

        window.setColor(Color.GRAY.darker());             //average earnings box
        window.fillRect(600,530,200,40);

        window.setColor(Color.GRAY.darker());             //total earnings box
        window.fillRect(600,410,200,40);

        window.setColor(Color.GRAY.darker());                   //instruction box
        window.fillRect(600,350,200,40);

    }

    //some of the text on screen (nonchanging)
    public void displayInfo(Graphics window) {
        window.setColor(Color.WHITE);
        window.drawString("Total Earnings: ",610,435);

        window.setColor(Color.WHITE);
        window.drawString("Average Earnings: ",610,555);

        window.setColor(Color.WHITE);
        window.drawString("Win Percentage: ",610,495);
    }

}
