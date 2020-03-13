package UI;

import RunningCode.SoundHandler;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class WaterWidget extends UIElement {
    double waterCoolDown = 60*1000*15;
    double waterTimeLeft = waterCoolDown;
    double waterTimerWidgetMultiplier = 280/waterTimeLeft;
    Timer reminderTimer = new Timer("Reminder");
    public WaterWidget(int xPos, int yPos, int xSize, int ySize) {
        super(xPos, yPos, xSize, ySize);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(200 ,150 , 200, 300);
        g.setColor(new Color(51, 193, 250));
        g.fillRect(210 ,155+(int)(waterTimerWidgetMultiplier*waterCoolDown)-(int)(waterTimerWidgetMultiplier*waterTimeLeft), 180, (int)(waterTimerWidgetMultiplier*waterTimeLeft)+5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Teko", Font.BOLD, 30));
        g.drawString((int)(waterTimeLeft/1000)/60+":"+(int)(waterTimeLeft/1000)%60, 270, 320);
    }
    boolean played = false;
    boolean scheduled = false;
    @Override
    public void update(double deltaTime) {
        if(waterTimeLeft>0)
            waterTimeLeft -= deltaTime;
        else
        if(!played) {
            SoundHandler.playSound("DrinkWater.wav");
            played = true;
            reminderTimer = new Timer("Reminder");
            if(!scheduled) {
                reminderTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SoundHandler.playSound("DrinkWater.wav");
                    }
                }, 0, 1000*60);
                scheduled = true;
            }
        }
    }
    @Override
    public void onClicked() {
        waterTimeLeft = waterCoolDown;
        played = false;
        SoundHandler.playSound("Refill.wav");
        reminderTimer.cancel();
        scheduled = false;
    }
}
