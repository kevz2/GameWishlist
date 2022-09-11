package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents the pop-up window used to filter the game wishlist by game title
 */

public class FilterByTitlePopWindow extends NonGenresFilterPopWindow {

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by game title
    public FilterByTitlePopWindow(GameWishlistGUI gui) {
        super(gui, 1, "Game Title", "",
                "Enter title of target game", "", "Filter By Game Title");
    }

    // MODIFIES: button
    // EFFECTS: adds listener to the button
    @Override
    public void addListener() {
        button.addActionListener(new TitleFilterListener());
    }

    private class TitleFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */

        // EFFECTS: filters the game wishlist by the game title information entered by the user when the button
        //          is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByTitle();
            dispose();
        }
    }

    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.titleFilterInput = firstField.getText();
    }
}
