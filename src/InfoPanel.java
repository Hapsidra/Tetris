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
        labelNext=new JLabel("Next:");
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
        },1000*75,1000*75);
        add(labelNext,BorderLayout.EAST);
        labelNext.setHorizontalTextPosition(SwingConstants.LEFT);
        labelNext.setMaximumSize(new Dimension(58,26));
        labelNext.setPreferredSize(new Dimension(58,26));
        labelNext.setMinimumSize(new Dimension(58,26));
    }
    public void updateNext(int figure,int orientation){
        if (figure == Figure.O) {
            labelNext.setIcon(new ImageIcon("img/O"+(orientation+1)+".png"));
        } else if (figure == Figure.I) {
            labelNext.setIcon(new ImageIcon("img/I"+(orientation+1)+".png"));
        }
        else if (figure == Figure.LR) {
            labelNext.setIcon(new ImageIcon("img/LR"+(orientation+1)+".png"));
        }
        else if (figure == Figure.LL) {
            labelNext.setIcon(new ImageIcon("img/LL"+(orientation+1)+".png"));
        }
        else if (figure == Figure.T) {
            labelNext.setIcon(new ImageIcon("img/T"+(orientation+1)+".png"));
        }
        else if (figure == Figure.SL) {
            labelNext.setIcon(new ImageIcon("img/SL"+(orientation+1)+".png"));
        }
        else if (figure == Figure.SR) {
            labelNext.setIcon(new ImageIcon("img/SR"+(orientation+1)+".png"));
        }
    }
    public int getLevel(){
        return level;
    }
}
