import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

public class GameStats {
    private final LocalDate gameDate;
    private int [][] scores;

    public GameStats() {
        this.gameDate = LocalDate.now();
        this.scores = new int [4][2];
        //1st column - Player 1 (Blue), 2nd column - Player 2 (Black)
        //1st row - score
        //2nd row - dice rolls made
        //3rd row - sum of all rolls
        //4th row - doubles made

        //for(int i = 0; i < 4; i++) {for(int j = 0; j < 2;j++) {this.scores[i][j] = 0;}}
    }
    public int[][]  getScores() {
        return this.scores;
    }
    public LocalDate getGameDate() {return this.gameDate;}

    public void exportSingle(File f,GameStats gs,boolean toappend) throws IOException{ // export single game
        if(f.exists()) {
            OutputStream os = new FileOutputStream(f.toString(),toappend);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(gs.gameDate.getDayOfMonth());
            dos.writeInt(gs.gameDate.getMonthValue());
            dos.writeInt(gs.gameDate.getYear());
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    dos.writeInt(gs.scores[i][j]);
                }
            }
            os.close();
            dos.close();
        }
    }

    public void exportToBinFile() throws IOException //export the game keeping the chronological order
    {
        int d=this.gameDate.getDayOfMonth();
        this.gameDate.getYear();
        this.gameDate.getMonth();
        File f=new File("laststats.bin");
        if (f.createNewFile()==true) { //if there is no file exists
            exportSingle(f,this,true);
        }
        else //there is a file already
        {
                Deque<GameStats> prevgames = loadQueueFromFile(f);
               if(prevgames.size()==3) //Maximum size - remove last element
                prevgames.removeLast();
                prevgames.addFirst(this);
                Iterator<GameStats> itr=prevgames.iterator();
                exportSingle(f,itr.next(),false); //Overwrite the file
                while (itr.hasNext()) {
                    exportSingle(f,itr.next(),true);
                }

        }

    }
    public static Deque<GameStats> loadQueueFromFile(File f)  throws IOException
    {
        Deque <GameStats> prevgames=new LinkedList<GameStats>();
        InputStream is=new FileInputStream(f.toString());
        DataInputStream dis=new DataInputStream(is);
        int value;
        while(is.read()!=-1) //each iteration scans 1 Game
        {
            //Since we read a byte,we skip the next 2 and save the last one for value
            is.read();
            is.read();
            value=is.read();
            GameStats gs=new GameStats();
            int day=value;
            value=dis.readInt();
            int month=value;
            value=dis.readInt();
            int year=value;
            LocalDate ld=LocalDate.of(year,month,day);
            for(int i=0;i<4;i++)for(int j=0;j<2;j++){value=dis.readInt(); gs.getScores()[i][j]=value;}
            prevgames.addLast(gs);
        }
        is.close();
        dis.close();
        return prevgames;
    }
    public void print()
    {
        System.out.println(this.gameDate);
        for(int i=0;i<4;i++)
        System.out.println(this.scores[i][0]+"  "+this.scores[i][1]);
    }
    public void paintgamestats(Graphics g,int y)
    {
        g.setColor(Color.yellow);
        Font f = new Font("Comic Sans MS",Font.PLAIN,15);
        g.setFont(f);
        g.drawString("Date: "+this.gameDate.toString(),Placements.scoreslastgamesx,y);
        y+=Placements.scoressmallverticalspace;
        g.drawString("Blue",Placements.scoreslastgamesx,y);
        g.drawString("Black",Placements.scoreslastgamesx+Placements.scoressmallhorizontalspace,y);
        y+=Placements.scoressmallverticalspace;
        g.drawString("Score:",Placements.scorestatsx,y);
        g.drawString(this.scores[0][0]+"",Placements.scoreslastgamesx,y);
        g.drawString(this.scores[0][1]+"",Placements.scoreslastgamesx+Placements.scoressmallhorizontalspace,y);
        y+=Placements.scoressmallverticalspace;
        g.drawString("Dice Rolls:",Placements.scorestatsx,y);
        g.drawString(this.scores[1][0]+"",Placements.scoreslastgamesx,y);
        g.drawString(this.scores[1][1]+"",Placements.scoreslastgamesx+Placements.scoressmallhorizontalspace,y);
        y+=Placements.scoressmallverticalspace;
        g.drawString("Dice Total Sum:",Placements.scorestatsx,y);
        g.drawString(this.scores[2][0]+"",Placements.scoreslastgamesx,y);
        g.drawString(this.scores[2][1]+"",Placements.scoreslastgamesx+Placements.scoressmallhorizontalspace,y);
        y+=Placements.scoressmallverticalspace;
        g.drawString("Total Doubles:",Placements.scorestatsx,y);
        g.drawString(this.scores[3][0]+"",Placements.scoreslastgamesx,y);
        g.drawString(this.scores[3][1]+"",Placements.scoreslastgamesx+Placements.scoressmallhorizontalspace,y);
    }

}

