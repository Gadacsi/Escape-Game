/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;

/**
 * Sound class constructor
 */
public class Sound {
    Clip clip;
    URL[] soundURL = new URL[10];
    ArrayList<String> soundNames = new ArrayList<>();

    /**
     * Array holding the different sounds
     * ArrayList holding the names of the sounds
     */
    public Sound() {
        soundURL[0] = getClass().getResource("sounds/step.wav");
        soundURL[1] = getClass().getResource("sounds/failed.wav");
        soundURL[2] = getClass().getResource("sounds/gettingHealth.wav");
        soundURL[3] = getClass().getResource("sounds/gettingWater.wav");
        soundURL[4] = getClass().getResource("sounds/escaped.wav");
        soundURL[5] = getClass().getResource("sounds/eating.wav");
        soundNames.add(0, "step");
        soundNames.add(1, "failed");
        soundNames.add(2, "gettingHealth");
        soundNames.add(3, "gettingWater");
        soundNames.add(4, "escaped");
        soundNames.add(5, "eating");
        soundNames.forEach( // forEach loop :)
                (names)->System.out.println(names) // lambda expression :)
        );

    }


    /**
     * setting up the sound stream
     */
    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(Exception e) {

        }
    }

    /**
     * used to play the sound effects
     */
    public void play() {
        clip.start();
    }

    /**
     * used to play music / sounds that need looping
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * used to end the looping
     */
    public void stop() {
        clip.stop();
    }
}
