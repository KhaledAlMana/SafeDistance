/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmssafedistance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static java.lang.Thread.currentThread;

/**
 *
 * @author Khaled
 */
public class GUImain extends JFrame{
    
    JButton ReadInput,Calculate,Exit,ShowLog,ReadStatus;
    JLabel SizeLabel,Options;
    
    public static JLabel SizeInput;
    
    JPanel JPOptions;
    JPsimulation JPsimu;
    public static Thread d;
    
    static ReadInput  Input =  new ReadInput();
//    public static int s=0; 
    int reset=0;
    public static String DangerMessage = null;
    public static String Log = null;
    public static String StatusMessage = null;
    
    public GUImain() throws HeadlessException, FileNotFoundException {
        //Interface
            setLayout(new BorderLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Airplanes Safe Distance - Algorithms Project");
            setBackground(Color.LIGHT_GRAY);
        ////  INITIALIZATION !!!!
        ReadInput = new JButton("Read Input");
        Calculate = new JButton("Calculate");
        ShowLog = new JButton("Show Log");
        ReadStatus = new JButton("Read Status");
        Exit = new JButton("Exit");
               

        
        JPOptions= new JPanel();
        JPOptions.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=1;
        c.gridx=0;
        c.gridy=0;
        c.ipady = 30;
        c.ipadx = 30;
        c.anchor=GridBagConstraints.CENTER;
        JPOptions.setBounds(10, 10, 10, 10);
        JPOptions.setBackground(Color.LIGHT_GRAY);

        Options= new JLabel("Options");
        SizeLabel= new JLabel("Size");
        SizeInput= new JLabel();
        
        Options.setFont(new Font("Times New Roman", 2, 18)); 
        SizeLabel.setFont(new Font("Tahoma", 1, 14)); 
        SizeInput.setFont(new Font("Tahoma", 1, 14)); 
        

            
        
        ////  Action Listerners !!
        ReadInput.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                StatusMessage=null;
                try {
                    Input.getFileSize();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUImain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
         
        Calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Log=null;
                DangerMessage=null;
                StatusMessage=null;
                
                try {
                    Input.getFileSize();
                    Input.calculate();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUImain.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(reset>0){
                getContentPane().remove(JPsimu);     
                JPsimu= new JPsimulation();
                d=new Thread(JPsimu);
                add(JPsimu,BorderLayout.CENTER);
                validate();
                repaint();
                    JPsimulation.done=0;
                    try {
                        d.start();
                    } catch (Exception e) {
                    }
                }
                else {
                     try {

                        d.start();
                        reset++;
                       } catch (Exception e) {
                    }
                }

               
                 
               if(DangerMessage!=null){
                  new DangerFrame();
                           Log+=DangerMessage;

               }
              else 
                 JOptionPane.showMessageDialog(null, "Danger Free! :)" , 
                "Danger Status", JOptionPane.INFORMATION_MESSAGE);  
                     
            }

        }
        );   
        
        ShowLog.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               if(Log!=null)
                 new LogFrame();
              else 
                  JOptionPane.showMessageDialog(null, "Log is empty, calculate first.",
                "Log & Status of All Planes", JOptionPane.WARNING_MESSAGE); 

            }
        }
        );  
                
        ReadStatus.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 JOptionPane.showMessageDialog(null, StatusMessage ,
               "Input Info.", JOptionPane.INFORMATION_MESSAGE); 

            }
        }
        );  
                        
        Exit.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        }
        );  
        ////  ADD TO PANEL!!        
        JPOptions.add(Options,c);
                c.gridy++;
        JPOptions.add(ReadInput,c);
                c.gridy++;
        JPOptions.add(Calculate,c);
                c.gridy++;
        JPOptions.add(ShowLog,c);
                c.gridy++;
        JPOptions.add(ReadStatus,c);
                c.gridy++;
        JPOptions.add(Exit,c);
                c.gridy++;
        JPOptions.add(SizeLabel,c);
                  c.gridx++;
        JPOptions.add(SizeInput,c);
        
                
    /// For Simulation Side
            Input.getFileSize();
            Input.calculate();
            JPsimu = new JPsimulation(); 
            d= new Thread(JPsimu);
           pack();
           setVisible(true);
           setSize(800,600);
           setLocationRelativeTo(null);
        /// Add to Frame
        this.add(JPOptions,BorderLayout.EAST);
        this.add(JPsimu,BorderLayout.CENTER);
         

        
    }
        public static void main(String args[]) throws FileNotFoundException {
         GUImain g=new GUImain();


    }
    
}
