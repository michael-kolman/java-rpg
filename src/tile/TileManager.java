package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNumber;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tiles = new Tile[10];
        mapTileNumber = new int[gp.MAX_SCREEN_COLUMNS][gp.MAX_SCREEN_ROWS];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/water.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/wall.png")));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void loadMap(){

        try{

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("maps/test_map.txt");
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gp.MAX_SCREEN_COLUMNS && row < gp.MAX_SCREEN_ROWS){

                String line = bufferedReader.readLine();

                while(col < gp.MAX_SCREEN_COLUMNS){

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col ++;
                }
                if(col == gp.MAX_SCREEN_COLUMNS){
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.MAX_SCREEN_COLUMNS && row < gp.MAX_SCREEN_ROWS){

            int tile = mapTileNumber[col][row];

            g2.drawImage(tiles[tile].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col ++;
            x += gp.TILE_SIZE;

            if(col == gp.MAX_SCREEN_COLUMNS){
                col = 0;
                x = 0;
                row ++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
