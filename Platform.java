public class Platform {
    private int x, y, w, h;

    public Platform(int x, int y){ // constructor for the Platform
        this.x = x;
        this.y = y;
        w = 80;
        h = 20;
    } // end of constructor

    public int getPlatX(){ // getter method for the platform's x coord
        return x;
    } // end of method

    public int getPlatY(){ // getter method for the platform's y coord
        return y;
    } // end of method

    public int getPlatW(){ // getter method for the platform's width
        return w;
    } // end of method

    public int getPlatH(){ // getter method for the platform's height
        return h;
    } // end of method

    public void setPlat(int y){ // setter method for the platform's y coord
        this.y = y;
    } // end of method

    public void setPlat(int x, int y){ // setter method for the platform's x and y coord
        this.x = x;
        this.y = y;
    } // end of method

    public boolean notOnScreen(){ // checks if the platform isn't on the screen
        return y > 710 - 60;
    } // end of method
}
