package RunningCode;

import UI.WaterWidget;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Panel extends JPanel {
    Timer waterReminder;
    double deltaTime = 1000;
    public static WaterWidget waterWidget = new WaterWidget(200,150,200,300);
    public Panel()  {
        waterReminder = new Timer("water reminder");
        waterReminder.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
                waterWidget.update(deltaTime);
            }
        }, 0, (long)deltaTime);
        addMouseListener(new InputHandler());
    }
    public void paintComponent(Graphics g)  {
        g.setColor(Color.GRAY.brighter());
        g.fillRect(0 ,0 , 600, 600);
        waterWidget.render(g);
    }

    public static void mouseClicked(Point p)    {
        waterWidget.isClicked(p);
    }
}
