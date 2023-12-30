/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * keyboard event listener
 */
public class WASD extends KeyAdapter {
    char direction;

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W -> direction = 'W';
            case KeyEvent.VK_A -> direction = 'A';
            case KeyEvent.VK_S -> direction = 'S';
            case KeyEvent.VK_D -> direction = 'D';
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D -> direction = Character.MIN_VALUE;
        }
    }
}
