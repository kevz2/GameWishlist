package ui;

import javax.swing.*;
import java.awt.*;

/*
Represents a generic pop-up window that appears when clicking on one of the filter function buttons that does not
involve the genres of a game entry
 */

public abstract class NonGenresFilterPopWindow extends JDialog {

    protected static final int SPACING = 10;
    protected static final int WINDOW_WIDTH = 400;
    protected static final int WINDOW_HEIGHT = 250;
    protected static final int LABEL_WIDTH = (WINDOW_WIDTH - 3 * SPACING) / 10 * 3;
    protected static final int LABEL_HEIGHT = 50;
    protected static final int FIELD_WIDTH = (WINDOW_WIDTH - 3 * SPACING) / 10 * 7;
    protected static final int FIELD_HEIGHT = 50;
    protected static final int BUTTON_WIDTH = WINDOW_WIDTH / 5;
    protected static final int BUTTON_HEIGHT = 30;
    protected static final String BUTTON_STRING = "Filter";
    protected int rowNumber;
    protected SpringLayout layout;
    protected GameWishlistGUI gui;
    protected JLabel firstLabel;
    protected JTextField firstField;
    protected JPanel firstRow;
    protected JLabel secondLabel;
    protected JTextField secondField;
    protected JPanel secondRow;
    protected JButton button;


    // MODIFIES: this
    // EFFECTS: constructs a pop-up window
    public NonGenresFilterPopWindow(GameWishlistGUI gui, int i, String firstLabelName, String secondLabelName,
                                    String firstFieldPrompt, String secondFieldPrompt, String windowTitle) {

        initialize(gui, i);
        customize(windowTitle);
        addRows(firstLabelName, secondLabelName, firstFieldPrompt, secondFieldPrompt);
        addButton();
        addListener();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes some fields
    public void initialize(GameWishlistGUI gui, int i) {
        this.gui = gui;
        rowNumber = i;
        layout = new SpringLayout();
    }

    // MODIFIES: this
    // EFFECTS: customizes the components of the pop-up window
    public void customize(String windowTitle) {
        this.setTitle(windowTitle);
        this.setLayout(layout);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: puts the labels and text fields onto the pop-up window at their corresponding positions
    public void addRows(String firstLabelName, String secondLabelName,
                        String firstFieldPrompt, String secondFieldPrompt) {
        if (rowNumber == 1) {
            addOneRow(firstLabelName, firstFieldPrompt);
        } else {
            addTwoRows(firstLabelName, secondLabelName, firstFieldPrompt, secondFieldPrompt);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a single row of label and text field to the pop-up window
    public void addOneRow(String firstLabelName, String firstFieldPrompt) {
        makeFirstRow(firstLabelName, firstFieldPrompt);

        this.add(firstRow);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, firstRow, 0,
                SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, firstRow, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
    }

    // MODIFIES: this
    // EFFECTS: adds two rows of labels and text fields to the pop-up window
    public void addTwoRows(String firstLabelName, String secondLabelName,
                           String firstFieldPrompt, String secondFieldPrompt) {
        makeFirstRow(firstLabelName, firstFieldPrompt);
        makeSecondRow(secondLabelName, secondFieldPrompt);

        this.add(firstRow);
        layout.putConstraint(SpringLayout.NORTH, firstRow, 20,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, firstRow, 0,
                SpringLayout.HORIZONTAL_CENTER, this);

        this.add(secondRow);
        layout.putConstraint(SpringLayout.NORTH, secondRow, 20,
                SpringLayout.SOUTH, firstRow);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, secondRow, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
    }

    // MODIFIES: this
    // EFFECTS: makes the first row to be added to the pop-up window
    public void makeFirstRow(String firstLabelName, String firstFieldPrompt) {
        SpringLayout rowLayout = new SpringLayout();
        firstLabel = new JLabel(firstLabelName, SwingConstants.RIGHT);
        firstField = new JTextField(firstFieldPrompt);
        firstRow = new JPanel();

        firstRow.setLayout(rowLayout);
        firstRow.setMinimumSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));
        firstRow.setPreferredSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));

        firstLabel.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        firstLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        firstLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        firstField.setMinimumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        firstField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        firstField.setFont(new Font("Dialog", Font.PLAIN, 12));

        firstRow.add(firstLabel);
        firstRow.add(firstField);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, firstLabel, 0,
                SpringLayout.VERTICAL_CENTER, firstRow);

        rowLayout.putConstraint(SpringLayout.WEST, firstLabel, SPACING, SpringLayout.WEST, firstRow);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, firstField, 0,
                SpringLayout.VERTICAL_CENTER, firstRow);

        rowLayout.putConstraint(SpringLayout.WEST, firstField, SPACING, SpringLayout.EAST, firstLabel);

    }

    // MODIFIES: this
    // EFFECTS: makes the second row to be added to the pop-up window
    public void makeSecondRow(String secondLabelName, String secondFieldPrompt) {
        SpringLayout rowLayout = new SpringLayout();
        secondLabel = new JLabel(secondLabelName, SwingConstants.RIGHT);
        secondField = new JTextField(secondFieldPrompt);
        secondRow = new JPanel();

        secondRow.setLayout(rowLayout);
        secondRow.setMinimumSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));
        secondRow.setPreferredSize(new Dimension(WINDOW_WIDTH, LABEL_HEIGHT));

        secondLabel.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        secondLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        secondLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        secondField.setMinimumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        secondField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        secondField.setFont(new Font("Dialog", Font.PLAIN, 12));

        secondRow.add(secondLabel);
        secondRow.add(secondField);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, secondLabel, 0,
                SpringLayout.VERTICAL_CENTER, secondRow);

        rowLayout.putConstraint(SpringLayout.WEST, secondLabel, SPACING, SpringLayout.WEST, secondRow);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, secondField, 0,
                SpringLayout.VERTICAL_CENTER, secondRow);

        rowLayout.putConstraint(SpringLayout.WEST, secondField, SPACING, SpringLayout.EAST, secondLabel);

    }

    // MODIFIES: this
    // EFFECTS: creates the button and adds it to the pop-up window
    public void addButton() {
        button = new JButton(BUTTON_STRING);
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        this.add(button);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.SOUTH, button, -50, SpringLayout.SOUTH, this);

    }

    // MODIFIES: this
    // EFFECTS: adds listener to the button
    public abstract void addListener();



}
