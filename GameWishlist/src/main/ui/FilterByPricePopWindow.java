package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/*
Represents the pop-up window used to filter the game wishlist by price range
 */

public class FilterByPricePopWindow extends NonGenresFilterPopWindow {

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by price range
    public FilterByPricePopWindow(GameWishlistGUI gui) {
        super(gui, 2, "Minimum Price", "Maximum Price",
                "Enter price in $, up to 2 decimal places",
                "Enter price in $, up to 2 decimal places",
                "Filter By Price");
    }

    // MODIFIES: button
    // EFFECTS: adds listener to the button
    @Override
    public void addListener() {
        button.addActionListener(new PriceFilterListener());
    }

    private class PriceFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */

        // EFFECTS: filters the game wishlist by the price range information entered by the user when the
        //          button is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByPrice();
            dispose();
        }
    }

    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.minPriceFilterInput = new BigDecimal(firstField.getText());
        gui.maxPriceFilterInput = new BigDecimal(secondField.getText());
    }

}
