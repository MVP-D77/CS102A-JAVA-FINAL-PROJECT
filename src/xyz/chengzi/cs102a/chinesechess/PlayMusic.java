package xyz.chengzi.cs102a.chinesechess;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
public class PlayMusic extends Frame{
    AudioClip a;
    AudioClip c;
    AudioClip b;
    File f = new File("src/18zvr-nrg0t.wav");
    File go = new File("src/go.wav");
    File eat = new File("src/eat.wav");
    URL music;
    URL music1;
    URL music2;
    public PlayMusic(){
        super();
    }
    public void play() {
        try {
            music = f.toURL();
            a= Applet.newAudioClip(music);
            a.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void go() {
        try {
            music1 = go.toURL();
            b= Applet.newAudioClip(music1);
            b.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void eat() {
        try {
            music2 = eat.toURL();
            c= Applet.newAudioClip(music2);
            c.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        a.stop();
    }

    public void stop1(){
        b.stop();
    }

    public void stop2(){
        c.stop();
    }
}