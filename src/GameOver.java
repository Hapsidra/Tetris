import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class GameOver extends JDialog implements ActionListener{
    private JLabel message;
    private JButton ok,no;
    private ScorePanel scorePanel;
    private Map map;
    public GameOver(ScorePanel scorePanel,Map map) {
        this.scorePanel=scorePanel;
        this.map=map;
        setTitle("Game Over");
        setSize(300,150);
        setModal(true);
        setLayout(new FlowLayout());
        if(scorePanel.getScore()>scorePanel.getRecord()) {
            message = new JLabel("Game Over. New High Score: " + Integer.toString(scorePanel.getScore()) + "! Play again?");
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("record.txt")));
                printWriter.print(scorePanel.getScore());
                printWriter.close();
            }catch (IOException e){}
        }
        else {
            message = new JLabel("Game Over :( Your score: " + Integer.toString(scorePanel.getScore()) + " Play again?");
        }
        add(message);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        ok=new JButton("OK");
        ok.addActionListener(this);
        add(ok);

        no=new JButton("NO");
        no.addActionListener(this);
        add(no);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand()=="OK"){
            if(scorePanel.getScore()>scorePanel.getRecord())
                scorePanel.setRecord(scorePanel.getScore());
            scorePanel.setScore(0);
            map.clear();
            map.addNext();
            this.hide();
        }
        else{
            System.exit(0);
        }
    }
}
