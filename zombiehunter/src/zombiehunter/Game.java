package zombiehunter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.ArrayList;

// Actual game
public class Game {

    private Player player; // player object
    
    private Zombie zombie; //zombie object

    private BufferedImage backgroundImg; // game background image

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
        
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
                
                        ArrayList projectiles = player.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) 
         {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
            }
        };
        threadForInitGame.start();
        
    }
    
    
    // Set variables and objects for the game
    private void Initialize()
    {
        player = new Player();
        zombie  = new Zombie();
    }
    
    // Load game files - images, sounds, ...
    private void LoadContent()
    {
        try
        {
            URL backgroundImgUrl = this.getClass().getResource("/images/parkingstart.png"); //game background image
            backgroundImg = ImageIO.read(backgroundImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Restart game - reset some variables
    public void RestartGame()
    {
        player.ResetPlayer();
    }
    
    // Update game logic
    public void UpdateGame(long gameTime)
    {
        player.Update(); // move the player
        if(Canvas.keyboardKeyState(KeyEvent.VK_SPACE))
         player.shoot();
         
        zombie.Update(); // move zombie
    }
    
    // draw the game to the screen
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        g2d.drawRect((int)zombie.rect2.getX(), (int)zombie.rect2.getY(), (int)zombie.rect2.getWidth(), (int)zombie.rect2.getHeight());
        zombie.Draw(g2d);
        
        g2d.drawRect((int)player.rect.getX(), (int)player.rect.getY(), (int)player.rect.getWidth(), (int)player.rect.getHeight());
        player.Draw(g2d);
        
        ArrayList projectiles = player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(p.getX(), p.getY(), 10, 5);
          }
    }
    
    // draw the game over screen
    public void DrawGameOver(Graphics2D g2d, long gameTime)
    {
        Draw(g2d);
        
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);

    }   
	
}
