package zombiehunter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Rectangle;


// Zombie
public class Zombie {
    
    private Random random; //generates random number for starting coordinates of zombie
    
    public int x; // x coordiante of zombie

    public int y; // y coordinate of zombie

    private BufferedImage zombieImg; // image of zombie

    public int zombieImgWidth; // width of zombie
    
    public int zombieImgHeight; //height of zombie
    
    public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
    
    public Zombie()
    {
        Initialize();
        LoadContent();
    }
    
    
    private void Initialize()
    {   
        random = new Random();
        x = random.nextInt(Framework.frameWidth - zombieImgWidth); //set starting x coordinate
        y = random.nextInt(Framework.frameHeight - zombieImgHeight); //set starting y coordinate
    }
    
    private void LoadContent()
    {
        try
        {
            URL zombieImgUrl = this.getClass().getResource("/images/zombiefront.png");
            zombieImg = ImageIO.read(zombieImgUrl);
            zombieImgWidth = zombieImg.getWidth();
            zombieImgHeight = zombieImg.getHeight();
        }
        catch (IOException ex) {
            Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Update() //move the zombie
    {
      rect2.setRect(x + 18, y, 22, 52);
    }
    
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(zombieImg, x, y, null);
    }
    
    
   }
