import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Map extends JPanel {
    public static final int N = 20, M = 10;
    private MapUnit mapUnits[][]= new MapUnit[N][M];
    private Figure figure;
    private int nextFigure;
    private int nextOrientation;
    private Main main;
    private Random random;
    public Map(Main main) {
        this.main=main;
        setLayout(new GridLayout(N, M));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mapUnits[i][j] = new MapUnit();
                add(mapUnits[i][j]);
            }
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    figure.move(Figure.TO_LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    figure.move(Figure.TO_RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    figure.move(Figure.TO_DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    figure.setOrientation();
                }
            }
        });
        random=new Random();
        generateNext();
        next();
    }

    public MapUnit[][]getMapUnits(){
        return mapUnits;
    }

    private void generateNext(){
        nextFigure = random.nextInt(Figure.FIGURES.length);
        nextOrientation=random.nextInt(Figure.FIGURES[nextFigure].length);
        main.getInfoPanel().updateNext(nextFigure);
    }

    public void next() {
        //Обновляем счет
        main.getScorePanel().setScore(main.getScorePanel().getScore()+computeScore(dropLines()));

        //Добавляем фигуру
        if(canCreateFigure())
            figure=new Figure(this,nextFigure,nextOrientation,main.getInfoPanel().getLevel());
        else
            main.gameOver();

        //Обновляем информацию
        generateNext();
    }

    private boolean canCreateFigure(){
        Cube []cubes=new Cube[4];
        for(int i=0;i<4;i++){
            cubes[i]=new Cube(Figure.FIGURES[nextFigure][nextOrientation][i]);
        }
        for(Cube cube:cubes){
            try {
                if (mapUnits[cube.getY()][cube.getX()].isFilled())
                    return false;
            }catch (ArrayIndexOutOfBoundsException e){}
        }
        return true;
    }

    //Вычисляем счет который надо прибавить на основе опущенных строк
    public int computeScore(int droppedCount){
        if(droppedCount==0)
            return 0;
        return computeScore(droppedCount-1)*2+100;
    }

    //Функция убирает заполненные строки и возвращает кол-во убранных строк
    private int dropLines() {
        int droppedCount=0;
        for (int i = 0; i < N; i++) {
            //Проверяем заполнена ли строка
            boolean needDrop = true;
            for (int j = 0; j < M; j++) {
                if (!mapUnits[i][j].isFilled()) {
                    needDrop = false;
                    break;
                }
            }
            if (needDrop) {
                //Удаляем строку и сдвигаем все вниз
                droppedCount++;
                for (int j = 0; j < M; j++) {
                    for (int drop = i; drop >= 0; drop--) {
                        if (drop == 0) {
                            mapUnits[drop][j].setFilled(false);
                        } else {
                            mapUnits[drop][j].setFilled(mapUnits[drop-1][j].isFilled());
                        }
                    }

                }
            }
        }
        return droppedCount;
    }

    //Функция очищаем карту
    public void clearLines(){
        figure.stop();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                mapUnits[i][j].setFilled(false);
            }
        }
    }
}
