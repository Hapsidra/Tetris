import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by hapsi on 13.06.2016.
 */
public class Map extends JPanel {
    public static final int N = 20, M = 10;
    private MapUnit mapUnits[][];
    private Figure figure;
    private int nextFigure;
    private ScorePanel scorePanel;
    private InfoPanel infoPanel;
    private Random random;
    private Main main;
    public Map(ScorePanel scorePanel, InfoPanel infoPanel,Main main) {
        this.scorePanel = scorePanel;
        this.infoPanel = infoPanel;
        this.main=main;
        setLayout(new GridLayout(N, M));
        mapUnits = new MapUnit[N][M];
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

    public void addNext() {
        int k=checkMap();
        if(k==1)
            scorePanel.setScore(scorePanel.getScore()+100);
        else if(k==2)
            scorePanel.setScore(scorePanel.getScore()+300);
        else if(k==3)
            scorePanel.setScore(scorePanel.getScore()+700);
        else if(k==4)
            scorePanel.setScore(scorePanel.getScore()+1500);


        int figureStartI=0,figureStartJ=4;
        if (nextFigure == Figure.O) {
            if(mapUnits[figureStartI][figureStartJ].getState()||mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()){
                main.gameOver();
            }
            else
                figure = new Figure0(this,mapUnits, infoPanel.getLevel());
        } else if (nextFigure == Figure.I) {
            boolean orient = random.nextBoolean();
            if (orient == true) {
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI + 1][figureStartJ].getState() || mapUnits[figureStartI + 2][figureStartJ].getState() || mapUnits[figureStartI + 3][figureStartJ].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureI(this,mapUnits, orient, infoPanel.getLevel());
            } else {
                figureStartJ = 3;
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI][figureStartJ + 2].getState() || mapUnits[figureStartI][figureStartJ + 3].getState()) {
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
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI + 1][figureStartJ].getState() || mapUnits[figureStartI + 2][figureStartJ].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 1) {
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI][figureStartJ + 2].getState() || mapUnits[figureStartI + 1][figureStartJ + 2].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 2) {
                if (mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI + 1][figureStartJ + 1].getState() || mapUnits[figureStartI + 2][figureStartJ].getState() || mapUnits[figureStartI + 2][figureStartJ + 1].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLR(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 3) {
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI + 1][figureStartJ].getState() || mapUnits[figureStartI + 1][figureStartJ + 1].getState() || mapUnits[figureStartI + 1][figureStartJ + 2].getState()) {
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
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI + 1][figureStartJ + 1].getState() || mapUnits[figureStartI + 2][figureStartJ + 1].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 1) {
                if (mapUnits[figureStartI][figureStartJ + 2].getState() || mapUnits[figureStartI + 1][figureStartJ].getState() || mapUnits[figureStartI + 1][figureStartJ + 1].getState() || mapUnits[figureStartI + 1][figureStartJ + 2].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 2) {
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI][figureStartJ + 2].getState() || mapUnits[figureStartI][figureStartJ + 3].getState()) {
                    main.gameOver();
                }
                else
                    figure = new FigureLL(this,mapUnits, orient, infoPanel.getLevel());
            } else if (orient == 3) {
                if (mapUnits[figureStartI][figureStartJ].getState() || mapUnits[figureStartI][figureStartJ + 1].getState() || mapUnits[figureStartI][figureStartJ + 2].getState() || mapUnits[figureStartI + 1][figureStartJ].getState()) {
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
                if(mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI+2][figureStartJ+1].getState()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ+2].getState()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==2){
                if(mapUnits[figureStartI][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI+2][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()){
                    main.gameOver();
                }
                else
                    figure = new FigureT(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==3){
                if(mapUnits[figureStartI][figureStartJ].getState()||mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI][figureStartJ+2].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()){
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
                if(mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI+2][figureStartJ].getState()){
                    main.gameOver();
                }
                else
                    figure = new FigureSL(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ+2].getState()){
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
                if(mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI+2][figureStartJ+1].getState()){
                    main.gameOver();
                }
                else
                    figure = new FigureSR(this,mapUnits, orient, infoPanel.getLevel());
            }
            else if(orient ==1){
                if(mapUnits[figureStartI][figureStartJ+1].getState()||mapUnits[figureStartI+1][figureStartJ].getState()||mapUnits[figureStartI+1][figureStartJ+1].getState()||mapUnits[figureStartI][figureStartJ+2].getState()){
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
    public int checkMap() {
        int k = 0;
        int indexs[] = new int[4];
        for (int i = N - 1; i >= 0; i--) {
            boolean flag = true;
            for (int j = 0; j < M; j++) {
                if (!mapUnits[i][j].getState()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                indexs[k] = i;
                k++;
            }
        }
        //////////////Удалить улаленные////////
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < M; j++)
                mapUnits[indexs[i]][j].setState(false);
        }
        ///////////////////////////////////////
        if (k > 0) {
            ////////Вычисляем верхушку
            int up = -1;
            for (int i = 0; i < N; i++) {
                boolean exit=false;
                for (int j = 0; j < M; j++) {
                    if (mapUnits[i][j].getState()) {
                        exit=true;
                        break;
                    }
                }
                if(exit){
                    up=i;
                    break;
                }
            }
            if(up==-1)
                return k;
            ///////////////////////

            int i=N-1;
            while(i>up){
                boolean empty=true;
                for(int j=0;j<M;j++){
                    if(mapUnits[i][j].getState())
                    {
                        empty=false;
                        break;
                    }
                }
                if(empty){
                    for(int t=i;t>up;t--){
                        for(int j=0;j<M;j++) {
                            if (mapUnits[t-1][j].getState() &&!mapUnits[t][j].getState()) {
                                mapUnits[t][j].setColor(mapUnits[t-1][j].getColor());
                                mapUnits[t][j].setState(true);
                                mapUnits[t-1][j].setState(false);
                            }
                        }
                    }
                    for(int j=0;j<M;j++)
                        mapUnits[up][j].setState(false);
                    up++;
                }
                else{
                    i--;
                }
            }
        }
        return k;
    }
    public void clear(){
        figure.timer.cancel();
        for(int i=0;i<this.mapUnits.length;i++){
            for(int j=0;j<mapUnits[i].length;j++){
                mapUnits[i][j].setState(false);
            }
        }
    }

}
