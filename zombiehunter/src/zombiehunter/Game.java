package zombiehunter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
    }
    
    // draw the game to the screen
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        zombie.Draw(g2d);
        
        player.Draw(g2d);
    }
    
    
    // draw the game over screen
    public void DrawGameOver(Graphics2D g2d, long gameTime)
    {
        Draw(g2d);
        
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);

    }
}
