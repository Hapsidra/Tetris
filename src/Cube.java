/**
 * Created by hapsi on 13.06.2016.
 */
public class Cube {
    private int y, x;
    private MapUnit mapUnits[][];
    public Cube(MapUnit mapUnits[][], int y, int x) {
        this.mapUnits=mapUnits;
        this.y = y;
        this.x = x;
        mapUnits[y][x].setFilled(true);
    }

    public void moveLeft() {
        mapUnits[y][x].setFilled(false);
        x--;
        mapUnits[y][x].setFilled(true);
    }

    public void moveDown() {
        mapUnits[y][x].setFilled(false);
        y++;
        mapUnits[y][x].setFilled(true);
    }

    public void moveRight() {
        mapUnits[y][x].setFilled(false);
        x++;
        mapUnits[y][x].setFilled(true);
    }
    public void moveToIJ(int i, int j){
        if(!(this.y ==i && this.x ==j)) {
            mapUnits[this.y][this.x].setFilled(false);
            this.y = i;
            this.x = j;
            mapUnits[i][j].setFilled(true);
        }
    }
}
