package main;

import object.Chest;
import object.Door;
import object.Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new Key();
        gp.obj[0].worldX = 37 * gp.TILE_SIZE;
        gp.obj[0].worldY = 12 * gp.TILE_SIZE;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = 37 * gp.TILE_SIZE;
        gp.obj[1].worldY = 27 * gp.TILE_SIZE;

        gp.obj[2] = new Door();
        gp.obj[2].worldX = 22 * gp.TILE_SIZE;
        gp.obj[2].worldY = 29 * gp.TILE_SIZE;

        gp.obj[3] = new Door();
        gp.obj[3].worldX = 8 * gp.TILE_SIZE;
        gp.obj[3].worldY = 19 * gp.TILE_SIZE;

        gp.obj[4] = new Chest();
        gp.obj[4].worldX = 8 * gp.TILE_SIZE;
        gp.obj[4].worldY = 7 * gp.TILE_SIZE;
    }
}
