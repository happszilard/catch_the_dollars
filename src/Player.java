import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Player information
public class Player {

    private int playerX;
    private BufferedImage playerImg;
    private int lives;

    public Player() {
        playerX = 0;
        lives = 3;
        File file = new File("images\\player.png");
        try {
            playerImg = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Failed to load image");
        }
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
