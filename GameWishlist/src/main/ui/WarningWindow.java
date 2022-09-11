package ui;

import javax.swing.*;
import java.awt.*;

/*
Represents a pop-up window containing a warning message
 */

public class WarningWindow extends JDialog {

    protected static final int WARNING_WINDOW_WIDTH = 300;
    protected static final int WARNING_WINDOW_HEIGHT = 300;

    protected SpringLayout layout = new SpringLayout();

    // MODIFIES: this
    // EFFECTS: constructs and warning window showing message
    public WarningWindow(JLabel message) {

        this.setLayout(layout);
        this.setMinimumSize(new Dimension(WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT));

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        message.setMinimumSize(new Dimension(WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT));
        message.setPreferredSize(new Dimension(WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT));

        this.add(message);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0,
                SpringLayout.VERTICAL_CENTER, this);

        pack();
        setVisible(true);

    }

}
