import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuestionTimer {
 

  public static JButton startButton = new JButton("START");
  public static JButton resetButton = new JButton("RESET");
  public static JLabel timeLabel = new JLabel();
  
  private static  int elapsedTime = 300000;
  public  static  int seconds = 0;
  public  static  int minutes = 0;
  private static  int hours  =  0;
  public  static boolean stoped = false;
  private static String seconds_string = String.format("%02d", seconds);
  private static String minutes_string = String.format("%02d", minutes);
  private static String hours_string = String.format("%02d", hours);
 
  private static Timer timer = new Timer(1000, new ActionListener() {
  
  public void actionPerformed(ActionEvent e) {
	
   elapsedTime=elapsedTime-1000;
   hours = (elapsedTime/3600000);
   minutes = (elapsedTime/60000) % 60;
   seconds = (elapsedTime/1000) % 60;
   seconds_string = String.format("%02d", seconds);
   minutes_string = String.format("%02d", minutes);
   hours_string = String.format("%02d", hours);
   if(minutes == 0 && seconds == 0)
   {
	   timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	   stop();
   }
   timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
  
   
  }
  
 });
 
 
 QuestionTimer(){
  
  timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
  timeLabel.setBounds(400, 10, 100 , 25);
  timeLabel.setFont(new Font("Verdana",Font.PLAIN,13));
  timeLabel.setBorder(BorderFactory.createBevelBorder(1));
  timeLabel.setOpaque(true);
  timeLabel.setVisible(false);
  timeLabel.setBackground(new Color(100,100,160));
  timeLabel.setForeground(Color.WHITE);
  timeLabel.setHorizontalAlignment(JTextField.CENTER);
  
  startButton.setBounds(100,200,100,50);
  startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
  startButton.setFocusable(false);
  startButton.setVisible(false);
  //startButton.addActionListener(this);
  
  resetButton.setBounds(200,200,100,50);
  resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
  resetButton.setFocusable(false);
  resetButton.setVisible(false);
  //resetButton.addActionListener(this);
 }
 
 public static void start() {
		 timer.start();
	 
  
 }
 
 public static void stop() {
  stoped=true;
  timer.stop();
 }
 
 
 public static void reset() {
  timer.stop();
  elapsedTime=300000;
  seconds =0;
  minutes=0;
  hours=0;
  seconds_string = String.format("%02d", seconds);
  minutes_string = String.format("%02d", minutes);
  hours_string = String.format("%02d", hours);       timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
 }

}
