import javax.swing.*;
import java.awt.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class MapUnit extends JPanel {
    private boolean filled;
    private static final Color  EMPTY_COLOR = Color.BLACK,
                                FILLED_COLOR = Color.WHITE,
                                BORDER_COLOR = new Color(50, 74, 92);
    public MapUnit(){
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        filled =false;
    }
    public void setFilled(boolean filled){
        this.filled = filled;
        repaint();
    }
    public boolean isFilled(){
        return filled;
    }
    public void paintComponent(Graphics g){
        if(filled){
            g.setColor(FILLED_COLOR);
        }
        else {
            g.setColor(EMPTY_COLOR);
        }
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
