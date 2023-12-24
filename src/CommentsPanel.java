/**
 * @author Kristof
 * Java Intermediate
 * Final Assignment(5)
 * December 19th 2023
 * Escape Game
 * version 2.0
 **/

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * button panel for movements
 */
public class CommentsPanel extends JPanel {

    JLabel label1;
    JLabel label2;


    /**
     * a JPanel to be used for text information of onscreen events
     */
    CommentsPanel() {
        setBorder(BorderFactory.createEtchedBorder(1));

        label1 = new JLabel();
        label1.setBorder(new BevelBorder(BevelBorder.LOWERED));
        label1.setBounds(4, 3, 294, 50);
        label1.setForeground(Color.WHITE);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setText("Escape Game");
        label1.setFont(new Font("Dialog", Font.PLAIN, 15));

        label2 = new JLabel();
        label2.setBorder(new BevelBorder(BevelBorder.LOWERED));
        label2.setBounds(4, 107, 294, 50);
        label2.setForeground(Color.WHITE);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setText(""); // TODO: 2023-12-13 add all actions written onto screen
        label2.setFont(new Font("Dialog", Font.PLAIN, 25));


        setLayout(null);
        setBounds(0, 400, 300, 162);
        setBackground(Color.darkGray);

        add(label1);
        add(label2);

    }
}
