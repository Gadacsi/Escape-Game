import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JLabel movement;

    ButtonsPanel() {
        setBorder(BorderFactory.createEtchedBorder(1));

        movement = new JLabel();
        movement.setBorder(new BevelBorder(BevelBorder.LOWERED));
        movement.setBounds(2,2,175,50);

        setLayout(null);
        setBounds(302,400,177,160);
        setBackground(new Color(51,53,56));

        add(movement);
    }
}
