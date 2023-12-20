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
        mapTiles = new MapTile[15];
        getMapTileImage();
    }

    public void getMapTileImage() {

        mapTiles[0] = new MapTile();
        mapTiles[0].name = "grass";
        mapTiles[0].image = new ImageIcon(getClass().getResource("images/grass.png"));
        mapTiles[1] = new MapTile();
        mapTiles[1].name = "wall";
        mapTiles[1].image = new ImageIcon(getClass().getResource("images/wall.png"));
        mapTiles[1].collision = true;
        mapTiles[2] = new MapTile();
        mapTiles[2].image = new ImageIcon(getClass().getResource("images/dirt.png"));
        mapTiles[3] = new MapTile();
        mapTiles[3].name = "water";
        mapTiles[3].image = new ImageIcon(getClass().getResource("images/water.png"));
        mapTiles[3].collision = true;
        mapTiles[4] = new MapTile();
        mapTiles[4].image = new ImageIcon(getClass().getResource("images/playerMale.png"));
        mapTiles[5] = new MapTile();
        mapTiles[5].image = new ImageIcon(getClass().getResource("images/playerFemale.png"));
        mapTiles[6] = new MapTile();
        mapTiles[6].name = "enemy";
        mapTiles[6].image = new ImageIcon(getClass().getResource("images/enemy.png"));
        mapTiles[7] = new MapTile();
        mapTiles[7].name = "exit";
        mapTiles[7].image = new ImageIcon(getClass().getResource("images/exit.png"));
        mapTiles[8] = new MapTile();
        mapTiles[8].name = "health";
        mapTiles[8].image = new ImageIcon(getClass().getResource("images/health.png"));
        mapTiles[8].collision = true;
        mapTiles[9] = new MapTile();
        mapTiles[9].name = "wallLight";
        mapTiles[9].image = new ImageIcon(getClass().getResource("images/wallLight.png"));
        mapTiles[9].collision = true;
        mapTiles[10] = new MapTile();
        mapTiles[10].name = "well";
        mapTiles[10].image = new ImageIcon(getClass().getResource("images/well.png"));
        mapTiles[10].collision = true;
        mapTiles[11] = new MapTile();
        mapTiles[11].name = "rock1";
        mapTiles[11].image = new ImageIcon(getClass().getResource("images/rock1.png"));
        mapTiles[11].collision = true;
        mapTiles[12] = new MapTile();
        mapTiles[12].name = "rock2";
        mapTiles[12].image = new ImageIcon(getClass().getResource("images/rock2.png"));
        mapTiles[12].collision = true;
        mapTiles[13] = new MapTile();
        mapTiles[13].name = "treeTop";
        mapTiles[13].image = new ImageIcon(getClass().getResource("images/treeTop.png"));
        mapTiles[13].collision = true;
        mapTiles[14] = new MapTile();
        mapTiles[14].name = "treeBottom";
        mapTiles[14].image = new ImageIcon(getClass().getResource("images/treeBottom.png"));
        mapTiles[14].collision = true;



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
