import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hello extends JFrame 
{
    public Hello() 
    {
        super("Hello Application");
    }

    public static void main(String args[]) 
    {
        final JFrame h = new Hello();

        h.setBounds(100, 100, 300, 300);
        h.setVisible(true);
        h.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        h.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawString("No worries.", 60, 60);
    }
}

