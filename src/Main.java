import javax.swing.*;
import java.awt.*;

// Application frame
public class Main extends JFrame {
    private final Map map;
    private final JLabel score;
    private final BackgroundPanel menuPanel;
    private final BackgroundPanel helpPanel;
    private final JPanel cardPanel;


    public Main() {
        setTitle("Catch the dollars!");
        setBounds(10,10,700,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        CardLayout layout = new CardLayout();

        SoundController controller = new SoundController();
        try {
            controller.playBackgroundMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Play panel
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Score: ");
        score = new JLabel("0");

        JButton backToMenu = new JButton("Back to menu");
        backToMenu.setBackground(Color.LIGHT_GRAY);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(score,BorderLayout.CENTER);
        scorePanel.add(backToMenu,BorderLayout.EAST);

        map = new Map(this);
        playPanel.add(map,BorderLayout.CENTER);
        playPanel.add(scorePanel,BorderLayout.NORTH);

        //Menu panel
        menuPanel = new BackgroundPanel("images\\background_menu.jpg");
        menuPanel.setLayout(new GridLayout(9,5));
        JButton playButton = new JButton("Play");
        JButton helpButton = new JButton("Help");
        playButton.setBackground(Color.LIGHT_GRAY);
        helpButton.setBackground(Color.LIGHT_GRAY);
        JPanel[] panels = new JPanel[46];

        //Puts the buttons in the menu panel to the right place
        for(int i = 1; i <= 45; i++) {
            if(i == 18)
                menuPanel.add(playButton);
            else
                if(i == 28)
                    menuPanel.add(helpButton);
                else {
                    panels[i] = new JPanel();
                    panels[i].setOpaque(Boolean.FALSE);
                    menuPanel.add(panels[i]);
                }

        }

        //Help panel
        helpPanel = new BackgroundPanel("images\\background_help.jpg");
        helpPanel.setLayout(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.LIGHT_GRAY);
        helpPanel.add(backButton,BorderLayout.NORTH);

        //Card Panel
        cardPanel = new JPanel();
        cardPanel.setLayout(layout);
        add(cardPanel);

        cardPanel.add(menuPanel, "MenuPanel");
        cardPanel.add(playPanel,"PlayPanel");
        cardPanel.add(helpPanel, "HelpPanel");

        helpButton.addActionListener(e -> {
            SoundController controller1 = new SoundController();
            try {
                controller1.playMusic("sounds\\Click.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            layout.show(cardPanel,"HelpPanel");
            helpPanel.repaint();
        });

        backButton.addActionListener(e -> {
            SoundController controller12 = new SoundController();
            try {
                controller12.playMusic("sounds\\Click.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            layout.show(cardPanel,"MenuPanel");
            menuPanel.repaint();
        });

        playButton.addActionListener(e -> {
            SoundController controller13 = new SoundController();
            try {
                controller13.playMusic("sounds\\Click.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            layout.show(cardPanel, "PlayPanel");
            map.initGame();
            map.startGame();
        });

        backToMenu.addActionListener(e -> {
            SoundController controller14 = new SoundController();
            try {
                controller14.playMusic("sounds\\Click.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            map.setGameOver(Boolean.TRUE);
            layout.show(cardPanel, "MenuPanel");
            menuPanel.repaint();
        });

        menuPanel.repaint();
        setVisible(true);
    }

    public JLabel getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score.setText(Integer.toString(score));
    }

    public static void main(String[] args) {
        new Main();
    }
}
