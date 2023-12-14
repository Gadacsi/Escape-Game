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
 * main game window house all the containers extending JFrame
 */
public class GameWindow extends JFrame {
    final int FRAME_WIDTH = 800; // main screen width
    final int FRAME_HEIGHT = 600; // main screen height
    ImageIcon logo = new ImageIcon("./res/logo.png"); // icon for main screen corner image
    GamePanel gamePanel; // where movement will take place
    CommentsPanel commentsPanel; // where actions will be listed
    InformationPanel informationPanel; // information of objects on screen
    ButtonsPanel buttonsPanel; // game buttons layout screen

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
        gamePanel = new GamePanel();
        commentsPanel = new CommentsPanel();
        informationPanel = new InformationPanel();
        buttonsPanel = new ButtonsPanel();
        // adding health bar and character images to informationPanel
        informationPanel.add(gamePanel.player.playerLabel());
        informationPanel.add(gamePanel.player.getPlayerHealthBar());
        informationPanel.add(gamePanel.player.getPlayerHungerBar());
        informationPanel.add(gamePanel.player.getPlayerThirstBar());
        informationPanel.add(gamePanel.enemy.enemyLabel());
        informationPanel.add(gamePanel.enemy.enemyHealth());
        // adding panels to GameWindow (JFrame)
        add(gamePanel);
        add(commentsPanel);
        add(informationPanel);
        add(buttonsPanel);
        // making GamePanel visible
        setVisible(true);
        // making the X close the game
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
