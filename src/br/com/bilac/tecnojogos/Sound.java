package br.com.bilac.tecnojogos;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final AudioClip FIRST_BLOOD = Applet.newAudioClip(Sound.class.getResource("Sons/Tech_firstblood_02.wav"));
    public static final AudioClip BATTLE_BEGINS = Applet.newAudioClip(Sound.class.getResource("Sons/Announcer_battle_begin_01.wav"));
    public static final AudioClip MONSTER_KILL = Applet.newAudioClip(Sound.class.getResource("Sons/Announcer_kill_monster_01.wav"));
    public static final AudioClip BACKGROUND_MUSIC = Applet.newAudioClip(Sound.class.getResource("Sons/background-civil-war-music.wav"));
    public static final AudioClip BULLET_SHOT = Applet.newAudioClip(Sound.class.getResource("Sons/Rifle-Sound.wav"));
    public static final AudioClip EXPLOSION = Applet.newAudioClip(Sound.class.getResource("Sons/Bomb-Sound.wav"));
}
