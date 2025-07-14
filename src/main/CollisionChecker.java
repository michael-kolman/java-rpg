package main;

import body.Body;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }

    public void checkTile(Body body){

        int bodyLeftX = body.worldX + body.collisionShape.x;
        int bodyRightX = body.worldX + body.collisionShape.x + body.collisionShape.width;
        int bodyTopY = body.worldY + body.collisionShape.y;
        int bodyBottomY = body.worldY + body.collisionShape.y + body.collisionShape.height;

        int bodyLeftColumn = bodyLeftX / gp.TILE_SIZE;
        int bodyRightColumn = bodyRightX / gp.TILE_SIZE;
        int bodyTopRow = bodyTopY / gp.TILE_SIZE;
        int bodyBottomRow = bodyBottomY / gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (body.direction){
            case "up":
                bodyTopRow = (bodyTopY - body.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileManager.mapTileNumber[bodyLeftColumn][bodyTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[bodyRightColumn][bodyTopRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    body.collisionOn = true;
                }
                break;
            case "down":
                bodyBottomRow = (bodyBottomY + body.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileManager.mapTileNumber[bodyLeftColumn][bodyBottomRow];
                tileNum2 = gp.tileManager.mapTileNumber[bodyRightColumn][bodyBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    body.collisionOn = true;
                }
                break;
            case "left":
                bodyLeftColumn = (bodyLeftX - body.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileManager.mapTileNumber[bodyLeftColumn][bodyTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[bodyLeftColumn][bodyBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    body.collisionOn = true;
                }
                break;
            case "right":
                bodyRightColumn = (bodyRightX + body.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileManager.mapTileNumber[bodyRightColumn][bodyTopRow];
                tileNum2 = gp.tileManager.mapTileNumber[bodyRightColumn][bodyBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    body.collisionOn = true;
                }
                break;
        }
    }
}
