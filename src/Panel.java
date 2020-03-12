import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Panel extends JPanel {
    double deltaTime = 5;
    Timer waterReminder;
    double waterCoolDown = 60*1000*0.25;
    double waterTimeLeft = waterCoolDown;
    double waterTimerWidgetMultiplier = 280/waterTimeLeft;
    public Panel()  {
        waterReminder = new Timer("water reminder");
        waterReminder.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
                if(waterTimeLeft>0)
                    waterTimeLeft-=deltaTime;
            }
        }, 0, (long)deltaTime);
    }
    public void paintComponent(Graphics g)  {
        g.setColor(Color.GRAY.brighter());
        g.fillRect(0 ,0 , 600, 600);
        g.setColor(Color.GRAY);
        g.fillRect(200 ,150 , 200, 300);
        g.setColor(new Color(100, 150, 250));
        g.fillRect(210 ,160+(int)(waterTimerWidgetMultiplier*waterCoolDown)-(int)(waterTimerWidgetMultiplier*waterTimeLeft), 180, (int)(waterTimerWidgetMultiplier*waterTimeLeft));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Teko", Font.BOLD, 30));
        g.drawString((int)(waterTimeLeft/1000)+"", 280, 320);
    }
}
