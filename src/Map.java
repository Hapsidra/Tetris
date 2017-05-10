import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Map extends JPanel {
    public static final int N = 20, M = 10;
    private MapUnit mapUnits[][]= new MapUnit[N][M];
    private Figure figure;
    private int nextFigure;
    private ScorePanel scorePanel;
    private StatusPanel infoPanel;
    private Random random;
    private Main main;
    public Map(ScorePanel scorePanel, StatusPanel infoPanel, Main main) {
        this.scorePanel = scorePanel;
        this.infoPanel = infoPanel;
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
                    figure.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    figure.moveRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    figure.moveDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    figure.setOrientation();
                }
            }
        });
        random=new Random();
        nextFigure = random.nextInt(Figure.FIGURES_COUNT);
        addNext();
    }

    public int computeScore(int droppedCount){
        if(droppedCount==0)
            return 0;
        return computeScore(droppedCount-1)*2+100;
    }

    public void addNext() {
        scorePanel.setScore(scorePanel.getScore()+computeScore(dropLines()));

        int figureStartI=0,figureStartJ=4;
        if (nextFigure == Figure.O) {
            if(mapUnits[figureStartI][figureStartJ].isFilled()||mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()){
                main.gameOver();
            }
            else
                figure = new Figure0(this,mapUnits, infoPanel.getLevel());
        } else if (nextFigure == Figure.I) {
            boolean orient = random.nextBoolean();
            if (orient) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI + 1][figureStartJ].isFilled() || mapUnits[figureStartI + 2][figureStartJ].isFilled() || mapUnits[figureStartI + 3][figureStartJ].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureI(this,mapUnits, orient, infoPanel.getLevel());
            } else {
                figureStartJ = 3;
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI][figureStartJ + 2].isFilled() || mapUnits[figureStartI][figureStartJ + 3].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureI(this,mapUnits, orient, infoPanel.getLevel());
            }
        }
        else if (nextFigure == Figure.LR) {
            int  orient = random.nextInt(4);

            //Проверка на умещаемость
            if (orient == 0) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 1][figureStartJ].isFilled() || mapUnits[figureStartI + 2][figureStartJ].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 1) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI][figureStartJ + 2].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 2].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 2) {
                if (mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 2][figureStartJ].isFilled() || mapUnits[figureStartI + 2][figureStartJ + 1].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 3) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI + 1][figureStartJ].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 2].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            }
            //Проверка на умещаемость

        }
        else if (nextFigure == Figure.LL) {
            int  orient = random.nextInt(4);
            //Проверка на умещаемость
            if (orient == 0) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 2][figureStartJ + 1].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 1) {
                if (mapUnits[figureStartI][figureStartJ + 2].isFilled() || mapUnits[figureStartI + 1][figureStartJ].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 1].isFilled() || mapUnits[figureStartI + 1][figureStartJ + 2].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 2) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI][figureStartJ + 2].isFilled() || mapUnits[figureStartI][figureStartJ + 3].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 3) {
                if (mapUnits[figureStartI][figureStartJ].isFilled() || mapUnits[figureStartI][figureStartJ + 1].isFilled() || mapUnits[figureStartI][figureStartJ + 2].isFilled() || mapUnits[figureStartI + 1][figureStartJ].isFilled()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            }
            //Проверка на умещаемость

        }
        else if (nextFigure == Figure.T) {
            int  orient = random.nextInt(4);

            //Проверка на умещаемость
            if(orient==0) {
                if(mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI+2][figureStartJ+1].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ+2].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==2){
                if(mapUnits[figureStartI][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI+2][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==3){
                if(mapUnits[figureStartI][figureStartJ].isFilled()||mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI][figureStartJ+2].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            //Проверка на умещаемость

        }
        else if (nextFigure == Figure.SL) {
            int  orient = random.nextInt(2);

            ////Проверка на умещаемость
            if(orient==0) {
                if(mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI+2][figureStartJ].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureSL(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ+2].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureSL(this,mapUnits, orient, infoPanel.getLevel());
            }
            ////////////////////////////

        }
        else if (nextFigure == Figure.SR) {
            int  orient = random.nextInt(2);

            ////Проверка на умещаемость
            if(orient==0) {
                if(mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI+2][figureStartJ+1].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureSR(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].isFilled()||mapUnits[figureStartI+1][figureStartJ].isFilled()||mapUnits[figureStartI+1][figureStartJ+1].isFilled()||mapUnits[figureStartI][figureStartJ+2].isFilled()){
                    main.gameOver();
                }
                else
                    figure = new FigureSR(this,mapUnits, orient, infoPanel.getLevel());
            }
            ////////////////////////////
        }

        nextFigure = random.nextInt(Figure.FIGURES_COUNT);
        infoPanel.updateNext(nextFigure);
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
                            mapUnits[drop][j].setColor(mapUnits[drop-1][j].getColor());
                        }
                    }

                }
            }
        }
        return droppedCount;
    }
    //Функция очищаем карту
    public void clear(){
        figure.timer.cancel();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                mapUnits[i][j].setFilled(false);
            }
        }
    }

}
