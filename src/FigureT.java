/**
 * Created by hapsi on 14.06.2016.
 */
public class FigureT extends Figure {
    private int  position;

    public FigureT(Map m,MapUnit mapUnits[][], int pos,int speed){
        super(m,mapUnits,speed);

        if(pos==0) {
            cubes[0] = new Cube(mapUnits, i , j+1);
            cubes[1] = new Cube(mapUnits, i+1, j);
            cubes[2] = new Cube(mapUnits, i + 1, j+1);
            cubes[3] = new Cube(mapUnits, i + 2, j+1);
        }
        else if(pos==1){
            cubes[0] = new Cube(mapUnits, i, j+1);
            cubes[1] = new Cube(mapUnits, i+1, j);
            cubes[2] = new Cube(mapUnits, i+1, j+1);
            cubes[3] = new Cube(mapUnits, i+1, j+2);
        }
        else if(pos==2){
            cubes[0] = new Cube(mapUnits, i, j);
            cubes[1] = new Cube(mapUnits, i+1, j);
            cubes[2] = new Cube(mapUnits, i+1, j+1);
            cubes[3] = new Cube(mapUnits, i+2, j);
        }
        else if(pos==3){
            cubes[0] = new Cube(mapUnits, i, j);
            cubes[1] = new Cube(mapUnits, i, j+1);
            cubes[2] = new Cube(mapUnits, i, j+2);
            cubes[3] = new Cube(mapUnits, i+1, j+1);
        }
        position=pos;
    }
    public void moveLeft(){
        if(position==0)
            moveLeft0();
        else if(position==1)
            moveLeft1();
        else if(position==2)
            moveLeft2();
        else if(position==3)
            moveLeft3();
    }
    public void moveRight(){
        if(position==0)
            moveRight0();
        else if(position==1)
            moveRight1();
        else if(position==2)
            moveRight2();
        else if(position==3)
            moveRight3();
    }
    public void moveDown(){
        if(position==0)
            moveDown0();
        else if(position==1)
            moveDown1();
        else if(position==2)
            moveDown2();
        else if(position==3)
            moveDown3();
    }
    private void moveLeft0(){
        if(j>0 && !mapUnits[i][j].isFilled()&& !mapUnits[i+1][j-1].isFilled()&& !mapUnits[i+2][j].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight0(){

        if(j<Map.M-2&& !mapUnits[i][j+2].isFilled()&& !mapUnits[i+1][j+2].isFilled()&& !mapUnits[i+2][j+2].isFilled()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[1].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }
    private void moveDown0(){

        if(i!=Map.N-3 && !mapUnits[i+3][j+1].isFilled() &&!mapUnits[i+2][j].isFilled())
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
        if(j>0 && !mapUnits[i][j].isFilled()&& !mapUnits[i+1][j-1].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight1(){
        if(j<Map.M-3&& !mapUnits[i][j+2].isFilled()&& !mapUnits[i+1][j+3].isFilled()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[1].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }
    private void moveDown1(){
        if(i!=Map.N-2 && !mapUnits[i+2][j].isFilled()&& !mapUnits[i+2][j+1].isFilled()&& !mapUnits[i+2][j+2].isFilled())
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
    private void moveLeft2(){
        if(j>0 && !mapUnits[i+2][j-1].isFilled()&& !mapUnits[i+1][j-1].isFilled()&& !mapUnits[i][j-1].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight2(){
        if(j<Map.M-2&& !mapUnits[i][j+1].isFilled()&& !mapUnits[i+1][j+2].isFilled()&& !mapUnits[i+2][j+1].isFilled()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[1].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }
    private void moveDown2(){
        if(i!=Map.N-3 && !mapUnits[i+3][j].isFilled()&& !mapUnits[i+2][j+1].isFilled())
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
    private void moveLeft3(){
        if(j>0 && !mapUnits[i][j-1].isFilled()&& !mapUnits[i+1][j].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }
    private void moveRight3(){
        if(j<Map.M-3&& !mapUnits[i][j+3].isFilled()&& !mapUnits[i+1][j+2].isFilled()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[1].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }
    private void moveDown3(){
        if(i!=Map.N-2 && !mapUnits[i+1][j].isFilled()&& !mapUnits[i+2][j+1].isFilled()&& !mapUnits[i+1][j+2].isFilled())
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
            if (j==Map.M-2||mapUnits[i+1][j+2].isFilled())
                return;
            //cubes[0].moveToIJ(i, j+1);
            //cubes[1].moveToIJ(i + 1, j + 2);
            cubes[3].moveToIJ(i+1, j + 2);
            //cubes[2].moveToIJ(i+1, j );

            /*
            0123
            0132
            0213
            0231
            1032
1203
1230
1302
1320
2013
2031
2103
2130
2301
2310
3012
3021
3102
3120
3201
3210
3210
3210
3210
             */

            position=1;
        }
        else if(position==1){
            if (i==Map.N-2 || mapUnits[i+2][j+1].isFilled())
                return;

            j=j + 1;

            //cubes[0].moveToIJ(i + 1, j + 1);
            cubes[3].moveToIJ(i+2, j);
            cubes[2].moveToIJ(i+1, j+1 );
            cubes[1].moveToIJ(i+1, j );


            position=2;
        }
        else if(position==2){
            if (j==0 ||mapUnits[i+1][j-1].isFilled())
                return;

            i++;
            j--;

            cubes[0].moveToIJ(i, j);
            //cubes[3].moveToIJ(i + 1, j+1);
            //cubes[2].moveToIJ(i, j+2 );
            //cubes[1].moveToIJ(i, j+1);

            position=3;
        }
        else if(position==3){
            if (i==0 ||mapUnits[i-1][j+1].isFilled()) {
                return;
            }

            i--;

            cubes[0].moveToIJ(i, j+1);
            cubes[1].moveToIJ(i+1, j);
            cubes[2].moveToIJ(i + 1, j+1);
            //cubes[3].moveToIJ(i + 2, j+1);

            position = 0;
        }
    }
}
