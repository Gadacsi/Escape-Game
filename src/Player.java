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
 * user in the game
 */
public class Player extends Entity {
    WASD wasd; //KeyAdapter with KeyEvent methods
    boolean collision = false; // used to check player collision
    GamePanel gamePanel; // the GamePanel extending JPanel
    JLabel playerLabel; // used for information about the player
    JProgressBar playerHealthBar; // a percentage bar showing the health of player
    JProgressBar playerHungerBar;
    JProgressBar playerThirstBar;

    /**
     * constructor for the player class
     */
    Player(WASD wasd, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.wasd = wasd;
        setLabel('U');
        setStrength(100);
        setHealth(100);
        setHunger(100);
        setThirst(100);
        setPlayerIcon(selectCharacter());
        setStartingLocation();
        getPlayerHealthBar();
    }

    /**
     * setting the starting location of the player image
     */
    public void setStartingLocation() {
        mapX = 80;
        mapY = 384;
    }

    /**
     * JOptionPane popup for selection character image
     */
    public ImageIcon selectCharacter() {
        ImageIcon playerIconMale = new ImageIcon(getClass().getResource("playerMale.png"));
        ImageIcon playerIconFemale = new ImageIcon(getClass().getResource("playerFemale.png"));
        String[] characterOption = {"Male", "Female"};
        int choice = JOptionPane.showOptionDialog(null,
                "Pick a character",
                "Character Selection",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                characterOption,
                null);
        if (choice == 0) {
            setPlayerIcon(playerIconMale);
            return playerIconMale;
        } else {
            setPlayerIcon(playerIconFemale);
            return playerIconFemale;
        }
    }

    public void setPlayerIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getPlayerIcon() {
        return this.imageIcon;
    }

    /**
     * showing player health on screen
     */
    public JProgressBar getPlayerHealthBar() {
        playerHealthBar = new JProgressBar();
        playerHealthBar.setBounds(150, 0, 150, 20);
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(new Color(196, 33, 71));
        playerHealthBar.setBackground(Color.black);
        playerHealthBar.setString("Health %");
        playerHealthBar.setValue(this.getHealth());
        return playerHealthBar;
    }

    public JProgressBar getPlayerHungerBar() {
        playerHungerBar = new JProgressBar();
        playerHungerBar.setBounds(150, 20, 150, 20);
        playerHungerBar.setStringPainted(true);
        playerHungerBar.setForeground(new Color(9, 40, 82));
        playerHungerBar.setString("Hunger %");
        playerHungerBar.setValue(this.getHunger());
        return playerHungerBar;
    }

    public JProgressBar getPlayerThirstBar() {
        playerThirstBar = new JProgressBar();
        playerThirstBar.setBounds(150, 40, 150, 20);
        playerThirstBar.setStringPainted(true);
        playerThirstBar.setForeground(new Color(35, 122, 235));
        playerThirstBar.setString("Thirst %");
        playerThirstBar.setValue(this.getThirst());
        return playerThirstBar;
    }

    /**
     * setting name of character and showing information
     */
    public JLabel playerLabel() {
        playerLabel = new JLabel();
        playerLabel.setBounds(0, 0, 150, 60);
        playerLabel.setIcon(getPlayerIcon());
        playerLabel.setText(JOptionPane.showInputDialog(null, "What is your name?", "your name"));
        playerLabel.setIconTextGap(10);
        playerLabel.setHorizontalAlignment(JLabel.CENTER);

        return playerLabel;
    }

    /**
     * method to move player
     */
    public void move() {
        collision = false;
        checkCollision(); // check tiles if they can be walked on
        if (!collision) { // only move player if tile collision is false
            switch (wasd.direction) {
                case 'W' -> mapY = mapY - 16; // moving the player one 16x16 block at a time
                case 'A' -> mapX = mapX - 16;
                case 'S' -> mapY = mapY + 16;
                case 'D' -> mapX = mapX + 16;
            }
        }
    }

    /**
     * player effects on movements
     */
    public void movementCosts() {
        if (!collision) {
            switch (wasd.direction) {
                case 'W', 'D', 'A', 'S' -> {

                    if (this.getHunger() < 1 && this.getThirst() < 1) {
                        this.setHealth(this.getHealth() - 10);
                        this.playerHealthBar.setValue(this.getHealth());
                        if (this.getHealth() == 0)
                            gamePanel.gameOver = true;
                    } else {
                        this.setHunger(this.getHunger() - 1);
                        this.playerHungerBar.setValue(this.getHunger());
                        this.setThirst(this.getThirst() - 2);
                        this.playerThirstBar.setValue(this.getThirst());
                    }

                }
            }
        }
    }

    /**
     * method to check if location is safe to walk onto
     */

    public void checkCollision() {
        int tile;
        switch (wasd.direction) {
            case 'W' -> {
                tile = gamePanel.mapManager.map[(mapY - 16) / 16][mapX / 16];
                // getting location of tile on map
                if (gamePanel.mapManager.mapTiles[tile].collision)
                    // if location of tile has collision (true)
                    collision = true;
                // also set player collision to true
            }
            case 'A' -> {
                tile = gamePanel.mapManager.map[mapY / 16][(mapX - 16) / 16];
                if (gamePanel.mapManager.mapTiles[tile].collision)
                    collision = true;
            }
            case 'S' -> {
                tile = gamePanel.mapManager.map[(mapY + 16) / 16][mapX / 16];
                if (gamePanel.mapManager.mapTiles[tile].collision)
                    collision = true;
            }
            case 'D' -> {
                tile = gamePanel.mapManager.map[mapY / 16][(mapX + 16) / 16];
                if (gamePanel.mapManager.mapTiles[tile].collision)
                    collision = true;
            }
        }
    }

    /**
     * draw player according to map location and player image size
     */

    public void drawPlayer(Graphics2D graphics2D) {
        graphics2D.drawImage(getPlayerIcon().getImage(), mapX, mapY, 16, 16, null);
    }
}
