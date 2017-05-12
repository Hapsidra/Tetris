import java.awt.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Figure0 extends Figure{
    private int width=2,height=2;
    private static final Color color=new Color(255, 0, 0);

    public Figure0(Map m,MapUnit mapUnits[][],int speed){
        super(m,mapUnits,speed);

        cubes[0]=new Cube(mapUnits,i,j);
        cubes[1]=new Cube(mapUnits,i,j+1);
        cubes[2]=new Cube(mapUnits,i+1,j);
        cubes[3]=new Cube(mapUnits,i+1,j+1);
    }
    public void moveLeft(){
        if(j>0 && !mapUnits[i][j-1].isFilled()&& !mapUnits[i+1][j-1].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    public void moveRight(){
        if(j<Map.M-width&& !mapUnits[i][j+width].isFilled()&& !mapUnits[i+1][j+width].isFilled()) {
            cubes[1].moveRight();
            cubes[0].moveRight();
            cubes[3].moveRight();
            cubes[2].moveRight();
            j++;
        }
    }
    public void moveDown(){
        if(i!=Map.N-height && !mapUnits[i+height][j].isFilled()&& !mapUnits[i+height][j+1].isFilled())
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
