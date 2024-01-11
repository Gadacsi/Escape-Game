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
import java.util.Objects;

/**
 * button panel for movements
 */
public class CommentsPanel extends JPanel {

    JLabel label1;
//    JLabel label2;
    JButton[] buttons;

    /**
     * a JPanel to be used for text information of onscreen events
     */
    CommentsPanel() {
        setBorder(BorderFactory.createEtchedBorder(1));

        label1 = new JLabel();
        label1.setBorder(new BevelBorder(BevelBorder.LOWERED));
        label1.setBounds(4, 3, 294, 50);
        label1.setForeground(Color.WHITE);
        label1.setBackground(new Color(74, 74, 74));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setText("Escape Game");
        label1.setFont(new Font("Dialog", Font.PLAIN, 15));

        buttons = new JButton[10];

        buttons[0] = new JButton();
        buttons[0].setBounds(4,55,20,20);
        buttons[0].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("items/backpack.png"))));
        buttons[0].setBackground(new Color(82, 79, 79));
        buttons[0].setBorder(BorderFactory.createEmptyBorder());

        buttons[1] = new JButton();
        buttons[1].setBounds(26, 55, 20, 20);
        buttons[1].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("items/key.png"))));
        buttons[1].setBackground(new Color(82, 79, 79));
        buttons[1].setBorder(BorderFactory.createEmptyBorder());

//        label2 = new JLabel();
//        label2.setBorder(new BevelBorder(BevelBorder.LOWERED));
//        label2.setBounds(4, 107, 294, 50);
//        label2.setForeground(Color.WHITE);
//        label2.setHorizontalAlignment(SwingConstants.CENTER);
//        label2.setText(""); // TODO: 2023-12-13 add all actions written onto screen
//        label2.setFont(new Font("Dialog", Font.PLAIN, 25));


        setLayout(null);
        setBounds(0, 400, 300, 162);
        setBackground(new Color(82, 79, 79));

        add(label1);
//        add(label2);


    }
}
