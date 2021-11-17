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

public class editQuestion implements ActionListener {
	JFrame editQFrame = new JFrame();
	 
    JLabel editLabel = new JLabel("Please Enter the Question ID : ");
    JLabel option1  = new JLabel("Option 1 : ");
    JLabel option2  = new JLabel("Option 2 : ");
    JLabel option3  = new JLabel("Option 3 : ");
    JLabel option4  = new JLabel("Option 4 : ");
    JLabel ans      = new JLabel("Correct Option : ");
    
    
    
    JTextField editField     = new JTextField();
	JTextField option1Field  = new JTextField();
    JTextField option2Field  = new JTextField();
    JTextField option3Field  = new JTextField();
    JTextField option4Field  = new JTextField();
    JTextField ansField      = new JTextField();
    
    JButton edit = new JButton("Select");
    JButton change = new JButton("Confirm Changes");
    
    String question , answer , op1 , op2 , op3 , op4;
    int num;
    
    Connection conn;
    

	editQuestion()
	{

       
        editQFrame.setSize(500,500);
        editQFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        editQFrame.setLocation(300, 60);
        editQFrame.setLayout(null);
        editQFrame.setResizable(false);
        
        
        //---------------------labels---------------------
        editLabel.setBounds(50, 25, 200, 25);

        option1.setBounds(50, 100, 100, 25);
        option1.setVisible(false);

        option2.setBounds(50, 150, 100, 25);
        option2.setVisible(false);
        
        option3.setBounds(50, 200, 100, 25);
        option3.setVisible(false);
        
        option4.setBounds(50, 250, 100, 25);
        option4.setVisible(false);
        
        ans.setBounds(50 , 300 , 100 , 25);
        ans.setVisible(false);
        
        //-----------textfields---------------------
        
        editField.setBounds(50, 60, 200, 25);
        editField.addActionListener(this);
        
        option1Field.setBounds(175, 100, 80, 25);
        option1Field.addActionListener(this);
        option1Field.setVisible(false);
        
        option2Field.setBounds(175, 150, 80, 25);
        option2Field.addActionListener(this);
        option2Field.setVisible(false);
        
        option3Field.setBounds(175, 200, 80, 25);
        option3Field.addActionListener(this);
        option3Field.setVisible(false);
        
        option4Field.setBounds(175, 250, 80, 25);
        option4Field.addActionListener(this);
        option4Field.setVisible(false);
        
        ansField.setBounds(175 , 300 , 80 , 25);
        ansField.addActionListener(this);
        ansField.setVisible(false);
        
        //--------------------buttons--------------------------
        edit.setBounds(170 , 125 , 100 ,30);
        edit.setFocusable(false);
        edit.addActionListener(this);
        
        


        
        editQFrame.add(editLabel);
        editQFrame.add(editField);
        editQFrame.add(option1);
        editQFrame.add(option1Field);
        editQFrame.add(option2);
        editQFrame.add(option2Field);
        editQFrame.add(option3);
        editQFrame.add(option3Field);
        editQFrame.add(option4);
        editQFrame.add(option4Field);
        editQFrame.add(ans);
        editQFrame.add(ansField);
        editQFrame.add(edit);
        editQFrame.add(change);
        
        connDB();
        
        editQFrame.setVisible(true);
        
       
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == edit)
		{
			edit.setVisible(false);
			editLabel.setText("Enter The Question Below : ");
			
			 num = Integer.parseInt(editField.getText());
			
			option1.setVisible(true);
			option2.setVisible(true);
			option3.setVisible(true);
			option4.setVisible(true);
			ans.setVisible(true);
			
			option1Field.setVisible(true);
			option2Field.setVisible(true);
			option3Field.setVisible(true);
			option4Field.setVisible(true);
			ansField.setVisible(true);
			
			getQuestions(num);
			
			
			editField.setText(question);
			option1Field.setText(op1);
			option2Field.setText(op2);
			option3Field.setText(op3);
			option4Field.setText(op4);
			ansField.setText(answer);
			
			
			
			change.setBounds(170 , 350 , 150 ,30);
	        change.setFocusable(false);
	        change.addActionListener(this);
			change.setVisible(true);
		}
		if(e.getSource() == change)
		{
			int value = JOptionPane.showConfirmDialog(editQFrame, "Are You Sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if(value == 0)
            {
				//num = Integer.parseInt(editField.getText());
				
				question = editField.getText();
				op1      = option1Field.getText();
				op2      = option2Field.getText();
				op3      = option3Field.getText();
				op4      = option4Field.getText();
				answer   = ansField.getText();
				
				
				setQuestions(num);
				
				option1.setVisible(false);
				option2.setVisible(false);
				option3.setVisible(false);
				option4.setVisible(false);
				ans.setVisible(false);
				
				option1Field.setVisible(false);
				option2Field.setVisible(false);
				option3Field.setVisible(false);
				option4Field.setVisible(false);
				ansField.setVisible(false); 
				change.setVisible(false);
				
				editLabel.setText("Enter The questionID : ");
				editField.setText("");
				editField.setVisible(true);
				edit.setVisible(true);
				
				 
            }
			
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
	public void getQuestions(int number)
    {
	    try{
	    	
	        Statement stm = conn.createStatement();  
	        String sql1  = "select * from exdb.questions where questionID = " + number ;
	        //String sql   = "INSERT INTO  questions \n VALUES ('"+question +"', '"+ answer + "', '"+ op1 +"', '"+ op2 + "', '"+op3+"', '"+ op4 +"');";
	        ResultSet rs = stm.executeQuery(sql1);
	        //stm.executeUpdate(sql); 
	        
	        rs.next();
	        question = rs.getString(1);
	        answer   = rs.getString(2);
	        op1   = rs.getString(3);
	        op2   = rs.getString(4);
	        op3   = rs.getString(5);
	        op4   = rs.getString(6);
	        
	        
	        System.out.println("Question Fetched From DataBase");
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
	    
    }
	public void setQuestions(int number)
    {
	    try{
	    	String sql1  = "select * FROM questions WHERE questionID = " + number ;
	        Statement stm = conn.createStatement();
	        //String sql   = "UPDATE  exdb.questions SET question = '" + question + "', answer = '"+ answer + "' , option1 = '"+ op1 +"', option2 = '"+ op2 + "', option3 = '"+op3+"' ,option4 = '"+ op4 +"'  where questionID = " + number +"; COMMIT;";
	        String updateQuestion = "UPDATE questions SET question = '"+"<html>"+question +"</html>"+"'  WHERE questionID = " + number;
	        String updateAnswer   = "UPDATE questions SET answer   = '" + answer    +"'  WHERE questionID = " + number;
	        String updateOption1  = "UPDATE questions SET option1  = '" + op1       +"'  WHERE questionID = " + number;
	        String updateOption2  = "UPDATE questions SET option2  = '" + op2       +"'  WHERE questionID = " + number;
	        String updateOpttion3 = "UPDATE questions SET option3  = '" + op3       +"'  WHERE questionID = " + number;
	        String updateOption4  = "UPDATE questions SET option4   = '" + op4       +"'  WHERE questionID = " + number;
	        
	        ResultSet rs = stm.executeQuery(sql1); 
	        
	        stm.executeUpdate(updateQuestion);
	        stm.executeUpdate(updateAnswer);
	        stm.executeUpdate(updateOption1);
	        stm.executeUpdate(updateOption2);
	        stm.executeUpdate(updateOpttion3);
	        stm.executeUpdate(updateOption4);
	        
	        
	        
	        rs.close();
	        
	        JOptionPane.showMessageDialog(null, "RECORDS UPDATED");
            System.out.println("RECORD UPDATED SUCCESSFULLY");
	       
        
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
	    
    }
}
