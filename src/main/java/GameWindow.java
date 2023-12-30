/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/

import javax.swing.*;

/**
 * main game-window for all the containers
 * extends JFrame
 */
public class GameWindow extends JFrame {
    final int FRAME_WIDTH = 800; // main screen width
    final int FRAME_HEIGHT = 600; // main screen height
    ImageIcon logo = new ImageIcon("./res/images/logo.png"); // icon for main screen corner image
    GamePanel gamePanel; // where movement will take place
    CommentsPanel commentsPanel; // where actions will be listed
    PlayerPanel PlayerPanel; // information of objects on screen
    InformationPanel informationPanel; // game buttons layout screen
    /**
     * GameWindow constructor
     */
    GameWindow() {
        super("Escape Game 2.0"); // calling JFrame constructor to set title
        setSize(FRAME_WIDTH, FRAME_HEIGHT); // setting screen size
        setResizable(false); // make screen not resizable
        setLocationRelativeTo(null); // placing screen in the middle on monitor
        setIconImage(logo.getImage()); // placing logo onto screen
        setLayout(null); // removing default LayoutManager

        // instantiating the panels
        gamePanel = new GamePanel(this);
        commentsPanel = new CommentsPanel();
        PlayerPanel = new PlayerPanel();
        informationPanel = new InformationPanel(gamePanel.player);
        // adding health bar and character images to PlayerPanel
        PlayerPanel.add(gamePanel.player.playerLabel());
        PlayerPanel.add(gamePanel.player.getPlayerHealthBar());
        PlayerPanel.add(gamePanel.player.getPlayerHungerBar());
        PlayerPanel.add(gamePanel.player.getPlayerThirstBar());
        PlayerPanel.add(gamePanel.enemy.enemyLabel());
        PlayerPanel.add(gamePanel.enemy.enemyHealth());
        // adding panels to GameWindow (JFrame)
        add(gamePanel);
        add(commentsPanel);
        add(PlayerPanel);
        add(informationPanel);
        // making GamePanel visible
        setVisible(true);
        // making the X close the game
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
