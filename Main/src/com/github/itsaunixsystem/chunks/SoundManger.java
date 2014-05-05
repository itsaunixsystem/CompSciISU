package com.github.itsaunixsystem.chunks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

public class SoundManger {
    private HashMap<String, Sound> soundHashMap;
    private HashMap<String, Music> musicHashMap;
    private float volume;

    public SoundManger() {
        soundHashMap = new HashMap<>();
        musicHashMap = new HashMap<>();
        loadSound("click", Gdx.files.internal("Main/res/click.wav"));
        volume = 1f;
    }

    public void changeVolume(float volume) {
        this.volume = volume;
    }

    public void loadMusic(String name, FileHandle music) {
        musicHashMap.put(name, Gdx.audio.newMusic(music));
    }

    public void loadSound(String name, FileHandle fileHandle) {
        soundHashMap.put(name, Gdx.audio.newSound(fileHandle));
    }

    public void playMusic(String name, boolean loop) {
        playMusic(name, 1, loop);
    }

    public void playMusic(String name, float volume, boolean loop) {
        Music music = musicHashMap.get(name);
        music.setLooping(loop);
        music.play();
    }

    public void playSound(String name) {
        playSound(name, 1);
    }

    public void playSound(String name, float volume) {
        Sound sound = soundHashMap.get(name);
        sound.play(volume * this.volume);
    }
}
