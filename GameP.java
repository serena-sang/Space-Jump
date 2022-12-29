import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameP extends JPanel implements ActionListener {
    private JLabel l1;
    private JLabel l2;
    private JButton b1;
    private JButton b2;
    private JPanel p1;
    private CenterP p2;
    private JLabel l3;

    public GameP(){ // constructor
        // create panels
        p1 = new JPanel();
        p2 = new CenterP();

        this.setLayout( new BorderLayout(0,0) ); // distances between panels

        this.add(p1, BorderLayout.NORTH ); // panel p1 is in the North
        p1.setLayout(new FlowLayout()); // inside p1 FlowLayout will set up elements
        p1.setBackground(new Color(165,184,207));

        // create labels
        l1 = new JLabel( "Points: ");
        l2 = new JLabel("0");
        l3 = new JLabel( "                                                             ");
        b1 = new JButton("Start");
        b2 = new JButton( "Instructions");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b1.addKeyListener(p2); // add key listener

        // add labels and buttons to the panel
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(b1);
        p1.add(b2);

        this.add(p2, BorderLayout.CENTER ); // panel p2 will be set up in the centre
        p2.setLayout(null); // inside p2 there is no layout-manual set up
    } // end of constructor

    public void actionPerformed(ActionEvent e) {
        JButton b1 = (JButton)e.getSource();
        if (b1.getText().equals("Instructions"))  // displays the instructions for the user
            JOptionPane.showMessageDialog(null, "Use the right and left arrow keys to move your character!" +
                    "\nGather as many points as you can by going up! The more platforms that pass the screen, the more points you get!" +
                    "\nIf you go below the screen, you lose!" +
                    "\nAvoid the monster! Be careful! If you touch them, you lose!" +
                    "\nUse your space bar to shoot at the monster!" +
                    "\nTip: If you align yourself under another platform, your bunny will shoot up!" +
                    "\nGood luck!", "Instructions",JOptionPane.WARNING_MESSAGE );
        else{
            if (b1.getText().equals("Start")) {
                p2.startTimer(); // starts the game
            }
        }
    }

    public class CenterP extends JPanel implements ActionListener, KeyListener {
        private ArrayList<Platform> myPlatforms = new ArrayList<>();
        private ArrayList<Bullets> blt= new ArrayList<>();
        private ArrayList<Monster> mon= new ArrayList<>();
        private Character obj = new Bunny(45, 445, 63, 115);
        private Timer t;
        private ImageIcon spaceBg;
        private int playerX, playerDx, monsterX, monsterDx, points, timerCount, initialY, landCount, newY;
        private double diffY, charMove;
        private boolean shoot = false;

        public CenterP(){ // constructor for the central panel
            this.setLayout(null); // inside p2 there is no layout-manual set up
            addKeyListener(this);
            setFocusable(true);
            this.requestFocusInWindow();
            t = new Timer(10, this); // start the timer
            spaceBg = new ImageIcon("images/spaceBG.jpg"); // sets the background

            // set starting platforms
            Platform plat1 = new Platform (50, 560);
            myPlatforms.add(plat1);
            Platform plat2 = new Platform(150, 460);
            myPlatforms.add(plat2);
            Platform plat3 = new Platform(250, 360);
            myPlatforms.add(plat3);
            Platform plat4 = new Platform(350, 260);
            myPlatforms.add(plat4);
            Platform plat5 = new Platform(230, 150);
            myPlatforms.add(plat5);
            Platform plat6 = new Platform(130, 60);
            myPlatforms.add(plat6);

            obj.setIsRight(true); // sets the facing direction for the bunny
            monsterDx = 2; // how much the monster moves
            timerCount = 0;
            charMove = 27.0; // sets how fast the screen will move

            Monster mon1 = Monster.setData(); // initializes the monster
            mon.add(mon1); // adds the monster
        } // end of constructor

        public void startTimer(){ //method to start the timer
            t.start();
            obj.setJump(true);
        } // end of method

        public void checkHitPlat(){ // checks if the bunny has hit the platform or it
            for (Platform myPlatform : myPlatforms) {
                if (obj.checkHitPlatform(myPlatform)) {
                    obj.setJump(true);
                    landCount++; //allows the platforms to know if they need to move or not
                    obj.setStartPoint(obj.getY()); //allows the bunny to jump from the point
                    obj.setIsOnPlatform(true);
                }
                obj.setIsOnPlatform(false);
            }
        } // end of method

        public void movePlat(){ // method to calculate how much the bunny has moved
            if ((landCount - 1) % 3 == 0){
                initialY = obj.getY();
            }
            diffY = (initialY - obj.getY())/charMove; // calculates the difference of the y values
        } // end of method

        public void paintComponent(Graphics g){ // paint method to draw the images and shapes needed
            super.paintComponent(g);
            g.drawImage(spaceBg.getImage(), 0, 0, 500, 710, null); // displays the background

            obj.myDraw(g); // displays the character
            //if (hasMonster) {
                for (Monster monster : mon) { //displays the monster
                    monster.myDraw(g);
                }
            //}

            for (Platform myPlatform : myPlatforms) { // displays the platforms
                g.setColor(new Color(191, 191, 191));
                g.fillRect(myPlatform.getPlatX(), myPlatform.getPlatY(), myPlatform.getPlatW(), myPlatform.getPlatH());
            }

            for (Bullets bullets : blt){ // displays the bullets
                bullets.myDraw(g);
            }
        } // end of method

        public void endGame(){ // method that ends the game
            if (points == 1) // displays the message when the player loses
                JOptionPane.showMessageDialog(null, "You got " + points +
                        " point!", "End of Game", JOptionPane.INFORMATION_MESSAGE);
            else // displays the message when the player loses (and has multiple points)
                JOptionPane.showMessageDialog(null, "You got " + points +
                        " points!", "End of Game", JOptionPane.INFORMATION_MESSAGE);
            t.stop(); // stops the time
            System.exit(0);
        } // end of method

        public void keyTyped(KeyEvent e) { //required method for key listener
        } // end of method

        public void keyPressed(KeyEvent e){ // method to see which key the user has pressed
            if (e.getKeyCode() == KeyEvent.VK_LEFT){ // moves the bunny left 5 pixels
                playerDx += -5;
                obj.setIsLeft(true);
                obj.setIsRight(false);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){ // moves the bunny right 5 pixels
                playerDx += 5;
                obj.setIsRight(true);
                obj.setIsLeft(false);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // allows the bunny to shoot
                shoot = true;
            }
            repaint();
        } // end of method

        public void keyReleased(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) { // stops the bunny from moving
                playerDx = 0;
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // stops the bunny from shooting
                shoot = false;
            }
            repaint();
        } // end of method

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == t) {
                for (Monster monster : mon) { // loop to make the monster move
                    monsterX = monster.getX();
                    monsterX += monsterDx;
                    monster.setX(monsterX);
                    monster.move();
                }
                timerCount = (timerCount + 1) % 100000;
                if (shoot && timerCount % 5 == 0) {
                    blt.add(new Bullets(obj.getX() + 32, obj.getY(), obj.getDx(), obj.getDy())); // adds bullets to the bunny
                }
                for (int i = 0; i < blt.size(); i++) {
                    blt.get(i).myMove(); // moves the bullets
                    if (blt.get(i).isExited(0, 0, 500, 710)) {
                        blt.remove(i); // removes the bullets if it's off the screen
                    }
                }
                for (int i = 0; i < mon.size(); i++) {
                    if (obj.getRect().intersects(mon.get(i).getRect(monsterX))) { // checks if the bunny touches the monster
                        endGame(); // ends the game
                    }

                    for (int j = 0; j < blt.size(); j++) {
                        if (!blt.isEmpty() && !mon.isEmpty() && blt.get(j).getRect().intersects(mon.get(i).getRect(monsterX))) { // checks if the bullet hits the monster
                            blt.remove(j--);
                            mon.remove(i--);
                        }
                    }
                }

                // calculates how much the character moves
                playerX = obj.getX();
                playerX += playerDx;
                obj.setX(playerX);
                obj.move();
                checkHitPlat(); // checks if the bunny has hit any platforms

                if (points % 25 == 0){ // allows player to jump faster as they move up
                    charMove -= 2;
                    if (charMove < 20){ // limits how fast the player can jump
                        charMove = 20;
                    }
                }
                if (obj.getY() < 355) { // if the bunny's y coord is smaller than 355, then change the y coord of the platforms
                    for (Platform myPlatform : myPlatforms) {
                        newY = (int) (myPlatform.getPlatY() + diffY); // calculates how much the platform moves
                        myPlatform.setPlat(newY); // changes the platform's movement
                    }
                    movePlat(); // moves the platforms by y amount
                }
                for (Platform myPlatform : myPlatforms) {
                    if (myPlatform.notOnScreen()) { //if the platform is not on the screen
                        points++; // adds one to the total amount of points
                        l2.setText("" + points);
                        myPlatform.setPlat((int) (Math.random() * 400), (int) (Math.random() * 141) + 10); // resets the platforms at a random x coord
                    }
                }
                if (!obj.getIsOnPlatform() && !obj.getJump()) { //if the bunny isn't on a platform or jumping
                    obj.fall();
                }

                repaint();

                if (obj.getY() > 710) { // if the bunny falls under the screen, it'll end the game
                    endGame();
                }
            }
        }
    } // end of method
}//end of class
