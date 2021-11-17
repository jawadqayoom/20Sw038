import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class myFrame extends JFrame implements ActionListener ,KeyListener {

    //---------------------panels-----------------

    JPanel player;

    //-------------------------------Labels-----------------------------------------------
    //----> welcome , are you sHarp ? , congrats  , Enter name , for displaying questions
    JLabel wlcm_msg , wlcm_msg1       , wlcm_msg2 , L_uName ,   ques_Labels ;

    //----------textFields-------------------------
    //-------> Username
    JTextField T_uname;
    //--------------/TextFields---------------------

    //------------buttons-------------
    
    //----> Yes , no , next , submit

    JButton b1  , b2 , b3   , b4     , A , B , C , D ;

    //------------/buttons-------------

    //---------------------JMenuBar\Menu\MenuItem-------------------
    
    JMenuBar bar ;
    JMenu admin , help  ;
    JMenuItem addQuestion , editQuestion , delQuestion , exit;

    //----------------------/JMenuBar\Menu\MenuItem------------------

    //------------------Strings---------------
    String question[] = { "Whats your name ?" , "Whats your fathers name ? " , "Example question3 " , "Example Question4"}; 
    String user_name;
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
     editQuestion = new JMenuItem("Edit Questions");
     delQuestion  = new JMenuItem("Delete Questions");
     exit  = new JMenuItem("Exit") ;
     exit.addActionListener(this);

     bar.add(admin);
     bar.add(help);
     admin.add(addQuestion);
     admin.add(editQuestion);
     admin.add(delQuestion);
     admin.add(exit);

    //----------------------/JMenuBar\Menu\MenuItem------------------

        //---------------------LABELS--------------------------------

        wlcm_msg  = new JLabel("HELLO WELCOME TO MY QUIZ ");
        wlcm_msg.setOpaque(true);
        wlcm_msg.setBackground(new Color(100,100,160));
        wlcm_msg.setBounds(50, 20, 600, 50);
        wlcm_msg.setFont(new Font("Serif", Font.PLAIN, 40));

        wlcm_msg1 = new JLabel("Are You Sharp Enough To Get 10\\10 ?");
        wlcm_msg1.setOpaque(true);
        wlcm_msg1.setBounds(100, 150, 500 , 50);
        wlcm_msg1.setFont(new Font("Serif", Font.PLAIN , 30));
        wlcm_msg1.setBackground(new Color(75,120,100));

        wlcm_msg2 = new JLabel("CONGRATULATIONS YOUR QUIZ WILL BEGIN NOW !");
        wlcm_msg2.setOpaque(true);
        wlcm_msg2.setBounds(50, 100, 600 , 200);
        wlcm_msg2.setFont(new Font("Serif", Font.PLAIN , 24));
        wlcm_msg2.setBackground(new Color(75,40,120));
        wlcm_msg2.setVisible(false);

      //---------------------/LABELS--------------------------------


        //---------------------BUTTONS--------------------------------

        b1 = new JButton("YES");
        b1.setFocusable(false);
        b1.setBounds(180, 230, 90, 25);
        b1.setFont(new Font("Comic Sans", Font.BOLD , 14));
        b1.addActionListener(this);

        b2 = new JButton("NO I'M DULL");
        b2.setFocusable(false);
        b2.setBounds(270, 230, 160, 25);
        b2.setFont(new Font("Comic Sans", Font.BOLD , 14));
        b2.addActionListener(this);

        //---------------------/BUTTONS--------------------------------
        

        //---------------------ADD COMPONENTS TO FRAME -------------------------

        this.add(wlcm_msg);
        this.add(wlcm_msg1);
        this.add(wlcm_msg2);
        this.add(b1);
        this.add(b2);
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
            this.setVisible(false);

            wlcm_msg.setVisible(false);
            wlcm_msg1.setVisible(false);
            b1.setVisible(false);
            b2.setVisible(false);
            wlcm_msg2.setVisible(true);

            b3 = new JButton("Next");
            b3.setFocusable(false);
            b3.setBounds(530, 320 , 90, 25);
            b3.addActionListener(this);
            b3.setFont(new Font("Comic Sans", Font.BOLD , 14));
            b3.setForeground(Color.GREEN);
            b3.setBackground(new Color(75,40,120));
            this.add(b3);
            
            this.setVisible(true);

           

        }
        
        
        else if(e.getSource() == b2 || e.getSource()==exit)
        {    
            int value = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if(value == 0)
            {
                System.exit(0);
            }
            
        }


        if(e.getSource() == b3)
        {
            wlcm_msg2.setVisible(false);
            b3.setVisible(false);

            L_uName = new JLabel("Enter your beautiful name below :) ");
            L_uName.setOpaque(true);
            L_uName.setBounds(50, 100, 400 , 150);
            L_uName.setFont(new Font("Comic Sans", Font.PLAIN , 24));
            L_uName.setBackground(new Color(75,120,100));
            L_uName.setVisible(true);
    
            T_uname = new JTextField();
            T_uname.setBounds(50, 280, 200 , 30);
            T_uname.setBackground(Color.DARK_GRAY);
            T_uname.setForeground(Color.WHITE);
            T_uname.addKeyListener(this);
            T_uname.setFont(new Font("consolas" , Font.BOLD , 14));
            T_uname.setVisible(true);

            b4 = new JButton("SUBMIT");
            b4.setFocusable(false);
            b4.setBounds(100, 280 , 90, 25);
            b4.addActionListener(this);
            b4.setFont(new Font("Comic Sans", Font.BOLD , 14));
            b4.setVisible(true);
    
            this.add(L_uName);
            this.add(T_uname);
            this.add(b4);

            this.setVisible(true);

        }

        if(e.getSource() == b4 )
        {
            L_uName.setVisible(false);
            T_uname.setVisible(false);
            b4.setVisible(false);
            user_name = T_uname.getText();
            
            //genQuestion(question, this);

        }
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
            L_uName.setVisible(false);
            T_uname.setVisible(false);
            b4.setVisible(false);
            user_name = T_uname.getText();
            
            genQuestion(question, e);

        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void genQuestion(String ques[] , KeyEvent e )
    {
        
            
            int i =0;
            ques_Labels = new JLabel();
            ques_Labels.setText(ques[i]);
            ques_Labels.setOpaque(true);
            ques_Labels.setBounds(50, 100, 400 , 150);
            ques_Labels.setFont(new Font("Comic Sans", Font.PLAIN , 24));
            ques_Labels.setBackground(new Color(75,120,100));
            ques_Labels.setVisible(true);

            this.add(ques_Labels);


    } 

    public void connDB()
    {
          
               try{
              Class.forName("com.mysql.cj.jdbc.Driver");
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/?user=root", "root", "root" );
              
  
              }
             catch(Exception e){
  
  
              e.printStackTrace();
  
             }
  }


}