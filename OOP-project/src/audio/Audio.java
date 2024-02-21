package audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Audio {

    // Possibility to add more than one song/effect
    public static int BACKGROUND_MUSIC1 = 0;

    public static int MEOW_SOUND1 = 0;

    private Clip[] music, sfx;
    private int musicID;
    private boolean musicOn, sfxOn;

    public Audio() {
        loadAudio();
        playMusic(BACKGROUND_MUSIC1);
    }

    public void muteMusic(boolean option) {
        this.musicOn = option;
        for(Clip clip : music) {
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(musicOn);
        }
    }

    public void muteSFX(boolean option) {
        this.sfxOn = option;
        for (Clip clip : sfx) {
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(sfxOn);
        }
    }

    public void playSFX(int effect) {
        if(!sfxOn) {
            sfx[effect].setMicrosecondPosition(0);
            sfx[effect].start();
        }
    }

    public void playMusic(int song) {
        if (music[musicID].isActive())
            music[musicID].stop();

        musicID = song;
        music[musicID].setMicrosecondPosition(0);
        music[musicID].loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void loadAudio() {
        String[] names = {"music"};
        music = new Clip[names.length];
        for (int i = 0; i < music.length; i++)
            music[i] = getClip(names[i]);

        String[] sfxNames = { "cat" };
        sfx = new Clip[sfxNames.length];
        for (int i = 0; i < sfx.length; i++)
            sfx[i] = getClip(sfxNames[i]);

    }

    private Clip getClip(String name) {
        URL url = getClass().getResource("/" + name + ".wav");
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public boolean isSfxOn() {
        return sfxOn;
    }
}
