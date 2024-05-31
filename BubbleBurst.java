import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import static javax.swing.JOptionPane.*;

class FirstGUI extends JFrame implements ActionListener
{
  int hardness;
  private JButton b1,b2;
  private JSlider jsd;
  private JPanel p1,p2;
  private JRadioButton rb1,rb2,rb3;
  private ButtonGroup bg;
  private  JScrollPane jsp;
  private JSlider jsb;
  Container pane; 
  private Border br,br1;
  int round=0;
  public FirstGUI()
  {
    pane=getContentPane();
     p1=new JPanel();
     p1.setLayout(new FlowLayout());
     b1=new JButton("Start");
     b2=new JButton("Restart");
     p1.add(b1);
     p1.add(b2);
     
     p2=new JPanel();
     //p2.setLayout(new GridLayout(4,1));
     p2.setLayout(new FlowLayout());
     //p2.setBackground(Color.blue);
     p2.setSize(200,200);
     rb1=new JRadioButton("Easy",true);
     
     rb2=new JRadioButton("Medium");
     rb3=new JRadioButton("Hard");
     bg=new ButtonGroup();
     bg.add(rb1);
     bg.add(rb2);
     bg.add(rb3);
     jsb = new JSlider(JSlider.HORIZONTAL, 4, 6, 4); 
     jsb.setMajorTickSpacing(1); 
     jsb.setPaintTrack(true);
     jsb.setPaintTicks(true);
     jsb.setPaintLabels(true);
     p2.add(jsb);
     //p2.add(rb1);
     //p2.add(rb2);
     //p2.add(rb3);
    
int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
jsp=new JScrollPane(p2,v,h);

br=BorderFactory.createMatteBorder(3,3,3, 3,Color.red); 
br1=BorderFactory.createTitledBorder(br,"Select the Difficulty");
p2.setBorder(br1);

  pane.setLayout(new BorderLayout(20,20));
  pane.add(p1,BorderLayout.NORTH);
  pane.add(jsp,BorderLayout.SOUTH);
  b1.addActionListener(this);
  b2.addActionListener(this);
   b2.setEnabled(false);
  }
 public void actionPerformed(ActionEvent ae)
{
 if(ae.getSource()==b1)
 {
  hardness=jsb.getValue();
  /*
  if(rb1.isSelected())
    hardness=4;
  else if(rb2.isSelected())
     hardness=5;
  else if(rb3.isSelected())
     hardness=6;
  else
  {
     showMessageDialog(null,"Select the Game Difficulty\nTo continue the game");       
    return;
  }*/
   System.out.println("hardness:"+hardness);
   
  SecondGUI w=new SecondGUI(hardness,b2,++round);
  w.setSize(1200,1200);
  //w.setExtendedState(JFrame.MAXIMIZED_BOTH);
   //w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  w.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   w.setVisible(true); 
  b1.setEnabled(false);
 }
  else if(ae.getSource()==b2)
  {
    hardness=jsb.getValue();
/*if(rb1.isSelected())
    hardness=4;
  else if(rb2.isSelected())
     hardness=5;
  else if(rb3.isSelected())
     hardness=6;
  else
  {
     showMessageDialog(null,"Select the Game Difficulty\nTo continue the game");       
    return;
  }*/
  SecondGUI w=new SecondGUI(hardness,b2,++round);
  w.setSize(1200,1200);
  //w.setExtendedState(JFrame.MAXIMIZED_BOTH);
//   w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  w.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

   w.setVisible(true); 

  }
}
  

        
}
class  BubbleBurst
{
 public static void main(String args[])
{
 FirstGUI fg=new FirstGUI();
 fg.setSize(500,500);
 fg.setTitle("BubbleBurst");
 fg.setVisible(true);
 fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}