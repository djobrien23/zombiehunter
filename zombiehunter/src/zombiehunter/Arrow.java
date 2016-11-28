package zombiehunter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

// player arrows

public class Arrow {
    
    // For creating new arrows.
    public final static long timeBetweenNewArrows = Framework.secInNanosec / 10;
    public static long timeOfLastCreatedArrow = 0;
    
    // Damage that is made to an enemy helicopter when it is hit with a arrow.
    public static int damagePower = 20;
    
    // Position of the arrow on the screen. Must be of type double because movingXspeed and movingYspeed will not be a whole number.
    public double xCoordinate;
    public double yCoordinate;
    
    // Moving speed and direction.
    private static int arrowSpeed = 20;
    private double movingXspeed;
    private double movingYspeed;
    
    // Images of helicopter machine gun arrow. Image is loaded and set in Game class in LoadContent() method.
    public static BufferedImage arrowImg;
    
    
    /**
     * Creates new machine gun arrow.
     * 
     * @param xCoordinate From which x coordinate was arrow fired?
     * @param yCoordinate From which y coordinate was arrow fired?
     * @param mousePosition Position of the mouse at the time of the shot.
     */
    public Arrow(int xCoordinate, int yCoordinate, Point mousePosition)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        setDirectionAndSpeed(mousePosition);
    }
    
    
    /**
     * Calculate the speed on a x and y coordinate.
     * 
     * @param mousePosition 
     */
    private void setDirectionAndSpeed(Point mousePosition)
    {
        // Unit direction vector of the arrow.
        double directionVx = mousePosition.x - this.xCoordinate;
        double directionVy = mousePosition.y - this.yCoordinate;
        double lengthOfVector = Math.sqrt(directionVx * directionVx + directionVy * directionVy);
        directionVx = directionVx / lengthOfVector; // Unit vector
        directionVy = directionVy / lengthOfVector; // Unit vector
        
        // Set speed.
        this.movingXspeed = arrowSpeed * directionVx;
        this.movingYspeed = arrowSpeed * directionVy;
    }
    
    
    /**
     * Checks if the arrow is left the screen.
     * 
     * @return true if the arrow left the screen, false otherwise.
     */
    public boolean isItLeftScreen()
    {
        if(xCoordinate > 0 && xCoordinate < Framework.frameWidth &&
           yCoordinate > 0 && yCoordinate < Framework.frameHeight)
            return false;
        else
            return true;
    }
    
    
    /**
     * Moves the arrow.
     */
    public void Update()
    {
        xCoordinate += movingXspeed;
        yCoordinate += movingYspeed;
    }
    
    
    /**
     * Draws the arrow to the screen.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(arrowImg, (int)xCoordinate, (int)yCoordinate, null);
    }
}

