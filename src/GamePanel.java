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
import java.awt.event.*;

/**
 * game panel for showing map in GUI and other objects
 */
public class GamePanel extends JPanel implements ActionListener {

    int score; // TODO: 2023-12-13 save score in text file
    WASD wasd = new WASD();
    Timer timer;
    boolean gameOver = false; // TODO: 2023-12-13 set gameOver screen
    Enemy enemy = new Enemy();
    Player player = new Player(wasd, this);
    MapManager mapManager = new MapManager();

    GamePanel() {
        setBackground(Color.GRAY);
        setBounds(0, 0, 800, 400);
        setFocusable(true);
        addKeyListener(wasd);
        startGame();

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        mapManager.drawMap(graphics2D);

        player.drawPlayer(graphics2D);

        graphics2D.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {

            player.move();
            player.movementCosts();
        }

        repaint();
    }
    public void startGame() {
        gameOver = false;
        timer = new Timer(110, this);
        timer.start();
    }
}



