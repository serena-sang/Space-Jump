import javax.swing.*;
import java.awt.*;

public class Monster extends Character{
    private static int x, y, w, h;
    private ImageIcon monster = null; // the onion!

    public Monster (int x, int y, int w, int h){ // manipulates the x and y coords and the width and height
        super (x, y, w ,h);
        monster = new ImageIcon("images/monster.png");
    } // end of constructor
	
	public Rectangle getRect(int x) { // getter method to get the rectangle around the monster
        Monster.x = x;
        return new Rectangle(Monster.x,y,w,h);
    } // end of method

    public static Monster setData(){ // initializes where the monster starts
        x = 45;
        y = 100;
        w = 60;
        h = 60;
        return new Monster (x,y,w,h);
    } // end of method

    public void myDraw(Graphics g) { // draws the image of the monster
        g.drawImage(monster.getImage(), getX(), getY(), getW(), getH(),null);
    } // end of method
} // end of class
