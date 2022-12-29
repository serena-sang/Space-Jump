import java.awt.*;
import javax.swing.*;

public class Game{
    public static void main(String[] args) {
        ImageIcon img = new ImageIcon("images/SpaceJumpIcon.png"); // creates the image icon
        JFrame f = new JFrame("Space Jump"); // java JFrame object
        f.setIconImage(img.getImage()); // displays icon
        Container cont = f.getContentPane(); // get container - top of the frame
        cont.setLayout(new BorderLayout()); // set Layout to Border

        GameP mgp= new GameP(); // create an object of our game panel
        cont.add(mgp, BorderLayout.CENTER ); // add this game panel to the center of the frame

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make frame closed when x button is pressed
        f.setVisible(true); // make the frame visible
        f.setSize(500, 710); // set the size of the frame
        f.setResizable(false); // makes the frame not resizeable
    }//end of main
}//end of class
