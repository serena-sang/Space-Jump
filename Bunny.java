import java.awt.*;
import javax.swing.*;

public class Bunny extends Character{
    private int startPoint, tempY, dx, dy, vel;
    private boolean isJump;

    private double gravity, t, termVel;
    private ImageIcon leftSprite = null;
    private ImageIcon rightSprite = null;

    public Bunny(int x, int y, int w, int h){ // constructor to initialize the variables
        super(x, y, w, h); // gets info from Character
        leftSprite = new ImageIcon("images/leftSprite.png");
        rightSprite = new ImageIcon("images/rightSprite.png");
        startPoint = 450;
        gravity = 9.8;
        vel = -60;
        termVel = 5.75;
        t = 0;
        dx = 10;
        dy = 10;
    } // end of constructor

    public int getDx(){ // getter method to find the dx
        return dx;
    } // end of method

    public int getDy(){ // getter method to find the dy
        return dy;
    } // end of method

    public boolean getJump(){ // getter method to get if the bunny is jumping or not
        return isJump;
    } // end of method

    public Rectangle getRect() { // getter method to get the rectangle around the monster
        return new Rectangle(getX(),getY(),getW(),getH());
    } // end of method

    public void setStartPoint(int startPoint) { // setter method to set the starting point
        this.startPoint = startPoint;
    } // end of method

    public void setJump(boolean jump){ // setter method to set if the bunny is jumping or not
        isJump = jump;
    } // end of method

    public void move() { // allows the bunny to jump and wraps the screen
        if (isJump)
            jump();
        checkBounds();
    } // end of method

    public void jump() { // jump method for the bunny
        tempY = (int) (startPoint + vel * t + 0.5 * gravity * t * t); // uses the kinematics equation
        setY(tempY); // sets the y coord of the bunny
        if (tempY > startPoint) { // tests if the y coord is greater than the starting point
            tempY = startPoint;
            isJump = false;
            t = 0; // resets the time
        } else {
            t += 0.15; // changes the time
        }
    } // end of method

    public void fall(){ // fall method
        if (vel < termVel){ // tests if the velocity is smaller than the max velocity
            vel = (int)termVel;
        } else {
            vel += gravity; // adds gravity to the velocity to make it fall down
        }
        setY(getY() + vel);
        vel = -60; // resets the velocity
    } // end of method

    public boolean checkHitPlatform(Object obj){ // checks if the character has hit the platform or not
        Platform other = (Platform) obj;
        return getX() + getW() >= other.getPlatX() &&
                getX() <= other.getPlatX() + other.getPlatW() &&
                getY() + getH() >= other.getPlatY() &&
                getY() + getH() <= other.getPlatY() + other.getPlatH();
    } // end of method

    public void myDraw(Graphics g) { // displays if the bunny is facing left or right
        if (getIsRight())
            g.drawImage(rightSprite.getImage(), getX(), getY(), getW(), getH(),null);
        if (getIsLeft())
            g.drawImage(leftSprite.getImage(), getX(), getY(), getW(), getH(),null);
    } // end of method
} // end of class
