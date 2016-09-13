import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by hapsi on 26.08.2016.
 */

public class ScorePanel extends JPanel{
    private JLabel labelScore,labelRecord;
    private int intScore,intRecord;
    public ScorePanel(){
        setLayout(new BorderLayout());
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("record.txt")));
            intRecord=scanner.nextInt();
            scanner.close();
        }catch (FileNotFoundException e){
        }
        intScore=0;
        labelScore=new JLabel("Score: 0");
        add(labelScore, BorderLayout.WEST);
        labelRecord=new JLabel("Record: "+Integer.toString(intRecord));
        add(labelRecord, BorderLayout.EAST);
    }
    public void setScore(int score){
        intScore=score;
        labelScore.setText("Score: "+Integer.toString(score));
    }
    public void setRecord(int record){
        intRecord=record;
        labelRecord.setText("Record: "+Integer.toString(record));
    }
    public int getScore(){
        return intScore;
    }
    public int getRecord(){
        return intRecord;
    }
}
