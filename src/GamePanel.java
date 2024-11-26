import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public static final Dimension WINDOW_SIZE = new Dimension(1000,800);
    private static final int cellSize = 20;

    private final UserInput userInput;
    private final Game game;
    private ArrayList<Cell> cells;

    public GamePanel(Game game){
        setPreferredSize(WINDOW_SIZE);
        userInput = new UserInput(this);
        addKeyListener(userInput);
        addMouseListener(userInput);
        this.game = game;
        initCells();
    }

    public void updateGame(){
        updateCells();
    }

    public void resetGameState(){
        for(Cell c: cells){
            c.setAlive(false);
        }
        repaint();
    }


    @Override
    public void paintComponent(Graphics g){
        revalidate();


        drawCells(g);
        // TODO this should be painted only once, and repainted only when resolution changes
        drawGrid(g);
    }

    public void initCells(){
        cells = new ArrayList<>();
        for(int row=0; row < WINDOW_SIZE.height/cellSize; row++){
            for(int col=0; col<WINDOW_SIZE.width/cellSize; col++){
                cells.add(new Cell(col*cellSize,row*cellSize));
            }
        }
    }

    public void drawGrid(Graphics g){
        for(int i=0; i<WINDOW_SIZE.width/cellSize; i++){
            g.setColor(Color.GRAY);
            if(i%10 == 0)
                g.setColor(Color.BLACK);
            g.drawLine(0,i*cellSize,WINDOW_SIZE.width,i*cellSize);
            g.drawLine(i*cellSize, 0, i*cellSize, WINDOW_SIZE.height);
        }
    }

    public void drawCells(Graphics g){
        for(Cell c:cells){
            if(c.isAlive()) {
                g.setColor(Color.BLACK);
                g.fillRect(c.getX(), c.getY(), cellSize, cellSize);
            }else{
                g.setColor(Color.WHITE);
                g.fillRect(c.getX(), c.getY(), cellSize, cellSize);
            }
        }
    }

    public void changeCellStateOnMouseClick(int x, int y){
        Cell c = getCellBasedOnCoordinates(x,y);
        try {
            c.setAlive(!c.isAlive());
        }catch(NullPointerException _){

        }
    }

    public Cell getCellBasedOnCoordinates(int x, int y) {
        for (Cell c : cells) {
            if (
                    x > c.getX() &&
                            x < c.getX() + cellSize &&
                            y > c.getY() &&
                            y < c.getY() + cellSize
            ) return c;
        }
        return null;
    }

    public void updateCells(){
        ArrayList<Cell> updatedCells = new ArrayList<>();

        for(Cell c:cells){
            updatedCells.add(new Cell(c.getX(),c.getY()));
            int aliveNeighbours = getAliveNeighboursCount(c);
            if(c.isAlive()) {
                switch (aliveNeighbours) {
                    case 2,3:
                        updatedCells.getLast().setAlive(true);
                        break;
                    default:
                        updatedCells.getLast().setAlive(false);
                        break;
                }
            }else if(aliveNeighbours == 3) {
                updatedCells.getLast().setAlive(true);
            }
        }

        cells = updatedCells;
    }


    public int getAliveNeighboursCount(Cell c){

        int count = 0;

        for(Cell cell:cells){
            if(
                    cell.isAlive() &&
                            !cell.equals(c) &&
                    cell.getX() >= c.getX()-cellSize &&
                            cell.getX() <= c.getX() + cellSize &&
                            cell.getY() >= c.getY()-cellSize &&
                            cell.getY() <= c.getY() + cellSize
            ){
                count++;
            }
        }

        return count;

    }


    public Game getGame(){
        return game;
    }

}
