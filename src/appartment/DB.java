
package appartment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DB 
{
     Connection con;
     String serv[]={"Maintanance","Swimming","Gym","Club","Backup Power"};
    DB()
    {
     try
        {
            //housekeeping
            Class.forName("com.mysql.jdbc.Driver");
         
            con=DriverManager.getConnection("jdbc:mysql://localhost/appartment","aks","hay");
            // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshay","aki","papu");

        }
        catch(Exception e)
        {
            System.out.println("Error:dbmanger-const: "+ e.toString());
        }
 }    
    
 void connect()
 {
     try
     {
           // statements allow to issue SQL queries to the database
      Statement statement = con.createStatement();
      // resultSet gets the result of the SQL query
      ResultSet obj = statement.executeQuery("SELECT flatno,name FROM info ORDER BY name");
      while(obj.next())
      {
          System.out.println(""+obj.getString("flatno")+"  "+obj.getString("name"));
      }
     }    
     catch(Exception e)
        {
            System.out.println("Error:dbmanger-connect: "+ e.toString());
        }
 }
    ResultSet selectq(String q)
    {
        try
     {
           // statements allow to issue SQL queries to the database
      Statement statement = con.createStatement();
      // resultSet gets the result of the SQL query
      ResultSet o1 = statement.executeQuery(q);
      //System.out.println("Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getNString("phone"));
     System.out.println("selectq() works");
      return o1;
     }    
     catch(Exception e)
        {
            System.out.println("Error:dbmanger-selectq: "+ e.toString());
            return null;
        }
    }
    boolean insertq(String q)
    
    {
        try
     {
           // statements allow to issue SQL queries to the database
      Statement statement = con.createStatement();
      // resultSet gets the result of the SQL query
      //if(statement.executeUpdate("INSERT INTO info (flatno, name, phone) VALUES ('4B','Om  Makhija',9991112293)")>0)
      if(statement.executeUpdate(q)>0)
      {System.out.println("Inserted Success full");
       return true;
      }
      else
      {
      System.out.println("Failed to Insert");  
      return false;
      }
      //System.out.println("Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getNString("phone"));
     
      
     }    
     catch(Exception e)
        {
            System.out.println("Error:dbmanger-selectq: "+ e.toString());
            return false;
        }
    }
    
     boolean updateq(String q)
    
    {
        try
     {
           // statements allow to issue SQL queries to the database
      Statement statement = con.createStatement();
      // resultSet gets the result of the SQL query
      //if(statement.executeUpdate("INSERT INTO info (flatno, name, phone) VALUES ('4B','Om  Makhija',9991112293)")>0)
      //if(statement.executeUpdate("UPDATE servcost SET price=550 WHERE serv='S'")>0)
      if(statement.executeUpdate(q)>0)
      {System.out.println("Updated Success full");
       return true;
      }
      else
      {
      System.out.println("Failed to Update");  
      return false;
      }
      //System.out.println("Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getNString("phone"));
     
      
     }    
     catch(Exception e)
        {
            System.out.println("Error:dbmanger-selectq: "+ e.toString());
            return false;
        }
    }
    
      boolean deleteq(String q)
    
    {
        try
     {
           // statements allow to issue SQL queries to the database
      Statement statement = con.createStatement();
      // resultSet gets the result of the SQL query
      //if(statement.executeUpdate("INSERT INTO info (flatno, name, phone) VALUES ('4B','Om  Makhija',9991112293)")>0)
      //if(statement.executeUpdate("UPDATE servcost SET price=550 WHERE serv='S'")>0)
      if(statement.executeUpdate(q)>0)
      {System.out.println("Delete Success full");
       return true;
      }
      else
      {
      System.out.println("Failed to Delete");  
      return false;
      }
      //System.out.println("Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getNString("phone"));
     
      
     }    
     catch(Exception e)
        {
            System.out.println("Error:dbmanger-selectq: "+ e.toString());
            return false;
        }
    }
   

    String calcBill(String flat) 
    {
        String text="error";
        try
        {
             text="<html>";
        ResultSet o1=selectq("SELECT * FROM info WHERE flatno='"+flat+"'");
        while(o1.next())
      {
          text+="Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getString("phone");
      }
      
        //="Name: "+o1.getString("name")+"<br/>"+"Flat No.: "+o1.getString("flatno")+"<br/>"+"Contact No: "+o1.getNString("phone");
         o1=selectq("SELECT services.serv,price FROM info,services,servcost WHERE info.flatno=services.flatno AND services.serv=servcost.serv AND info.flatno='"+flat+"'");
          text+="<br/> <br/> Bill:- <hr/>";
          double cost=0;
         while(o1.next())
      {
         text+=fullservConv(o1.getString("serv"))+"  Rs:"+o1.getString("price")+"/-<br/>" ;
         cost+=Double.parseDouble(o1.getString("price"));
      }
         text+="<hr/> Total: Rs: "+cost+"/- <br/> <br/> For any queries, please contact admin";
         text+="</html>";
        }
        catch(Exception e)
        {
            System.out.println("Error:dbmanger-calcBill: "+ e.toString());
            text="error";
        }
        return text;
    }
    String fullservConv(String ch)
    {
        String temp="Other";
        for(int i=0;i<=serv.length;i++)
        {
            if(serv[i].startsWith(ch))
            {
                temp=serv[i];
                break;
            }
        }
        return temp;
    }

    boolean login(String user, String pass) 
    {
      String q="SELECT * FROM `admin` WHERE admin='"+user+"'";  
      ResultSet obj=selectq(q);
      
      try
      {
          String passR="";
            while(obj.next())
           {
               passR=obj.getString("pass");
               System.out.println("Its "+passR);
           }
          if(passR.equalsIgnoreCase(pass))
          {
              System.out.println("Its True..!");
              return true;
          }
      }
      catch(Exception e)
              {
                  System.out.println(e.toString()+ "Error:dbmanger-login: ");
                  
              }
              return false;
        }
    String stats()
    {
        String q="SELECT serv,count(*) AS co from  services group by serv";
        String text="";
        try
        {
            ResultSet o1=selectq(q); 
            text="<html><table border=\"1\"><tr><th>Services</th><th>No. of Flats</th></tr>";
            
       
        while(o1.next())
      {
          text+="<tr><td>"+fullservConv(o1.getString("serv"))+"</td> <td>"+o1.getString("co")+"</td></tr>";
      }
        text+="</table></html>";
        }catch(Exception e)
        {
            System.out.println("Error:dbmanger-stats: "+ e.toString());
            text="error";
        }
        return text;
    }

    void updAdd(String[] nfp, boolean[] choice) {
        //To change body of generated methods, choose Tools | Templates.
       int lnfp=nfp.length,lc=choice.length;
       //String q="INSERT INTO `info`(`flatno`, `name`, `phone`) VALUES ("+nfp[0]+","+nfp[1]+","+Integer.parseInt(nfp[2])+");";
      // String q=String.format("INSERT INTO info(flatno,name,phone) VALUES('%s','%s',%)","12B","Karan",91234912);
     int f=1;
     //String q=String.format"INSERT INTO info(flatno,name,phone) VALUES('%s','%s',%s)", "Aks","22b","9999999");
       while(f==1)
     { if( !insertq("INSERT INTO info(name,flatno,phone) VALUES('"+nfp[0]+"','"+nfp[1]+"',"+nfp[2]+")"))
      {
          JOptionPane.showMessageDialog(null,"Some problem occured while updating, retry again !","Error Occured :(",JOptionPane.PLAIN_MESSAGE);
          f=0;
            break;      
      }
       for(int i=0;i<lc;i++)
           {
               if(choice[i])
               {
                   if(!updateq("INSERT INTO services(flatno, serv) VALUES ('"+nfp[1]+"','"+serv[i].charAt(0)+"')"))
                   {
                      JOptionPane.showMessageDialog(null,"Problem occured while updating services, Edit again with same flatno !","Error Occured :(",JOptionPane.PLAIN_MESSAGE);
                       f=0;
                      break;
                   }
               }
           }
       if(f==1)
       {
           JOptionPane.showMessageDialog(null,"Member Succesfull Added !","Success :)",JOptionPane.PLAIN_MESSAGE);
       }
     f=0;
     }
       System.out.println("upAdd....1"+nfp[0]+nfp[1]+nfp[2]);
       //insertq(q);
    }
    boolean editService(String q)
    {
        boolean status=false;
        String t[][]=new String[6][2];
         try
        {
         status=insertq("SELECT * FROM servcost");
      
        return true;
        }
        catch(Exception e)
        {
            System.out.println("Error:dbmanger-editservice: "+ e.toString());
            return false;
        }
    }

    boolean checkFlat(String flatno) {
         //To change body of generated methods, choose Tools | Templates.
        String q="SELECT flatno FROM info WHERE flatno='"+flatno+"'";  
      
      int f=0;
      try
      {
          ResultSet obj=selectq(q);
        
            while(obj.next())
           {
               f++;
           }
         
      }
      catch(Exception e)
              {
                  System.out.println(e.toString()+ "Error:dbmanger-check: ");
                  
              }
             if(f>0)
                 return true;
             else
                  return false;
         
    }

    void updEdit(String string, boolean[] choice) {
         //To change body of generated methods, choose Tools | Templates.
         // String q=String.format("INSERT INTO info(flatno,name,phone) VALUES('%s','%s',%)","12B","Karan",91234912);
     int f=1;
     //String q=String.format"INSERT INTO info(flatno,name,phone) VALUES('%s','%s',%s)", "Aks","22b","9999999");
       while(f==1)
     { 
       for(int i=0;i<5;i++)
           {
               if(choice[i])
               {
                   if(!updateq("INSERT INTO services(flatno, serv) VALUES ('"+string+"','"+serv[i].charAt(0)+"')"))
                   {
                      JOptionPane.showMessageDialog(null,"Problem occured while updating services, Edit again with same flatno !","Error Occured :(",JOptionPane.PLAIN_MESSAGE);
                       f=0;
                      break;
                   }
               }
           }
       if(f==1)
       {
           JOptionPane.showMessageDialog(null,"Member Succesfull Edited !","Success :)",JOptionPane.PLAIN_MESSAGE);
       }
     f=0;
     }
      
    }
}
