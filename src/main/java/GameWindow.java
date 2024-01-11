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
    final int FRAME_WIDTH = 800; // 1920; // main screen width
    final int FRAME_HEIGHT = 600; // 1080; // main screen height
//    final int FRAME_WIDTH = 1920;
//    final int FRAME_HEIGHT = 1080;
    ImageIcon logo = new ImageIcon("./res/images/logo.png"); // icon for main screen corner image
    GamePanel gamePanel; // where movement will take place
    CommentsPanel commentsPanel; // where actions will be listed
    PlayerPanel playerPanel; // information of objects on screen
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
        playerPanel = new PlayerPanel();
        informationPanel = new InformationPanel(gamePanel.player);
        // adding health bar and character images to PlayerPanel
        playerPanel.add(gamePanel.player.getPlayerLabel());
        playerPanel.add(gamePanel.player.getPlayerHealthBar());
        playerPanel.add(gamePanel.player.getPlayerHungerBar());
        playerPanel.add(gamePanel.player.getPlayerThirstBar());
//        playerPanel.add(gamePanel.enemy.enemyLabel());
//        playerPanel.add(gamePanel.enemy.enemyHealth());


        // adding panels to GameWindow (JFrame)
        add(gamePanel);
        add(commentsPanel);
        add(playerPanel);
        add(informationPanel);
        // making GamePanel visible
        setVisible(true);
        // making the X close the game
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
