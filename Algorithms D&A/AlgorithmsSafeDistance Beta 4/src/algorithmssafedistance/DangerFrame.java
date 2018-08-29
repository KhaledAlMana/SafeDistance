/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmssafedistance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Khaled
 */
public class DangerFrame extends JFrame{
    JTextArea DangerMessage;
    JButton Close;
    JScrollPane Scroller;
    public DangerFrame() throws HeadlessException {
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(600,400);
        setTitle("Airplanes Safe Distance - Danger Status");
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        DangerMessage = new JTextArea(GUImain.DangerMessage);
        DangerMessage.setEditable(false);
        Scroller = new JScrollPane(DangerMessage);
        Close = new JButton("Close");
        
        Close.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        add(Scroller,BorderLayout.CENTER);
        add(Close,BorderLayout.SOUTH);
            
            
    
    }
    
    
    
}
