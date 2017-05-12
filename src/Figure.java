import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Figure {
    public static final int FIGURES_COUNT = 3;
    public static final int O=0,I=1,LR=2,LL=3,T=4,SL=5,SR=6;
    public static final int TO_DOWN=0,TO_LEFT=1,TO_RIGHT=2;

    private static final int[][][][]FIGURES = {
            //Квадратик
            {
                    {
                            {-1, 4},
                            {-1, 5},
                            {0, 4},
                            {0, 5},
                    }
            },
            //Палка
            {
                    //Вертальная
                    {
                            {-1, 4},
                            {-0, 4},
                            {1, 4},
                            {2, 4},
                    },
                    //Горизонтальная
                    {
                            {0, 3},
                            {0, 4},
                            {0, 5},
                            {0, 6}
                    }
            },
            //LR
            {
                    //
                    {
                            {-1,4},
                            {-1,5},
                            {0,4},
                            {1,4}
                    },
                    {
                            {-1,4},
                            {-1,5},
                            {-1,6},
                            {0,6}
                    },
                    {
                            {-2,5},
                            {-1,5},
                            {0,4},
                            {0,5}
                    },
                    {
                            {-1,4},
                            {0,4},
                            {0,5},
                            {0,6}
                    }
            }
    };

    private Cube cubes[]=new Cube[4];
    private Map map;
    private java.util.Timer timer;
    private int figure;
    private int orientation;

    public Figure(Map map,int figure){
        this.map=map;
        this.figure=figure;
        this.orientation=0;
        for(int i=0;i<4;i++){
            cubes[i]=new Cube(FIGURES[figure][0][i]);
        }
        enable(true);
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    //move(TO_DOWN);
            }
        },1000,1000);

    }

    public Cube[] getCubes() {
        return cubes;
    }

    public void setCubes(Cube[] cubes) {
        this.cubes = cubes;
    }
    private void enable(boolean enable){
        MapUnit[][]mapUnits=map.getMapUnits();
        for(Cube cube:getCubes()){
            try {
                mapUnits[cube.getY()][cube.getX()].setFilled(enable);
            }catch (ArrayIndexOutOfBoundsException e){}
        }
    }

    private boolean canMove(int to){
        MapUnit [][]mapUnits=map.getMapUnits();
        for (Cube cube:cubes) {
            boolean needCheck=true;
            //Проверяем не надодится ли рядом кубик этой же фигуры
            for(Cube temp:cubes){
                if(temp==cube)
                    continue;

                if(to==TO_DOWN) {
                    //Если находятся на одном и том же столбце и если другая загромождает
                    if (cube.getX() == temp.getX() && cube.getY() < temp.getY()) {
                        needCheck = false;
                        break;
                    }
                }
                else if(to==TO_LEFT){
                    if(cube.getY()==temp.getY() && cube.getX()>temp.getX()){
                        needCheck=false;
                        break;
                    }
                }
                else if(to==TO_RIGHT){
                    if(cube.getY()==temp.getY()&&cube.getX()<temp.getX()){
                        needCheck=false;
                        break;
                    }
                }
            }

            if(needCheck) {
                try {
                    if (to == TO_DOWN && (cube.getY() + 1 >= Map.N || mapUnits[cube.getY() + 1][cube.getX()].isFilled()))
                        return false;
                    else if (to == TO_LEFT && (cube.getX() <= 0 || mapUnits[cube.getY()][cube.getX() - 1].isFilled()))
                        return false;
                    else if (to == TO_RIGHT&& (cube.getX() +1>=Map.M || mapUnits[cube.getY()][cube.getX() + 1].isFilled()))
                        return false;
                }catch (ArrayIndexOutOfBoundsException e){

                }
            }
        }
        return true;
    }

    public void move(int to){
        if(canMove(to)) {
            Cube[] cubes = getCubes();
            enable(false);
            //move all cubes down
            if(to==TO_DOWN) {
                for (Cube cube : cubes) {
                    cube.setY(cube.getY() + 1);
                }
            }
            else if(to==TO_LEFT){
                for (Cube cube : cubes) {
                    cube.setX(cube.getX() - 1);
                }
            }
            else if(to==TO_RIGHT){
                for (Cube cube : cubes) {
                    cube.setX(cube.getX() + 1);
                }
            }
            enable(true);
        }
        else if(to==TO_DOWN){
            stop();
            map.next();
        }
    }
    public void setOrientation(){
        enable(false);
        int offsetY=cubes[0].getY() -FIGURES[figure][orientation][0][0];
        int offsetX=cubes[0].getX()-FIGURES[figure][orientation][0][1];
        orientation=orientation==FIGURES[figure].length-1?0:orientation+1;
        for(int i=0;i<4;i++) {
            cubes[i].setY(offsetY+FIGURES[figure][orientation][i][0]);
            cubes[i].setX(offsetX+FIGURES[figure][orientation][i][1]);
        }
        enable(true);
    }
    public void stop(){
        timer.cancel();
    }
}
