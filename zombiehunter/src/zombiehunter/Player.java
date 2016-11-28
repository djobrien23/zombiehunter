package zombiehunter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Player {
    
    private double movingXspeed; //speed moving in x direction
    private double movingYspeed; //speed moving in y direction
    private double acceleratingSpeed; //speed that player walks at
    private double speedStopped; //speed at rest

    private Random random; //generates random number for starting coordinates of player
 
    public int x; //x coordinate of player

    public int y; //y coordinate of player

    private BufferedImage playerImg; //placeholder for changing player image
    private BufferedImage frontPlayerImg; //front image of player
    private BufferedImage backPlayerImg; //back image of player
    private BufferedImage leftPlayerImg; //left side image of player
    private BufferedImage rightPlayerImg; //right side image of player

    public int playerImgWidth; //width of player
    public int playerImgHeight; //height of player
    public int frontPlayerImgWidth; //width of player
    public int frontPlayerImgHeight; //height of player
    public int backPlayerImgWidth; //width of player
    public int backPlayerImgHeight; //height of player
    public int leftPlayerImgWidth; //width of player
    public int leftPlayerImgHeight; //height of player
    public int rightPlayerImgWidth; //width of player
    public int rightPlayerImgHeight; //height of player
    
    
    public Player()
    {
        Initialize();
        LoadContent();
        
        x = random.nextInt(Framework.frameWidth - playerImgWidth); //set starting x coordinate
        y = random.nextInt(Framework.frameHeight - playerImgHeight); //set starting y coordinate
    }
    
    
    private void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
        
        movingXspeed = 0;
        movingYspeed = 0;
        acceleratingSpeed = 2;
        speedStopped = 0;
    }
    
    private void LoadContent()
    {
        try
        {
            URL frontPlayerImgUrl = this.getClass().getResource("/images/darylfront.png"); //front facing
            frontPlayerImg = ImageIO.read(frontPlayerImgUrl);
            frontPlayerImgWidth = frontPlayerImg.getWidth();
            frontPlayerImgHeight = frontPlayerImg.getHeight();
            
            URL backPlayerImgUrl = this.getClass().getResource("/images/darylback.png"); //back facing
            backPlayerImg = ImageIO.read(backPlayerImgUrl);
            backPlayerImgWidth = backPlayerImg.getWidth();
            backPlayerImgHeight = backPlayerImg.getHeight();
            
            URL leftPlayerImglayerImgUrl = this.getClass().getResource("/images/darylleft.png"); //left facing
            leftPlayerImg = ImageIO.read(leftPlayerImglayerImgUrl);
            leftPlayerImgWidth = leftPlayerImg.getWidth();
            leftPlayerImgHeight = leftPlayerImg.getHeight();
            
            URL rightPlayerImgUrl = this.getClass().getResource("/images/darylright.png"); //right facing
            rightPlayerImg = ImageIO.read(rightPlayerImgUrl);
            rightPlayerImgWidth = rightPlayerImg.getWidth();
            rightPlayerImgHeight = rightPlayerImg.getHeight();

        }
        catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        playerImg = frontPlayerImg; //start of game player image
    }
    
    public void ResetPlayer() //set up the player when starting a new game
    {     
        x = random.nextInt(Framework.frameWidth - playerImgWidth);
        y = random.nextInt(Framework.frameHeight - playerImgHeight);
        
    }
    
    public void Update() //move the player
    {
        if(Canvas.keyboardKeyState(KeyEvent.VK_D) || Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))
        {  
             movingXspeed = acceleratingSpeed;
             playerImg = rightPlayerImg;
        }
        else if(Canvas.keyboardKeyState(KeyEvent.VK_A) || Canvas.keyboardKeyState(KeyEvent.VK_LEFT))
        {
             movingXspeed = -acceleratingSpeed;
             playerImg = leftPlayerImg;
        }
        else    // Stopping
            if(movingXspeed < 0)
                movingXspeed = speedStopped;
            else if(movingXspeed > 0)
                movingXspeed = speedStopped;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_W) || Canvas.keyboardKeyState(KeyEvent.VK_UP))
        {
            movingYspeed = -acceleratingSpeed;
            playerImg = backPlayerImg;
        }
        else if(Canvas.keyboardKeyState(KeyEvent.VK_S) || Canvas.keyboardKeyState(KeyEvent.VK_DOWN))
        {
            movingYspeed = acceleratingSpeed;
            playerImg = frontPlayerImg;
        }
        else    // Stopping
            if(movingYspeed < 0)
                movingYspeed = speedStopped;
            else if(movingYspeed > 0)
                movingYspeed = speedStopped;
        
        
        // Moves the player.
        x += movingXspeed;
        y += movingYspeed;
        
        // check that player doesn't move out of bounds
        if(x <= -15) //left border
        	x += acceleratingSpeed;
        if(x >= 747) //right border
        	x -= acceleratingSpeed;
        if(y <= -3) //top border
        	y += acceleratingSpeed;
        if(y >= 514) //bottom border
           y -= acceleratingSpeed;
    }
    
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawImage(playerImg, x, y, null); //draw player at x and y coordiantes
    
    }
    
}
