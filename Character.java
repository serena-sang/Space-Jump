import java.awt.*;

public class Character {
    private int x, y, w, h;
    private boolean isOnPlatform, isLeft, isRight;

    public Character(int x, int y, int w, int h) { // constructor that sets the x and y and width and height of the character
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    } // end of constructor

    public int getX() { // getter method for the x coord
        return x;
    } // end of method

    public void setX(int x) { // setter method for the x coord
        this.x = x;
    } // end of method

    public int getY() { // getter method for the y coord
        return y;
    } // end of method

    public int setY(int y) { // setter method for the y coord
        return this.y = y;
    } // end of method

    public int getH() { // getter method for the height
        return h;
    } // end of method

    public int getW() { // getter method for the width
        return w;
    } // end of method

    public void setIsLeft (boolean isLeft){ // setter method to turn the object facing towards the left
        this.isLeft = isLeft;
    } // end of method

    public void setIsRight(boolean isRight){ // setter method to turn the object facing towards the right
        this.isRight = isRight;
    } // end of method

    public boolean getIsLeft(){ // getter method to see if the object is facing the left
        return isLeft;
    } // end of method

    public boolean getIsRight(){ // getter method to see if the object is facing the right
        return isRight;
    } // end of method

    public void setJump(boolean isJump) { // used in the Bunny child class
    } // end of method

    public boolean getJump() { // used in the Bunny child class
        return false;
    } // end of method

    public Rectangle getRect(){ // getter method for Monster class
        return new Rectangle(0,0,0,0);
    } // end of method

    public void setStartPoint(int y) { // setter method used in the Bunny child class
    } // end of method

    public int getDx() { // getter method for Bunny child class
        return 0;
    } // end of method

    public int getDy() { // getter method for Bunny child class
        return 0;
    } // end of method

    public boolean getIsOnPlatform(){ // getter method to see if the character is on a platform
        return isOnPlatform;
    } // end of method

    public void setIsOnPlatform(boolean onPlatform){ // setter method to set if the character is on a platform
        isOnPlatform = onPlatform;
    } // end of method

    public void move(){ // moves the object to stay on the screen
        checkBounds();
    } // end of method

    public void checkBounds() { // wraps the screen around so the character can stay on the screen
        if(getX()>500)
            setX(-45);

        if(getX()<-45)
            setX(500);
    } // end of method

    public void fall() { // used in the Bunny child class
    } // end of method

    public boolean checkHitPlatform(Object obj) { // used in Bunny child class
        return false;
    } // end of method

    public void myDraw(Graphics g) { // used in the child classes
    } // end of method
} // end of class
