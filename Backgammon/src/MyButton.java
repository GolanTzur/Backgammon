import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class MyButton extends JButton
{

	public MyButton() {
		// TODO Auto-generated constructor stub
	}
	
	public MyButton(int w,int h,String s)
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(w,h));
		this.setLabel(s);
	}
	
	public MyButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public MyButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public MyButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public MyButton(String text, Icon icon) {
		
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	public void paintComponent(Graphics g)
	{
	      Graphics2D g2 = (Graphics2D) g.create();
	      if(this.getLabel()=="Scores"||this.getLabel()=="Play"||this.getLabel()=="Back")
	      {
	    	  paintScoresPlayButtons(g2);
	      }
	      else if(this.getLabel()=="Roll")
	      {
	    	  setContentAreaFilled(false);
	    	  paintRollButton(g2);
	      }
	      g2.dispose();
	      
    }
	public void paintScoresPlayButtons(Graphics2D g)
	{
		 g.setPaint(new GradientPaint(new Point(0, 0), Color.WHITE, new Point(0,
	     getHeight()), Color.orange));
         g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
	     g.setPaint(Color.BLACK);
	     Font f = new Font("Comic Sans MS", Font.BOLD,20);
	     g.setFont(f); 
	     g.drawString(getText(),(int)this.getSize().getWidth()/3,(int)this.getSize().getHeight()/2);
	}
	public void paintRollButton(Graphics2D g)
	{
		 g.setPaint(new GradientPaint(new Point(0, 0), Color.WHITE, new Point(0,getHeight()), Color.GRAY));
		 g.fillOval(0, 0, getWidth(), getHeight());
	     g.setPaint(Color.BLACK);
	     Font f = new Font("Comic Sans MS", Font.BOLD,20);
	     g.setFont(f); 
	     g.drawString(getText(),(int)this.getSize().getWidth()/3,(int)this.getSize().getHeight()/2);
		
	}
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
       // g.drawRoundRect(x, y, width-1, height-1, r, r);
		if(this.getLabel()=="Roll")
		{
        System.out.println("roll");
    	Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
        g2.dispose();
		}
    }
}
