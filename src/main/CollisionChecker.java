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

    public int checkObject(Body body, boolean player){

        int index = 999;

        for (int i = 0; i < gp.objects.length; i++) {

            if(gp.objects[i] != null){
                // get body's collision shape coordinates
                body.collisionShape.x = body.worldX + body.collisionShape.x;
                body.collisionShape.y = body.worldY + body.collisionShape.y;

                // get the object's collision shape
                gp.objects[i].collisionShape.x = gp.objects[i].worldX + gp.objects[i].collisionShape.x;
                gp.objects[i].collisionShape.y = gp.objects[i].worldY + gp.objects[i].collisionShape.y;

                switch (body.direction){
                    case "up" :
                        body.collisionShape.y -= body.speed;
                        if(body.collisionShape.intersects(gp.objects[i].collisionShape)){
                            if(gp.objects[i].collision){
                                body.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down" :
                        body.collisionShape.y += body.speed;
                        if(body.collisionShape.intersects(gp.objects[i].collisionShape)){
                            if(gp.objects[i].collision){
                                body.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left" :
                        body.collisionShape.x -= body.speed;
                        if(body.collisionShape.intersects(gp.objects[i].collisionShape)){
                            if(gp.objects[i].collision){
                                body.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right" :
                        body.collisionShape.x += body.speed;
                        if(body.collisionShape.intersects(gp.objects[i].collisionShape)){
                            if(gp.objects[i].collision){
                                body.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }

                body.collisionShape.x = body.collisionDefaultX;
                body.collisionShape.y = body.collisionDefaultY;
                gp.objects[i].collisionShape.x = gp.objects[i].collisionDefaultX;
                gp.objects[i].collisionShape.y = gp.objects[i].collisionDefaultY;
            }
        }
        
        return index;
    }
}
