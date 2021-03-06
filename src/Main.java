import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hapsi on 13.06.2016.
 */

public class Main implements ActionListener {
    private ScorePanel scorePanel;
    private InfoPanel infoPanel;
    private Map map;
    private EndDialog gameOver;
    public Main(){
        JFrame jFrame =new JFrame("Tetris");
        jFrame.setSize(400,800);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ////Меню//////////////
        JMenuBar jMenuBar=new JMenuBar();
        JMenu menuFile=new JMenu("File");
        jMenuBar.add(menuFile);
        JMenuItem menuFileExit=new JMenuItem("Exit");
        JMenuItem menuItemRestart=new JMenuItem("Restart");
        menuFile.add(menuItemRestart);
        menuFile.add(menuFileExit);
        menuItemRestart.addActionListener(this);
        menuFileExit.addActionListener(this);
        jFrame.setJMenuBar(jMenuBar);
        //////////////////////

        ////////Панель счета////////////
        scorePanel=new ScorePanel();
        jFrame.add(scorePanel,BorderLayout.SOUTH);
        ////////////////////////////////

        ////Панель информации/////////////////
        infoPanel=new InfoPanel();
        jFrame.add(infoPanel,BorderLayout.NORTH);
        //////////////////////////////////

        ////Карта///////////
        map=new Map(this);
        jFrame.add(map);
        map.repaint();
        map.setFocusable(true);
        /////////////////
        jFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Exit"))
            System.exit(0);
        else if(e.getActionCommand().equals("Restart")){
            restart();
        }
    }
    public void restart(){
        scorePanel.setScore(0);
        map.clearLines();
        map.next();
    }
    public void gameOver(){
        if(gameOver==null)
            gameOver=new EndDialog(this);
        gameOver.show();
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }
    public InfoPanel getInfoPanel(){
        return infoPanel;
    }
}
