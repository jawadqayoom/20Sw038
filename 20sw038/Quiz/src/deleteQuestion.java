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

public class deleteQuestion implements ActionListener {
	JFrame deleteQFrame = new JFrame();
	 
    JLabel deleteLabel = new JLabel("Please Enter the Question ID : ");
    JLabel option1  = new JLabel("Option 1 : ");
    JLabel option2  = new JLabel("Option 2 : ");
    JLabel option3  = new JLabel("Option 3 : ");
    JLabel option4  = new JLabel("Option 4 : ");
    JLabel ans      = new JLabel("Correct Option : ");
    
    
    
    JTextField deleteField     = new JTextField();
	JTextField option1Field = new JTextField();
    JTextField option2Field = new JTextField();
    JTextField option3Field = new JTextField();
    JTextField option4Field = new JTextField();
    JTextField ansField     = new JTextField();
    
    JButton delete = new JButton("Select");
    JButton confirm = new JButton("CONFIRM");
    
    String question , answer , op1 , op2 , op3 , op4;
    int num;
    
    Connection conn;
    

	deleteQuestion()
	{

       
        deleteQFrame.setSize(500,500);
        deleteQFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        deleteQFrame.setLocation(300, 60);
        deleteQFrame.setLayout(null);
        deleteQFrame.setResizable(false);
        
        
        //---------------------labels---------------------
        deleteLabel.setBounds(50, 25, 200, 25);

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
        
        deleteField.setBounds(50, 60, 200, 25);
        deleteField.addActionListener(this);
        
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
        delete.setBounds(170 , 125 , 100 ,30);
        delete.setFocusable(false);
        delete.addActionListener(this);
        
        


        
        deleteQFrame.add(deleteLabel);
        deleteQFrame.add(deleteField);
        deleteQFrame.add(option1);
        deleteQFrame.add(option1Field);
        deleteQFrame.add(option2);
        deleteQFrame.add(option2Field);
        deleteQFrame.add(option3);
        deleteQFrame.add(option3Field);
        deleteQFrame.add(option4);
        deleteQFrame.add(option4Field);
        deleteQFrame.add(ans);
        deleteQFrame.add(ansField);
        deleteQFrame.add(delete);
        deleteQFrame.add(confirm);
        
        connDB();
        System.out.println("Connection Established");
        
        deleteQFrame.setVisible(true);
        
       
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == delete)
		{
			delete.setVisible(false);
			deleteLabel.setText("Enter The Question Below : ");
			
			 num = Integer.parseInt(deleteField.getText());
			
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
			System.out.println("Question Fetched From DataBase");
			
			deleteField.setText(question);
			option1Field.setText(op1);
			option2Field.setText(op2);
			option3Field.setText(op3);
			option4Field.setText(op4);
			ansField.setText(answer);
			
			
			
			confirm.setBounds(170 , 350 , 150 ,30);
	        confirm.setFocusable(false);
	        confirm.addActionListener(this);
			confirm.setVisible(true);
		}
		if(e.getSource() == confirm)
		{
			int value = JOptionPane.showConfirmDialog(deleteQFrame, "Are You Sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if(value == 0)
            {
				//num = Integer.parseInt(deleteField.getText());
				
				question = deleteField.getText();
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
				confirm.setVisible(false);
				
				deleteLabel.setText("Enter The questionID : ");
				deleteField.setText("");
				deleteField.setVisible(true);
				delete.setVisible(true);
				
				//new deleteQuestion();
				
				
				 
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
	  }
	public void getQuestions(int number)
    {
	    try{
	    	
	        Statement stm = conn.createStatement();  
	        String sql1  = "SELECT * from exdb.questions where questionID = " + number ;
	        
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
	        
	        
        
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
	    
    }
	public void setQuestions(int number)
    {
	    try{
	    	String sql1  = "select * FROM questions WHERE questionID = " + number ;
	        Statement stm = conn.createStatement();
	        
	        String sql = "DELETE  FROM exdb.questions WHERE questionID = " + number;
	        
	        ResultSet rs = stm.executeQuery(sql1); 
	        
	        stm.executeUpdate(sql);
	        
	        System.out.println("Question deleted ");
	        
	        rs.close();
	        
	        JOptionPane.showMessageDialog(null, "RECORDS UPDATED");
            System.out.println("RECORD UPDATED SUCCESSFULLY");
	       
        
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
	    
    }
}