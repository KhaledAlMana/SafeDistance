/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmssafedistance;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
//import static algorithmssafedistance.GUImain.s;


public class ReadInput {
    
    public  static Point[] Airplanes;
    public  static Point[] Destination;
    public  static Point[] Intersections;
    public  static int[] speed;
    public  static int Size=0;
       
    Line line1;
    Line line2;
    int res;
    public  Point intersect;
    
    public void getFileSize() throws FileNotFoundException{
        Size=0;
        String fileName="AirplanesInput.txt";
        
       try{
          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);
          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);
          //Variable to hold the one line data
          String line;
          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null){
             Size++;
          }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"File AirplaneInput.txt is missing or input is not well typed.\n\nError:  "+e.getMessage(),"Error finding AirplaneInput.txt", JOptionPane.ERROR_MESSAGE);
           System.exit(0);
       }
       Size/=2;
       GUImain.SizeInput.setText(Size+"");
       
       initialize();
    }
    


    
    public void initialize() throws FileNotFoundException{
     Airplanes= new Point[Size];
     Destination= new Point[Size];
     speed=new int[Size];
//     Intersections= new Point[((Size-1)*(Size/2))];
//     s=0;
     scanAirplanes();
    }
    
    
    
    public void scanAirplanes() throws FileNotFoundException{
        
        String fileName="AirplanesInput.txt";
         int x=0,y=0,i=0,toggle=0;
       try{
          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);
          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);
          //Variable to hold the one line data
          String line;
          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null){
             if(toggle==0){
                 x=Integer.parseInt(line);
                 toggle++;
             }
             else if (toggle==1){
             y=Integer.parseInt(line);
             Airplanes[i]=new Point(x, y);
             i++;
             x=0; y=0;
             toggle--;
             }
          }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"File AirplaneInput.txt is missing or input is not well typed.\n\nError:  "+e.getMessage(),"Error finding AirplaneInput.txt", JOptionPane.ERROR_MESSAGE);
           System.exit(0);       }
       for(int j=0;j <10 ; j++)
        GUImain.StatusMessage+="Airplane:["+Airplanes[j].x+","+Airplanes[j].y+"]"+"\n";
       
       scanDes();
     }
      
    public void scanDes() throws FileNotFoundException{
        String fileName="DesInput.txt";
        int x=0,y=0,i=0,toggle=0;
       try{
          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);
          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);
          //Variable to hold the one line data
          String line;
          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null){
             if(toggle==0){
                 x=Integer.parseInt(line);
                 toggle++;
             }
             else if (toggle==1){
                y=Integer.parseInt(line);
                Destination[i]=new Point(x, y);
                i++;
                x=0; y=0;
                toggle--;
            }
          }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"File DesInput.txt is missing or input is not well typed.\nError:  "+e.getMessage(),"Error finding AirplaneInput.txt", JOptionPane.ERROR_MESSAGE);
           System.exit(0);       }
       for(int j=0;j <10 ; j++)
        GUImain.StatusMessage+="Destination : ["+Destination[j].x+","+Destination[j].y+"]"+"\n";
            
       scanSpeed();
     }
   
     public void scanSpeed() throws FileNotFoundException{
        String fileName="SpeedInput.txt";
        int i=0;
       try{
          //Create object of FileReader
          FileReader inputFile = new FileReader(fileName);
          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);
          //Variable to hold the one line data
          String line;
          // Read file line by line and print on the console
          
          while ((line = bufferReader.readLine()) != null){
            {
                if(i<10){
               speed[i]=Integer.parseInt(line);
                i++;
                }
            }
            }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"File SpeedInput.txt is missing or input is not well typed.\n\nError:  "+e.getMessage(),"Error finding AirplaneInput.txt", JOptionPane.ERROR_MESSAGE);
           System.exit(0);       }
       for(int j=0;j <10 ; j++)
        GUImain.StatusMessage+="Speed of Airplane <"+j+"> ["+speed[j]+"]"+"\n";
     }
        
public void calculate(){
    int i=0;
    int k=0;
    int MinDis=100;
    for(i=0;i<Size-1;i++){
       for(k=i+1;k<Size;k++){
           
        line1 = new Line(Airplanes[i], Destination[i]);
        line2 = new Line(Airplanes[k], Destination[k]);
        intersect=new Point(0,0);
        //calculate if it is save distance between two plane
        res=SafeDistance(Airplanes[i], Airplanes[k]);
            if(res < MinDis)
            {
                findIntersection(Airplanes[i],Airplanes[k],Destination[i],Destination[k],i,k);
                //System.out.println(intersect.x+ ",,,"+intersect.y); 
            }      
            else
                GUImain.Log+="Safe distance between Airplane:" + i + " ["+ Airplanes[i].x + " ,"+ Airplanes[i].y + "] and Airplane: " + k +" ["+ Airplanes[k].x + " ,"+ Airplanes[k].y + "]"+"\n";
           }
    }
}
     
public  void findIntersection(Point pln1,Point pln2,Point tr1,Point tr2,int p,int q ) {
   
    int denominator = (pln1.x - tr1.x) * (pln2.y - tr2.y)
            - (pln1.y - tr1.y) * (pln2.x - tr2.x);
    int px = 0;
    int py = 0;

    if (denominator == 0)
    {
        GUImain.Log+="Airplane <"+p+"> Line:["+pln1.x+","+pln1.y+"],["+tr1.x+","+tr1.y+"] and Airplane <"+q+"> Line: ["+pln2.x+","+pln2.y+"],["+tr2.x+","+tr2.y+"]  are Parallel. They wil not intersect, but they are close."+"\n";
        
    } else 
    {  
        px = ((pln1.x * tr1.y - pln1.y * tr1.x) * (pln2.x - tr2.x) - (pln1.x - tr1.x)
                * (pln2.x * tr2.y - pln2.y * tr2.x))
                / denominator;
        py = ((pln1.x * tr1.y - pln1.y * tr1.x) * (pln2.y - tr2.y) - (pln1.y - tr1.y)
                * (pln2.x * tr2.y - pln2.y * tr2.x))
                / denominator;

         intersect=new Point(px,py);
         speedNtime(intersect,pln1,pln2,speed[p],speed[q],p,q);
    }
}



public   int SafeDistance(Point xx,Point yy)
{
    int res1=0,res2=0,fres=0;
    res1=(int)java.lang.Math.pow((yy.x-xx.x),2); 
    res2= (int)java.lang.Math.pow((yy.y-xx.y),2); 
    fres= (int)Math.sqrt(res1 +res2);
    return fres;
}

     
     

public  void speedNtime(Point intr,Point pln1,Point pln2,int spd1,int spd2,int x,int y)        
{
   double dis1,dis2;
   double time1=0,time2=0,time3=0;
   
   dis1=SafeDistance(intr, pln1);
    //System.out.println(dis1+" dis1");
   dis2=SafeDistance(intr, pln2);
      // System.out.println(dis2+" dis2");
    try {
           time1=dis1/spd1;
       //System.out.println(time1);
            time2=dis2/spd2;
       
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Input Error: "+e.getMessage());
    }
     time3=time2-time1;
    //System.out.println(t2);
DecimalFormat dec = new DecimalFormat("#0.00");
     if(time3<0)
       time3=Math.abs(time3);
         
   if (time1==time2){
      GUImain.DangerMessage+="Airplane <"+x+"> and Airplane <"+y+"> will intersect in ["+intr.x+","+intr.y+"] in the same time after "+(dec.format(time1*60))+" mintue(s)."+" NOT SAFE!\n";
//      Intersections[s]=new Point(intr.x, intr.y);
//      s++;
   }else if ((time3)<10){
      GUImain.DangerMessage+="Airplane <"+x+"> and Airplane <"+y+"> will intersect in ["+intr.x+","+intr.y+"] in differance time of "+(dec.format(time3*60))+" mintue(s)."+" NOT SAFE!\n";
//      Intersections[s]=new Point(intr.x, intr.y);
//      s++;
   }else 
      GUImain.Log+="SAFE"+"\n";
}

}

