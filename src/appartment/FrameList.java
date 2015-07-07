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
public class FrameList extends FrameD 
{
    JTextField jtf;
    JButton bCheck;
     JLabel jl;
     
     
    FrameList()
    {
     
      
        setTitle("Amount to paid by ");
        setLayout(new FlowLayout());
       
     
        jl=new JLabel("Enter flat no");
        /*
        //String flat=jtf.getText().toString();
            System.out.println("It works"+flat);
           // String q="SELECT flatno,name FROM info WHERE flatno='"+flat+"'";
            //DB o=new DB();
           //String r=o.selectq(q);
         * */
            DB obj=new DB();
           String text= obj.genrateList();
          
               jl.setText(text);
          
           add(jl);
        
    }

    
}
