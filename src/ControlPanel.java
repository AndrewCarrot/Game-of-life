import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton startButton = new JButton("start");
    public ControlPanel(){
        setPreferredSize(new Dimension(GamePanel.WINDOW_SIZE.width, 200));
        startButton.setSize(20,20);
        add(startButton);
    }
}
