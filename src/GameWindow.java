import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel, ControlPanel controlPanel){
        jFrame = new JFrame("TheGame");
        jFrame.getContentPane().add(controlPanel, BorderLayout.NORTH);
        jFrame.getContentPane().add(gamePanel, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
