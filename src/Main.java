import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hapsi on 13.06.2016.
 */

public class Main implements ActionListener {
    private JFrame jFrame;
    private Map map;
    private InfoPanel infoPanel;
    private ScorePanel scorePanel;
    public Main(){
        jFrame =new JFrame("Tetris");
        jFrame.setSize(350,600);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ////Меню//////////////
        JMenuBar jMenuBar=new JMenuBar();
        JMenu menuFile=new JMenu("File");
        jMenuBar.add(menuFile);
        JMenuItem menuFileExit=new JMenuItem("Exit");
        menuFile.add(menuFileExit);
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
        map=new Map(scorePanel,infoPanel);
        jFrame.add(map);
        map.repaint();
        map.setFocusable(true);
        /////////////////
        jFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Exit")
            System.exit(0);
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }


}
