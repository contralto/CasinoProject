//Name - Claudia Gonciulea
//Class - AP Comp Sci A
//Project - Probabilistic Games

import java.awt.Graphics;                   //LOTS of imports for methods needed, used for graphics
import java.awt.Color;
import javax.swing.JPanel;

public class DBSlow extends JPanel {                  //https://javatutorial.net/display-text-and-graphics-java-jframe

    //creates "board" (green bg and size of window)
    public DBSlow() {
        setSize(800,600);
        setBackground(Color.GREEN.darker());
        setVisible(true);
    }

    //draws out all the shapes needed
    public void paint(Graphics window)    {
        board(window);
        displayInfo(window);
    }

    //designs all the shapes so they can be painted
    private void board(Graphics window) {

        window.setColor(Color.RED.darker());              //card
        window.fillRect(100,170,150,250);

        window.setColor(Color.WHITE);                     //die 1
        window.fillRect(305,180,100,100);

        window.setColor(Color.WHITE);                     //die 2
        window.fillRect(420,300,100,100);

        window.setColor(Color.GRAY.darker());             //title box
        window.fillRect(0,25,500,75);

        //winning % box
        window.fillRect(600,470,200,40);

        //average earnings box
        window.fillRect(600,530,200,40);

        //total earnings box
        window.fillRect(600,410,200,40);

        //instruction box
        window.fillRect(600,350,200,40);

        window.setColor(Color.LIGHT_GRAY);
        window.fillRect(0, 497, 200, 75);

        window.setColor(Color.GREEN.darker());
        window.fillRect(100, 145, 150, 20);

        window.fillRect(250, 520, 300, 15);
    }

    //some of the text on screen (nonchanging)
    private void displayInfo(Graphics window) {
        window.setColor(Color.WHITE);
        window.drawString("Total Earnings: ",610,435);

        window.setColor(Color.WHITE);
        window.drawString("Average Earnings: ",610,555);

        window.setColor(Color.WHITE);
        window.drawString("Win Percentage: ",610,495);
    }

}
