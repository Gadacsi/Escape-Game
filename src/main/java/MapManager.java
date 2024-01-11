/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * mapManager constructor
 */
public class MapManager {
    public int[][] map;

    public String[] maps;
    public MapTile[] mapTiles;

    public MapManager() {
        maps = new String[] {"level1.txt", "level1_secret.txt"};
        map = new int[25][49];
        loadMap(maps[0]);
        mapTiles = new MapTile[15];
        getMapTileImage();
    }

    /**
     * each map tile images placed into an array
     */
    public void getMapTileImage() {
//        mapTiles[0] = new MapTile();
//        mapTiles[0].name = "grass 0";
//        mapTiles[0].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/grass_0.png")));
//        mapTiles[1] = new MapTile();
//        mapTiles[1].name = "grass 1";
//        mapTiles[1].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/grass_1.png")));
//        mapTiles[2] = new MapTile();
//        mapTiles[2].name = "grass 2";
//        mapTiles[2].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/grass_2.png")));


        mapTiles[0] = new MapTile();
        mapTiles[0].name = "grass";
        mapTiles[0].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/grass.png")));
        mapTiles[1] = new MapTile();
        mapTiles[1].name = "wall";
        mapTiles[1].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/wall.png")));
        mapTiles[1].collision = true;
        mapTiles[2] = new MapTile();
        mapTiles[2].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/dirt.png")));
        mapTiles[3] = new MapTile();
        mapTiles[3].name = "water";
        mapTiles[3].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/water.png")));
        mapTiles[3].collision = true;
        mapTiles[4] = new MapTile();
        mapTiles[4].name = "exit";
        mapTiles[4].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/exit.png")));
        mapTiles[5] = new MapTile();
        mapTiles[5].name = "health";
        mapTiles[5].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/health.png")));
        mapTiles[5].collision = true;
        mapTiles[6] = new MapTile();
        mapTiles[6].name = "wallLight";
        mapTiles[6].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/wallLight.png")));
        mapTiles[6].collision = true;
        mapTiles[7] = new MapTile();
        mapTiles[7].name = "well";
        mapTiles[7].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/well.png")));
        mapTiles[7].collision = true;
        mapTiles[8] = new MapTile();
        mapTiles[8].name = "rock1";
        mapTiles[8].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/rock1.png")));
        mapTiles[8].collision = true;
        mapTiles[9] = new MapTile();
        mapTiles[9].name = "rock2";
        mapTiles[9].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/rock2.png")));
        mapTiles[9].collision = true;
        mapTiles[10] = new MapTile();
        mapTiles[10].name = "treeTop";
        mapTiles[10].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/treeTop.png")));
        mapTiles[10].collision = true;
        mapTiles[11] = new MapTile();
        mapTiles[11].name = "treeBottom";
        mapTiles[11].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/treeBottom.png")));
        mapTiles[11].collision = true;
//        mapTiles[12] = new MapTile();
//        mapTiles[12].name = "door";
//        mapTiles[12].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("map_tiles/door.png")));
//        mapTiles[12].collision = true;

    }

    public void loadMap(String maps) {
        try {

            InputStream inputStream = getClass().getResourceAsStream("maps/"+maps);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int row = 0;
            int col = 0;

            while (col < 49 && row < 24) {

                String line = bufferedReader.readLine();

                while (col < 49) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    map[row][col] = num;
                    col++;
                }
                if (col == 49) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception ignored) {
        }
    }

    public void drawMap(Graphics2D graphics2D) {
        int col = 0; // coordinates on the map
        int row = 0;
        int x = 0; // coordinates on the screen
        int y = 0;

        while (row < 24 && col < 49) {

            int tile = map[row][col];

            graphics2D.drawImage(mapTiles[tile].image.getImage(), x, y, 16, 16, null);
            col++;
            x += 16;

            if (col == 49) {
                col = 0;
                x = 0;
                row++;
                y += 16;
            }
        }
    }
}
