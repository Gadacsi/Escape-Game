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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * game panel for showing map in GUI and other objects
 */
public class GamePanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 400;
    WASD wasd = new WASD();
    Timer timer;
    boolean gameOver = false;
    boolean winGame = false;
    Enemy enemy = new Enemy();
    Player player = new Player(wasd, this);
    Sound sound = new Sound();
    MapManager mapManager = new MapManager();
    GameWindow gameWindow;

    /**
     * GamePanel Constructor
     */
    GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.GRAY);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setFocusable(true);
        addKeyListener(wasd);
        startGame();
    }

    /**
     * method of the parent class
     *
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        if(!gameOver) {
            mapManager.drawMap(graphics2D);

            player.drawPlayer(graphics2D);

            graphics2D.dispose();
        }
        else
            if(!winGame)
                loseGame(graphics2D);
            else
                winGame(graphics2D);

    }

    /**
     *
     * keyboard event listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            player.checkForItem();
            player.move();
            player.movementCosts();
        }
        repaint();
        gameWindow.informationPanel.updateScoreboard();
    }

    /**
     * method used to star game Timer
     */
    public void startGame() {
        gameOver = false;
        timer = new Timer(110, this);
        timer.start();
    }

    /**
     * screen used for losing
     */
    public void loseGame(Graphics2D graphics2D) {
        saveGame();
        playSound(1);
        setBackground(Color.BLACK);
        graphics2D.setColor(Color.RED);
        graphics2D.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics fontMetrics = getFontMetrics(graphics2D.getFont());
        graphics2D.drawString("You Failed To Escape!", (PANEL_WIDTH - fontMetrics.stringWidth("You Failed To Escape!")) /2, PANEL_HEIGHT / 2);
        timer.stop();
    }

    /**
     * screen used for winning
     */
    public void winGame(Graphics2D graphics2D) {
        saveGame();
        playSound(4);
        setBackground(new Color(3,44,252));
        graphics2D.setColor(new Color(215,211,227));
        graphics2D.setFont(new Font("Footlight MT Light", Font.BOLD, 50));
        FontMetrics fontMetrics = getFontMetrics(graphics2D.getFont());
        graphics2D.drawString("You Escaped", (PANEL_WIDTH - fontMetrics.stringWidth("You Escaped")) /2, PANEL_HEIGHT / 2);
        graphics2D.setFont(new Font("Footlight MT Light", Font.PLAIN, 25));
        graphics2D.drawString(("You took " + player.getStepsTaken() + " Steps"), 100, 300);
        timer.stop();
    }

    /**
     * save step count to txt file
     */
    public void saveGame() {
        Path path = Paths.get("C:\\temp\\scores.txt");

        String save = "Steps Taken " + player.getStepsTaken();
        byte[] data = save.getBytes();
        OutputStream outputStream;
        try {
            Files.createDirectories(path.getParent());
            outputStream = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE));
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Message: " + e);
        }
    }

    /**
     * method used to play sound effects
     */
    public void playSound(int sound) {
        this.sound.setFile(sound);
        this.sound.play();
    }

}



