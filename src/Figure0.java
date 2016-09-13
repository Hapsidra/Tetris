import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Figure0 extends Figure{
    private int width=2,height=2;
    private static final Color color=new Color(255, 0, 0);

    public Figure0(Map m,MapUnit mapUnits[][],int speed){
        super(m,mapUnits,speed);

        cubes[0]=new Cube(mapUnits,i,j, color);
        cubes[1]=new Cube(mapUnits,i,j+1,color);
        cubes[2]=new Cube(mapUnits,i+1,j,color);
        cubes[3]=new Cube(mapUnits,i+1,j+1,color);
    }
    public void moveLeft(){
        if(j>0 && !mapUnits[i][j-1].getState()&& !mapUnits[i+1][j-1].getState()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    public void moveRight(){
        if(j<Map.M-width&& !mapUnits[i][j+width].getState()&& !mapUnits[i+1][j+width].getState()) {
            cubes[1].moveRight();
            cubes[0].moveRight();
            cubes[3].moveRight();
            cubes[2].moveRight();
            j++;
        }
    }
    public void moveDown(){
        if(i!=Map.N-height && !mapUnits[i+height][j].getState()&& !mapUnits[i+height][j+1].getState())
        {
            i++;
            cubes[2].moveDown();
            cubes[3].moveDown();
            cubes[1].moveDown();
            cubes[0].moveDown();
        }
        else {
            timer.cancel();
            map.addNext();
        }
    }
    public void setOrientation(){}
}
