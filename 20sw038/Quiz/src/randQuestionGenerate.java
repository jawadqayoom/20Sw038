import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
//import javax.swing.JLabel;


public  class randQuestionGenerate  {

	private static int length = 20;
	private static Integer[] WORan = new Integer[length];
    private static Integer[] RanNum = new Integer[length];
    private static Integer[] rand ;
    public static String[][] options;
    public static String[] question ;
    Connection conn;
    Statement stm;
    
    
    
    
     //--------------constructor--------------------
    randQuestionGenerate()
    {
    	connDB();
    	setQuestion();
    	
    }
    
    //-------------------------------------------------
    
    
    
    
    //->functions defined
    
    //Generates rand numbers and stores in array
	 private  void Ran()
	    {

	        int num;

	        Random rand = new Random();

	        for(int i = 0; i < RanNum.length;  i++ ){

	            num = rand.nextInt(20);
	            RanNum[i] = num;
	            //System.out.println(RanNum[i]);
	        }
	        System.out.println("Random number generating...");
	    }
	//generates the randomnumber array with unique elements and returns the array's length
		 private  void CheckRanLen()
		 {
			 Ran();
			 
			 LinkedHashSet<Integer> linkedhastset = new LinkedHashSet<>(Arrays.asList(RanNum));

	         WORan = linkedhastset.toArray(new Integer[] {});

			 
		 }
    //retruns the randomnumber array with unique elements
	 private  void randNumber(){
	        
	       //System.out.println("_________________________________- Free of Duplicates-__________________________________");
		
		 CheckRanLen();
		 rand = new Integer[WORan.length];
		 question = new String[WORan.length];
		 options = new String[WORan.length][5];
		 
	       for(int i = 0; i < WORan.length; i ++)
	       {
	    	   rand[i] = WORan[i];
	               
	       }
	       System.out.println("Random number generated");
	       
	    }
	 
	 //connects the database
	 public void connDB()
	    {
		 System.out.println("connecting to database ...");
	               try{
	            	   Class.forName("com.mysql.cj.jdbc.Driver");
	   	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/exdb" , "root" , "root"  ); 
	   	            
	               	  }
	             catch(Exception e){  e.printStackTrace(); }
	               System.out.println("Database Connection was succesful ...");
	  }
	 
	 
	 //returns a random question fetched from database
	    private  void fetchQuestion(int quesid)
	    {	
	    	
	    	 try{
	    		 stm = conn.createStatement();
	 	        String sql1  = "select * from exdb.questions where questionID = " + rand[quesid];
	 	        ResultSet rs = stm.executeQuery(sql1);	 	        
	 	       System.out.println("fetching question...");
	 	        if(quesid == 0)
	 	        {
	 	        	rs.next();
			        question[quesid]   = rs.getString(1);
			        //answer             = rs.getString(2);
			        options[quesid][0] = rs.getString(3);
			        options[quesid][1] = rs.getString(4);
			        options[quesid][2] = rs.getString(5);
			        options[quesid][3] = rs.getString(6);
			        options[quesid][4] = rs.getString(2);
	 	        }
	 	        else if (rs.next())
	 	        {
	 		        question[quesid]   = rs.getString(1);
			        //answer             = rs.getString(2);
			        options[quesid][0] = rs.getString(3);
			        options[quesid][1] = rs.getString(4);
			        options[quesid][2] = rs.getString(5);
			        options[quesid][3] = rs.getString(6);
			        options[quesid][4] = rs.getString(2);
	 	        }
	
	 	       }
	 	    
	 	    catch(SQLException ae) { ae.printStackTrace();  }
	    	 System.out.println("Question fetched ! ");
	    }	   
	    //stores random questions in an array
	    public  void setQuestion()
	    {
	    	randNumber();
	   	    for(int i = 0 ; i < rand.length ; i++)
	   	    {
	   	    	fetchQuestion(i);
    	     }
	   	 System.out.println("Questions ans  are ready :)");
	       
	    }
}