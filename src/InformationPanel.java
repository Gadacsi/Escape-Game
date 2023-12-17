import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class InformationPanel extends JPanel {
    JLabel movement;
    JLabel scoreboard;
    Player player;

    InformationPanel(Player player) {
        this.player = player;
        setBorder(BorderFactory.createEtchedBorder(1));

        movement = new JLabel();
//        movement.setBorder(new BevelBorder(BevelBorder.LOWERED));
        movement.setBounds(2, 2, 88, 157);
        movement.setForeground(Color.WHITE);
        movement.setVerticalAlignment(SwingConstants.TOP);
        movement.setHorizontalAlignment(SwingConstants.CENTER);
        movement.setText("<html> W = UP <br>" +
                "A = LEFT <br>" +
                "S = DOWN <br>" +
                "D = RIGHT <br>");

        scoreboard = new JLabel();
//        scoreboard.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scoreboard.setBounds(90, 2, 88, 157);
        scoreboard.setForeground(Color.WHITE);
        scoreboard.setVerticalAlignment(SwingConstants.TOP);
        scoreboard.setHorizontalAlignment(SwingConstants.CENTER);
        scoreboard.setText("<html> Steps = " + player.getStepsTaken() + "<html>");

        setLayout(null);
        setBounds(302, 400, 177, 160);
        setBackground(new Color(51, 53, 56));


        add(movement);
        add(scoreboard);
    }
    public void updateScoreboard() {
        scoreboard.setText("<html> Steps = " + player.getStepsTaken() + "<html>");
    }
}
