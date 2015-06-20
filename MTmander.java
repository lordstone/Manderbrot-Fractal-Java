import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class MTmander extends Thread{//implements Runnable{
 // private Thread t;
  private String threadName;
  private double cxmin;
  private double cymin;
  private double cxmax;
  private double cymax;
  private int start_y;
  private int end_y;
  private int max_iter;
  private double threshold;
  private int[][] target;
  private int thSeq;
  MTmander( String name, double cxmin, double cymin, double cxmax, double cymax,int starty, int endy, int max_iter, double threshold, int[][] target, int thSequence){
    threadName = name;
    this.cxmin = cxmin;
    this.cymin = cymin;
    this.cxmax = cxmax;
    this.cymax = cymax;
    this.start_y = starty;
    this.end_y = endy;
    this.max_iter = max_iter;
    this.threshold = threshold;
    this.target = target;
    thSeq = thSequence;
  }
  /*
  public void start ()
  {
    System.out.println("Starting " +  threadName );
    if (t == null)
    {
      t = new Thread (this, threadName);
      t.start ();
    }
  }
  */
  public void run() {
    int delta = 0;
    if(thSeq ==1)
      delta = 240;
    //double rR= 0 , gR = 0 , bR = 0;
    for(int i = start_y; i<end_y; i++)
    {
      for(int j = 0; j<640; j++)
      {
        ComplexNumber c = new ComplexNumber(0,0);
        c.setReal(cxmin + (double)j/(640-1.0)*(cxmax-cxmin)); //[maps x to cxmin..cxmax]
        c.setImag(cymin + (double)i/(480-1.0)*(cymax-cymin));// [maps y to cymin..cymax]
        ComplexNumber z = new ComplexNumber(0,0);
        int k = 0;
        while(k < max_iter && z.abs() < threshold)
        {
          z = z.squared().add(c);
          k++;
        }
        //System.out.println(k);
        target[i-delta][j] = k;
        //cout << k <<endl;
      }//end for j
    }//end for i
    //System.out.println("finished");
  }//end void run
};//end class