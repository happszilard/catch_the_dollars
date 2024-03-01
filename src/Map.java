import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Map panel
public class Map extends JPanel {
    private final Player player;
    private final DollarBomb[] dollarBombs;
    private Boolean gameOver;
    private ObjectController objectController;
    private final Main frame;
    private BufferedImage background;

    public Map(Main frame) {
        this.frame = frame;
        player = new Player();
        dollarBombs = new DollarBomb[2];
        setFocusable(true);
        requestFocusInWindow();
        objectController = new ObjectController(this, frame);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(!gameOver) {
                    if(key == KeyEvent.VK_LEFT) {
                        if((player.getPlayerX() - 20) >= 0)
                            player.setPlayerX(player.getPlayerX() - 20);
                    }
                    if(key == KeyEvent.VK_RIGHT) {
                        if((player.getPlayerX() + 100 + 20) <= getWidth())
                            player.setPlayerX(player.getPlayerX() + 20);
                        else
                            player.setPlayerX(getWidth()-100);
                    }
                    repaint();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void setDollarBombs(DollarBomb dollarBomb, int ind) {
        this.dollarBombs[ind] = dollarBomb;
    }

    public DollarBomb getDollarBombs(int ind) {
        return dollarBombs[ind];
    }


    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public void startGame() {
        this.repaint();
        objectController.start();
    }

    public void initGame() {
        gameOver = Boolean.FALSE;
        dollarBombs[0] = new DollarBomb();
        dollarBombs[1] = new DollarBomb();
        player.setPlayerX(getWidth()/2);
        player.setLives(3);
        setFocusable(true);
        requestFocusInWindow();
        objectController = new ObjectController(this, frame);
        frame.setScore(0);
        BufferedImage bufferedImage = null;
        File file = new File("images\\background3.jpg");
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Failed to load image");
        }
        setMapBackground(bufferedImage);
    }

    public void setMapBackground(BufferedImage background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw map background
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        //draw player
        g.drawImage(player.getPlayerImg(), player.getPlayerX(), getHeight() - 100, 100, 100, null);
        //draw the falling objects
        for (int i = 0; i <= 1; i++)
            g.drawImage(dollarBombs[i].getObjImg(), dollarBombs[i].getObjX(), dollarBombs[i].getObjY(), 50, 50, null);

    }

}
