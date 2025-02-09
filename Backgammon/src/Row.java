import java.awt.*;
import java.util.*;

public class Row {
private Color c;
private int amount;
private boolean ismarked;

	public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

	public Color getC() {
	return c;
}

public void setC(Color c) {
	this.c = c;
}

	public Row(Color co,int a)
	{
		this.ismarked=false;
		this.amount=a;
		this.c=co;
	}
	public boolean Ismarked() {
		return ismarked;
	}

	public void setIsmarked(boolean ismarked) {
		this.ismarked = ismarked;
	}

	public Row()//For Empty Rows
	{
		this.ismarked=false;
		this.amount=0;
		this.c=null;
	}
	public void Detract()
	{
		this.amount--;
		if(this.amount==0)
		this.setC(null);
	}
	public void Add(Color co)
	{
		this.amount++;
		if(this.amount==1)
		this.setC(co);
	}

	

	

}
