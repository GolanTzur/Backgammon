import java.awt.*;
import java.io.*;

public class HistoryStats {
    private int gamesplayed;
    private int rolls;
    private int sum;
    private int doubles;
    public HistoryStats(int gamesplayed,int rolls, int sum, int doubles) {this.gamesplayed=gamesplayed;this.rolls = rolls;this.sum = sum;this.doubles = doubles;}
    public int getgamesplayed() {return gamesplayed;}
    public int getRolls() {return this.rolls;}
    public int getSum() {return this.sum;}
    public int getDoubles() {return this.doubles;}
    public void setDoubles(int doubles) {this.doubles = doubles;}
    public void setGamesplayed(int gamesplayed) {this.gamesplayed = gamesplayed;}
    public void setRolls(int rolls) {this.rolls = rolls;}
    public void setSum(int sum) {this.sum = sum;}
    public void updateHistory(int rolls, int sum, int doubles)
    {
        this.gamesplayed++;
        this.rolls+=rolls;
        this.sum+=sum;
        this.doubles+=doubles;
    }
    public void exportToBin() throws IOException
    {
        File file = new File("historystats.bin");
        FileOutputStream fos;
        DataOutputStream dos;
        file.createNewFile();
        fos = new FileOutputStream(file);
        dos = new DataOutputStream(fos);
        dos.writeInt(this.gamesplayed);
        dos.writeInt(this.rolls);
        dos.writeInt(this.sum);
        dos.writeInt(this.doubles);
        fos.close();
        dos.close();
    }
    public static HistoryStats loadHistoryStats() throws IOException
    {
        File file = new File("historystats.bin");
        if(file.exists())
        {
            FileInputStream fis;
            DataInputStream dis;
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            int gamesplayed = dis.readInt();
            int rolls = dis.readInt();
            int sum = dis.readInt();
            int doubles = dis.readInt();
            HistoryStats hs = new HistoryStats(gamesplayed,rolls,sum,doubles);
            return hs;
        }
        return new HistoryStats(0,0,0,0);
    }
    public void paintHistoryStats(Graphics g)
    {
        Font f = new Font("Comic Sans MS", Font.BOLD,30);
        Font f1 = new Font("Comic Sans MS",Font.PLAIN,20);
        g.setFont(f);
        int y=Placements.scoresheadliney;
        g.drawString("All stats",Placements.scoreshistoryx,y);
        y+=2*Placements.scoreslargeverticalspace;
        g.setFont(f1);
        g.drawString("Games Played:  "+gamesplayed,Placements.scoreshistoryx,y);
        y+=Placements.scoreslargeverticalspace;
        g.drawString("Dice Rolls:  "+rolls,Placements.scoreshistoryx,y);
        y+=Placements.scoreslargeverticalspace;
        g.drawString("Dice Total Sum:  "+sum,Placements.scoreshistoryx,y);
        y+=Placements.scoreslargeverticalspace;
        g.drawString("Total Doubles:  "+doubles,Placements.scoreshistoryx,y);

    }
    public void print()
    {
        System.out.println("Games: "+this.gamesplayed);
        System.out.println("Rolls: "+this.rolls);
        System.out.println("Sum: "+this.sum);
        System.out.println("Doubles: "+this.doubles);
    }


}
