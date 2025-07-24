package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Key;

public class UI {

    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Key key = new Key();
        keyImage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished){

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text = "You found Grandpa's Dragon Ball!";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            int y = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE * 3);
            g2.drawString(text, x, y);

            text = "It took you " + decimalFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.SCREEN_WIDTH / 2 - textLength / 2;
            y = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE * 2);
            g2.drawString(text, x, y);


            gp.gameThread = null;
        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.TILE_SIZE/2, gp.TILE_SIZE/2, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawString("x" + gp.player.keyCount, 74, 65);

        // time
        playTime += (double)1/60;
        g2.drawString("Time: " + decimalFormat.format(playTime), gp.TILE_SIZE * 11, 65);

        // message
        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.TILE_SIZE/2, gp.TILE_SIZE * 5);

            messageCounter++;

            if(messageCounter > 60){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
