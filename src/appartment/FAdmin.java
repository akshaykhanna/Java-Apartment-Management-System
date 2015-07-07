/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appartment;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Akshay
 */
public class FAdmin extends FrameD implements ActionListener
{
    JButton add,edit,serv,stat,list;
     JLabel jl,jls;
    FAdmin()
    {
        setTitle("Admin Panel");
        setLayout(new GridLayout(5,1));
        add=new JButton("Add");
        /*
        add.addActionListener(new ActionListener(){
            

            @Override
            public void actionPerformed(ActionEvent e) {
                frameAdd();
//To change body of generated methods, choose Tools | Templates.
            }
        });
         edit.addActionListener(new ActionListener(){
            

            @Override
            public void actionPerformed(ActionEvent e) {
                frameRemove();
//To change body of generated methods, choose Tools | Templates.
            }
        });*/
        edit=new JButton("Edit");
        serv=new JButton("Services");
        
        stat=new JButton("Stats");
        list=new JButton("List");
        stat.addActionListener(this);
        add.addActionListener(this);
        edit.addActionListener(this);
        serv.addActionListener(this);
        list.addActionListener(this);
        add(add);
        add(edit);
        add(serv);
        add(stat);
        add(list);
        setSize(400,300);
    }
     private void frameAdd() {
                 //To change body of generated methods, choose Tools | Templates.
        jl=new JLabel("Visible");
         add(jl);
            }
     private void frameRemove(){
         remove(jl);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
         //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==stat)
        {
          System.out.println("stats works");
          Fstats obj=new Fstats();
            /*jls=new JLabel();
            DB obj=new DB();
            jls.setText(obj.stats());
            add(jls);*/
        }
        else if(e.getSource()==add)
        {
             System.out.println("Add works");
            FAdd objA=new FAdd();
            objA.addEventListen();
        }
         else if(e.getSource()==serv)
        {
             System.out.println("serv works");
            FService obj=new FService();
            obj.create();
            
        }
        else if(e.getSource()==edit)
        {
            FAdd obE;
             System.out.println("Edit works");
            String flatno=JOptionPane.showInputDialog("Enter Flatno");
            DB obj=new DB();
           if( obj.checkFlat(flatno))
           {
               obE=new FAdd(flatno);
               obE.editEventListen();
           }
           else
               JOptionPane.showMessageDialog(null,"No such flat no. !","Edit Failed :(",JOptionPane.PLAIN_MESSAGE);
           
        }
        if(e.getSource()==list)
        {
          System.out.println("list works");
          FrameList obj=new FrameList();
            /*jls=new JLabel();
            DB obj=new DB();
            jls.setText(obj.stats());
            add(jls);*/
        }
        
    }
            
}
