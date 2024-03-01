import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import static java.lang.Integer.parseInt;

//Controls the falling objects
public class DollarBombController extends Thread {
    private DollarBomb dollarBomb;
    private int increment;
    private final Random random;
    private final Map map;
    private final int index;
    private final Main frame;

    public DollarBombController(int index, int increment, Map map, Main frame) {
        random = new Random();
        this.increment = increment;
        this.map = map;
        this.index = index;
        this.frame = frame;
        this.dollarBomb = this.map.getDollarBombs(index);
    }


    @Override
    public void run() {
        SoundController controller = new SoundController();
        BufferedImage bufferedImage = null;

        dollarBomb.setObjX(random.nextInt(map.getWidth() - 50));
        while (!map.getGameOver()) {

            // Object is inside the map
            while (dollarBomb.getObjY() <= map.getHeight() && !map.getGameOver()) {
                try {
                    Thread.sleep(random.nextInt(30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dollarBomb.setObjY(dollarBomb.getObjY() + increment);
                map.repaint();

                // Caught a Bomb
                if(dollarBomb.getObjY() >= (map.getHeight() - 100)
                        && dollarBomb.getObjY() < (map.getHeight() - 30)
                        && (dollarBomb.getObjX() + 50 - 12) >= map.getPlayer().getPlayerX()
                        && dollarBomb.getObjX() + 10 <= (map.getPlayer().getPlayerX() + 100)
                        && !dollarBomb.getDollar()) {
                    try {
                        controller.playMusic("sounds\\Explosion.wav");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Game over
                    if(map.getPlayer().getLives() == 1) {
                        map.setGameOver(true);
                        File file = new File("images\\background_game_over.jpg");
                        try {
                            bufferedImage = ImageIO.read(file);
                        } catch (IOException e) {
                            System.out.println("Failed to load image");
                        }
                    } else { // Fewer lives
                        map.getPlayer().setLives(map.getPlayer().getLives() - 1);
                        File file;
                        if(map.getPlayer().getLives() == 2) {
                            file = new File("images\\background2.jpg");
                        } else {
                            file = new File("images\\background1.jpg");
                        }
                        try {
                            bufferedImage = ImageIO.read(file);
                        } catch (IOException e) {
                            System.out.println("Failed to load image");
                        }
                    }
                    map.setMapBackground(bufferedImage);
                    map.repaint();
                    break;
                }
                //Caught a Dollar
                if(dollarBomb.getObjY() >= (map.getHeight() - 100)
                        && dollarBomb.getObjY() < (map.getHeight() - 70)
                        && (dollarBomb.getObjX() + 50 - 20) >= map.getPlayer().getPlayerX()
                        && dollarBomb.getObjX() + 20 <= (map.getPlayer().getPlayerX() + 100)
                        && dollarBomb.getDollar()) {
                    try {
                        controller.playMusic("sounds\\dollar.wav");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int score = parseInt(frame.getScore().getText()) + 1;
                    frame.setScore(score);
                    frame.repaint();
                    break;
                }

            }
            //Resets the object
            if(!map.getGameOver()) {
                dollarBomb = new DollarBomb();
                dollarBomb.setObjX(random.nextInt(map.getWidth() - 50));
                map.setDollarBombs(dollarBomb,index);
            }

        }

    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
