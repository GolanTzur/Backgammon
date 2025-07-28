import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Picture extends JPanel implements MouseListener{
	private Image i;
	private int sn;
	private Game g;
	private static JFrame menuw;
	private static ExtendedFrame gamew;
	private MyButton rollbutton;
	

	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public static void main(String[] args)
	{
		MenuWindow();
	}
	
	public Image getI() {
		return i;
	}
	public void setI(Image i) {
		this.i = i;
	}
	public void paintComponent(Graphics g)
	{
		
	    ImageIcon ii=new ImageIcon("Board.jpg");
		this.i=ii.getImage();
		
		if(sn==1)
		{
		g.drawImage(i, 0, 0, 1200, 680, null);	
        paintmenuwindow(g);
		}
		else
		{
		g.drawImage(i, 0, 0, Placements.mainpnlsizewidth, Placements.mainpnlsizeheight-20, null);
		paintgamewindow(g);
		
		}
	}
	public void paintmenuwindow(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRoundRect(Placements.windowmenubackgroundx,Placements.windowmenubackgroundy, 600, 80, 30, 30);
		g.setColor(Color.yellow.brighter());
		Font f = new Font("Comic Sans MS", Font.BOLD,40);
	    g.setFont(f);
	    g.drawString("Backgammon",Placements.windowmenutitlex ,Placements.windowmenutitley);
	}
	public void paintgamewindow(Graphics g)
	{
		this.getG().paintgame(g);
		if(this.getG().getPlays().size()!=0)
		DrawNumbers(g);
	}
	public void DrawNumbers(Graphics g)
	{
		int d1=this.getG().getDice1();
		int d2=this.getG().getDice2();
		int turnsleft=this.getG().getPlays().size();
		boolean equal=d1==d2;
		ImageIcon icmi=new ImageIcon(d2+"cube.jpg");
		Image ii=icmi.getImage();
		ImageIcon icm=new ImageIcon(d1+"cube.jpg");
		Image i=icm.getImage();
		if(equal)//Double Mode
		{
		int counter =1;
		if(counter<=turnsleft)
		{
		g.drawImage(i,Placements.mainpnlfirstdiex,Placements.mainpnldicey,50,50, null);
		counter++;
		}
		if(counter<=turnsleft)
		{
		g.drawImage(i,Placements.mainpnlfirstdiex-Placements.mainpnldiceinterval,Placements.mainpnldicey,50,50, null);
		counter++;
		}
		if(counter<=turnsleft)
		{
		g.drawImage(i,Placements.mainpnlseconddiex,Placements.mainpnldicey,50,50, null);
		counter++;
		}
		if(counter<=turnsleft)
		g.drawImage(i,Placements.mainpnlseconddiex+Placements.mainpnldiceinterval,Placements.mainpnldicey,50,50, null);
		}
		else // Regular Mode
		{
		if(turnsleft==2)// 2 Moves Left
		{
			g.drawImage(i,Placements.mainpnlfirstdiex,Placements.mainpnldicey,50,50, null);
			g.drawImage(ii,Placements.mainpnlseconddiex,Placements.mainpnldicey,50,50, null);
		}
		else// 1 Move Left
		{
			if(this.g.getPlays().get(0)==d1)
				g.drawImage(i,Placements.mainpnlfirstdiex,Placements.mainpnldicey,50,50, null);
			else
				g.drawImage(ii,Placements.mainpnlseconddiex,Placements.mainpnldicey,50,50, null);
		}
		
		}
	}
	public Game getG() {
		return g;
	}
	public void setG(Game g) {
		this.g = g;
	}
	public void EndOfTurn(Color c)
	{
		if(c==Color.black)
		{
		//this.rollbutton.setVisible(true);
		gamew.getSp().setBlueturn(true);
		gamew.getSp().repaint();
		this.g.setIsblueturn(true);
		this.g.setIsrolled(false);
		}
		else
		{
			//this.rollbutton.setVisible(true);
			gamew.getSp().setBlueturn(false);
			gamew.getSp().repaint();
			this.g.setIsblueturn(false);
			this.g.setIsrolled(false);
		}
		this.g.setMarkcode(-1);
	}
	public static void MenuWindow()
	{
		
		Picture jl= new Picture(1);
	    menuw= new JFrame("Backgammon");
		menuw.setSize(1200,Placements.windowmenuheight);
	    MyButton b1=new MyButton(200,120,"Play");
		MyButton b2=new MyButton(200,120,"Scores");
		jl.add(b1);
		jl.add(b2);
		jl.SetButtonLocation(b1,Placements.windowmenubutton1x,Placements.windowmenubutton1y);
		jl.SetButtonLocation(b2,Placements.windowmenubutton2x,Placements.windowmenubutton2y);
		b1.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		         GameWindow();
		    }});
		    
		menuw.add(jl);
		menuw.setVisible(true);
	}
	public static void GameWindow()
	{
		menuw.setVisible(false);
		gamew= new ExtendedFrame("Game");
		gamew.setLayout(null);
		gamew.setSize(1200,Placements.gamewindowheight);
		Picture jl= new Picture(2);//Game Panel
		jl.rollbutton=new MyButton(70,70,"Roll");
		JButton back=new JButton("Back");
		gamew.getSp().add(back);
		back.setLocation(Placements.statspnlbackbuttonx, Placements.statspnlbackbuttony);
		back.setPreferredSize(new Dimension(100,30));
		back.setBounds(Placements.statspnlbackbuttonx, Placements.statspnlbackbuttony,back.getPreferredSize().width,back.getPreferredSize().height);
		jl.add(jl.rollbutton);
		jl.SetButtonLocation(jl.rollbutton, Placements.mainpnlrollbuttonx, Placements.mainpnlrollbuttony);
		gamew.add(jl);
		gamew.setVisible(true);
		gamew.getSp().setLocation(Placements.statspnlposx,Placements.statspnlposy);
		gamew.getSp().setBounds(Placements.statspnlposx,Placements.statspnlposy,Placements.statspnlsizewidth,Placements.statspnlsizeheight);
		jl.setLocation(Placements.mainpnlposx,Placements.mainpnlposy);
		jl.setBounds(Placements.mainpnlposx,Placements.mainpnlposy, Placements.mainpnlsizewidth, Placements.mainpnlsizeheight);
		jl.rollbutton.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	boolean wait=false; //Wait if no legal moves
		        jl.getG().RollDices();
		        jl.rollbutton.setVisible(false);
		        if(jl.getG().Isblueturn())
		        {
		        	if(jl.getG().getP1().getEatenpins()==0)
		        	{
		        		if(jl.getG().getP1().isEatingmode()==false)
		        		{
		        	try {
						if(jl.getG().AvailableSwitchWholeBoard(Color.blue)==false)//No Possible Move
						{
							wait=true;
							jl.EndOfTurn(Color.blue);
							jl.repaint();
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	}
		        		else // blue eating mode
		        		{
		        			try {
								if(jl.getG().AvailableEatMove(Color.blue)==false&&jl.getG().AvailableSwitchWholeBoard(Color.blue)==false)
								{
									System.out.println("inside capture - no move for blue");
									jl.repaint();
									wait=true;
									jl.EndOfTurn(Color.blue);
								}
									
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        		}
		        	}
		        	else//There Are blue eaten pins
		        	{
		        		
		        		try {
							if(jl.getG().AvailableSwitchEatenPin(Color.blue))
							jl.getG().setMarkcode(24);
							else
							{
							
							wait=true;
							jl.EndOfTurn(Color.blue);
							jl.repaint();
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
		        }
		        else//Black's turn
		        {
		        	System.out.println("black");
		        	if(jl.getG().getP2().getEatenpins()==0)
		        	{
		        		if(jl.getG().getP2().isEatingmode()==false)
		        		{
		        	try {
						if(jl.getG().AvailableSwitchWholeBoard(Color.black)==false)//No Possible Move
						{
							System.out.println("no moves for black");
							wait=true;
							jl.EndOfTurn(Color.black);
							jl.repaint();
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	}
		        		else // black eating mode
		        		{
		        			try {
								if(jl.getG().AvailableEatMove(Color.black)==false&&jl.getG().AvailableSwitchWholeBoard(Color.black)==false)
								{
									System.out.println("inside capture - no move for black");
									jl.repaint();
									wait=true;
									jl.EndOfTurn(Color.black);
								}
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        		}
		        	}
		        	else//There Are black eaten pins
		        	{
		        		System.out.println("before available switch: "+jl.getG().getPlays().size());	
		        		try {
							if(jl.getG().AvailableSwitchEatenPin(Color.black))
							jl.getG().setMarkcode(25);
							else
							{
							
						    wait=true;
							jl.EndOfTurn(Color.black);
							jl.repaint();
							
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
		        }
		        if(wait==true)
		        {
		        	javax.swing.Timer timer = new javax.swing.Timer(2000, evt -> {
		        	    jl.getG().getPlays().removeAllElements();
		        	    jl.rollbutton.setVisible(true);
		        	    jl.repaint();
		        	});
		        	timer.setRepeats(false);  
		        	timer.start(); 
		        	 wait=false;
		        }
		        else
		        jl.repaint();
		    }});
		back.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		        gamew.setVisible(false);
		        MenuWindow();
		    }});
		
	}
	public void SetButtonLocation(JButton j,int x,int y)
	{
		j.setLocation(x, y);
		j.setBounds(x, y, j.getPreferredSize().width, j.getPreferredSize().height);
	}

	public Picture(int n)
{
	this.sn=n;
	if(n!=1)//Game Window
	{
		this.addMouseListener(this);
		this.setSize(Placements.mainpnlsizewidth,Placements.mainpnlsizeheight);
		this.g=new Game();
	}
	else
	this.setSize(1200,Placements.windowmenuheight);
	setLayout(null);
	
}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		boolean wait=false; //wait before no available moves
		if(this.getG().Isrolled()==true)//Check if the Dice is rolled
		{
			
		if(this.getG().Isblueturn()==true)//Check who's turn
		{
			int row=this.g.GetRow(e.getX(), e.getY());
			if(this.g.getMarkcode()!=24)//No Blue eaten pins
			{
			if(row!=-1)//Check if the Click is valid
			{
			if(this.g.getP1().isEatingmode()==true&&this.getG().getMarkcode()==-1&&this.g.getBoard()[row].getC()==Color.blue&&this.g.Eat(Color.blue, row))// Eating Requires only 1 click and not 2 like other cases
			{
				
				gamew.getSp().setP1(gamew.getSp().getP1()+1);//Increase The Score
				if(this.g.getP1().getScore()==15)//Blue Wins
				{
					repaint();
					JOptionPane.showMessageDialog(this, "Blue Wins", "Game Over", JOptionPane.INFORMATION_MESSAGE);
					{
						gamew.setVisible(false);
				        MenuWindow();
					}
				}
					else
						try {
							if(this.g.getPlays().size()==0||(!(this.g.AvailableEatMove(Color.blue))&&!(this.g.AvailableSwitchWholeBoard(Color.blue))))
							{
								if(this.g.getPlays().size()>0)//--
								{
									repaint();
									wait=true;
								}
								else
									this.rollbutton.setVisible(true);
								this.EndOfTurn(Color.blue);
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				}
			
			else//Regular Move
			{
			if(this.getG().getMarkcode()==-1)//In Case none of the pins is marked currently
			{
			if(this.g.getBoard()[row].getC()==Color.BLUE)//Valid Selection
				{
					this.g.getBoard()[row].setIsmarked(true);
					this.g.setMarkcode(row);
				}
			}
			else//In Case there's a marked pin already
			{
				int prvrow=this.g.getMarkcode();//The row index of the previous click
				if(row==prvrow)//Cancel the mark
				{
					this.g.getBoard()[row].setIsmarked(false);
					this.g.setMarkcode(-1);
				}
				else
				{
					if(this.getG().Switch(prvrow, row, Color.blue))//The Switch is valid
					{
				    
					this.getG().getBoard()[prvrow].setIsmarked(false);
					this.g.setMarkcode(-1);
					try {
						if(this.g.EndOfTurn(Color.blue))
						{
							if(this.g.getPlays().size()>0)
							{
								repaint();
								wait=true;
							}
							else
							{
								this.rollbutton.setVisible(true);
							}
							this.EndOfTurn(Color.blue);
						}
						
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			}
			}
			
		}
			}
			else //There are Blue eaten pins
			{
				if(row!=-1)
				{
					try {
						if(!this.g.AvailableSwitchEatenPin(Color.blue))
						{
							repaint();
							wait=true;
							this.EndOfTurn(Color.blue);
						}
						else//There are possible moves
						{
							if(this.g.SwitchEatenPin(Color.blue, row))//The Switch is valid
							{
								 if(this.g.calculateEatingMode(Color.blue)==true)//Checking whether the blue enters eating mode
								    	this.g.getP1().setEatingmode(true);
								if(this.g.getPlays().size()==0)//No turns left
								{
									this.EndOfTurn(Color.blue);
									this.rollbutton.setVisible(true);
								}
								else if(this.g.getP1().getEatenpins()!=0)//There are turns left and eaten pins for the blue
								{
									if(!this.g.AvailableSwitchEatenPin(Color.blue))
									{
										repaint();
										wait=true;
										this.EndOfTurn(Color.blue);
									}
								}
								else // There are turns left and no eaten pins for the blue
								{
									if(!this.g.EndOfTurn(Color.blue))// There are available switches on the board after eaten mode
									{
									this.g.setMarkcode(-1);
									//System.out.println("marked code -1");
									}
									else
									{
										repaint();
										wait=true;
										this.EndOfTurn(Color.blue);
									}
									}
								}
							}
						}
				
			
				
					 catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			}
			
		
		else//Black's turn
		{
			int row=this.g.GetRow(e.getX(), e.getY());
			if(this.g.getMarkcode()!=25)// No Black eaten pins
			{
			if(row!=-1)//Check if the Click is valid
			{
				if(this.g.getP2().isEatingmode()==true&&this.g.getBoard()[row].getC()==Color.black&&this.getG().getMarkcode()==-1&&this.g.Eat(Color.black, row))// Eating Requires only 1 click and not 2 like other cases
				{
					gamew.getSp().setP2(gamew.getSp().getP2()+1);//Increase The Score
					if(this.g.getP2().getScore()==15)//Blue Wins
					{
						repaint();
						JOptionPane.showMessageDialog(this, "Black Wins", "Game Over", JOptionPane.INFORMATION_MESSAGE);
						{
							gamew.setVisible(false);
					        MenuWindow();
						}
					}
					else
						try {
							if(this.g.getPlays().size()==0||(!(this.g.AvailableEatMove(Color.black))&&!(this.g.AvailableSwitchWholeBoard(Color.black))))
							{
								if(this.g.getPlays().size()>0)
								{
									repaint();
									wait=true;
								}
								else
								this.rollbutton.setVisible(true);
								
								this.EndOfTurn(Color.black);
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			else //Regular Move
			{
			if(this.getG().getMarkcode()==-1)//In Case none of the pins is marked currently
			{
			if(this.g.getBoard()[row].getC()==Color.BLACK)//Valid Selection
				{
					this.g.getBoard()[row].setIsmarked(true);
					this.g.setMarkcode(row);
				}
			}
			else//In Case there's a marked pin already
			{
				int prvrow=this.g.getMarkcode();//The row index of the previous click
				if(row==prvrow)//Cancel the mark
				{
					this.g.getBoard()[row].setIsmarked(false);
					this.g.setMarkcode(-1);
				}
				else
				{
					
					if(this.getG().Switch(prvrow, row,Color.BLACK))//The Switch is valid
					{
				    if(this.g.calculateEatingMode(Color.BLACK)==true)//Checking whether the black enters eating mode
				    	this.g.getP2().setEatingmode(true);
					this.getG().getBoard()[prvrow].setIsmarked(false);
					this.g.setMarkcode(-1);
					try {
						if(this.g.EndOfTurn(Color.black))
						{
							if(this.g.getPlays().size()>0)
							{
								repaint();
								wait=true;
							}
							else
								this.rollbutton.setVisible(true);
							
							this.EndOfTurn(Color.black);
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		            }
				}
			}
			}
			}
			}
		
			
			else //There are Black eaten pins
			{
				try {
					if(!this.g.AvailableSwitchEatenPin(Color.black))//--
					{
						repaint();
						wait=true;
						this.EndOfTurn(Color.black);
					}
					else//There are possible moves
					{
						if(this.g.SwitchEatenPin(Color.black, row))//The Switch is valid
						{
							
							if(this.g.getPlays().size()==0)//No turns left
							{
								this.EndOfTurn(Color.black);
								this.rollbutton.setVisible(true);
							}
							else if(this.g.getP2().getEatenpins()!=0)//There are turns left and eaten pins for the black
							{
								if(!this.g.AvailableSwitchEatenPin(Color.black))//--
								{
									repaint();
									wait=true;
									this.EndOfTurn(Color.black);
								}
							}
							else // There are turns left and no eaten pins for the black
							{
								if(!this.g.EndOfTurn(Color.black))// There are available switches on the board after eaten mode
								{
								this.g.setMarkcode(-1);
								}
								else
								{
									repaint();
									wait=true;
									this.EndOfTurn(Color.black);
								}
								}
							}
						}
					}
			
				 catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		
			}
			
			
		
		}
		
		if(wait==true)
        {
			javax.swing.Timer timer = new javax.swing.Timer(2000, evt -> {
			    this.getG().getPlays().removeAllElements();
			    this.rollbutton.setVisible(true);
			    this.repaint();
			});
			timer.setRepeats(false);  
			timer.start(); 
        	 wait=false;
        }
        else
        this.repaint();
		}
	}
	
		

		



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




}
