package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
Represents the pop-up window used to filter the game wishlist by genres
 */

public class FilterByGenresPopWindow extends JDialog {

    private static final int SPACING = 10;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 400;
    private static final int LABEL_WIDTH = (WINDOW_WIDTH - 3 * SPACING) / 10 * 2;
    private static final int LABEL_HEIGHT = 50;
    private static final int FIELD_WIDTH = (WINDOW_WIDTH - 3 * SPACING) / 10 * 8;
    private static final int FIELD_HEIGHT = 50;
    private static final int BUTTON_WIDTH = WINDOW_WIDTH / 8;
    private static final int BUTTON_HEIGHT = 30;
    private static final String BUTTON_STRING = "Filter";

    private SpringLayout layout;
    private GameWishlistGUI gui;

    private JLabel firstGenreLabel;
    private JLabel secondGenreLabel;
    private JLabel thirdGenreLabel;
    private JLabel fourthGenreLabel;
    private JLabel fifthGenreLabel;
    private JTextField firstGenreField;
    private JTextField secondGenreField;
    private JTextField thirdGenreField;
    private JTextField fourthGenreField;
    private JTextField fifthGenreField;
    private JButton button;

    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> fields;


    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to filter the game wishlist by genres
    public FilterByGenresPopWindow(GameWishlistGUI gui) {
        initialize(gui);
        customize();
        addRows();
        addButton();
        addListener();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes some fields and all the labels and text fields
    public void initialize(GameWishlistGUI gui) {
        this.gui = gui;
        layout = new SpringLayout();
        labels = new ArrayList<>();
        fields = new ArrayList<>();
        initializeLabels();
        initializeFields();
        addLabelsToList();
        addFieldsToList();
    }

    // MODIFIES: this
    // EFFECTS: constructs all the labels with texts
    public void initializeLabels() {
        firstGenreLabel = new JLabel("First Genre", SwingConstants.RIGHT);
        secondGenreLabel = new JLabel("Second Genre", SwingConstants.RIGHT);
        thirdGenreLabel = new JLabel("Third Genre", SwingConstants.RIGHT);
        fourthGenreLabel = new JLabel("Fourth Genre", SwingConstants.RIGHT);
        fifthGenreLabel = new JLabel("Fifth Genre", SwingConstants.RIGHT);
    }

    // MODIFIES: this
    // EFFECTS: adds all the labels to the list of labels
    public void addLabelsToList() {
        labels.add(firstGenreLabel);
        labels.add(secondGenreLabel);
        labels.add(thirdGenreLabel);
        labels.add(fourthGenreLabel);
        labels.add(fifthGenreLabel);
    }

    // MODIFIES: this
    // EFFECTS: constructs all the text fields with prompts
    public void initializeFields() {
        firstGenreField = new JTextField("Enter genre name");
        secondGenreField = new JTextField("Make this blank if not applicable");
        thirdGenreField = new JTextField("Make this blank if not applicable");
        fourthGenreField = new JTextField("Make this blank if not applicable");
        fifthGenreField = new JTextField("Make this blank if not applicable");
    }

    // MODIFIES: this
    // EFFECTS: adds all the text fields to the list of text fields
    public void addFieldsToList() {
        fields.add(firstGenreField);
        fields.add(secondGenreField);
        fields.add(thirdGenreField);
        fields.add(fourthGenreField);
        fields.add(fifthGenreField);
    }

    // MODIFIES: this
    // EFFECTS: customizes the components of the pop-up window
    public void customize() {
        this.setTitle("Filter by Genres");
        this.setLayout(layout);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLabelSizeAndFont();
        setTextFieldSizeAndFont();
    }

    // MODIFIES: this
    // EFFECTS: sets the sizes fonts of the labels
    public void setLabelSizeAndFont() {
        for (JLabel label : labels) {
            label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            label.setFont(new Font("Dialog", Font.PLAIN, 13));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the sizes fonts of the labels
    public void setTextFieldSizeAndFont() {
        for (JTextField field : fields) {
            field.setMinimumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
            field.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
            field.setFont(new Font("Dialog", Font.PLAIN, 12));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the labels and text fields to the pop-up window at their corresponding positions
    public void addRows() {

        for (int i = 0; i < labels.size(); i++) {

            JLabel label = labels.get(i);
            JTextField textField = fields.get(i);
            JPanel row = new JPanel();

            addOneRow(row, label, textField);
            this.add(row);

            layout.putConstraint(SpringLayout.NORTH, row, SPACING + i * (LABEL_HEIGHT + SPACING),
                    SpringLayout.NORTH, this);

            layout.putConstraint(SpringLayout.WEST, row, SPACING, SpringLayout.WEST, this);

        }
    }

    // MODIFIES: this
    // EFFECTS: adds one row that contains a single label and text field to the pop-up window
    public void addOneRow(JPanel row, JLabel label, JTextField textField) {
        SpringLayout rowLayout = new SpringLayout();
        row.setLayout(rowLayout);
        row.setMinimumSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));
        row.setPreferredSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));

        row.add(label);
        row.add(textField);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, label, SPACING, SpringLayout.WEST, row);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, textField, SPACING, SpringLayout.EAST, label);

    }

    // MODIFIES: this
    // EFFECTS: sets the sizes of the button and adds it to the pop-up window
    public void addButton() {
        button = new JButton(BUTTON_STRING);
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        this.add(button);

        layout.putConstraint(SpringLayout.NORTH, button,
                SPACING + labels.size() * (LABEL_HEIGHT + SPACING), SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
    }

    // MODIFIES: button
    // EFFECTS: adds listener to the button
    public void addListener() {
        button.addActionListener(new GenresFilterListener());
    }

    private class GenresFilterListener implements ActionListener {

        /*
        Represents the listener for the button
         */


        // EFFECTS: filters the game wishlist by the genre information entered by the user when the button
        //          is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {
            giveInputToGUI();
            gui.filterByGenres();
            dispose();
        }
    }


    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {
        gui.firstGenreFilterInput = firstGenreField.getText();
        gui.secondGenreFilterInput = secondGenreField.getText();
        gui.thirdGenreFilterInput = thirdGenreField.getText();
        gui.fourthGenreFilterInput = fourthGenreField.getText();
        gui.fifthGenreFilterInput = fifthGenreField.getText();
    }

}
