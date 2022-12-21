import javax.sound.sampled.*;
import java.io.*;

public class SoundEffect {
    AudioInputStream ding;
    AudioInputStream da;

    Clip clip;




    public void ding(boolean enabled) {
        if(!enabled)
            return;
        try{
            ding = AudioSystem.getAudioInputStream(new File("./src/SoundEffectFiles/ding.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ding);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

    }

    public void da(boolean enabled) {
        if(!enabled)
            return;
        try{
            da = AudioSystem.getAudioInputStream(new File("./src/SoundEffectFiles/da.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(da);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }

    }


}
