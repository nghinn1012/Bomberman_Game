package uet.oop.bomberman.sound;

import uet.oop.bomberman.BombermanGame;

import javax.sound.sampled.*;

import static uet.oop.bomberman.sound.Music.Loopable.NONELOOP;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Music implements Runnable {

    // Tên file các audio
    public static final String explosion = "explosion";
    public static final String dead_bomber = "dead";
    public static final String dead_enemy = "dead2";
    public static final String BG = "background_music";
    public static final String placebomb = "place_bomb";
    public static final String powerup = "power_up";

    private static boolean _isMuted = false;

    private Clip clip;

    public enum Loopable {
        NONELOOP,
        LOOP;
    }

    // Mặc định không phát lại
    private Loopable loopable = NONELOOP;

    public Music(String fileName) {
        String path = "/audio/" + fileName + ".wav";

        try {
            URL defaultSound = getClass().getResource(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
            // load clip
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Loopable getLoopable() {
        return loopable;
    }

    public void setLoopable(Loopable loopable) {
        this.loopable = loopable;
    }

    public void play(){
        if (!_isMuted) {
            clip.setFramePosition(0);  // Chạy từ đầu
            clip.start();
        }
    }

    public void loop(){
        if (!_isMuted) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stop(){
        clip.stop();
    }

    @Override
    public void run() {
        switch (loopable) {
            case LOOP:
                this.loop();
                break;
            case NONELOOP:
                this.play();
                break;
        }
    }

    public static void mute() {
        _isMuted = !_isMuted;
        if (_isMuted) {
            BombermanGame.musicPlayer.stop();
        } else {
            BombermanGame.musicPlayer.loop();
        }
    }

    public static boolean isMuted() {
        return _isMuted;
    }
}
