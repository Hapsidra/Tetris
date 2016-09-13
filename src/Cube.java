import java.awt.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Cube {
    private int i, j;
    private Color color;
    private MapUnit mapUnits[][];
    public Cube(MapUnit mapUnits[][], int i, int j,Color color) {
        this.mapUnits=mapUnits;
        this.i = i;
        this.j = j;
        this.color=color;
        mapUnits[i][j].setColor(color);
        mapUnits[i][j].setState(true);
    }

    public void moveLeft() {
        mapUnits[i][j].setState(false);
        j--;
        mapUnits[i][j].setColor(color);
        mapUnits[i][j].setState(true);
    }

    public void moveDown() {
        mapUnits[i][j].setState(false);
        i++;
        mapUnits[i][j].setColor(color);
        mapUnits[i][j].setState(true);
    }

    public void moveRight() {
        mapUnits[i][j].setState(false);
        j++;
        mapUnits[i][j].setColor(color);
        mapUnits[i][j].setState(true);
    }
    public void moveToIJ(int i, int j){
        if(!(this.i==i && this.j==j)) {
            mapUnits[this.i][this.j].setState(false);
            this.i = i;
            this.j = j;
            mapUnits[i][j].setColor(color);
            mapUnits[i][j].setState(true);
        }
    }
}
