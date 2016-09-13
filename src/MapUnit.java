import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by hapsi on 13.06.2016.
 */
public class MapUnit extends JPanel {
    private boolean state;
    private Color color;
    private static Color emptyColor=Color.BLACK,borderColor=new Color(50, 74, 92);
    public MapUnit(){
        setBorder(BorderFactory.createLineBorder(borderColor));
        state=false;
    }
    public void setColor(Color c){
        color=c;
        repaint();
    }
    public Color getColor(){
        return  color;
    }
    public void setState(boolean state){
        this.state=state;
        repaint();
    }
    public boolean getState(){
        return state;
    }
    public void paintComponent(Graphics g){
        if(state){
            g.setColor(color);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        else {
            g.setColor(emptyColor);
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
}
