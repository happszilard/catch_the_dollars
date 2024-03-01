import javax.sound.sampled.*;
import java.io.File;

public class SoundController {

    void playMusic(String location) throws Exception {

        File musicPath = new File(location);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    void playBackgroundMusic() throws Exception {

        File musicPath = new File("sounds\\background_music.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

}
