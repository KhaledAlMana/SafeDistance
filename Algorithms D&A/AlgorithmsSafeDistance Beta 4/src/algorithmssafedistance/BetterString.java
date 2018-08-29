package algorithmssafedistance;

import java.awt.Color;
import java.awt.Graphics;
import static algorithmssafedistance.ReadInput.Airplanes;
import static algorithmssafedistance.ReadInput.Destination;
import static algorithmssafedistance.ReadInput.Size;
import java.awt.Font;

public class BetterString {
 
    
    double x, y;
    int rad;
    int n;
    int toggle=0;
    Color c;      
    double q,p;
    double pp,qq;
    
    public BetterString(double x, double y, int n, Color c) {
        this.x = x;
        this.y = y;
        this.n=n;
        this.c=c;
    }
 
    
    public void paintString(Graphics g) {
        g.setColor(c);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        if(n>-1)
        g.drawString((n+1)+"",(int)x,(int)y);
        else if (n==-1)
         g.drawString("Center",(int)x,(int)y);
        
    }
    public void paintStringCustom(Graphics g) {
        g.setColor(c);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

         g.drawString("Danger ["+(x-300)+","+(y-300)+"]",(int)x,(int)y);
        
    }
    

    
    
 
 
}