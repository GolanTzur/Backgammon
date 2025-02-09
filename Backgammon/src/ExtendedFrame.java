import javax.swing.JFrame;

public class ExtendedFrame extends JFrame
{
private StatsPanel sp;
	public ExtendedFrame(String s)
	{
		super(s);
		this.sp=new StatsPanel();
		this.add(sp);
		
	}
	public StatsPanel getSp() {
		return sp;
	}
	public void setSp(StatsPanel sp) {
		this.sp = sp;
	}

}
