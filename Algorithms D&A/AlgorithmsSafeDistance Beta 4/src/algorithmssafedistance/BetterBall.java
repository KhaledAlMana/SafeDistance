package algorithmssafedistance;

import java.awt.Color;
import java.awt.Graphics;
import static algorithmssafedistance.ReadInput.Airplanes;
import static algorithmssafedistance.ReadInput.Destination;
import static algorithmssafedistance.ReadInput.Size;
import static algorithmssafedistance.JPsimulation.done;

public class BetterBall {
 
    
    double x, y;
    int rad;
    Color c;
    int n;
    int toggle=0;
    int add=0;      
    double q,p;
    double pp,qq;
    int check=0;
    public BetterBall(double x, double y, Color c, int rad, int n) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.rad = rad;
        this.n=n;
    }
 
    public void moveBall() {
        if (toggle==0){
            q=0;
            p=0;
            pp=0;
            qq=0;
            p=Math.abs(Destination[n].x-Airplanes[n].x);
            q=Math.abs(Destination[n].y-Airplanes[n].y);

                pp=p/Size;
                qq=q/Size;
                
                if(pp%2==0 && qq%2==0){
                    pp/=2;
                    qq/=2;
                }
                else if(pp%2!=0 && qq%2!=0){
                    pp/=3;
                    qq/=3;
                }
                


         toggle++;
        }
        else if (toggle==1){
            
    if(p>0){
        
            if(Airplanes[n].x>Destination[n].x){
                 x-=pp;
                 p-=pp;

            }
            else if(Airplanes[n].x<Destination[n].x){
                 x+=pp;
                 p-=pp;
            }
        
       } 
         else check++;
      if(q>0){
            if(Airplanes[n].y>Destination[n].y)
            {
                y-=qq;
                q-=qq;
            }
             else if(Airplanes[n].y<Destination[n].y)
             {
                 y+=qq;
                 q-=qq;
             }
        }
      else 
          check++;
      
      if(check==2)
          done++;
      }
        
         
    }
 
    public void paintBall(Graphics g) {
        g.setColor(c);
        g.fillOval((int)x,(int)y, rad, rad);
       //g.drawLine(Airplanes[n].x+300,Airplanes[n].y+300, Destination[n].x+300,Destination[n].y+300);
    } 
   

 
 
}