package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents the pop-up window used to filter the game wishlist by released year
 */

public class FilterByYearPopWindow extends  NonGenresFilterPopWindow {

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by released year
    public FilterByYearPopWindow(GameWishlistGUI gui) {
        super(gui,1, "Released Year", "",
                "Enter released year of target game", "",
                "Filter By Released Year");
    }

    // MODIFIES: button
    // EFFECTS: adds listener to the button
    @Override
    public void addListener() {
        button.addActionListener(new YearFilterListener());
    }

    private class YearFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */

        // EFFECTS: filters the game wishlist by the year information entered by the user when the button is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByYear();
            dispose();
        }
    }

    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.yearFilterInput = stringToInt(firstField);
    }

    // EFFECTS: returns the text in the field as an int value
    public int stringToInt(JTextField field) {
        String text = field.getText();
        return Integer.parseInt(text);
    }
}
