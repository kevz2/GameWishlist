package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents the pop-up window used to filter the game wishlist by ESRB rating
 */

public class FilterByRatingPopWindow extends NonGenresFilterPopWindow {

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by ESRB rating
    public FilterByRatingPopWindow(GameWishlistGUI gui) {
        super(gui, 1, "ESRB Rating", "",
                "Enter ESRB Rating", "",
                "Filter By ESRB Rating");
    }

    // MODIFIES: button
    // EFFECTS: adds the listener to the button
    @Override
    public void addListener() {
        button.addActionListener(new RatingFilterListener());
    }

    private class RatingFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */

        // EFFECTS: filters the game wishlist by the ESRB rating information entered by the user when the
        //          button is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByRating();
            dispose();
        }
    }

    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.ratingFilterInput = firstField.getText();
    }

}
