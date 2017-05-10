import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by hapsi on 26.08.2016.
 */

public class ScorePanel extends JPanel{
    private JLabel labelScore,labelRecord;
    private int score, highScore;
    public ScorePanel(){
        setLayout(new BorderLayout());
        try {
            Scanner scanner = new Scanner(new File("data.txt"));
            highScore =scanner.nextInt();
            scanner.close();
        }catch (FileNotFoundException e){}

        score = 0;
        labelScore=new JLabel("Score: 0");
        add(labelScore, BorderLayout.WEST);
        labelRecord=new JLabel("High score: "+Integer.toString(highScore));
        add(labelRecord, BorderLayout.EAST);
    }
    public void setScore(int score){
        this.score =score;
        labelScore.setText("Score: "+Integer.toString(score));
    }
    public void setHighScore(int hs){
        highScore =hs;
        labelRecord.setText("High score: "+Integer.toString(hs));
    }
    public int getScore(){
        return score;
    }
    public int getHighScore(){
        return highScore;
    }
}
