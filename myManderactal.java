import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class myManderactal extends ManderbrotFractal implements KeyListener{
  // inherits ManderbrotFractal aka the original ManderbrotFractal
  
  public myManderactal()
  {
    surface = new BufferedImage(X_RES,Y_RES,BufferedImage.TYPE_INT_RGB);
    view = new JLabel(new ImageIcon(surface));
    view.addKeyListener(this);
    view.setFocusable(true);
    Graphics g = surface.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0,0,X_RES,Y_RES);
    scale = 1;
    g.dispose();
    xcen = 0; ycen = 0;
    toPaint();
  }//constructor
  public void keyTyped(KeyEvent e){};//not in use
  public void keyReleased(KeyEvent e){};//not in use
  public void keyPressed(KeyEvent e) {//listener
    int loc = e.getKeyCode();
    if (loc == KeyEvent.VK_LEFT){
      xcen -= 1/ scale * 0.4;
    }else if (loc == KeyEvent.VK_UP){
      ycen -= 1 / scale * 0.4;
    }else if (loc == KeyEvent.VK_DOWN){
      ycen += 1/scale * 0.4;
    }else if (loc == KeyEvent.VK_RIGHT){
      xcen += 1/scale * 0.4;
    }else if (loc == KeyEvent.VK_ENTER){
      scale += scale * 0.35;
    }else if (loc == KeyEvent.VK_SPACE){
      scale = scale * 0.75;
    }//end if
    toPaint();
  };
  
  public static void main(String[] args)
  {
    myManderactal canvas = new myManderactal();
    JFrame frame = new JFrame();
    int vertexes = 0;
    // Change this next part later to be dynamic.
    vertexes = 10;
    int canvasSize = vertexes * vertexes;
    frame.setSize(canvasSize, canvasSize);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(canvas.view);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }
  
};