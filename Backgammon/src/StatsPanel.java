import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StatsPanel extends JPanel {
	private int p1;
	private int p2;
	private boolean blueturn;

	public boolean isBlueturn() {
		return blueturn;
	}

	public void setBlueturn(boolean blueturn) {
		this.blueturn = blueturn;
	}

	public StatsPanel()
	{
	this.setLayout(null);
	this.setSize(1200,50);
	this.p1=0;
	this.p2=0;
	this.blueturn =true;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public StatsPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public StatsPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public StatsPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight());
		g.setColor(Color.black);
		Font f = new Font("Comic Sans MS", Font.TYPE1_FONT,15);
	    g.setFont(f);
	    g.drawString("Player 1 (Blue) : "+p1,30 ,(int)this.getSize().getHeight()/2);
	    g.drawString("Player 2 (Black) : "+p2,1000 ,(int)this.getSize().getHeight()/2);
	    f = new Font("Comic Sans MS", Font.TYPE1_FONT,20);
	    g.setFont(f);
	    if(blueturn)
	    g.drawString("Player 1's turn",600 ,(int)this.getSize().getHeight()/2);
	    else
	    g.drawString("Player 2's turn",600 ,(int)this.getSize().getHeight()/2);	
		
	}
	public void SetButtonLocation(JButton j,int x,int y)
	{
		j.setLocation(x, y);
		j.setBounds(x, y, j.getPreferredSize().width, j.getPreferredSize().height);
	}
}
