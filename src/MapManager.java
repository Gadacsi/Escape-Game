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

/**
 * mapManager constructor
 */
public class MapManager {
    GameMap gameMap = new GameMap(); //
    int[][] map;
    public MapTile[] mapTiles;

    public MapManager() {
        map = gameMap.getMap();
        mapTiles = new MapTile[10];
        getMapTileImage();
    }

    public void getMapTileImage() {

        mapTiles[0] = new MapTile();
        mapTiles[0].image = new ImageIcon(getClass().getResource("grass.png"));
        mapTiles[1] = new MapTile();
        mapTiles[1].image = new ImageIcon(getClass().getResource("wall.png"));
        mapTiles[1].collision = true;
        mapTiles[2] = new MapTile();
        mapTiles[2].image = new ImageIcon(getClass().getResource("dirt.png"));
        mapTiles[3] = new MapTile();
        mapTiles[3].image = new ImageIcon(getClass().getResource("water.png"));
        mapTiles[3].collision = true;
        mapTiles[4] = new MapTile();
        mapTiles[4].image = new ImageIcon(getClass().getResource("playerMale.png"));
        mapTiles[5] = new MapTile();
        mapTiles[5].image = new ImageIcon(getClass().getResource("playerFemale.png"));
        mapTiles[6] = new MapTile();
        mapTiles[6].image = new ImageIcon(getClass().getResource("enemy.png"));
        mapTiles[7] = new MapTile();
        mapTiles[7].image = new ImageIcon(getClass().getResource("exit.png"));
        mapTiles[8] = new MapTile();
        mapTiles[8].image = new ImageIcon(getClass().getResource("health.png"));
        mapTiles[8].collision = true;
        mapTiles[9] = new MapTile();
        mapTiles[9].image = new ImageIcon(getClass().getResource("wallLight.png"));
        mapTiles[9].collision = true;


    }

    public void drawMap(Graphics2D graphics2D) {

        int col = 0; // coordinates on the map
        int row = 0;
        int x = 0; // coordinates on the screen
        int y = 0;

        while (row < 25 && col < 49) {

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
