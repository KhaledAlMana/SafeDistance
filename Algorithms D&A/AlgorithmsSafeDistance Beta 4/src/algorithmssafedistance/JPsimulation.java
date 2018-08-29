/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmssafedistance;

import static algorithmssafedistance.ReadInput.Airplanes;
import static algorithmssafedistance.ReadInput.Destination;
import static algorithmssafedistance.ReadInput.Intersections;
import static algorithmssafedistance.ReadInput.Size;
//import static algorithmssafedistance.GUImain.s;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Khaled
 */
public class JPsimulation extends JPanel implements Runnable 
{

      double sVal1=0,sVal2=0;
      Random rand = new Random();
      float r = rand.nextFloat();
      float g = rand.nextFloat();
      float b = rand.nextFloat();
      Color randomColor;
      int i=0,k=0;
      int x=0,y=0;
        Image dbi;
        Graphics dbg;
        BetterBall[] airp;
        BetterString[] dist;
       // BetterBall[] ints;
        BetterString center;
    public static int done=0;
        
    public JPsimulation() {
        super();
        setSize(600,600);
        setBackground(Color.GRAY);
        airp = new BetterBall[Size];
        dist = new BetterString[Size];
       // ints =  new BetterBall[Size];
        
        for(int n=0; n<Size ;n++){
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
         randomColor= new Color(r, g, b);
         x=Airplanes[n].x+300;
         y=Airplanes[n].y+300;
         airp[n] = new BetterBall(x,y, randomColor,20,n);
         x=Destination[n].x+300;
         y=Destination[n].y+300;
         dist[n] = new BetterString(x,y,n, randomColor);
//         if(s>=n){
//         x=Intersections[n].x+300;
//         y=Intersections[n].y+300;
//         ints[n] = new BetterBall(x,y, Color.RED,15,n);
//         }
        }
        center=new BetterString(300, 300,-1,Color.BLACK);
    }
    
    @Override
    public void paint(Graphics g) {
        dbi = createImage(getWidth(), getHeight());
        dbg = dbi.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbi, 0, 0, this);
        repaint();
    }

      @Override
    public void paintComponents(Graphics g) {
 
        for (int i = 0; i < Size; i++) {
            airp[i].paintBall(g);
            dist[i].paintString(g);
//            if(s>=i)
//             ints[i].paintBall(g);


        }
        center.paintString(g);
        

        repaint();

    }
    
    public void move() {

        for (int i = 0; i < Size ; i++) {
            airp[i].moveBall();
        }
    }

    @Override
    public void run() {
        try {
            while (done<Size) {
                move();
                Thread.sleep(50);

               
            }
        } catch (Exception e) {
            System.out.println(e);
        }    }
 
 


 
}
