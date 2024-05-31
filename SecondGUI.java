import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class SecondGUI extends JFrame implements Runnable
{
 int xcoor[],ycoor[],xpcoor[],ypcoor[],i,j,k,n,pos;
 int cbox[][];
boolean flag[];
 boolean status=false;
 int x1,x2,y1,y2,c=0,x,y;
 JPanel jpanel,p1;
 JLabel l1,l2;
 Container pane;
 MouseListener ml=null;
 JButton b;
 JToolBar toolbar;
  String difficulty;
 int rand,round,circount;
 boolean burst=false;
 int clock=16;
 Thread timer;
//hardness is number of circles in the game
//playing area x-300 y-300 width-600 height-600
Reposition_Local rl[];
//SecondGUI sgui;
 class Values
 {
   int x1,y1,x,y,hardness;
  }
Values v;
public SecondGUI()
{
}
 public SecondGUI(int hardness,JButton b2,int round)
 {
  
  //sgui=this;
 clock=clock-round;
 this.round=round;
v=new Values();
  l1=new JLabel("Round :"+round);
  l1.setBackground(Color.pink);
  l2=new JLabel("Time :");
  l1.setFont(new Font("Candara",Font.BOLD,32));
  l2.setFont(new Font("Candara",Font.BOLD,32));
  toolbar= new JToolBar();
  toolbar.add(l1);
  toolbar.add(new JLabel("     "));
  toolbar.add(l2);
  timer=new Thread(this);
  setTitle("Bubble Burt (Round:"+round+")");

 // p1=new JPanel();
 // p1.setLayout(new FlowLayout());
 // p1.add(l2);
//  p1.setBackground(Color.blue);
   b=b2;
   i=0;
   circount=0;
   System.out.println("difficulty:"+hardness);
   n=hardness;
   xcoor=new int[hardness];		//current coordiantes
   ycoor=new int[hardness];
   xpcoor=new int[hardness];		//previous coordinates to clear the circle
   ypcoor=new int[hardness];
   cbox=new int[hardness][2];
   flag=new boolean[hardness];
    rl=new Reposition_Local[hardness];
   pane=getContentPane(); 
   jpanel=new JPanel();
   jpanel.setBounds(300,300,600,600);
   jpanel.setBackground(Color.blue);
   pane.setLayout(new FlowLayout());
   pane.add(toolbar);
   //l1.setText("Bubble Burst");
   //l1.setFont(new Font("Serif", Font.PLAIN, 24));
   if(hardness==4)
         difficulty="Easy";
   else if(hardness==5)
         difficulty="Medium";
   else if(hardness==6)
          difficulty="Hard";

rand=new Random().nextInt(hardness);
 for(int ri=0;ri<hardness;ri++)
 {
   if(ri==rand)
      flag[ri]=true;
  else
    flag[ri]=false;
}
JOptionPane.showMessageDialog(null,"Game Difficulty "+difficulty+"\n No of circles for Game:"+n+"\nClick the Mouse to Define Origin of Circle");  
 addWindowListener(new WindowAdapter()
  {
     public void windowClosing(WindowEvent we)
      {
        b.setEnabled(true);
     }
});

 ml=new MouseAdapter()
  {
    @Deprecated
   public void mousePressed(MouseEvent me)
   {
   int ci;
   if(i<n && !status)
   {
     v.x1=me.getX();
     v.y1=me.getY();


     if(v.x1>=330 && v.y1>=330 && v.x1<=870 && v.y1<=870)
     {

         for(ci=0;ci<i;ci++)
        {
	   int d1=Math.abs(xcoor[ci]-v.x1);
 	   int d2=Math.abs(ycoor[ci]-v.y1);
	   double dist=Math.sqrt((double)((d1*d1)+(d2*d2)));
	   
//	   if(dist>50)
           System.out.println("Distance:"+dist);

             if(dist<=70)
            {
         JOptionPane.showMessageDialog(null,"Circle Inserts...One of all ready existing Circles\nClick the Mouse Inside JPanel again");  
          return;
             }
        }
         xcoor[i]=v.x1;
         ycoor[i]=v.y1;
         xpcoor[i]=v.x1;
         ypcoor[i]=v.y1;
         int bx,by;
         bx=v.x1-50;
         by=v.y1-50;
         if(bx<300)
               bx=300;
         if(by<300)
               by=300;

       repaint();

         rl[i]=new Reposition_Local(v,i);
       i++;
       if(i==n)			//start hops of circle in neighbourhood 
       {
            status=true;
          for(int k=0;k<n;k++)
                 rl[k].start();
                 if(i==n)
                 {
                   timer.start();
                 }
       }
      }
     else
     {
         JOptionPane.showMessageDialog(null,"Circle not fully inside JPanel\nClick the Mouse Inside JPanel again");  
      }
     System.out.println("x1:"+v.x1+"\t"+"y1:"+v.y1);
    }
    else 
      {    
     
        try
           {
               //Thread.sleep(1000);
                  x2=me.getX();
                  y2=me.getY();
             System.out.println("x2:"+x2+"\t"+"y2:"+y2);
          for(int k=0;k<n;k++)
         {
                 //rl[k].start();
             int d1=x2-rl[k].x1;
             int d2=y2-rl[k].y1;
            double dist=Math.sqrt((double)((d1*d1)+(d2*d2)));
              if(dist<=30)
              {
                
                     rl[k].gameOver=true;
                     v.x1=rl[k].x1;
                     v.y1=rl[k].y1;
                     v.x=rl[k].x1;
                     v.y=rl[k].y1;
                    burst=true;
                    repaint();
                    try
                   {
                    rl[k].stop();
                    
                     //v.x1=rl[k].x1;
                   //  v.y1=rl[k].y1;
      
                   // repaint();
                   circount++;
                   int gover=0;
                   for(int gcount=0;gcount<n;gcount++)
                   {
                    if(rl[k].gameOver)
                    {
                        gover++;
                    }
                   }
                   if( circount==n)
                   {
                           timer.stop();
                   }
                   if(circount==n && round==10)
                   {
                    JOptionPane.showMessageDialog(null,"Game Over\nYou Won the game");  
                   }
                      
                      //repaint();
//                      System.out.println("n value:..."+n);
  //                    System.out.println("count:..."+circount);
    //                  if(n==circount)
      //                {
        //               JOptionPane.showMessageDialog(null,"Game Over","Round "+round, JOptionPane.INFORMATION_MESSAGE);  
 
          //            }
                   }catch(Exception e)
                   {
                     System.out.println("Stop error:"+e);
                  }

               }
         }
/*  String msg="";
  if(dist>50)
     msg="\nMouse Clicked outside the circle";
 else if(dist==50)
    msg="\nMouse Clicked is on the circle";
 else
    msg="\nMouse Clicked inside the circle";
   JOptionPane.showMessageDialog(null,"Distance between line points:"+dist+msg);  
*/
             }catch(Exception e)
          { }
  }  //outer else
}
 };




addMouseListener(ml);

}

@Deprecated
public void run()
{
try
{
while(clock>=0)
{
 l2.setText("Time :"+clock);
 clock--;
Thread.sleep(1000);
}
if(clock<=0  && circount<n)
{
  for(int k=0;k<n;k++)
      rl[k].stop();

      JOptionPane.showMessageDialog(null,"Game Over\nYou Lost the Game");     
}
}catch(Exception e)
{
 System.out.println(e);
}
}

//nested class that represents Reposition_Local with multiple threads
class Reposition_Local extends Thread
{
  int nhs=50;	//neighborhoodsize
  int minx,maxx,maxy,miny;
  int x1,y1;
  int p;
  Random random;
  SecondGUI sgui;
  boolean gameOver;
  Values val;
  public Reposition_Local(Values v,int p)
  {
    val=v;
    random = new Random();
    x1=v.x1;
    y1=v.y1;
    gameOver=false;
    this.p=p;
    minx=v.x1-20*round;
    maxx=v.x1+20*round;   	//because circle radius is 30 and neighborhood size is 50 
    miny=v.y1-20*round;
    maxy=v.y1+20*round;
		//circle can move 20 points either sides from the x and y coordiantes
   // start();
  }
    public void run()
    {
System.out.println("random move starts");
   
 while(true && !gameOver)
    {
	try
                 {
                           //xcoor[p];
                           //ycoor[p];
 synchronized(v)
           {           
	if(!flag[p])
                 {
                   try
                  {
                    v.wait();
                  }catch(Exception e)
                  {
                  }
                  }
                  
	  v.x=x1;
	  v.y=y1;

                    //x1=x1+(random.nextInt(max - min) + min); 
                    //y1=y1+(random.nextInt(max - min) + min); 
                    int  nx1=random.nextInt(maxx - minx) + minx; 
                    int  ny1=random.nextInt(maxy - miny) + miny; 
                   // System.out.println("nx1..:"+nx1);
                   // System.out.println("ny1..:"+ny1);
                    boolean sizestatus=false,repstatus=false;
                    if( nx1>=330 && nx1<=870 && ny1>=330 && ny1<=870)
                     {
                      sizestatus=true;
                     for(int i=0;i<n;i++)   //n-hardness
                     {
                      if(i!=p)
                      {
                   //   System.out.println("xpcoor["+i+"] is:"+xpcoor[i]);
                   //   System.out.println("ypcoor["+i+"] is:"+ypcoor[i]);
                      int d1=Math.abs(xpcoor[i]-nx1);
                      int d2=Math.abs(ypcoor[i]-ny1);
                       double dist=Math.sqrt((double)((d1*d1)+(d2*d2)));
                     //  System.out.println("distance...."+dist);
                       if(dist<70)
                       {
                        repstatus=true;
                        break;
                       }
                       }  
                      }                   
                    }
                    System.out.println("sizestatus...."+sizestatus);
                    System.out.println("repstatus...."+repstatus);
                    
                     if(sizestatus && !repstatus)
                     {
                      x1=nx1;
                      y1=ny1;
                   v.x1=x1;
                   v.y1=y1;
                   xpcoor[p]=x1;
                   ypcoor[p]=y1;

  System.out.println("p:"+p+" x1:"+v.x1+"\t"+"y1:"+v.y1);
   System.out.println("p:"+p+" x :"+v.x+"\t"+"y :"+v.y);
                   repaint();
                     }

                  Thread.sleep(500);
               rand=new Random().nextInt(n);
                for(int ri=0;ri<n;ri++)
                {
                  if(circount<n-1)
                  {
             if(ri==rand)
                 flag[ri]=true;
              else
                   flag[ri]=false;
               }
               else
               {
                flag[ri]=true;
               }
              }
                 v.notifyAll();                


}
                  }catch(Exception e)
                  {
 	   System.out.println(e);
                  }
  }//while close
/*if(gameOver)		//when game over
{
                 repaint();
	  v.x=x1;
	  v.y=y1;
                  // v.x1=x1;
                  //v.y1=y1;
                      repaint();
}*/
     }//run close


}   //nested class closing


int sco=0;
 public void paint(Graphics g)
{
if(sco==0)
{
 // super.paint(g);
  sco++;
}

System.out.println("Repaint called"+"x1:"+v.x1+"\t"+"y1:"+v.y1);
  g.drawRect(300,300,600,600);
   //g.drawLine(x1,y1,x2,y2);
  if(burst)
  {
//    circount++;
    System.out.println("Count of burst:"+circount);
    g.setColor(Color.white);
    //g.drawRect(x,y,100,100);
    g.drawOval(v.x-(60/2),v.y-(60/2),60,60);
    clearCircle(g);
    burst=false;
    return;
  }
g.setColor(Color.red);
   // if(status)
    //   g.drawRect(x1,y1,100,100);
  // g.drawRect(x1,y1,60,60);
  
g.drawOval(v.x1-(60/2),v.y1-(60/2),60,60);
System.out.println("c value:"+c);
if(c==0 || c>n )
{
   clearCircle(g);
 // clearCircle(g);
 }
c++;

}
 public void clearCircle(Graphics g)
{
System.out.println("Clear graph  x:"+v.x+"\t"+"y:"+v.y);
 g.setColor(Color.white);
 //g.drawRect(x,y,100,100);
 g.drawOval(v.x-(60/2),v.y-(60/2),60,60);
}
}

   
