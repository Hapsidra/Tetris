import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hapsi on 13.06.2016.
 */
abstract public class Figure {
    public static int FIGURES_COUNT = 7;
    public static final int O=0,I=1,LR=2,LL=3,T=4,SL=5,SR=6;

    protected Cube cubes[];
    protected Timer timer;
    protected int i=0,j=4;
    protected MapUnit mapUnits[][];
    protected Map map;
    public Figure(Map map,MapUnit mapUnits[][],int speed){
        this.mapUnits=mapUnits;
        this.map=map;
        cubes=new Cube[4];
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveDown();
            }
        },1200-(speed*100),1200-(speed*100));
    }
    abstract public void moveLeft();
    abstract public void moveRight();
    abstract public void moveDown();
    abstract public void setOrientation();
}
