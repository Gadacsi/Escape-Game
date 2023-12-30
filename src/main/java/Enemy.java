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

// TODO: 2023-12-19 add enemy to game

/**
 * enemy in the game
 */
public class Enemy extends Entity {
    JLabel enemyLabel; //
    JProgressBar enemyHealth;
    Enemy() {
        imageIcon = new ImageIcon(getClass().getResource("images/enemy.png"));
        setLabel('E');
        setStrength(100);
        setHealth(100);
    }
    public JProgressBar enemyHealth() {
        enemyHealth = new JProgressBar();
        enemyHealth.setStringPainted(true);
        enemyHealth.setForeground(Color.blue);
        enemyHealth.setBackground(Color.black);
        enemyHealth.setString("Health %");
        enemyHealth.setValue(this.getHealth());

        return enemyHealth;
    }
    public JLabel enemyLabel() {
        enemyLabel = new JLabel("Enemy");
        enemyLabel.setIcon(imageIcon);
        enemyLabel.setHorizontalAlignment(JLabel.CENTER);
        enemyLabel.setIconTextGap(10);

        return enemyLabel;
    }
}
