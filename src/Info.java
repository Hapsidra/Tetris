import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by hapsi on 26.08.2016.
 */
public class Info extends JPanel {
    private int level;
    private Timer levelTimer;
    private JLabel labelLevel,labelNext;
    public Info(){
        setLayout(new BorderLayout());
        labelNext=new JLabel("Next");
        level=1;
        labelLevel=new JLabel("Level: 1");
        add(labelLevel,BorderLayout.WEST);

        levelTimer=new java.util.Timer();
        levelTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(level==10) {
                    levelTimer.cancel();
                    return;
                }
                level++;
                labelLevel.setText("Level: "+Integer.toString(level));
            }
        },1000*90,1000*90);
        add(labelNext,BorderLayout.EAST);
    }
    public void updateNext(int n){
        if (n == Figure.O) {
            labelNext.setIcon(new ImageIcon("img/o.png"));
        } else if (n == Figure.I) {
            labelNext.setIcon(new ImageIcon("img/i.png"));
        }
        else if (n == Figure.LR) {
            labelNext.setIcon(new ImageIcon("img/lr.png"));
        }
        else if (n == Figure.LL) {
            labelNext.setIcon(new ImageIcon("img/ll.png"));
        }
        else if (n == Figure.T) {
            labelNext.setIcon(new ImageIcon("img/t.png"));
        }
        else if (n == Figure.SL) {
            labelNext.setIcon(new ImageIcon("img/sl.png"));
        }
        else if (n == Figure.SR) {
            labelNext.setIcon(new ImageIcon("img/sr.png"));
        }
    }
    public int getLevel(){
        return level;
    }
}
