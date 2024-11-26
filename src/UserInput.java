import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserInput implements KeyListener, MouseListener {

    private GamePanel gamePanel;

    public UserInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (Game.running) {
                gamePanel.getGame().reset();
            }else{
                gamePanel.getGame().start();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!Game.running){
                gamePanel.updateCells();
                gamePanel.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        gamePanel.changeCellStateOnMouseClick(x, y);
        int aliveNeighbours = gamePanel.getAliveNeighboursCount(
                gamePanel.getCellBasedOnCoordinates(x,y)
        );
        gamePanel.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
