import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.*;

public class addQuestion implements ActionListener {
	JFrame addQFrame = new JFrame();
	 
    JLabel addLabel = new JLabel("Enter your question below : ");
    JLabel option1  = new JLabel("Option 1 : ");
    JLabel option2  = new JLabel("Option 2 : ");
    JLabel option3  = new JLabel("Option 3 : ");
    JLabel option4  = new JLabel("Option 4 : ");
    JLabel ans      = new JLabel("Correct Option : ");
    
    JTextField addField     = new JTextField();
	JTextField option1Field = new JTextField();
    JTextField option2Field = new JTextField();
    JTextField option3Field = new JTextField();
    JTextField option4Field = new JTextField();
    JTextField ansField     = new JTextField();
    
    JButton add = new JButton("ADD");
    
    String question , answer , op1 , op2 , op3 , op4;
    
    Connection conn;
    

	addQuestion()
	{

       
        addQFrame.setSize(500,500);
        addQFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addQFrame.setLocation(300, 60);
        addQFrame.setLayout(null);
        addQFrame.setResizable(false);
        
        //---------------------labels---------------------
        addLabel.setBounds(50, 25, 100, 25);

        option1.setBounds(50, 100, 100, 25);

        option2.setBounds(50, 150, 100, 25);
        
        option3.setBounds(50, 200, 100, 25);
        
        option4.setBounds(50, 250, 100, 25);
        
        ans.setBounds(50 , 300 , 100 , 25);
        
        //-----------textfields---------------------
        
        addField.setBounds(50, 60, 200, 25);
        addField.addActionListener(this);
        
        option1Field.setBounds(175, 100, 80, 25);
        option1Field.addActionListener(this);
        
        option2Field.setBounds(175, 150, 80, 25);
        option1Field.addActionListener(this);
        
        option3Field.setBounds(175, 200, 80, 25);
        option1Field.addActionListener(this);
        
        option4Field.setBounds(175, 250, 80, 25);
        option1Field.addActionListener(this);
        
        ansField.setBounds(175 , 300 , 80 , 25);
        ansField.addActionListener(this);
        
        //--------------------buttons--------------------------
        add.setBounds(170 , 350 , 100 ,30);
        add.setFocusable(false);
        add.addActionListener(this);


        
        addQFrame.add(addLabel);
        addQFrame.add(addField);
        addQFrame.add(option1);
        addQFrame.add(option1Field);
        addQFrame.add(option2);
        addQFrame.add(option2Field);
        addQFrame.add(option3);
        addQFrame.add(option3Field);
        addQFrame.add(option4);
        addQFrame.add(option4Field);
        addQFrame.add(ans);
        addQFrame.add(ansField);
        addQFrame.add(add);

        addQFrame.setVisible(true);
        
       connDB();
       
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == add)
		{
			question = addField.getText();
			op1      = option1Field.getText();
			op2      = option2Field.getText();
			op3      = option3Field.getText();
			op4      = option4Field.getText();
			answer   = ansField.getText();
			
			addQuestions();
			
			JOptionPane.showMessageDialog(null, "RECORDS UPDATED");
			
			addField.setText("");
			option1Field.setText("");
			option2Field.setText("");
		    option3Field.setText("");
			option4Field.setText("");
			ansField.setText("");
			
			
		}
		
	}
	
	 public void connDB()
	    {
	          
	               try{
	            	   Class.forName("com.mysql.cj.jdbc.Driver");
	   	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/exdb" , "root" , "root"  ); 
	               	  }
	             catch(Exception e){  e.printStackTrace(); }
	               System.out.println("Connection Established");
	  }
	public void addQuestions()
    {
	    try{
	    	
	        Statement stm = conn.createStatement();  
	        String sql1  = "select questionID from exdb.questions where question = '" + question+"'";
	        String sql   = "INSERT INTO  questions(question , answer , option1 , option2 , option3 , option4) \n VALUES ('"+"<html>"+question +"</html>" +"', '"+ answer + "', '"+ op1 +"', '"+ op2 + "', '"+op3+"', '"+ op4 +"');";
	        stm.executeUpdate(sql); 
	        System.out.println("New Question Added");
	        ResultSet rs = stm.executeQuery(sql1);
	        rs.next();
	        System.out.println("questionID for newly added question is : " + rs.getInt(1) );
	        
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
	    
    }
}
