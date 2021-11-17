import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JTable;
import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

public class myFrame extends JFrame implements ActionListener ,KeyListener {

    
	private static final long serialVersionUID = 1L;
	
	JPanel content;
	
    //-------------------------------Labels-----------------------------------------------
    //----> welcome , are you sHarp ? , congrats  , Enter name , for displaying questions
    JLabel wlcm_msg , wlcm_msg1       , wlcm_msg2 , L_uName ,   ques_Label  ;
    
    JLabel   info1 , info2 ,info3 , info4 , info5;

    //----------textFields-------------------------
    //-------> Username
    JTextField T_uname;
    //--------------/TextFields---------------------
    
    //------------buttons-------------
    
    //----> Yes , no , next , submit , op1 , op2 , op3 , op4 , start  , submit(username) ,playagain

    JButton b1  , b2 , b3   , b4     , A   , B   , C   , D   , button , Submit           , playAgain , leaderBoard;

    //------------/buttons-------------

    //---------------------JMenuBar\Menu\MenuItem-------------------
    
    JMenuBar bar ;
    JMenu admin , help  ;
    JMenuItem addQuestion , editQuestion , delQuestion , exit;

    //----------------------/JMenuBar\Menu\MenuItem------------------

    //------------------Strings---------------
    int quesNum = 0 ;
    
    int score = 0 ;
    int counter=0;
    String user_name , randQuestion[] , options[][];
    boolean timeup = false;
    String LButtonText[] = {"leaderboard" , "Info"};
    leaderboard l1 , L ;
    
    //--------------------/Strings---------------------- 

    //---------------JDBC-----------------------------------
    Connection conn;
    //---------------/JDBC-----------------------------------
    

    myFrame()
    {
        //--------------frame------------------------------

        this.setSize(700,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocation(300, 25);
        this.getContentPane().setBackground(new Color(123,50,180));

        //---------------------JMenuBar\Menu\MenuItem-------------------
    
     bar   = new JMenuBar() ;
     
     admin = new JMenu("Admin");
     
     help  = new JMenu("Help");
     
     
     

     addQuestion  = new JMenuItem("Add Questioins");
     addQuestion.addActionListener(this);
     
     editQuestion = new JMenuItem("Edit Questions");
     editQuestion.addActionListener(this);
     
     delQuestion  = new JMenuItem("Delete Questions");
     delQuestion.addActionListener(this);
     exit  = new JMenuItem("Exit") ;
     exit.addActionListener(this);

     bar.add(admin);
     bar.add(help);
     admin.add(addQuestion);
     admin.add(editQuestion);
     admin.add(delQuestion);
     admin.add(exit);

    //----------------------/JMenuBar\Menu\MenuItem------------------

     
     //-----------Panels------------------------------
     
       content = new JPanel();
       content.setBackground(new Color(100,100,160));
       content.setBounds(10,10,400 ,420);
       content.setLayout(null);
       content.setVisible(false);
       content.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "INFORMATION ABOUT PROJECT", TitledBorder.CENTER, TitledBorder.TOP));
       
       l1 = new leaderboard();
       
     //------------------/Panels------------------------
       
       
        //---------------------LABELS--------------------------------
       
//        info = new JLabel("-----INFORMATION REGARDING THIS PROJECT-----");
//        info.setO(true); 
//        info.setBackground(new Color(100,100,160));
//        info.setBounds(10, 10, 380 , 50);
//        info.setForeground(Color.CYAN);
//        info.setFont(new Font("Comic Sans", Font.BOLD, 12));paque
       
        info1 = new JLabel("<html>This fully customizeable Quiz has been developed by <html>");
        info1.setOpaque(true);
        info1.setBackground(new Color(100,100,160));
        info1.setBounds(10, 70 , 380, 50);
        info1.setForeground(Color.WHITE);
        info1.setFont(new Font("Comic Sans", Font.BOLD, 16));
        
        info2 = new JLabel("<html>Jawad Qayoom Roll # 20SW038 <html>");
        info2.setOpaque(true);
        info2.setBackground(new Color(100,100,160));
        info2.setBounds(10, 130 , 380, 50);
        info2.setForeground(Color.WHITE);
        info2.setFont(new Font("Comic Sans", Font.BOLD, 18));
        
        info3 = new JLabel("<html>The whole game idea is purely mine and code also .Although there are some patches Which are taken from internet like algorithm for unique random numbers(stackoverflow) and timer class (youtube\\\\brocode) </html>");
        info3.setOpaque(true);
        info3.setBackground(new Color(100,100,160));
        info3.setBounds(10, 180 , 380, 150);
        info3.setForeground(Color.WHITE);
        info3.setFont(new Font("Comic Sans", Font.BOLD, 16));
        
//        info4 = new JLabel("");
//        info4.setOpaque(true);
//        info4.setBackground(new Color(100,100,160));
//        info4.setBounds(10, 240 , 380, 50);
//        info4.setForeground(Color.WHITE);
//        info4.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        
//        info5 = new JLabel("");
//        info5.setOpaque(true);
//        info5.setBackground(new Color(100,100,160));
//        info5.setBounds(10, 300 , 380, 50);
//        info5.setForeground(Color.WHITE);
//        info5.setFont(new Font("Comic Sans", Font.BOLD, 12));
//        
        //content.add(info);
        content.add(info1);
        content.add(info2);
        content.add(info3);
        //content.add(info4);
        //content.add(info5);
       
        wlcm_msg  = new JLabel("HELLO WELCOME TO MY QUIZ ");
        wlcm_msg.setOpaque(true);
        wlcm_msg.setBackground(new Color(123,50,180));
        wlcm_msg.setBounds(50, 20, 600, 50);
        wlcm_msg.setFont(new Font("Serif", Font.PLAIN, 40));

        wlcm_msg1 = new JLabel("Are You Sharp Enough To Get 10\\10 ?");
        wlcm_msg1.setOpaque(true);
        wlcm_msg1.setBounds(100, 150, 500 , 50);
        wlcm_msg1.setFont(new Font("Serif", Font.PLAIN , 30));
        wlcm_msg1.setBackground(new Color(123,50,180));

        wlcm_msg2 = new JLabel("CONGRATULATIONS YOUR QUIZ WILL BEGIN NOW !");
        wlcm_msg2.setOpaque(true);
        wlcm_msg2.setBounds(50, 100, 600 , 200);
        wlcm_msg2.setFont(new Font("Serif", Font.PLAIN , 24));
        wlcm_msg2.setBackground(new Color(75,40,120));
        wlcm_msg2.setVisible(false);
        wlcm_msg2.setForeground(Color.yellow);
        
        ques_Label = new JLabel("Empty");
        ques_Label.setBounds(100, 40, 500 , 100);
        ques_Label.setHorizontalAlignment(JLabel.LEFT);
        ques_Label.setVerticalAlignment(JLabel.CENTER);
        ques_Label.setOpaque(true);
        ques_Label.setForeground(Color.YELLOW);
        ques_Label.setFont(new Font("Comic Sans", Font.BOLD , 14));
        ques_Label.setBackground(new Color(123,50,180));
        ques_Label.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "QUESTIONS", TitledBorder.CENTER, TitledBorder.TOP));
        ques_Label.setVisible(false);

      //---------------------/LABELS--------------------------------


        //---------------------BUTTONS--------------------------------

        b1 = new JButton("YES");
        b1.setFocusable(false);
        b1.setBounds(180, 230, 90, 25);
        b1.addActionListener(this);
        b1.setFont(new Font("Comic Sans", Font.BOLD , 14));
        b1.setForeground(Color.GREEN);
        b1.setBackground(new Color(75,40,120));

        b2 = new JButton("NO I'M DULL");
        b2.setFocusable(false);
        b2.setBounds(290, 230, 160, 25);
        b2.addActionListener(this);
        b2.setFont(new Font("Comic Sans", Font.BOLD , 14));
        b2.setForeground(Color.GREEN);
        b2.setBackground(new Color(75,40,120));
       // b2.setVisible(false);
        
        
        b3 = new JButton("Next");
        b3.setFocusable(false);
        b3.setBounds(530, 320 , 90, 25);
        b3.addActionListener(this);
        b3.setFont(new Font("Comic Sans", Font.BOLD , 14));
        b3.setForeground(Color.GREEN);
        b3.setBackground(new Color(75,40,120));
        b3.setVisible(false);


        button = new JButton("Start");
        button.setFocusable(false);
        button.setBounds(500, 150 , 140, 25);
        button.addActionListener(this);
        button.setFont(new Font("Comic Sans", Font.BOLD , 20));
        button.setForeground(Color.GREEN);
        button.setBackground(new Color(75,40,120));
        button.setVisible(false);
        
        A = new JButton("Ok");
        A.setFocusable(false);
        A.setBounds(100, 220 , 200 , 30);
        A.addActionListener(this);
        A.setFont(new Font("Comic Sans", Font.BOLD , 14));
        A.setForeground(Color.GREEN);
        A.setBackground(new Color(75,40,120));
        A.setVisible(false);
        
        B = new JButton("Ok");
        B.setFocusable(false);
        B.setBounds(100, 280, 200 , 30);
        B.addActionListener(this);
        B.setFont(new Font("Comic Sans", Font.BOLD , 14));
        B.setForeground(Color.GREEN);
        B.setBackground(new Color(75,40,120));
        B.setVisible(false);
        
        C = new JButton("Ok");
        C.setFocusable(false);
        C.setBounds(100, 340, 200 , 30);
        C.addActionListener(this);
        C.setFont(new Font("Comic Sans", Font.BOLD , 14));
        C.setForeground(Color.GREEN);
        C.setBackground(new Color(75,40,120));
        C.setVisible(false);
        
        D = new JButton("ok");
        D.setFocusable(false);
        D.setBounds(100, 400, 200 , 30);
        D.addActionListener(this);
        D.setFont(new Font("Comic Sans", Font.BOLD , 14));
        D.setForeground(Color.GREEN);
        D.setBackground(new Color(75,40,120));
        D.setVisible(false);
        
        //new leaderboard();
        
        playAgain = new JButton("Play Again");
        playAgain.setFocusable(false);
        playAgain.setBounds(530, 320 , 130, 25);
        playAgain.addActionListener(this);
        playAgain.setFont(new Font("Comic Sans", Font.BOLD , 14));
        playAgain.setForeground(Color.GREEN);
        playAgain.setBackground(new Color(75,40,120));
        playAgain.setEnabled(false);
        playAgain.setVisible(false);
        
        leaderBoard = new JButton();
        leaderBoard.setText(LButtonText[0]);
        leaderBoard.setFocusable(false);
		leaderBoard.setBounds(480, 200 , 180, 30);
		leaderBoard.addActionListener(this);
		leaderBoard.setFont(new Font("Comic Sans", Font.BOLD , 20));
		leaderBoard.setForeground(Color.WHITE);
		leaderBoard.setBackground(new Color(75,40,120));
		leaderBoard.setVisible(false);
		
        //---------------------/BUTTONS--------------------------------
        
        
        

        //---------------------ADD COMPONENTS TO FRAME -------------------------

        this.add(wlcm_msg);
        this.add(wlcm_msg1);
        this.add(wlcm_msg2);
        this.add(content);
        this.add(l1);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(A);
        this.add(B);
        this.add(C);
        this.add(D);
        this.add(button);
        this.add(playAgain);
        this.add(leaderBoard);
        //this.add(leaderboard.table);
        //this.add(QuestionTimer.resetButton);
        this.add(QuestionTimer.timeLabel);
        this.setJMenuBar(bar);
        
        //------------------------/ADD COMPONENTS TO FRAME--------------------------------------
        
        this.setVisible(true);
        //----------------/frame-----------------------

        //-----------------------------DATABASE_WORK---------------------
        connDB();
        //-----------------------------/DATABASE_WORK--------------------

    }//CONSTRUCTOR ENDS 

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            
//        	this.setVisible(false);
        	
            wlcm_msg.setVisible(false);
            wlcm_msg1.setVisible(false);
            b1.setVisible(false);
            b2.setVisible(false);
            wlcm_msg2.setVisible(true);
            b3.setVisible(true);
            
            
            this.setVisible(true);

           

        }
        
        
        else if(e.getSource() == b2 || e.getSource()==exit)
        {    
            int value = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if(value == 0)
            {
            	System.out.println(" Program ended ! ");
                System.exit(0);
                
            }
            
        }


        if(e.getSource() == b3)
        {
            wlcm_msg2.setVisible(false);
            b3.setVisible(false);
            playAgain.setVisible(false);
            
            L_uName = new JLabel("Enter your beautiful name below :) ");
            L_uName.setOpaque(true);
            L_uName.setBounds(150, 100, 400 , 150);
            L_uName.setFont(new Font("Comic Sans", Font.PLAIN , 24));
            L_uName.setBackground(new Color(75 , 40, 120));
            L_uName.setForeground(Color.white);
            L_uName.setVisible(true);
    
            T_uname = new JTextField();
            T_uname.setBounds(230, 260, 200 , 30);
            T_uname.setBackground(Color.DARK_GRAY);
            T_uname.setForeground(Color.WHITE);
            T_uname.addKeyListener(this);
            T_uname.setFont(new Font("consolas" , Font.BOLD , 14));
            T_uname.setVisible(true);

            b4 = new JButton("SUBMIT");
            b4.setFocusable(false);
            b4.setBounds(280, 300 , 90, 25);
            b4.addActionListener(this);
            b4.setFont(new Font("Comic Sans", Font.BOLD , 14));
            b4.setBackground(new Color(75,40,120));
            b4.setForeground(Color.WHITE);
            b4.setVisible(true);
    
            this.add(L_uName);
            this.add(T_uname);
            this.add(b4);
            this.add(ques_Label);
            

            this.setVisible(true);
            
        }
        
        //Generates question
        if(e.getSource() == b4 )
        { 
        	user_name = T_uname.getText(); 
        	L_uName.setVisible(false);
        	T_uname.setVisible(false);
        	b4.setVisible(false);
        	ques_Label.setVisible(false);
        	
        	System.out.println("All previous conponents are hidden ...");
            
            new randQuestionGenerate();
            System.out.println("Array size : " + randQuestionGenerate.question.length);
            randQuestion = new String[randQuestionGenerate.question.length];
            options = new String[randQuestionGenerate.question.length][5];
            
            for(int i = 0; i < randQuestionGenerate.question.length ; i++  )
        	{
        		randQuestion[i] = randQuestionGenerate.question[i];
        		for(int j =0 ; j <5 ; j++)
        		{
        			options[i][j] = randQuestionGenerate.options[i][j];
        		}
        	}
            
        new QuestionTimer();
        button.setVisible(true);
        content.setVisible(true);
        //-----button-------
        leaderBoard.setVisible(true);
        
       
                
        }
        if(e.getSource() == leaderBoard)
		{
        	if(leaderBoard.getText().equals(LButtonText[0]))
        	{
        		leaderBoard.setText(LButtonText[1]);
        		content.setVisible(false);
            	l1.setVisible(true);
        	}
        	else if (leaderBoard.getText().equals(LButtonText[1]))
        	{
        		leaderBoard.setText(LButtonText[0]);
        		content.setVisible(true);
            	l1.setVisible(false);
        	}
		}
        
        if(e.getSource() == button)
        {
        	 
        	 content.setVisible(false);
        	 l1.setVisible(false);
        	 leaderBoard.setVisible(false);
        	 button.setText("Next");
        	 button.setBounds(530, 320 , 90, 25);
        	 button.setFont(new Font("Comic Sans", Font.BOLD , 14));
        	 
        	 ques_Label.setVisible(true);
        	 QuestionTimer.timeLabel.setVisible(true);
        	 playAgain.setVisible(false);
        	 button.setVisible(true);
        	
        	 if(counter <= 10)
        	 {
        		 int opt = 0;
                 ques_Label.setText(randQuestion[counter]);
                 System.out.println("Question number : " + counter + " is " +randQuestion[counter]);
                 
                 
                 A.setText(options[counter][opt]);
                 System.out.println("Question number : " + counter + "'s option1 is " +A.getText());
                 opt++;
                 
                 B.setText(options[counter][opt]);
                 System.out.println("Question number : " + counter + "'s option2 is " +B.getText());
                 opt++;
                 
                 C.setText(options[counter][opt]);
                 System.out.println("Question number : " + counter + "'s option3 is " +C.getText());
                 opt++;
                 
                 D.setText(options[counter][opt]);
                 System.out.println("Question number : " + counter + "'s option4 is " +D.getText());
                 opt++;
                 
             	 System.out.println("Question number : " + counter + "'s answer is " +options[counter][4]);
             	
             	 counter++;
                
                 A.setVisible(true);
                 B.setVisible(true);
                 C.setVisible(true);
                 D.setVisible(true);
                 
        	 }
        	
            if(counter > 10)
            {
            	b2.setText("Exit");
            	b2.setVisible(true);
            	b2.setBounds(350, 320 , 130, 25);
            	playAgain.setEnabled(true);
            	
            	System.out.println("index is greater than 10 !!! ");
            	
            	ques_Label.setVisible(false);
            	button.setVisible(false);
            	A.setVisible(false);
            	B.setVisible(false);
            	C.setVisible(false);
            	D.setVisible(false);
            	playAgain.setVisible(true);
            	
            	wlcm_msg2.setText("CONGRATULATIONS You Have Scored " + score + "/10");
             	wlcm_msg2.setVisible(true);
            	
             	leaderboard.setScore(user_name , score);
            	//System.out.println("Question number : " + counter + "is " +randQuestion[counter]);
            	//System.out.println("Question number : " + counter + "'s ans is " +options[counter][4]);
            	//System.out.println("Question number : " + counter + "'s option1 is " +options[counter][4]);
            	QuestionTimer.reset(); 
            	
            }
            else
            {
            	 if(!QuestionTimer.stoped)
            	   {
            		 QuestionTimer.start();
            	   }
            	 if(QuestionTimer.stoped)
                {
            		 b2.setText("Exit");
                 	 b2.setVisible(true);
                 	 b2.setBounds(350, 320 , 130, 25);
            		 playAgain.setEnabled(true);
                	 System.out.println("Time's Up !! ");
                	 
                	ques_Label.setVisible(false);
                	button.setVisible(false);
                	A.setVisible(false);
                	B.setVisible(false);
                	C.setVisible(false);
                	D.setVisible(false);
                	
                	wlcm_msg2.setText("Time's Up! You Have Scored " + score + "/10");
                 	wlcm_msg2.setVisible(true);
                	
                 	
                	//System.out.println("Question number : " + counter + "is " +randQuestion[counter]);
                	//System.out.println("Question number : " + counter + "'s ans is " +options[counter][4]);
                	//System.out.println("Question number : " + counter + "'s option1 is " +options[counter][4]);
                	A.setEnabled(false);
                 	B.setEnabled(false);
                 	C.setEnabled(false);
                 	D.setEnabled(false);
                	QuestionTimer.stop();
                	b2.setText("Exit");
                	b2.setVisible(true);
                	playAgain.setVisible(true);
                	
                }
            	 
                System.out.println(QuestionTimer.stoped);
                A.setBackground(new Color(75,40,120));
                B.setBackground(new Color(75,40,120));
                C.setBackground(new Color(75,40,120));
                D.setBackground(new Color(75,40,120));
                A.setEnabled(true);
            	B.setEnabled(true);
            	C.setEnabled(true);
            	D.setEnabled(true);
                //this.add(button);
                
                System.out.println("The value of counter is : " + counter);
                
            }            
        }       

        if( e.getSource() == A )
        {
        	
        		System.out.println();
        		System.out.println("You pressed A ");
        		
        	if(A.getText().replaceAll("\0", "").equalsIgnoreCase(options[counter][4]))
        	{
        		A.setBackground(Color.GREEN);
        		System.out.println("Correct answer");
        		score++;
        		
        	}
        	else
        	{

        		System.out.println();
        		System.out.println("Wrong answer");
        	}
            A.setEnabled(false);
        	B.setEnabled(false);
        	C.setEnabled(false);
        	D.setEnabled(false);
        	

        }
        else if(e.getSource() == B)
        { 
        	System.out.println("You pressed B ");
        	if(B.getText().replaceAll("\0", "").equals(options[counter][4]))
        	{
        		B.setBackground(Color.GREEN);
        		System.out.println("Correct answer");
        		score++;
        		
        	}
        	else
        	{

        		System.out.println();
        		System.out.println("Wrong answer");
        	}
        	A.setEnabled(false);
        	B.setEnabled(false);
        	C.setEnabled(false);
        	D.setEnabled(false);
        	

        }
        else if(e.getSource() == C)
        {
        	System.out.println("You pressed C ");
        	if(C.getText().replaceAll("\0", "").equalsIgnoreCase(options[counter][4]))
        	{
        		C.setBackground(Color.GREEN);
        		System.out.println("Correct answer");
        		score++;
        		
        	}
        	else
        	{

        		System.out.println();
        		System.out.println("Wrong answer");
        	}
            A.setEnabled(false);
        	B.setEnabled(false);
        	C.setEnabled(false);
        	D.setEnabled(false);
        
        }
        else if(e.getSource() == D)
        {
        	System.out.println("You pressed D ");
        	if(D.getText().replaceAll("\0", "").equalsIgnoreCase(options[counter][4]))
        	{
        		D.setBackground(Color.GREEN);
        		System.out.println("Correct answer");
        		score++;
        	}
        	else
        	{
        		
        		System.out.println();
        		System.out.println("Wrong answer");
        	}
            A.setEnabled(false);
        	B.setEnabled(false);
        	C.setEnabled(false);
        	D.setEnabled(false); 
        	
        }
        
        if(e.getSource() == playAgain)
        {
        	//this.remove(leaderBoard);
        	button.setText("Start");
       	 	button.setBounds(500, 150 , 140, 25);
       	 	button.setFont(new Font("Comic Sans", Font.BOLD , 20));
        	b2.setVisible(false);
        	wlcm_msg2.setVisible(false);
        	counter = 0;
        	score   = 0;
        	b3.doClick();
        }
        

		
        
        //-------------------MENUBAR LISTENER---------------------
        if(e.getSource()== addQuestion)
        {
            new addQuestion();
        }
        if(e.getSource()== editQuestion)
        {
            new editQuestion();
        }
        if(e.getSource()== delQuestion)
        {
            new deleteQuestion();
        }
        //----------------------/MENUBAR LISTENER-----------------------------
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        

    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == 10)
        {
        	content.setVisible(true);
        	leaderBoard.setVisible(true);
        	user_name = T_uname.getText(); 
        	L_uName.setVisible(false);
        	T_uname.setVisible(false);
        	b4.setVisible(false);
        	ques_Label.setVisible(false);
    	
        		System.out.println("All previous conponents are hidden ...");
        
        		new randQuestionGenerate();
        		System.out.println("Array size : " + randQuestionGenerate.question.length);
        		randQuestion = new String[randQuestionGenerate.question.length];
        		options = new String[randQuestionGenerate.question.length][5];
        
        		for(int i = 0; i < randQuestionGenerate.question.length ; i++  )
        		{
        			randQuestion[i] = randQuestionGenerate.question[i];
        			for(int j =0 ; j <5 ; j++)
        			{
        				options[i][j] = randQuestionGenerate.options[i][j];
        			}
        		}
        
        			new QuestionTimer();
        			button.setVisible(true);          
        }
             
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
    	
    }

    
    public void connDB()
    {
               try{
            	   Class.forName("com.mysql.cj.jdbc.Driver");
   	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/exdb" , "root" , "root"  ); 
               	  }
             catch(Exception e){  e.printStackTrace(); }
  } 
    
}
