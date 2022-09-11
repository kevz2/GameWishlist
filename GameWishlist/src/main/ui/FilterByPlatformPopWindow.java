package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents the pop-up window used to filter the game wishlist by platform
 */

public class FilterByPlatformPopWindow extends NonGenresFilterPopWindow {

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by platform
    public FilterByPlatformPopWindow(GameWishlistGUI gui) {
        super(gui, 1, "Platform", "",
                "Enter platform name", "",
                "Filter By Platform");
    }

    // MODIFIES: button
    // EFFECTS: adds listener to the button
    @Override
    public void addListener() {
        button.addActionListener(new PlatformFilterListener());
    }

    private class PlatformFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */

        // EFFECTS: filters the game wishlist by the platform information entered by the user when the button
        //          is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByPlatform();
            dispose();
        }
    }


    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.platformFilterInput = firstField.getText();
    }
}
