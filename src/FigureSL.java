import java.awt.*;

/**
 * Created by hapsi on 14.06.2016.
 */
public class FigureSL extends Figure{
    private int  position;
    private static final Color color=new Color(113, 127, 116);
    public FigureSL(Map map,MapUnit mu[][], int pos,int speed){
        super(map,mu, speed);

        if(pos==0) {
            cubes[0] = new Cube(mapUnits, i+1, j,color);
            cubes[1] = new Cube(mapUnits, i , j+1,color);
            cubes[2] = new Cube(mapUnits, i + 1, j+1,color);
            cubes[3] = new Cube(mapUnits, i + 2, j,color);
        }
        else if(pos==1){
            cubes[0] = new Cube(mapUnits, i, j+1,color);
            cubes[1] = new Cube(mapUnits, i, j,color);
            cubes[2] = new Cube(mapUnits, i+1, j+1,color);
            cubes[3] = new Cube(mapUnits, i+1, j+2,color);
        }
        position=pos;
    }
    public void moveLeft(){
        if(position==0)
            moveLeft0();
        else if(position==1)
            moveLeft1();
    }
    public void moveRight(){
        if(position==0)
            moveRight0();
        else if(position==1)
            moveRight1();
    }
    public void moveDown(){
        if(position==0)
            moveDown0();
        else if(position==1)
            moveDown1();
    }
    private void moveLeft0(){
        if(j>0 && !mapUnits[i][j].getState()&& !mapUnits[i+1][j-1].getState()&& !mapUnits[i+2][j-1].getState()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight0(){
        if(j<Map.M-2&& !mapUnits[i][j+2].getState()&& !mapUnits[i+1][j+2].getState()&& !mapUnits[i+2][j+1].getState()) {
            cubes[1].moveRight();
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }
    private void moveDown0(){

        if(i!=Map.N-3 && !mapUnits[i+3][j].getState() &&!mapUnits[i+2][j+1].getState())
        {
            i++;
            cubes[3].moveDown();
            cubes[2].moveDown();
            cubes[1].moveDown();
            cubes[0].moveDown();
        }
        else {
            timer.cancel();
            map.addNext();
        }
    }
    private void moveLeft1(){
        if(j>0 && !mapUnits[i][j-1].getState()&& !mapUnits[i+1][j].getState()) {
            cubes[1].moveLeft();
            cubes[0].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight1(){
        if(j<Map.M-3&& !mapUnits[i][j+2].getState()&& !mapUnits[i+1][j+3].getState()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[0].moveRight();
            cubes[1].moveRight();
            j++;
        }
    }
    private void moveDown1(){
        if(i!=Map.N-2 && !mapUnits[i+1][j].getState()&& !mapUnits[i+2][j+1].getState()&& !mapUnits[i+2][j+2].getState())
        {
            i++;
            cubes[3].moveDown();
            cubes[2].moveDown();
            cubes[1].moveDown();
            cubes[0].moveDown();
        }
        else {
            timer.cancel();
            map.addNext();
        }
    }
    public void setOrientation(){
        if(position==0) {
            if (j==Map.M-2||mapUnits[i+1][ j + 2].getState()||mapUnits[i][ j].getState())
                return;

            cubes[3].moveToIJ(i + 1, j + 2);
            cubes[2].moveToIJ(i+1, j + 1);
            cubes[1].moveToIJ(i, j );
            cubes[0].moveToIJ(i, j+1);

            position=1;
        }
        else if(position==1){
            if (i==Map.N-2 || mapUnits[i+2][j].getState()|| mapUnits[i+1][j].getState())
                return;

            cubes[0].moveToIJ(i+1, j);
            cubes[1].moveToIJ(i, j +1);
            cubes[2].moveToIJ(i+1, j +1);
            cubes[3].moveToIJ(i + 2, j);

            position=0;
        }
    }
}
