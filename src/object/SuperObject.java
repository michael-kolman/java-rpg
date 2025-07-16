package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle collisionShape = new Rectangle(0, 0, 48, 48);
    public int collisionDefaultX = 0;
    public int collisionDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp){

        // determining where to draw the objects on the screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;  // the + gp.player.screenX keeps the player character near the center of the screen

        // this if statement makes sure the game is only drawing objects that are visible
        if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY){

            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
