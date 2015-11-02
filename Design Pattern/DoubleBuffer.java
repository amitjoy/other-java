import java.awt.*;
import java.awt.event.*;

public class DoubleBuffer extends Frame implements Runnable
{
  Image image; 
  Thread thread;
  Graphics graphics = null;
  int loopIndex = 0;

  public static void main(String [] args)
  {
    DoubleBuffer d = new DoubleBuffer();

    d.setSize(200, 200);

    d.addWindowListener(new WindowAdapter() {public void
      windowClosing(WindowEvent e) {System.exit(0);}});

    d.setTitle("Double buffering example");

    d.setVisible(true);

    d.drawGraphics();
  }

  public DoubleBuffer()
  {
  }

  public void drawGraphics() 
  {
    image = createImage(100, 100);
    graphics = image.getGraphics();
    thread = new Thread(this);
    thread.start();
  }

  public void run() 
  {
    while(true){
      repaint();
      try {Thread.sleep(100);}
      catch(InterruptedException e) {System.err.println(e);}
    }
  }

  public void paint (Graphics g) 
  {
    if(graphics != null){
      loopIndex += 4;
      if(loopIndex >= 100){
        loopIndex = 4;
      }
      graphics.setColor(new Color(255, 255, 255)); 
      graphics.fillRect(0, 0, 100, 100);
      graphics.setColor(new Color(2 * loopIndex, 0, 0)); 
      graphics.drawRect(0, 0, loopIndex, loopIndex);

      g.drawImage(image, 60, 60, this);
    }
  }
}
