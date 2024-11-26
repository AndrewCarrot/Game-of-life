public class Game implements Runnable{
    private final GamePanel gamePanel;
    private final ControlPanel controlPanel;
    private Thread thread;
    private final int FPS = 60;
    private final int UPS = 15;
    private final double timePerFrame = 1000000000d/FPS;
    private final double timePerUpdate = 1000000000d/UPS;
    public static boolean running = false;


    public Game(){
        gamePanel = new GamePanel(this);
        controlPanel = new ControlPanel();
        new GameWindow(gamePanel, controlPanel);
        gamePanel.requestFocusInWindow();

    }

    public void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop(){
        running = false;
    }

    public void reset(){
        gamePanel.resetGameState();
        running = false;
    }

    public void updateGame(){
        gamePanel.updateGame();
    }

    @Override
    public void run() {
        long lastCheck = System.currentTimeMillis();
        long previousTime = System.nanoTime();
        long nowMilis;
        long currentTime;
        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        while(running){
            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >= 1){
                updateGame();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames ++;
                deltaF--;
            }

            nowMilis = System.currentTimeMillis();
            if(nowMilis - lastCheck >= 1000){
                lastCheck = nowMilis;
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }
}
