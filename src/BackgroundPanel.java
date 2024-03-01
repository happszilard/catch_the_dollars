import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Sets the background of a panel
public class BackgroundPanel extends JPanel {
    private BufferedImage background;

    public BackgroundPanel(String location) {
        File file = new File(location);
        try {
            background = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Failed to load image");
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
