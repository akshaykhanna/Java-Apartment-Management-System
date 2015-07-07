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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Akshay
 */
public class FAdd extends FrameD
{
   JLabel jl[]=new JLabel[6];
   JTextField jtf[]=new JTextField[3];
   JCheckBox jcb[]=new JCheckBox[5];
   JButton b;
   DB objDB;
    FAdd()
    {
        GridLayout gl=new GridLayout(9,3);
        setLayout(gl);
        setSize(250,500);
        jl[0]=new JLabel("Name:");
        jl[1]=new JLabel("Flat no.:");
        jl[2]=new JLabel("Contact no.:");
        for(int i=0;i<3;i++)
        {
            add(jl[i]);
            jtf[i]=new JTextField();
            add(jtf[i]);
        }
        jl[3]=new JLabel("Choose");
        add(jl[3]);
        jl[4]=new JLabel("Services");
        add(jl[4]);
        //jcb[0]=new JCheckBox("Maintanance");
        //add(jcb[0]);
        objDB=new DB();
        for(int i=0;i<5;i++)
        {
            jcb[i]=new JCheckBox(objDB.serv[i]);
            System.out.println(objDB.serv[i]);
            add(jcb[i]);
        }
        jl[5]=new JLabel("");
        add(jl[5]);
        b=new JButton("Submit");
        add(b);
    }
    FAdd(String flatno)
    {
      GridLayout gl=new GridLayout(9,3);
        setLayout(gl);
        setSize(250,500);
        objDB=new DB();
        String q="SELECT * FROM info WHERE flatno='"+flatno+"'";
       
        
         jl[0]=new JLabel("Name:");
        jl[1]=new JLabel("Flat no.:");
        jl[2]=new JLabel("Contact no.:");
        
       try
       {  
           ResultSet rs=objDB.selectq(q);
           
           while(rs.next())
          {
          
          
           jtf[0]=new JTextField(rs.getString("name"));
           add(jl[0]);
           add(jtf[0]);
        jtf[1]=new JTextField(rs.getString("flatno"));
        add(jl[1]);
         add(jtf[1]);
        jtf[2]=new JTextField(rs.getString("phone"));
        add(jl[2]);  
        add(jtf[2]);
          }
           
        jl[3]=new JLabel("Choose");
        add(jl[3]);
        jl[4]=new JLabel("Services");
        add(jl[4]);
        //jcb[0]=new JCheckBox("Maintanance");
        //add(jcb[0]);
        objDB=new DB();
        q="SELECT * FROM services WHERE flatno='"+flatno+"'";
        rs=objDB.selectq(q);
        boolean cho[]=new boolean[5];
        char ch;
        while(rs.next())
        {
            ch=rs.getString("serv").charAt(0);
             for(int i=0;i<5;i++)
           {
               if(ch==objDB.serv[i].charAt(0))
                   cho[i]=true;
                
           }
        }
        for(int i=0;i<5;i++)
           System.out.println(cho[i]);
        for(int i=0;i<5;i++)
        {
            jcb[i]=new JCheckBox(objDB.serv[i]);
            if(cho[i])
             jcb[i].setSelected(true);
            System.out.println(objDB.serv[i]);
            add(jcb[i]);
        }
       }
       catch (Exception e)
       {
           System.out.println("Error-Fservice-Const"+e.toString());
       }
        
       
        jl[5]=new JLabel("");
        add(jl[5]);
        b=new JButton("Submit");
        add(b);
    }  
    
    void addEventListen()
    {
        setTitle("Add Members");
        addLis oh=new addLis();
        b.addActionListener(oh);
        for(int i=0;i<5;i++)
        {
            if(i<3)
            jtf[i].addActionListener(oh);
          
            jcb[i].addActionListener(oh);
            
        }
        
        
    }
    void editEventListen()
    {
        setTitle("Edit Members");
        editLis oh=new editLis();
        b.addActionListener(oh);
        for(int i=0;i<5;i++)
        {
            if(i<3)
            jtf[i].addActionListener(oh);
          
            jcb[i].addActionListener(oh);
            
        }
    }
    class addLis implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             //To change body of generated methods, choose Tools | Templates.
           
            if(e.getSource()==b)
            {
                 System.out.println("addLis works!");
                 String nfp[]=new String[3];
                 int f=1;
              do{
                  for(int i=0;i<3;i++)
                 {
                   if(jtf[i].getText().toString().trim()=="")
                   {
                       JOptionPane.showMessageDialog(null,"Enter all text fields !","Failed to Submit :(",JOptionPane.PLAIN_MESSAGE);
                       f=0;
                       break;
                   }
                  else
                   {
                   nfp[i]=jtf[i].getText().toString().trim();
                   System.out.println(nfp[i]);
                    }
                     
                 }
                  if(f==0)
                   break;
                  boolean choice[]=new boolean[5];
                   for(int i=0;i<5;i++)
                   {
                      choice[i]=jcb[i].isSelected();
                       System.out.println(jcb[i].isSelected()+"  " + choice[i]);
                   }
                   objDB=new DB();
                   objDB.updAdd(nfp,choice);
                  f=0;
              }while(f==1); 
            }
        }
        
    }
    class editLis implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getSource()==b)
            {
                 System.out.print("editLis works!");
                  
                 String nfp[]=new String[3];
                 int f=1;
              do{
                  for(int i=0;i<3;i++)
                 {
                   if(jtf[i].getText().toString().trim()=="")
                   {
                       JOptionPane.showMessageDialog(null,"Enter all text fields !","Failed to Submit :(",JOptionPane.PLAIN_MESSAGE);
                       f=0;
                       break;
                   }
                  else
                   {
                   nfp[i]=jtf[i].getText().toString().trim();
                   System.out.println(nfp[i]);
                    }
                     
                 }
                  if(f==0)
                   break;
                  String q="UPDATE info SET name='"+nfp[0]+"', flatno='"+nfp[1]+"',phone="+nfp[2]+" WHERE flatno='"+nfp[1]+"'";
                  if(!objDB.updateq(q))
                  JOptionPane.showMessageDialog(null,"Unable to alter fields !","Failed to Submit :(",JOptionPane.PLAIN_MESSAGE);
                  q="DELETE FROM `services` WHERE flatno='"+nfp[1]+"'";
                  if(objDB.deleteq(q))
                  {
                          
                  boolean choice[]=new boolean[5];
                   for(int i=0;i<5;i++)
                   {
                      choice[i]=jcb[i].isSelected();
                       System.out.println(jcb[i].isSelected()+"  " + choice[i]);
                   }
                   objDB=new DB();
                   objDB.updEdit(nfp[1],choice);
                      
                  }
                  
                  
                  
                  f=0;
              }while(f==1); 
            }
                 
            }//To change body of generated methods, choose Tools | Templates.
        }
        
    
    
}
