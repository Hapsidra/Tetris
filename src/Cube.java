/**
 * Created by hapsi on 13.06.2016.
 */
public class Cube {
    private int y, x;
    public Cube(int y, int x) {
        this.y = y;
        this.x = x;
    }
    public Cube(int []coordinates){
        this(coordinates[0],coordinates[1]);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
