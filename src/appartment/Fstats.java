/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appartment;

import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 *
 * @author Akshay
 */
public class Fstats extends FrameD
{
 Fstats()
 {
     BorderLayout bl=new BorderLayout();
     setSize(200,300);
     setLayout(bl);
     JLabel jl=new JLabel(),jls=new JLabel("<html><h1>Statistics</h1></html>");
     add(jls,BorderLayout.NORTH);
     
     DB obj=new DB();
            jl.setText(obj.stats());
            add(jl,BorderLayout.CENTER);
 }   
}
