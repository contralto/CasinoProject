//Name - Claudia Gonciulea
//Class - AP Comp Sci A
//Project - Probabilistic Games

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;


public class DBSlowRunner extends JPanel {
    private static final int WIDTH = 800;                   //LOTS of variables;
    private static final int HEIGHT = 600;
    private static int crd, earned;
    private static int bet = 10;
    private static String suit, cInfo;           // cNumber = A - K, cInfo = card info
    private static int d1, d2, dicesum = 0;
    private static int wcount, lcount, total = 0;
    private static double wperc;

    private final static JLabel cardLabel = new JLabel("Card: Draw to Find Out!");           //these are all different texts that change as the game is played
    private final static JLabel d1Label = new JLabel("");
    private final static JLabel d2Label = new JLabel("");
    private final static JLabel winLabel = new JLabel("$0");
    private final static JLabel avgEarnLabel = new JLabel("$0");
    private final static JLabel wpercLabel = new JLabel("0%");
    private final static JLabel betLabel = new JLabel("Current Bet Amount: $" + bet);
    private final static JLabel gamecount = new JLabel("Current Game Count: " + total);
    private final static JLabel generalLabel = new JLabel("Draw a Card!");
    private final static JLabel wolLabel = new JLabel("");                                   //Win Or Lose label (wolLabel)
    private static JButton drawb, rollb, fastb, instrcb, betb;

    public static void main(String args[]) {

        //intro and instructions
        JFrame gui = new JFrame("Input Dialog");   //https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
        JOptionPane.showMessageDialog(gui, "Welcome to Drawing and Balling! First, you'll need to draw a card. After that, you'll either win, lose, or move on to the dice!");
        JOptionPane.showMessageDialog(gui, "Depending on what you roll, you may win, lose, or have to roll again. Winning payout is double what you bet! Good luck!");

        //opens the window and sets background
        final JFrame frame = new JFrame("Drawing and Balling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.GREEN.darker());
        frame.getContentPane().setLayout(null);

        //"Drawing and Balling" title image
        JLabel title = new JLabel(new ImageIcon("title.png"));       //title image
        title.setBounds(-6, -13, 500, 150);
        frame.add(title);

        cardLabel.setBounds(100, 145, 150, 20);                         //displays card above red rectangle
        cardLabel.setOpaque(false);
        frame.add(cardLabel);


        d1Label.setBounds(350, 225, 10, 10);                          //displays number of die 1
        frame.add(d1Label);


        d2Label.setBounds(465, 345, 10, 10);                          //displays number of die 2
        frame.add(d2Label);


        winLabel.setBounds(710, 426, 100, 10);                         //total earnings label
        frame.add(winLabel);


        avgEarnLabel.setBounds(725,546, 100, 10);                     //average earnings label
        frame.add(avgEarnLabel);


        wpercLabel.setBounds(715, 486, 100, 10);                       //percentage of wins
        frame.add(wpercLabel);


        gamecount.setBounds(10, 510, 200, 15);                         //total gamecount label
        frame.add(gamecount);

        betLabel.setBounds(10, 545, 200, 15);                          //current bet amount label
        frame.add(betLabel);

        generalLabel.setBounds(665, 361, 100, 15);                  //instruction label
        frame.add(generalLabel);

        wolLabel.setBounds(250, 520, 300, 15);                         //displayed at bottom whether you won or lost
        frame.add(wolLabel);


        frame.getContentPane().add(new DBSlow());                    //adds all the shapes from DBSlow
        frame.setSize(WIDTH, HEIGHT);




        drawb = new JButton("Draw!");                        //adds draw button
        drawb.setBounds(120,430,100,50);
        frame.add(drawb);
        drawb.addActionListener(new ActionListener() {        //action listener (it'll react with ___ when you push it)
            @Override
            public void actionPerformed(ActionEvent arg0) {
                crd = (int)(Math.random() * 51);                  // will pick a random number from 0 - 51

                if (crd / 13 == 0)                                // determines suit of card
                    suit = "Spades";
                else if (crd / 13 == 1)
                    suit = "Clubs";
                else if (crd / 13 == 2)
                    suit = "Diamonds";
                else suit = "Hearts";

                if (crd % 13 == 0)                                 // determines card number/type (A, 2-10, J, Q, K) and labels card
                    cInfo = "Ace of " + suit;
                else if (crd % 13 >= 10) {
                    if (crd % 13 == 10)
                        cInfo = "Jack of " + suit;
                    else if (crd % 13 == 11)
                        cInfo = "Queen of " + suit;
                    else
                        cInfo = "King of " + suit;
                }
                else cInfo = ((crd % 13) + 1) + " of " + suit;
                cardLabel.setText("Card: " + cInfo);
                if (crd % 13 > 0 && crd % 13 <= 5) {
                    wolLabel.setText("Sorry, you lose! Bet goes to the house!");
                    lcount++;
                    total++;
                    setState(0);
                }
                else if (crd % 13 == 0 || crd % 13 == 12) {
                    wolLabel.setText("Congratulations! You won $"+ bet * 2 + "!");
                    earned += bet * 2;
                    wcount++;
                    total++;
                    setState(0);
                }
                else {
                    generalLabel.setText("Roll the dice!");
                    setState(1);
                }
            }
        } );





        rollb = new JButton("Roll!");                              //adds roll button
        rollb.setBounds(370,430,100,50);
        frame.add(rollb);
        rollb.setEnabled(false);
        rollb.addActionListener(new ActionListener() {             //action listener
            @Override
            public void actionPerformed(ActionEvent arg0) {
                d1 = (int)(Math.random()*6 + 1);                       //rolls dice
                d2 = (int)(Math.random()*6 + 1);
                dicesum = d1 + d2;
                d1Label.setText("" + d1);
                d2Label.setText("" + d2);

                //announces wins/losses/roll again
                if (dicesum >= 2 && dicesum <= 7) {
                    wolLabel.setText("Congratulations! You won $"+ bet * 2 + "!");
                    earned += bet * 2;
                    wcount++;
                    total++;
                    setState(2);
                }
                else if (dicesum == 8 || dicesum == 9) {
                    wolLabel.setText("Sorry, you lose! Bet goes to the house!");
                    lcount++;
                    total++;
                    setState(2);
                }
                else if (dicesum >= 10 && dicesum <= 12) {
                    generalLabel.setText("Roll Again!");
                }
            }
        } );






        instrcb = new JButton("Instructions");                         //adds Instructions button
        instrcb.setBounds(650,120,100,50);
        frame.add(instrcb);
        instrcb.addActionListener(new ActionListener() {               //action listener
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(frame, "First, you'll draw a card. Drawing an Ace or a King means you win! On the other hand, drawing a 2, 3, 4, or 5, means you lose.");
                JOptionPane.showMessageDialog(frame, "However, drawing any other card means you get to roll the dice! An 8 or 9 is an automatic loss, 7 or below is a win, and you get to roll again with a 10, 11, or 12.");

            }
        } );




        betb = new JButton("Change Bet");                  //adds recalulate button
        betb.setBounds(650,205,100,50);
        frame.add(betb);
        betb.addActionListener(new ActionListener() {               //action listener
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String input = JOptionPane.showInputDialog("How much would you like to bet? (Increments of 10 up to 50)");

                //makes sure the user doesn't add in anything aside from what's asked for
                if (input.equals("10")) {
                    bet = 10;
                }
                else if (input.equals("20")) {
                    bet = 20;
                }
                else if (input.equals("30")) {
                    bet = 30;
                }
                else if (input.equals("40")) {
                    bet = 40;
                }
                else if (input.equals("50")) {
                    bet = 50;
                }
                else {
                    bet = 10;
                    JOptionPane.showMessageDialog(frame, "Whoops! Try again! Bet value was set to default bet value ($10).");

                }
                betLabel.setText("Current Bet Amount: $" + bet);
            }
        } );


        fastb = new JButton("Fast Mode");                //adds fast mode button
        fastb.setBounds(650,35,100,50);
        frame.add(fastb);
        fastb.setEnabled(true);
        fastb.addActionListener(new ActionListener() {               //action listener
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DBFastMode fast = new DBFastMode();                       // sets off fast mode
                fast.startGame();
            }
        } );



        frame.setVisible(true);                                               //makes everything visible, lets it close, keeps from resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


    }

    //this sets/resets things depending on what's happening in the game
    private static void setState(int s) {                                    //HUGE HELP from my dad
        if (s == 0) {
            drawb.setEnabled(false);

            Timer timer = new Timer(0, new ActionListener() {                    //delays info clearing so user knows what happened

                @Override
                public void actionPerformed(ActionEvent ae) {
                    drawb.setEnabled(true);
                    cardLabel.setText("Card: Draw to Find Out!");
                    d1Label.setText("");
                    d2Label.setText("");
                    drawb.setEnabled(true);
                    rollb.setEnabled(false);
                    winLabel.setText("$" + earned);
                    wperc = 100*(double)wcount / (double)(wcount + lcount);
                    wperc = Math.round(wperc * 100);                               //rounds to 2 decimal plaes
                    wperc /= 100;
                    wpercLabel.setText(wperc + "%");
                    double value = ((earned - ((double)total*10)) / (double)total);
                    value = Math.round(value * 100);
                    value /= 100;
                    avgEarnLabel.setText("$" + value);
                    gamecount.setText("Current Game Count: " + total);
                    generalLabel.setText("Draw a Card!");
                    wolLabel.setText("");

                }
            });
            timer.setRepeats(false);
            timer.setCoalesce(true);
            timer.setInitialDelay(2500);
            timer.start();

        }
        else if (s == 1) {                                                   //user can now roll if necessary
            rollb.setEnabled(true);
            drawb.setEnabled(false);
        }
        else if (s == 2) {                                                   //safety in case user tries to push roll button again after winning/losing
            rollb.setEnabled(false);
            setState(0);
        }
    }
}

