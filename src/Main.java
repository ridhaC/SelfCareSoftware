import javax.swing.*;

public class Main {
    public static void main(String[] args)  {
        JFrame frame = new JFrame("Self Care Software");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100,100);
        frame.requestFocus();
        Panel panel = new Panel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
