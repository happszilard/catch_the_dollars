import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

// Object information (Dollar/Bomb)
public class DollarBomb {
    private int objX;
    private int objY;
    private BufferedImage objImg;
    private final Boolean isDollar;

    public DollarBomb() {
        objX = 0;
        objY = -50; // object appears above the map
        Random rnd = new Random();
        int check = rnd.nextInt(100);
        // 60% Dollar / 40% Bomb
        if (check <= 60) {
            isDollar = true;
            File file = new File("images\\dollar.png");
            try {
                objImg = ImageIO.read(file);
            } catch (IOException e) {
                System.out.println("Failed to load image");
            }
        }
        else {
            isDollar = false;
            File file = new File("images\\bomb.png");
            try {
                objImg = ImageIO.read(file);
            } catch (IOException e) {
                System.out.println("Failed to load image");
            }
        }

    }

    public int getObjX() {
        return objX;
    }

    public void setObjX(int objX) {
        this.objX = objX;
    }

    public int getObjY() {
        return objY;
    }

    public void setObjY(int objY) {
        this.objY = objY;
    }

    public BufferedImage getObjImg() {
        return objImg;
    }

    public Boolean getDollar() {
        return isDollar;
    }

}