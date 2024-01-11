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
import java.util.Objects;

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
        setStrength(100);
        setHealth(100);
        setHunger(100);
        setThirst(100);
        makePlayerIcons();
        setPlayerIcon(selectCharacter());
        setStartingLocation();

    }

    /**
     * setting the starting location of the player image
     */
    public void setStartingLocation() {
        mapX = 80;
        mapY = 368;
    }

    public void makePlayerIcons() {
        imageIcons = new ImageIcon[10];
        imageIcons[0] = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/playerMale_plain.png")));
        imageIcons[1] = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/playerMale_sword.png")));
    }

    public ImageIcon selectPlayerIcon(int selection) {
        switch (selection) {
            case 0 -> {
                return imageIcons[0];
            }
            case 1 -> {
                return imageIcons[1];
            }
        }
        return imageIcons[selection];
    }

    public void setPlayerIcon(ImageIcon imageIcon) {
        this.icon = imageIcon;
    }

    public ImageIcon getPlayerIcon() {
        return icon;
    }

    /**
     * JOptionPane popup for selection character image
     */
    public ImageIcon selectCharacter() {
        ImageIcon playerIconMale = selectPlayerIcon(0);
        ImageIcon playerIconFemale = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/playerFemale.png")));
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
        playerHungerBar.setForeground(new Color(51, 32, 22));
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
    public JLabel getPlayerLabel() {
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
                    gamePanel.playSound(0);
                    this.setStepsTaken(getStepsTaken() + 1);
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
                if (gamePanel.mapManager.mapTiles[tile].collision)
                    collision = true;
                if ((mapY - 16) == gamePanel.itemManager.itemTiles[3].mapY && mapX == gamePanel.itemManager.itemTiles[3].mapX && gamePanel.itemManager.itemTiles[3].show)
                    //closed door
                    collision = true;
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

    public void checkForItem() {
        String name = "nothing";
        switch (wasd.direction) {
            case 'W' -> {

                if ((mapY - 16) == gamePanel.itemManager.itemTiles[0].mapY && mapX == gamePanel.itemManager.itemTiles[0].mapX && gamePanel.itemManager.itemTiles[0].show)
                    // backpack
                    name = gamePanel.itemManager.itemTiles[0].name;
                if ((mapY - 16) == gamePanel.itemManager.itemTiles[1].mapY && mapX == gamePanel.itemManager.itemTiles[1].mapX && gamePanel.itemManager.itemTiles[1].show)
                    // key
                    name = gamePanel.itemManager.itemTiles[1].name;
                if ((mapY - 16) == gamePanel.itemManager.itemTiles[2].mapY && mapX == gamePanel.itemManager.itemTiles[2].mapX && gamePanel.itemManager.itemTiles[2].show)
                    // sword
                    name = gamePanel.itemManager.itemTiles[2].name;
                if ((mapY - 16) == 336 && mapX == 160)
                    name = "secret light";
                if ((mapY - 16) == gamePanel.itemManager.itemTiles[3].mapY && mapX == gamePanel.itemManager.itemTiles[3].mapX && gamePanel.itemManager.itemTiles[3].show)
                    name = "door closed";

                switch (name) {
                    case "backpack" -> {
                        gamePanel.itemManager.itemTiles[0].show = false;
                        gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[0]);
                        gamePanel.gameWindow.commentsPanel.repaint();

                    }
                    case "key" -> {
                        if (!gamePanel.itemManager.itemTiles[0].show) {
                            gamePanel.itemManager.itemTiles[1].show = false;
                            gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[1]);
                            gamePanel.gameWindow.commentsPanel.repaint();
                        }

                    }
                    case "sword" -> {
                        gamePanel.itemManager.itemTiles[2].show = false;
                        gamePanel.player.setPlayerIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("playerMale_sword.png"))));
                        gamePanel.gameWindow.playerPanel.repaint();
                    }
                    case "secret light" -> gamePanel.mapManager.loadMap(gamePanel.mapManager.maps[1]);

                    case "door closed" -> {
                        if (gamePanel.gameWindow.commentsPanel.buttons[1].isValid()) {
                            gamePanel.itemManager.itemTiles[3].show = false;
                            gamePanel.itemManager.itemTiles[4].show = true;
                        }


                    }
                }
            }
            case 'A' -> {

                if (mapY == gamePanel.itemManager.itemTiles[0].mapY && (mapX - 16) == gamePanel.itemManager.itemTiles[0].mapX && gamePanel.itemManager.itemTiles[0].show)
                    //backpack
                    name = gamePanel.itemManager.itemTiles[0].name;
                if (mapY == gamePanel.itemManager.itemTiles[1].mapY && (mapX - 16) == gamePanel.itemManager.itemTiles[1].mapX && gamePanel.itemManager.itemTiles[1].show)
                    //key
                    name = gamePanel.itemManager.itemTiles[1].name;
                if (mapY == gamePanel.itemManager.itemTiles[2].mapY && (mapX - 16) == gamePanel.itemManager.itemTiles[2].mapX && gamePanel.itemManager.itemTiles[2].show)
                    //sword
                    name = gamePanel.itemManager.itemTiles[2].name;
                if (mapY == 336 && (mapX - 16) == 160)
                    name = "secret light";
                if (mapY == gamePanel.itemManager.itemTiles[3].mapY && (mapX - 16) == gamePanel.itemManager.itemTiles[3].mapX && gamePanel.itemManager.itemTiles[3].show)
                    name = "door closed";

                switch (name) {
                    case "backpack" -> {
                        gamePanel.itemManager.itemTiles[0].show = false;
                        gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[0]);
                        gamePanel.gameWindow.commentsPanel.repaint();

                    }
                    case "key" -> {
                        if (!gamePanel.itemManager.itemTiles[0].show) {
                            gamePanel.itemManager.itemTiles[1].show = false;
                            gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[1]);
                            gamePanel.gameWindow.commentsPanel.repaint();
                        }
                    }
                    case "sword" -> {
                        gamePanel.itemManager.itemTiles[2].show = false;
                        gamePanel.player.playerLabel.setIcon(selectPlayerIcon(1));
                        gamePanel.player.setPlayerIcon(selectPlayerIcon(1));
                    }
                }
            }
            case 'S' -> {

                if ((mapY + 16) == gamePanel.itemManager.itemTiles[0].mapY && mapX == gamePanel.itemManager.itemTiles[0].mapX && gamePanel.itemManager.itemTiles[0].show)
                    name = gamePanel.itemManager.itemTiles[0].name;
                if ((mapY + 16) == gamePanel.itemManager.itemTiles[1].mapY && mapX == gamePanel.itemManager.itemTiles[1].mapX && gamePanel.itemManager.itemTiles[1].show)
                    name = gamePanel.itemManager.itemTiles[1].name;
                if ((mapY + 16) == gamePanel.itemManager.itemTiles[2].mapY && mapX == gamePanel.itemManager.itemTiles[2].mapX && gamePanel.itemManager.itemTiles[2].show)
                    name = gamePanel.itemManager.itemTiles[2].name;
                if ((mapY + 16) == 336 && mapX == 160)
                    name = "secret light";
                if ((mapY + 16) == gamePanel.itemManager.itemTiles[3].mapY && mapX == gamePanel.itemManager.itemTiles[3].mapX && gamePanel.itemManager.itemTiles[3].show)
                    name = "door closed";

                switch (name) {
                    case "backpack" -> {
                        gamePanel.itemManager.itemTiles[0].show = false;
                        gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[0]);
                        gamePanel.gameWindow.commentsPanel.repaint();

                    }
                    case "key" -> {
                        if (!gamePanel.itemManager.itemTiles[0].show) {
                            gamePanel.itemManager.itemTiles[1].show = false;
                            gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[1]);
                            gamePanel.gameWindow.commentsPanel.repaint();
                        }
                    }
                    case "sword" -> {
                        gamePanel.itemManager.itemTiles[2].show = false;
                        gamePanel.player.playerLabel.setIcon(selectPlayerIcon(1));
                        gamePanel.player.setPlayerIcon(selectPlayerIcon(1));
                    }
                }
            }
            case 'D' -> {

                if (mapY == gamePanel.itemManager.itemTiles[0].mapY && (mapX + 16) == gamePanel.itemManager.itemTiles[0].mapX && gamePanel.itemManager.itemTiles[0].show)
                    name = gamePanel.itemManager.itemTiles[0].name;
                if (mapY == gamePanel.itemManager.itemTiles[1].mapY && (mapX + 16) == gamePanel.itemManager.itemTiles[1].mapX && gamePanel.itemManager.itemTiles[1].show)
                    name = gamePanel.itemManager.itemTiles[1].name;
                if (mapY == gamePanel.itemManager.itemTiles[2].mapY && (mapX + 16) == gamePanel.itemManager.itemTiles[2].mapX && gamePanel.itemManager.itemTiles[2].show)
                    name = gamePanel.itemManager.itemTiles[2].name;
                if (mapY == 336 && (mapX + 16) == 160)
                    name = "secret light";
                if (mapY == gamePanel.itemManager.itemTiles[3].mapY && (mapX + 16) == gamePanel.itemManager.itemTiles[3].mapX && gamePanel.itemManager.itemTiles[3].show)
                    name = "door closed";

                switch (name) {
                    case "backpack" -> {
                        gamePanel.itemManager.itemTiles[0].show = false;
                        gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[0]);
                        gamePanel.gameWindow.commentsPanel.repaint();

                    }
                    case "key" -> {
                        if (!gamePanel.itemManager.itemTiles[0].show) {
                            gamePanel.itemManager.itemTiles[1].show = false;
                            gamePanel.gameWindow.commentsPanel.add(gamePanel.gameWindow.commentsPanel.buttons[1]);
                            gamePanel.gameWindow.commentsPanel.repaint();
                        }
                    }
                    case "sword" -> {
                        gamePanel.itemManager.itemTiles[2].show = false;
                        gamePanel.player.playerLabel.setIcon(selectPlayerIcon(1));
                        gamePanel.player.setPlayerIcon(selectPlayerIcon(1));
                    }
                }
            }
        }
    }

    /**
     * checking for map items to interact with
     */
    public void checkForMapItem() {
        int tile;

        switch (wasd.direction) {
            case 'W' -> {
                tile = gamePanel.mapManager.map[(mapY - 16) / 16][mapX / 16];
                // getting location of tile in direction of next step

                switch (gamePanel.mapManager.mapTiles[tile].name) {
                    case "health" -> {
                        gamePanel.playSound(2);
                        this.setHealth(100);
                        this.playerHealthBar.setValue(this.getHealth());
                    }
                    case "water", "well" -> {
                        gamePanel.playSound(3);
                        this.setThirst(100);
                        this.playerThirstBar.setValue(this.getThirst());
                    }
                    case "treeTop", "treeBottom" -> {
                        gamePanel.playSound(5);
                        this.setHunger(100);
                        this.playerHungerBar.setValue(this.getHunger());
                    }
                    case "exit" -> {
                        gamePanel.winGame = true;
                        gamePanel.gameOver = true;
                    }
                }
            }
            case 'A' -> {
                tile = gamePanel.mapManager.map[mapY / 16][(mapX - 16) / 16];

                switch (gamePanel.mapManager.mapTiles[tile].name) {
                    case "health" -> {
                        gamePanel.playSound(2);
                        this.setHealth(100);
                        this.playerHealthBar.setValue(this.getHealth());
                    }
                    case "water", "well" -> {
                        gamePanel.playSound(3);
                        this.setThirst(100);
                        this.playerThirstBar.setValue(this.getThirst());
                    }
                    case "treeTop", "treeBottom" -> {
                        gamePanel.playSound(5);
                        this.setHunger(100);
                        this.playerHungerBar.setValue(this.getHunger());
                    }
                    case "exit" -> {
                        gamePanel.winGame = true;
                        gamePanel.gameOver = true;
                    }
                }
            }
            case 'S' -> {
                tile = gamePanel.mapManager.map[(mapY + 16) / 16][mapX / 16];

                switch (gamePanel.mapManager.mapTiles[tile].name) {
                    case "health" -> {
                        gamePanel.playSound(2);
                        this.setHealth(100);
                        this.playerHealthBar.setValue(this.getHealth());
                    }
                    case "water", "well" -> {
                        gamePanel.playSound(3);
                        this.setThirst(100);
                        this.playerThirstBar.setValue(this.getThirst());
                    }
                    case "treeTop", "treeBottom" -> {
                        gamePanel.playSound(5);
                        this.setHunger(100);
                        this.playerHungerBar.setValue(this.getHunger());
                    }
                    case "exit" -> {
                        gamePanel.winGame = true;
                        gamePanel.gameOver = true;
                    }
                }
            }
            case 'D' -> {
                tile = gamePanel.mapManager.map[mapY / 16][(mapX + 16) / 16];

                switch (gamePanel.mapManager.mapTiles[tile].name) {
                    case "health" -> {
                        gamePanel.playSound(2);
                        this.setHealth(100);
                        this.playerHealthBar.setValue(this.getHealth());
                    }
                    case "water", "well" -> {
                        gamePanel.playSound(3);
                        this.setThirst(100);
                        this.playerThirstBar.setValue(this.getThirst());
                    }
                    case "treeTop", "treeBottom" -> {
                        gamePanel.playSound(5);
                        this.setHunger(100);
                        this.playerHungerBar.setValue(this.getHunger());
                    }
                    case "exit" -> {
                        gamePanel.winGame = true;
                        gamePanel.gameOver = true;
                    }
                }
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
