import java.awt.*;
import java.util.*;

public class Game
{
private int dice1;
private int dice2;
private boolean isrolled;
private Row[]board;
private int markcode;//-1 if none is marked, 24 for blue eaten pin,25 for black eaten pin
private boolean isblueturn;
private Vector<Integer>plays;
private Player p1;
private Player p2;


    public Game()
	{
    	
		this.dice1=0;
		this.dice2=0;
		this.isrolled=false;
		this.markcode=-1;
		this.isblueturn=true;
		this.board=new Row[24];
		this.plays=new Vector<Integer>();
		p1=new Player(Color.blue);
		p2=new Player(Color.black);
		Vector v=new Vector<Integer>();
		v.add(0);
		v.add(5);
		v.add(7);
		v.add(11);
		v.add(12);
		v.add(16);
		v.add(18);
		v.add(23);
		for(int i=0;i<24;i++)
		{
			if(!v.contains(i))
			this.board[i]=new Row();
			
		}
		this.board[0]=new Row(Color.BLUE,2);
		this.board[5]=new Row(Color.BLACK,5);
		this.board[7]=new Row(Color.BLACK,3);
		this.board[11]=new Row(Color.BLUE,5);
		this.board[12]=new Row(Color.BLACK,5);
		this.board[16]=new Row(Color.BLUE,3);
		this.board[18]=new Row(Color.BLUE,5);
		this.board[23]=new Row(Color.BLACK,2);
		InitRowPositions();
		
	}
    public void InitRowPositions()
    {
    	
    	int xpos=1098;
    	int lowerypos=410;
    	int currentypos=lowerypos;
    	int upperypos=15;
    	int rowwidth=50;
    	int rowheight =200;
    	Placements.rowpositions=new Rectangle[24];
    	Arithmetic a = BasicArithmetic.Subtraction;
    	for(int i=0;i<24;i++)
    	{
    		
    		if(i==6)
    			xpos=xpos-55;
    		if(i==18)
    			xpos=xpos+55;
    		if(i==12)
    		{
    			a = BasicArithmetic.Addition;
    			currentypos=upperypos;
    		}
    		
    		Placements.rowpositions[i]=new Rectangle(xpos,currentypos,rowwidth,rowheight);
    		
    		if(i!=11)
        		xpos=a.calculate(xpos, Placements.mainpnlpaintboardxinterval);
    	}
    	
    	
    }
    public int GetRow(int x,int y)
    {
    	int x1,x2,y1,y2;
    	for(int i=0;i<24;i++)
    	{
    		x1=Placements.rowpositions[i].x;
    		x2=(Placements.rowpositions[i].x+Placements.rowpositions[i].width);
    		if(x>=x1&&x<=x2)
    		{
    			y1=Placements.rowpositions[i].y;
        		y2=(Placements.rowpositions[i].y+Placements.rowpositions[i].height);
    			if(y>=y1&&y<=y2)
    			return i;
    		}
    		
    	}
    	return -1;
    	
    }
    public boolean Isblueturn() {
		return isblueturn;
	}
	public void setIsblueturn(boolean isblueturn) {
		this.isblueturn = isblueturn;
	}
	public void paintgame(Graphics g)
    {
    	int xpos=1140-Placements.mainpnlpinsize;
    	int startingypos=610-Placements.mainpnlpinsize;
    	int secondypos=20;
    	int ypos=startingypos;
    	Font f = new Font("Arial",Font.PLAIN,40);
	    g.setFont(f);
	    Arithmetic a1 = BasicArithmetic.Addition;
    	Arithmetic a = BasicArithmetic.Subtraction;
    	for(int i=0;i<24;i++)
    	{
    		if(i==12)
    		{
    		a=BasicArithmetic.Addition;
    		a1=BasicArithmetic.Subtraction;
    		}
    		if(i==6)
    			xpos=xpos-55;
    		if(i==18)
    			xpos=xpos+55;
    		if(this.getBoard()[i].getAmount()>0)
    		{
    			int amount=this.getBoard()[i].getAmount();
    			g.setColor(this.getBoard()[i].getC());
    			for(int j=0;j<amount&&j<5;j++)
    			{
    				g.fillOval(xpos, ypos, Placements.mainpnlpinsize,Placements.mainpnlpinsize);	
    				ypos=a.calculate(ypos,Placements.mainpnlpinsize);
    			}
    			if(amount>5)
    			{
    			if(i<12)
    				g.drawString(""+amount, xpos+10, a.calculate(ypos,10));
    			else
    				g.drawString(""+amount, xpos+10, a.calculate(ypos,(10+Placements.mainpnlpinsize)));
    			}
    			if(this.getBoard()[i].Ismarked())
    			{
    				g.setColor(Color.YELLOW);
    				if(i>11)
    				g.fillOval(a.calculate(xpos, 9),a1.calculate(ypos, 27),Placements.mainpnlpinsize/2,Placements.mainpnlpinsize/2);
    				else
    				g.fillOval(a1.calculate(xpos, 9),a1.calculate(ypos, 45),Placements.mainpnlpinsize/2,Placements.mainpnlpinsize/2);
    			}
    		}
    		
    		if(i!=11)
    		xpos=a.calculate(xpos, Placements.mainpnlpaintboardxinterval);
    		if(i<11)
    		ypos=startingypos;
    		else
    		ypos=secondypos;
    		
    		}
    	//paintrows(g);//Guide Lines
    	if(this.getP1().getEatenpins()!=0||this.getP2().getEatenpins()!=0)
    		painteatenpins(g);
    }
	public void painteatenpins(Graphics g)
	{
		int epbluey= Placements.mainpnleatenpinsyblue;
		int epblacky= Placements.mainpnleatenpinsyblack;
		int epblue=this.getP1().getEatenpins();
		int epblack=this.getP2().getEatenpins();
		for(int i=0;i<epblack;i++)
		{
			g.setColor(Color.black);
			g.fillOval(Placements.mainpnleatenpinsx,epblacky,Placements.mainpnlpinsize,Placements.mainpnlpinsize);
			if(!this.isblueturn&&i==0)
			{
				g.setColor(Color.yellow);
				g.fillOval(Placements.mainpnleatenpinsx+9,epblacky+10,Placements.mainpnlpinsize/2,Placements.mainpnlpinsize/2);//For marking automatically the first eaten pin
			}
			epblacky+=Placements.mainpnlpinsize;
		}
		
		for(int i=0;i<epblue;i++)
		{
			g.setColor(Color.blue);
			g.fillOval(Placements.mainpnleatenpinsx,epbluey,Placements.mainpnlpinsize,Placements.mainpnlpinsize);
			if(this.isblueturn&&i==0)
			{
				g.setColor(Color.yellow);
				g.fillOval(Placements.mainpnleatenpinsx+9,epbluey+10,Placements.mainpnlpinsize/2,Placements.mainpnlpinsize/2);//For marking automatically the first eaten pin
			}
			
			epbluey+=Placements.mainpnlpinsize;
		}
	}
	public void paintrows(Graphics g)
	{
		for(int i=0;i<24;i++)
		{
			g.drawRect(Placements.rowpositions[i].x,Placements.rowpositions[i].y, Placements.rowpositions[i].width, +Placements.rowpositions[i].height);
		}
	}
    public boolean Switch(int from,int to,Color c)
    {
    	if(c==Color.blue)
    	{
    		if(to>from)//The destination row must be higher than the source one
    		{
    			int steps=to-from;
    			if(this.plays.contains(steps))//Check if one of the cubes is equal to the number of steps given 
    			{
    				if(this.getBoard()[to].getC()==Color.blue||this.getBoard()[to].getAmount()==0)//Regular Move
    				{
    				this.getBoard()[to].Add(c);
    				this.getBoard()[from].Detract();
    				int i=this.plays.indexOf(steps);
    				this.plays.remove(i);
    				if(this.calculateEatingMode(c))
    				{
    				this.getP1().setEatingmode(true);
    				//System.out.println("eating mode activated for the blue");
    				}
    				return true;
    				}
    				else if(this.getBoard()[to].getC()==Color.black&&this.getBoard()[to].getAmount()==1)//Removing Opponent's pin Move
    				{
    					this.p2.setEatenpins(this.p2.getEatenpins()+1);
    					this.getBoard()[to].Detract();
    					this.getBoard()[to].Add(c);
        				this.getBoard()[from].Detract();
        				int i=this.plays.indexOf(steps);
        				this.p2.setEatingmode(false);
        				this.plays.remove(i);
        				if(this.calculateEatingMode(c))
        				{
        				this.getP1().setEatingmode(true);
        				//System.out.println("eating mode activated for the blue");
        				}
        				return true;
    				}
    				
    			}
    			
    		}
    	
    	}
    	else//The Color is Black
    	{
    		if(to<from)//The destination row must be lower than the source one
    		{
    			int steps=from-to;
    			if(this.plays.contains(steps))//Check if one of the cubes is equal to the number of steps given 
    			{
    				if(this.getBoard()[to].getC()==Color.black||this.getBoard()[to].getAmount()==0)//Regular Move
    				{
    				this.getBoard()[to].Add(c);
    				this.getBoard()[from].Detract();
    				int i=this.plays.indexOf(steps);
    				this.plays.remove(i);
    				if(this.calculateEatingMode(c))
    				{
    				this.getP2().setEatingmode(true);
    				//System.out.println("eating mode activated for the black");
    				}
    				return true;
    				}
    				else if(this.getBoard()[to].getC()==Color.blue&&this.getBoard()[to].getAmount()==1)//Removing Opponent's pin Move
    				{
    					this.p1.setEatenpins(this.p1.getEatenpins()+1);
    					this.getBoard()[to].Detract();
    					this.getBoard()[to].Add(c);
        				this.getBoard()[from].Detract();
        				int i=this.plays.indexOf(steps);
        				this.p1.setEatingmode(false);
        				this.plays.remove(i);
        				if(this.calculateEatingMode(c))
        				{
        				this.getP2().setEatingmode(true);
        				//System.out.println("eating mode activated for the black");
        				}
        				return true;
    				}
    				
    			}
    			
    		}
    	}
    	return false;
    }
    public boolean AvailableSwitchWholeBoard(Color c) throws InterruptedException
    {
       //System.out.println("Check for possible moves on board");
       //System.out.println(this.getPlays().toString());
       boolean dbl=true;
       if(this.plays.size()>1&&this.plays.get(0)!=this.plays.get(1))
       {
       dbl=false;
       //System.out.println("2 iterates");
       }
       else
       {
       //System.out.println("1 iterate");
       }
       for(int i=0;i<24;i++)
    		{
    			if(this.board[i].getC()==c)
    			{
    			if(c==Color.blue)
    			{
    			if( AvailableSwitch(i,(i+this.plays.get(0)),c))
    				return true;;
    			}
    			else//Black's turn
    			{
    				if( AvailableSwitch(i,(i-this.plays.get(0)),c))
        				return true;;
    			}
    			}
    		}
    		if(!dbl)
    		{
    			for(int j=0;j<24;j++)
        		{
    				if(this.board[j].getC()==c)
        			{
        			if(c==Color.blue)
        			{
        			if( AvailableSwitch(j,(j+this.plays.get(1)),c))
        				return true;;
        			}
        			else//Black's turn
        			{
        				if( AvailableSwitch(j,(j-this.plays.get(1)),c))
            				return true;;
        			}
        			}
        		}
    		}
    		java.util.concurrent.TimeUnit.SECONDS.sleep(3);
    		this.plays.removeAllElements();
    		this.setIsrolled(false);
    		//System.out.println("no move available");
    	    return false;
    }
    public boolean AvailableSwitch(int from,int to,Color c)
    {
    	if(c==Color.blue)
    	{
    		if(to<24)//Out of bounds
    		{
    		if(this.getBoard()[to].getC()==c||this.getBoard()[to].getAmount()==0||(this.getBoard()[to].getC()==Color.black&&this.getBoard()[to].getAmount()==1))
    		return true;
    		}
    	}
    	else if(c==Color.black)
    	{
    		if(to>0)//Out of bounds
    		{
        		if(this.getBoard()[to].getC()==c||this.getBoard()[to].getAmount()==0||(this.getBoard()[to].getC()==Color.blue&&this.getBoard()[to].getAmount()==1))
        		return true;
    		}
    	}
    	return false;
    }
    
    public boolean EndOfTurn(Color c) throws InterruptedException
    {
    	if(this.plays.size()==0)
    	{
    		return true;
    	}
    	if(c==Color.blue)
    	{
    		if(this.p1.isEatingmode()==true)
    		{
    		if(!AvailableEatMove(c)&&!AvailableSwitchWholeBoard(c))
    		return true;
        	}
    		else //No eating mode
    		{
    			if(!AvailableSwitchWholeBoard(c))
    	    		return true;
    		}
    	}
    	else //Black's turn
    	{
    		if(this.p2.isEatingmode()==true)
    		{
    		if(!AvailableEatMove(c)&&!AvailableSwitchWholeBoard(c))
    		return true;
        	}
    		else //No eating mode
    		{
    			if(!AvailableSwitchWholeBoard(c))
    	    		return true;
    		}
    	}
    	return false;
    }
    public boolean AvailableEatMove(Color c)
    {
    	int diceindex;
    	boolean flag=true;
    	if(c==Color.black)
    	{
    		for(int i= 0;i<6&&flag;i++)//0-5
    		{
    			if(this.board[i].getC()==Color.black)
    			{
    				diceindex=i+1;//1-6
    				flag=false;
    				for(int j=diceindex;j<=6;j++)
    				{
    					if(this.plays.contains(j))
    					{
    					//System.out.println("Available eat move for black");
    					return true;
    					}
    				}
    			}
    		}
    	}
    	else //Blue's turn
    	{
    		
    		for(int i= 23;i>17&&flag;i--)//0-5
    		{
    			if(this.board[i].getC()==Color.blue)
    			{
    				diceindex=24-i;//1-6
    				flag=false;
    				for(int j=diceindex;j<=6;j++)
    				{
    					if(this.plays.contains(j))
    					{
    					//System.out.println("Available eat move for blue");
    					return true;
    					}
    				}
    			}
    		}
    	}
    	return false;
    }
    public boolean AvailableSwitchEatenPin(Color c) throws InterruptedException
    {
    	if(this.plays.size()>0)
    	{
    	if(c==Color.blue)
    	{
    		for(int i=0;i<6;i++)
    		{
    			if(this.board[i].getAmount()==0||this.board[i].getC()==Color.blue||(this.board[i].getAmount()==1&&this.board[i].getC()==Color.black))//Check if there's any available move
    			{
    				int num=i+1;
    				if(this.plays.contains(num))//Check if one of the dices is equal to the specified row
    				return true;
    			}
    		}
    	}
    	else //Black's turn
    	{
    		for(int j=23;j>17;j--)
    		{
    			if(this.board[j].getAmount()==0||this.board[j].getC()==Color.black||(this.board[j].getAmount()==1&&this.board[j].getC()==Color.blue))
    			{
    				if(this.plays.contains(24-j))
    				return true;
    			}
    		}
    	}
    	}
    	java.util.concurrent.TimeUnit.SECONDS.sleep(3);
    	this.plays.removeAllElements();
		//System.out.println("no move available eaten pins");
    	return false;
    }
    public boolean SwitchEatenPin(Color c,int rowindex)
    {
    	//System.out.println("index : "+rowindex);
    	
    	if(c==Color.blue)
    	{
    		int blueindex=rowindex+1;
    		if(this.plays.contains(blueindex))
    		{
    			if(this.board[rowindex].getAmount()==0||this.board[rowindex].getC()==Color.blue)//Regular Move
    			{
    				this.board[rowindex].Add(c);
    				this.getP1().setEatenpins(this.getP1().getEatenpins()-1);
    				int i=this.plays.indexOf(blueindex);
    				this.plays.remove(i);
    				return true;
    			}
    			else if(this.board[rowindex].getAmount()==1&&this.board[rowindex].getC()==Color.black)//Removing Opponent's pin move
    			{
    				this.board[rowindex].Detract();
    				this.board[rowindex].Add(c);
    				int i=this.plays.indexOf(blueindex);
    				this.plays.remove(i);
    				this.p2.setEatingmode(false);
    				this.getP1().setEatenpins(this.getP1().getEatenpins()-1);
    				this.getP2().setEatenpins(this.getP2().getEatenpins()+1);
    				return true;
    			}
    		}
    	}
    	else //Black's turn
    	{
    		int blackindex=24-rowindex;//The row index from the black area (1-6)
    		//System.out.println("index : "+blackindex);
    		if(this.plays.contains(blackindex))
    		{
    			if(this.board[rowindex].getAmount()==0||this.board[rowindex].getC()==Color.black)//Regular Move
    			{
    				this.board[rowindex].Add(c);
    				this.getP2().setEatenpins(this.getP2().getEatenpins()-1);
    				int i=this.plays.indexOf(blackindex);
    				this.plays.remove(i);
    				return true;
    			}
    			else if(this.board[rowindex].getAmount()==1&&this.board[rowindex].getC()==Color.blue)//Removing Opponent's pin move
    			{
    				this.board[rowindex].Detract();
    				this.board[rowindex].Add(c);
    				int i=this.plays.indexOf(blackindex);
    				this.plays.remove(i);
    				this.p1.setEatingmode(false);
    				this.getP1().setEatenpins(this.getP1().getEatenpins()+1);
    				this.getP2().setEatenpins(this.getP2().getEatenpins()-1);
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public boolean calculateEatingMode(Color c)
    {
    	int numofpins=0;
    	if(c==Color.black)
    	{
    		for(int i=0;i<6;i++)
    		{
    			if(this.getBoard()[i].getC()==Color.black)
    			numofpins+=this.getBoard()[i].getAmount();
    		}
    		//System.out.println("pins in black area : "+ numofpins);
    		if(numofpins==15-this.getP2().getScore())//means all the black pins left are in the black area
    		{
    			//System.out.println("black eating mode activated");
    			return true;
    		}
    	}
    	else //Blue's turn
    	{
    		for(int i=23;i>17;i--)
    		{
    			if(this.getBoard()[i].getC()==Color.blue)
    			numofpins+=this.getBoard()[i].getAmount();
    		}
    		//System.out.println("pins in blue area : "+ numofpins);
    		if(numofpins==15-this.getP1().getScore())//means all the blue pins left are in the blue area
    		{
    			//System.out.println("blue eating mode activated");
    			return true;
    		}
    	}
    	return false;
    }
    public boolean Eat(Color c,int index)
    {
    	int diceindex;
    	System.out.println("eating at row "+index);
    	System.out.println("dices are "+this.plays.toString());
    	if(c==Color.blue)
    	{
    		diceindex=24-index;
    		
    		System.out.println("looking for dice "+diceindex);
    		if(this.plays.contains(diceindex))//the cubes contain the exact number of the row
    		{
    			this.board[index].Detract();
    			this.getP1().setScore(this.getP1().getScore()+1);
    			int ii =this.plays.indexOf(diceindex);
    			this.plays.remove(ii);
    			return true;
    		}
    		else//maybe the cubes contain a higher number than needed
    		{
    			//System.out.println("dice "+diceindex+" is not found");
    			if(index>18)//1-5
    			{
    				int i=diceindex;
    			    for(i=diceindex;i<=6;i++)
    			    {
    			    	//System.out.println("looking for "+i);
    			    	if(this.plays.contains(i))//the cubes contain the exact number of the row
    		    		{
    			    		//System.out.println(i+" is compatible ");
    		    			this.board[index].Detract();
    		    			this.getP1().setScore(this.getP1().getScore()+1);
    		    			int ii =this.plays.indexOf(i);
    		    			this.plays.remove(ii);
    		    			return true;
    		    		}
    			    }
    				
    			}
    		}
    		
    	}
    	else //Black's turn
    	{
    		diceindex=index+1;
    		//System.out.println("looking for dice "+diceindex);
    		if(this.plays.contains(diceindex))//the cubes contain the exact number of the row
    		{
    			this.board[index].Detract();
    			this.getP2().setScore(this.getP2().getScore()+1);
    			int ii =this.plays.indexOf(diceindex);
    			this.plays.remove(ii);
    			return true;
    		}
    		else//maybe the cubes contain a higher number than needed
    		{
    			System.out.println("dice "+diceindex+" is not found");
    			if(index<5)
    			{
    				int i=diceindex;
    			    for(i=diceindex;i<=6;i++)
    			    {
    			    	System.out.println("looking for "+i);
    			    	if(this.plays.contains(i))//the cubes contain the exact number of the row
    		    		{
    			    		System.out.println(i+" is compatible ");
    		    			this.board[index].Detract();
    		    			this.getP2().setScore(this.getP2().getScore()+1);
    		    			int ii =this.plays.indexOf(i);
    		    			this.plays.remove(ii);
    		    			return true;
    		    		}
    			    }
    				
    			}
    		}
    		
    	}
    	
    	return false;
    }
    
	public Vector<Integer> getPlays() {
		return plays;
	}
	public void setPlays(Vector<Integer> plays) {
		this.plays = plays;
	}
	public Player getP1() {
		return p1;
	}
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public Player getP2() {
		return p2;
	}
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	public boolean isIsrolled() {
		return isrolled;
	}
	public boolean isIsblueturn() {
		return isblueturn;
	}
	public boolean Isrolled() {
		return isrolled;
	}

	public void setIsrolled(boolean isrolled) {
		this.isrolled = isrolled;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}
	public void RollDices()
	{
		int max=6, min=1;
		Random r=new Random();
		this.dice1=r.nextInt(max - min + 1) + min;
		this.dice2=r.nextInt(max - min + 1) + min;
		this.isrolled=true;
		if(this.dice1==this.dice2)//Double Mode
		{
			this.plays.add(this.dice1);
			this.plays.add(this.dice1);
			this.plays.add(this.dice1);
			this.plays.add(this.dice1);
		}
		else
		{
			this.plays.add(this.dice1);
			this.plays.add(this.dice2);
		}
		this.isrolled=true;
	}
	public void setBoard(Row[] board) {
		this.board = board;
	}

	public int getMarkcode() {
		return markcode;
	}

	public void setMarkcode(int markcode) {
		this.markcode = markcode;
	}

	
	public Row[] getBoard() {
		return board;
	}

}
