/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appartment;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Akshay
 */
public class FService extends FrameD implements ActionListener
{
    JLabel jl[]=new JLabel[6];
    JTextField jtf[]=new JTextField[6];
    JButton b;
    DB obj;
    FService(){
       setTitle("Edit Services");
       setLayout(new GridLayout(6,2));
       //String q="SELECT * FROM `servcost";
       for(int i=0;i<5;i++)
       {
         jl[i]=new JLabel("Ak");
         jtf[i]=new JTextField("Ak");
         add(jl[i]);
         add(jtf[i]);
       }
       
     JPanel jp=new JPanel();
    
        b=new  JButton("Submit");
       b.addActionListener(this);
       jp.add(b,JPanel.CENTER_ALIGNMENT);
       add(jp);
       
    }
    void create()
    {
        String q="SELECT * FROM servcost";
       obj=new DB();
        
        
        
       try
       {  
           ResultSet rs=obj.selectq(q);
           int i=0;
           while(rs.next())
          {
           jl[i].setText(obj.fullservConv(rs.getString("serv")));
           jtf[i].setText(rs.getString("price"));
           i++;
          }
       }
       catch (Exception e)
       {
           System.out.println("Error-Fservice-Const"+e.toString());
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==b)
        {
            System.out.println("Button action");
           String q="";int f=1;
            for(int i=0;i<5;i++)
           {
               
             q="UPDATE servcost SET price="+Double.parseDouble(jtf[i].getText().toString())+" WHERE serv='"+jl[i].getText().toString().charAt(0)+"'";
             if(!obj.updateq(q))
             {
                 JOptionPane.showMessageDialog(null,"Some problem occured while updating, retry again !","Error Occured :)",JOptionPane.PLAIN_MESSAGE);
                 f=0;
                 break;
             }
           }
            if(f==1)
             JOptionPane.showMessageDialog(null,"Data Succesfully Updated!","Success :)",JOptionPane.PLAIN_MESSAGE);   
          
        }
    }
}
