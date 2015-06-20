import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class CanvasPlot
{
  public final int MAX_ITER = 255;
  JLabel view;
  BufferedImage surface;
  Random random = new Random();
  public double xcen, ycen;
  public double scale;
  public CanvasPlot()
  {
    
    surface = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
    view = new JLabel(new ImageIcon(surface));
    Graphics g = surface.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0,0,640,480);
    scale = 1;
    g.dispose();
    xcen = -0.75; ycen = 0.1;
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        toPaint();
      }
    };
    Timer timer = new Timer(500, listener);
    timer.start();
  }
  
  public void toPaint() {
    double cxmin=-2, cymin=-1,cxmax=1,  cymax=1;
    
    cxmin = xcen - 1.5 / scale;
    cxmax = xcen + 1.5 / scale;
    cymin = ycen - 1 / scale;
    cymax = ycen + 1 / scale;
    double rR = 0; double gR = 0 ; double bR = 0;
    Graphics g = surface.getGraphics();
    for(int i = 0; i<480; i++)
    {
      for(int j = 0; j<640; j++)
      {
        ComplexNumber c = new ComplexNumber(0,0);
        c.setReal(cxmin + (double)j/(640-1.0)*(cxmax-cxmin)); //[maps x to cxmin..cxmax]
        c.setImag(cymin + (double)i/(480-1.0)*(cymax-cymin));// [maps y to cymin..cymax]
        ComplexNumber z = new ComplexNumber(0,0);
        int k = 0;
        while(k < MAX_ITER && z.abs() < 2.0)
        {
          z = z.squared().add(c);
          k++;
        }
        //cout << k <<endl;
        if(k<MAX_ITER)
        {
          rR = k;gR = k;bR = k;
        }
        else
        {
          rR = 0 ; gR = 0; bR = 0;
        }
        Color clr = new Color((int)rR,(int) gR, (int) bR);
        //surface.setRGB(j,i,clr.getRGB());
        
        g.setColor(clr);
        g.drawLine(j,i,j,i);
        
        
      }// end for j 
    }// end for i
    g.dispose();
    
    
    
    view.repaint();
    scale += scale * 0.4;
  }
  
  public static void main(String[] args)
  {
    CanvasPlot canvas = new CanvasPlot();
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
  
  
  
}