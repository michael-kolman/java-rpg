package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tiles = new Tile[10];
        mapTileNumber = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROWS];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/water.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/wall.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tree.png")));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/sand.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/dirt.png")));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void loadMap(){

        try{

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("maps/world_map.txt");
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROWS){

                String line = bufferedReader.readLine();

                while(col < gp.MAX_WORLD_COL){

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col ++;
                }
                if(col == gp.MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROWS){

            int tile = mapTileNumber[worldCol][worldRow];

            // calculating positions for each tile in the world
            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;

            // determining where to draw the tile on the screen
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;  // the + gp.player.screenX keeps the player character near the center of the screen

            // this if statement makes sure the game is only drawing tiles that are visible
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tiles[tile].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
            worldCol ++;

            if(worldCol == gp.MAX_WORLD_COL){
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
