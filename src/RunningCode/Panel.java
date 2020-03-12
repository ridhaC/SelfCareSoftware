package RunningCode;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

public class Panel extends JPanel {
    double deltaTime = 5;
    Timer waterReminder;
    double waterCoolDown = 60*1000*0.06;
    double waterTimeLeft = waterCoolDown;
    double waterTimerWidgetMultiplier = 280/waterTimeLeft;
    boolean played = false;
    public Panel()  {
        waterReminder = new Timer("water reminder");
        waterReminder.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
                if(waterTimeLeft>0)
                    waterTimeLeft-=deltaTime;
                else
                    if(!played) {
                        playSound("DrinkWater.wav");
                        played = true;
                    }
            }
        }, 0, (long)deltaTime);
    }
    public void paintComponent(Graphics g)  {
        g.setColor(Color.GRAY.brighter());
        g.fillRect(0 ,0 , 600, 600);
        g.setColor(Color.GRAY);
        g.fillRect(200 ,150 , 200, 300);
        g.setColor(new Color(100, 150, 250));
        g.fillRect(210 ,155+(int)(waterTimerWidgetMultiplier*waterCoolDown)-(int)(waterTimerWidgetMultiplier*waterTimeLeft), 180, (int)(waterTimerWidgetMultiplier*waterTimeLeft)+5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Teko", Font.BOLD, 30));
        g.drawString((int)(waterTimeLeft/1000)+"", 280, 320);
    }
    void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

}
