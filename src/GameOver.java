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
    private JButton ok;
    private int score,record;
    public GameOver(int score,int record) {
        setTitle("Game Over");
        setSize(300,150);
        setModal(true);
        setLayout(new FlowLayout());
        this.score=score;
        this.record=record ;
        if(score>record) {
            message = new JLabel("Game Over. New High Score: " + Integer.toString(score) + "! Play again?");
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("record.txt")));
                printWriter.print(score);
                printWriter.close();
            }catch (IOException e){}
        }
        else {
            message = new JLabel("Game Over :( Your score: " + Integer.toString(score) + " Play again?");
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
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand()=="OK"){
            System.exit(0);
        }
    }
}
