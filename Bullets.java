import java.awt.*;

public class Bullets extends Rectangle {
    int dx, dy;

    public Bullets(int x, int y, int dx, int dy) { // constructor that gets the x and y coords and how much it moves by
        super(x, y, 5, 5);
        this.dx=dx;
        this.dy=dy;
    } // end of constructor

    public void myMove() { // moves the bullets up the screen
        y -= dy;
    } // end of method

    public Rectangle getRect() { // getter method to get the rectangle around the monster
        return new Rectangle(x,y, width, height);
    } // end of method

    public void myDraw(Graphics g) { // displays the bullets
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
    } // end of method

    public boolean isExited(int minX, int minY, int maxX, int maxY) { // checks if the bullets are outside the screen
        return x > maxX || x < minX || y > maxY || y < minY;
    } // end of method
} // end of class
