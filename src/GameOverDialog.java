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
public class GameOverDialog extends JDialog implements ActionListener{
    private JLabel messageLabel;
    private JButton okButton, noButton;
    private ScorePanel scorePanel;
    private Map map;
    public GameOverDialog(ScorePanel scorePanel, Map map) {
        this.scorePanel=scorePanel;
        this.map=map;
        setTitle("Game Over");
        setSize(300,150);
        setModal(true);
        setLayout(new FlowLayout());
        if(scorePanel.getScore()>scorePanel.getHighScore()) {
            messageLabel = new JLabel("Game Over. New High Score: " + Integer.toString(scorePanel.getScore()) + "! Play again?");
            try {
                PrintWriter printWriter = new PrintWriter(new File("data.txt"));
                printWriter.print(scorePanel.getScore());
                printWriter.close();
            }catch (IOException e){}
        }
        else {
            messageLabel = new JLabel("Game Over :( Your score: " + Integer.toString(scorePanel.getScore()) + " Play again?");
        }
        add(messageLabel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        okButton=new JButton("OK");
        okButton.addActionListener(this);
        add(okButton);

        noButton =new JButton("No");
        noButton.addActionListener(this);
        add(noButton);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("OK")){
            if(scorePanel.getScore()>scorePanel.getHighScore())
                scorePanel.setHighScore(scorePanel.getScore());
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
