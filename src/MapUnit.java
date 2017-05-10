import javax.swing.*;
import java.awt.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class MapUnit extends JPanel {
    private boolean filled;
    private Color color;
    private static Color emptyColor=Color.BLACK,borderColor=new Color(50, 74, 92);
    public MapUnit(){
        setBorder(BorderFactory.createLineBorder(borderColor));
        filled =false;
    }
    public void setColor(Color c){
        color=c;
        repaint();
    }
    public Color getColor(){
        return  color;
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
            g.setColor(color);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        else {
            g.setColor(emptyColor);
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
}
