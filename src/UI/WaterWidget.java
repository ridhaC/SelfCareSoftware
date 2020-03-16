package UI;

import RunningCode.SoundHandler;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class WaterWidget extends UIElement {
    double waterTimerMinutes = 15;
    double waterCoolDown = 60*1000*waterTimerMinutes;
    double waterTimeLeft = waterCoolDown;
    double waterTimerWidgetMultiplier = (ySize-((ySize/30)*2))/waterTimeLeft;
    double reminderCoolDown = 60*1000;
    double reminderTimeLeft = reminderCoolDown;

    int xBufferWater = xSize/20;
    int yBufferWater = ySize/30;

    WaterParticleEffects waterParticleEffects;

    public WaterWidget(int xPos, int yPos, int xSize, int ySize) {
        super(xPos, yPos, xSize, ySize);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(xPos ,yPos , xSize, ySize);
        g.setColor(new Color(51, 193, 250));
        g.fillRect(xPos+xBufferWater ,yPos+(yBufferWater)+(int)(waterTimerWidgetMultiplier*waterCoolDown)-(int)(waterTimerWidgetMultiplier*waterTimeLeft),
                xSize-(xBufferWater*2), (int)(waterTimerWidgetMultiplier*waterTimeLeft)-(yBufferWater));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Teko", Font.BOLD, 30));
        g.drawString((int)(waterTimeLeft/1000)/60+":"+(int)(waterTimeLeft/1000)%60, 270, 320);
        if(waterParticleEffects!=null)
            waterParticleEffects.render(g);
    }
    boolean played = false;
    @Override
    public void update(double deltaTime) {
        if(waterTimeLeft>0)
            waterTimeLeft -= deltaTime;
        else
            if(!played) {
                SoundHandler.playSound("DrinkWater.wav");
                played = true;
            }
        if(timerOver()) {
            reminderTimeLeft -= deltaTime;
            if(reminderTimeLeft<=0) {
                SoundHandler.playSound("DrinkWater.wav");
                reminderTimeLeft = reminderCoolDown;
            }
        }
        if(waterParticleEffects!=null)
            waterParticleEffects.update(deltaTime);
    }
    @Override
    public void onClicked() {
        waterTimeLeft = waterCoolDown;
        played = false;
        SoundHandler.playSound("Refill.wav");
        //waterParticleEffects =  new WaterParticleEffects(xPos+(xSize/2), yPos, 10, 10, 10);
    }

    public boolean timerOver()  {
        if(waterTimeLeft<=0)
            return true;
        return false;
    }
}
