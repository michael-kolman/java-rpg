package main;

//import org.w3c.dom.ls.LSOutput;

import body.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16 x 16 pixels
    final int SCALE = 3; // scale up our tiles, characters, etc

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48 x 48 pixels
    public final int MAX_SCREEN_COLUMNS = 16;
    public final int MAX_SCREEN_ROWS = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS; // 768 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS; // 576 pixels

    // World settings
    public final int MAX_WORLD_COL = 40;
    public final int MAX_WORLD_ROWS = 30;
    public final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROWS;


    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject[] objects = new SuperObject[10];

    public GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame(){

        assetSetter.setObject();
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1){
                update();
                repaint();
                delta --;
                drawCount ++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //TILE
        tileManager.draw(g2);

        //OBJECTS
        for (SuperObject superObject : objects) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();
    }
}
