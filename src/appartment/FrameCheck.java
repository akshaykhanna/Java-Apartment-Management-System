/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appartment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Akshay
 */
public class FrameCheck extends FrameD implements ActionListener
{
    JTextField jtf;
    JButton bCheck;
     JLabel jl;
     
     
    FrameCheck()
    {
     
      
        setTitle("Check your maintanance");
        setLayout(new FlowLayout());
        jtf=new JTextField("Flat no.");
        JPanel jp=new JPanel();
        jp.add(jtf);
        add(jp);
        bCheck=new  JButton("Check");
        bCheck.addActionListener(this);
        jl=new JLabel("Enter flat no");
        add(bCheck);
        add(jl);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if(e.getSource()==bCheck) 
        {
            String flat=jtf.getText().toString();
            System.out.println("It works"+flat);
           // String q="SELECT flatno,name FROM info WHERE flatno='"+flat+"'";
            //DB o=new DB();
           //String r=o.selectq(q);
            DB o=new DB();
           String text= o.calcBill(flat);
           if(text!="error")
           {
               jl.setText(text);
           }
           else
            jl.setText("Enter valid house no.");
            
           
        }
    }
}
