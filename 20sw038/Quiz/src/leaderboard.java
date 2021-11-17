import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
//import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class leaderboard extends JPanel  {

	private static final long serialVersionUID = 1L;

	public static String[] user_name ;
	public static Integer[] score ;
	public static String[][] data ;
	public static Integer[] rank ;
	public static DefaultTableModel model;
	public static String[] columns = { " Rank "," Name " , " Score "};
	public static JTable table;
	
	
	private static JScrollPane jps;
	private static Connection conn;
	private static int total = 0;
	
	leaderboard()
	{
		super(new GridLayout(1,0));
		connDB();
		rank       = new Integer[getTotalUser()];
		data = new String[rank.length][3];
		user_name  = new String[getTotalUser()];
		score      = new Integer[getTotalUser()];
		getScore();
		
	       this.setBackground(new Color(100,100,160));
	       this.setBounds(10,10,400 ,420);
	       //this.setLayout(null);
	       this.setVisible(false);
	       this.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Leader Board", TitledBorder.CENTER, TitledBorder.TOP));
	       
		
	       model = new DefaultTableModel(data, columns);
		   table = new JTable(model);
//				{
//					private static final long serialVersionUID = 1L;
//
//					public boolean isCellEditable(int data , int columns)
//					{
//						return false;
//					}
//				};
				
		table.setPreferredScrollableViewportSize(new Dimension(300,300));
		table.setFillsViewportHeight(true);
		table.setBackground(Color.GRAY);
		
		jps = new JScrollPane(table);
		//jps.setBounds(20, 20, 300, 300);
		add(jps);
		
	System.out.println("Table has been created .!");
//		
//		for(int i = 0 ; i < data.length ; i++ )
//		{
//			System.out.println();
//		 	System.out.print("Rank is : " + data[i][0]);
//		 	System.out.print("\tUser name = " + data[i][1]);
//		 	System.out.print("\tscore  = " + data[i][2]);
//		}
		
	}
	
	public void connDB()
    {
          
               try{
            	   Class.forName("com.mysql.cj.jdbc.Driver");
   	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/exdb" , "root" , "root"  ); 
               	  }
             catch(Exception e){  e.printStackTrace(); }
  }
	
	public static void setScore(String userName , int score)
    {
	    try{
	    	
	        Statement stm = conn.createStatement(); 
	        String sql   = "INSERT INTO  leaderboard VALUES ('"+userName +"', "+ score + " , null);";
	        stm.executeUpdate(sql); 
	        System.out.println("Score set in leaderboard");
	   
	        
	       }
	    
	    catch(SQLException ae) { ae.printStackTrace();  }
    }
	
	public static void getScore()
	{
		Statement stm;
		try {
			stm = conn.createStatement();
			String sql = "select score from exdb.leaderboard ;";
			ResultSet rs = stm.executeQuery(sql);
			int x=0;
			while(rs.next())
			{
					score[x]    = rs.getInt(1);
					x++;
			}
			
			int temp;
			for(int i=0 ;i <score.length ; i++)
			{		
				for(int j=i+1 ; j<=score.length - 1 ; j++)
				{
					if(score[i]<score[j])
					{
						temp     = score[i];
						score[i] = score[j];
						score[j] = temp;
					}
					if(score[i] == score[j])
					{
						score[i] = score[j];
					}
				}
			}
			ResultSet rs1;
			int y=0;
			
			for(int i = 0 ; i < score.length - 1  ; i++)
			{
//				System.out.println("i is "+i);	
//				System.out.println("score at index :"+i+" = " + score[i]);	
				String sql1 = "select username from exdb.leaderboard where score = " + score[i] + ";";
				rs1 = stm.executeQuery(sql1);
				//rs1.next();
				
				if(rs1.next() )
				{
//					System.out.println("value of y is "+y);
					user_name[y]    = rs1.getString(1);
//					System.out.println("user name at index : "+y+" = "+user_name[y]);
					y++;
					if(score[i+1] < score.length)
					{
						if(score[i] == score[i+1])
						{
							while(rs1.next())
							{
//								System.out.println("value of y is "+y);
								user_name[y]  = rs1.getString(1);
//								System.out.println("user name at index : "+y+" = "+user_name[y]);
								y++;
								i++;
							}
							
						}
					}
									
				}
			
				
			}
			
			for(int i = 0 ; i < data.length; i++)
			{
				rank[i] = i+1;
				data[i][0] = String.valueOf(rank[i]);
				data[i][1] = user_name[i];
				data[i][2] = String.valueOf(score[i]);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getTotalUser()
	{
		Statement stm;
		try {
			stm = conn.createStatement();
			String sql = "select userID from exdb.leaderboard order by userID desc;";
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			total = rs.getInt(1);
			
			//for(int i = 0 ; i < )
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}
	

}
