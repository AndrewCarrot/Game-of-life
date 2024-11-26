public class Cell {
    private int x;
    private int y;
    private boolean alive = false;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public boolean equals(Object o){
        if(o instanceof Cell){
            return this.x == ((Cell) o).getX() && this.y == ((Cell) o).getY();
        }
        return false;
    }
}
