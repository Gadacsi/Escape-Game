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
 * information panel of objects in game
 */
public class PlayerPanel extends JPanel {

    PlayerPanel() {
        setBorder(BorderFactory.createEtchedBorder(1));
        setBounds(480, 400, 300, 160);
        setBackground(Color.lightGray);
        setLayout(null);
    }
}
