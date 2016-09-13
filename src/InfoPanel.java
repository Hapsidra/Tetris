import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by hapsi on 26.08.2016.
 */
public class InfoPanel extends JPanel {
    private int level;
    private Timer levelTimer;
    private JLabel labelLevel,labelNext;
    public InfoPanel(){
        setLayout(new BorderLayout());
        labelNext=new JLabel("Next");
        level=1;
        labelLevel=new JLabel("Level: 1");
        add(labelLevel,BorderLayout.WEST);

        levelTimer=new java.util.Timer();
        levelTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(level!=9) {
                    level++;
                    labelLevel.setText("Level: "+Integer.toString(level));
                }
                else
                    levelTimer.cancel();
            }
        },1000*90,1000*90);
        add(labelNext,BorderLayout.EAST);
    }
    public void updateNext(int n){
        if (n == Figure.O) {
            labelNext.setIcon(new ImageIcon("o.png"));
        } else if (n == Figure.I) {
            labelNext.setIcon(new ImageIcon("i.png"));
        }
        else if (n == Figure.LR) {
            labelNext.setIcon(new ImageIcon("lr.png"));
        }
        else if (n == Figure.LL) {
            labelNext.setIcon(new ImageIcon("ll.png"));
        }
        else if (n == Figure.T) {
            labelNext.setIcon(new ImageIcon("t.png"));
        }
        else if (n == Figure.SL) {
            labelNext.setIcon(new ImageIcon("sl.png"));
        }
        else if (n == Figure.SR) {
            labelNext.setIcon(new ImageIcon("sr.png"));
        }
    }
    public int getLevel(){
        return level;
    }
}
