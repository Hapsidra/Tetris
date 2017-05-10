import java.awt.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class FigureI extends Figure {
    private int widthH = 4, heightH = 1;
    private int heightV = 4;
    private boolean isVertical;
    private static final Color color = new Color(228, 157, 184);

    public FigureI(Map m, MapUnit mapUnits[][], boolean pos, int speed) {
        super(m, mapUnits, speed);
        if (pos == true) {
            cubes[0] = new Cube(mapUnits, i, j, color);
            cubes[1] = new Cube(mapUnits, i + 1, j, color);
            cubes[2] = new Cube(mapUnits, i + 2, j, color);
            cubes[3] = new Cube(mapUnits, i + 3, j, color);
        } else {
            j = 3;
            cubes[0] = new Cube(mapUnits, i, j, color);
            cubes[1] = new Cube(mapUnits, i, j + 1, color);
            cubes[2] = new Cube(mapUnits, i, j + 2, color);
            cubes[3] = new Cube(mapUnits, i, j + 3, color);
        }
        isVertical = pos;
    }

    public void moveLeft() {
        if (isVertical)
            moveLeftV();
        else
            moveLeftH();
    }

    public void moveRight() {
        if (isVertical)
            moveRightV();
        else
            moveRightH();
    }

    public void moveDown() {
        if (isVertical)
            moveDownV();
        else
            moveDownH();
    }

    private void moveLeftV() {
        if (j > 0 && !mapUnits[i][j - 1].isFilled() && !mapUnits[i + 1][j - 1].isFilled() && !mapUnits[i + 2][j - 1].isFilled() && !mapUnits[i + 3][j - 1].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }

    private void moveRightV() {
        if (j < Map.M - 1 && !mapUnits[i][j + 1].isFilled() && !mapUnits[i + 1][j + 1].isFilled() && !mapUnits[i + 2][j + 1].isFilled() && !mapUnits[i + 3][j + 1].isFilled()) {
            cubes[1].moveRight();
            cubes[0].moveRight();
            cubes[3].moveRight();
            cubes[2].moveRight();
            j++;
        }
    }

    private void moveDownV() {
        if (i != Map.N - heightV && !mapUnits[i + heightV][j].isFilled()) {
            i++;
            cubes[3].moveDown();
            cubes[2].moveDown();
            cubes[1].moveDown();
            cubes[0].moveDown();
        } else {
            timer.cancel();
            map.addNext();
        }
    }

    private void moveLeftH() {
        if (j > 0 && !mapUnits[i][j - 1].isFilled()) {
            cubes[0].moveLeft();
            cubes[1].moveLeft();
            cubes[2].moveLeft();
            cubes[3].moveLeft();
            j--;
        }
    }

    private void moveRightH() {
        if (j < Map.M - widthH && !mapUnits[i][j + widthH].isFilled()) {
            cubes[3].moveRight();
            cubes[2].moveRight();
            cubes[1].moveRight();
            cubes[0].moveRight();
            j++;
        }
    }

    private void moveDownH() {
        if (i != Map.N - heightH && !mapUnits[i + heightH][j].isFilled() && !mapUnits[i + heightH][j + 1].isFilled() && !mapUnits[i + heightH][j + 2].isFilled() && !mapUnits[i + heightH][j + 3].isFilled()) {
            i++;
            cubes[2].moveDown();
            cubes[3].moveDown();
            cubes[1].moveDown();
            cubes[0].moveDown();
        } else {
            timer.cancel();
            map.addNext();
        }
    }

    public void setOrientation() {
        if (isVertical) {//Смена на горизонтальный
            if (j == 0 || j > Map.M - 3)
                return;
            if (!mapUnits[i + 1][j - 1].isFilled() && !mapUnits[i + 1][j + 1].isFilled() && !mapUnits[i + 1][j + 2].isFilled()) {
                i = i + 1;
                j = j - 1;

                cubes[0].moveToIJ(i, j);
                cubes[1].moveToIJ(i, j + 1);
                cubes[2].moveToIJ(i, j + 2);
                cubes[3].moveToIJ(i, j + 3);

                isVertical = false;
            }
        } else {
            if (i > Map.N - 3 || i == 0) {
                return;
            }
            if (!mapUnits[i + 1][j + 1].isFilled() && !mapUnits[i + 2][j + 1].isFilled()) {
                i = i - 1;
                j = j + 1;

                cubes[0].moveToIJ(i, j);
                cubes[1].moveToIJ(i + 1, j);
                cubes[2].moveToIJ(i + 2, j);
                cubes[3].moveToIJ(i + 3, j);

                isVertical = true;
            }
        }
    }
}
