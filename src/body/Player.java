package body;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Body{

    GamePanel gp;
    KeyHandler kh;

    // player character's position on the screen
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler kh){

        this.gp = gp;
        this.kh = kh;

        screenX = gp.SCREEN_WIDTH / 2 - (gp.TILE_SIZE / 2);
        screenY = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.TILE_SIZE * 14;
        worldY = gp.TILE_SIZE * 13;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/goku_right_2.png")));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(){

        if (kh.upPressed){
            direction = "up";
            worldY -= speed;
        } else if (kh.downPressed) {
            direction = "down";
            worldY += speed;
        } else if (kh.leftPressed) {
            direction = "left";
            worldX -= speed;
        } else if (kh.rightPressed) {
            direction = "right";
            worldX += speed;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up" :
                if(spriteNum == 1){

                    image = up1;
                }
                if(spriteNum == 2){

                    image = up2;
                }
                break;

            case "down" :
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;

            case "left" :
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;

            case "right" :
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
