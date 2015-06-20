import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class coloredMand extends myManderactal  {
  public final int MAX_ITER = 765;
  public final int MAX_COLOR_INT = 16777215;
  coloredMand(){
    super();
  }
  
  public void toPaint() {
    double cxmin=-2, cymin=-1,cxmax=1,  cymax=1;
    cxmin = xcen - 1.5 / scale;
    cxmax = xcen + 1.5 / scale;
    cymin = ycen - 1 / scale;
    cymax = ycen + 1 / scale;
    double rR = 0; double gR = 0 ; double bR = 0;
    Graphics g = surface.getGraphics();
    int[][] array1 = new int[240][640];
    int[][] array2 = new int[240][640];
    Thread th1 = new MTmander("th1", cxmin, cymin, cxmax, cymax, 0, 240, MAX_ITER, THRESHOLD, array1, 0);
    Thread th2 = new MTmander("th2", cxmin, cymin, cxmax, cymax,240 , 480, MAX_ITER, THRESHOLD, array2, 1);
    th1.start();
    th2.start();
    //System.out.println("Count how many times...");
    //paint
    try{
      th1.join();
      th2.join();
    }catch(InterruptedException e){
      
    }
    
    int k = 0;
    for(int i = 0;i<240;i++)
    {
      for(int j = 0;j<640;j++) 
      {
        for (int n = 0;n<2;n++)
        {
          if(n==0)
            k = array1[i][j];
          else
            k = array2[i][j];
          //System.out.println(k);
          if(k<MAX_ITER)
          {
            if(k<=255)
            {
              bR = k;
              gR = 0;
              rR = 0;
            }else if (k>255 && k <=510){
              bR = 255;
              gR = k - 255.0;
              rR = 0;
            }else{
              bR = 255;
              gR = 255;
              rR = k - 510.0;
            }
          }
         // System.out.println(rR + ":" + gR +":" +bR);
          Color clr = new Color((int)rR,(int) gR, (int) bR);
          //surface.setRGB(j,i,clr.getRGB());
          g.setColor(clr);
          g.drawLine(j,i+240*n,j,i+240*n);
          rR = 0 ; gR = 0; bR = 0;
          
        }//end for n
      }//end for j
    }//end for i
    g.dispose();
    view.repaint();
    //scale += scale * 0.4;
  }
  
  public static void main(String[] args)
  {
    coloredMand canvas = new coloredMand();
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