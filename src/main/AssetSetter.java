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

        gp.objects[0] = new Key();
        gp.objects[0].worldX = 37 * gp.TILE_SIZE;
        gp.objects[0].worldY = 12 * gp.TILE_SIZE;

        gp.objects[1] = new Key();
        gp.objects[1].worldX = 37 * gp.TILE_SIZE;
        gp.objects[1].worldY = 27 * gp.TILE_SIZE;

        gp.objects[2] = new Door();
        gp.objects[2].worldX = 22 * gp.TILE_SIZE;
        gp.objects[2].worldY = 29 * gp.TILE_SIZE;

        gp.objects[3] = new Door();
        gp.objects[3].worldX = 8 * gp.TILE_SIZE;
        gp.objects[3].worldY = 19 * gp.TILE_SIZE;

        gp.objects[4] = new Chest();
        gp.objects[4].worldX = 8 * gp.TILE_SIZE;
        gp.objects[4].worldY = 7 * gp.TILE_SIZE;
    }
}
