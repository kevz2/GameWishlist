package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
Represents the pop-up window that appears after clicking the button for adding a game
 */

public class AddGamePopWindow extends JDialog {

    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 35;
    private static final int ROW_WIDTH = WINDOW_WIDTH - 50;
    private static final int LABEL_WIDTH = ROW_WIDTH / 5;
    private static final int LABEL_HEIGHT = WINDOW_HEIGHT / 15;
    private static final int TEXT_FIELD_WIDTH = ROW_WIDTH / 5 * 4;
    private static final int TEXT_FIELD_HEIGHT = WINDOW_HEIGHT / 15;
    private static final int SPACING = 10;
    private static final int BETWEEN_SPACING = 5;
    private static final String ADD_GAME_STRING = "Add game";

    private GameWishlistGUI gui;
    private SpringLayout windowLayout;
    private JButton addGameToWishlistButton;

    protected JTextField gameTitleField;
    protected JTextField yearField;
    protected JTextField publisherField;
    protected JTextField developerField;
    protected JTextField platformField;
    protected JTextField priceField;
    protected JTextField firstGenreField;
    protected JTextField secondGenreField;
    protected JTextField thirdGenreField;
    protected JTextField fourthGenreField;
    protected JTextField fifthGenreField;
    protected JTextField ratingField;
    private JLabel gameTitleLabel;
    private JLabel yearLabel;
    private JLabel publisherLabel;
    private JLabel developerLabel;
    private JLabel platformLabel;
    private JLabel priceLabel;
    private JLabel firstGenreLabel;
    private JLabel secondGenreLabel;
    private JLabel thirdGenreLabel;
    private JLabel fourthGenreLabel;
    private JLabel fifthGenreLabel;
    private JLabel ratingLabel;


    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<JLabel> fieldLabels = new ArrayList<>();


    // MODIFIES: this
    // EFFECTS: constructs the pop-up window for adding a game to the wishlist
    public AddGamePopWindow(GameWishlistGUI gui) {
        initialize(gui);
        addTextFieldsToList();
        addLabelsToList();
        customize();
        renderAllRows();
        renderButton();
        addListener();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes some fields, the text fields and labels
    public void initialize(GameWishlistGUI gui) {
        this.gui = gui;
        windowLayout = new SpringLayout();
        addGameToWishlistButton = new JButton(ADD_GAME_STRING);
        initializeTextFields();
        initializeLabels();
    }

    // MODIFIES: this
    // EFFECTS: constructs the text fields with appropriate prompt messages
    public void initializeTextFields() {
        gameTitleField = new JTextField();
        yearField = new JTextField();
        publisherField = new JTextField();
        developerField = new JTextField();
        platformField = new JTextField("Enter the name of a single platform");
        priceField = new JTextField("Enter dollar amount without $, up to 2 decimal places");
        firstGenreField = new JTextField();
        secondGenreField = new JTextField("Make this blank if not applicable");
        thirdGenreField = new JTextField("Make this blank if not applicable");
        fourthGenreField = new JTextField("Make this blank if not applicable");
        fifthGenreField = new JTextField("Make this blank if not applicable");
        ratingField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: adds all text fields to the list of text fields
    public void addTextFieldsToList() {
        textFields.add(gameTitleField);
        textFields.add(yearField);
        textFields.add(publisherField);
        textFields.add(developerField);
        textFields.add(platformField);
        textFields.add(priceField);
        textFields.add(firstGenreField);
        textFields.add(secondGenreField);
        textFields.add(thirdGenreField);
        textFields.add(fourthGenreField);
        textFields.add(fifthGenreField);
        textFields.add(ratingField);
    }

    // MODIFIES: this
    // EFFECTS: constructs the labels with texts
    public void initializeLabels() {
        gameTitleLabel = new JLabel("Game Title", SwingConstants.RIGHT);
        yearLabel = new JLabel("Released Year", SwingConstants.RIGHT);
        publisherLabel = new JLabel("Publisher", SwingConstants.RIGHT);
        developerLabel = new JLabel("Developer", SwingConstants.RIGHT);
        platformLabel = new JLabel("Platform", SwingConstants.RIGHT);
        priceLabel = new JLabel("Price", SwingConstants.RIGHT);
        firstGenreLabel = new JLabel("First Genre", SwingConstants.RIGHT);
        secondGenreLabel = new JLabel("Second Genre", SwingConstants.RIGHT);
        thirdGenreLabel = new JLabel("Third Genre", SwingConstants.RIGHT);
        fourthGenreLabel = new JLabel("Fourth Genre", SwingConstants.RIGHT);
        fifthGenreLabel = new JLabel("Fifth Genre", SwingConstants.RIGHT);
        ratingLabel = new JLabel("ESRB Rating", SwingConstants.RIGHT);
    }

    // MODIFIES: this
    // EFFECTS: adds all labels to the list of labels
    public void addLabelsToList() {
        fieldLabels.add(gameTitleLabel);
        fieldLabels.add(yearLabel);
        fieldLabels.add(publisherLabel);
        fieldLabels.add(developerLabel);
        fieldLabels.add(platformLabel);
        fieldLabels.add(priceLabel);
        fieldLabels.add(firstGenreLabel);
        fieldLabels.add(secondGenreLabel);
        fieldLabels.add(thirdGenreLabel);
        fieldLabels.add(fourthGenreLabel);
        fieldLabels.add(fifthGenreLabel);
        fieldLabels.add(ratingLabel);
    }

    // MODIFIES: this
    // EFFECTS:  customizes the components of the pop-up window
    public void customize() {

        this.setTitle("Add a Game");
        this.setLayout(windowLayout);

        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        addGameToWishlistButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addGameToWishlistButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        setLabelSize();
        setTextFieldSize();
    }


    // MODIFIES: this
    // EFFECTS: sets the sizes of all labels
    public void setLabelSize() {
        for (JLabel label : fieldLabels) {
            label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the sizes of all text fields
    public void setTextFieldSize() {
        for (JTextField field : textFields) {
            field.setMinimumSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            field.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all labels and text fields to the pop-up window to their corresponding positions
    public void renderAllRows() {

        for (int i = 0; i < fieldLabels.size(); i++) {

            JLabel label = fieldLabels.get(i);
            JTextField textField = textFields.get(i);
            JPanel row = new JPanel();

           renderOneRow(row, label, textField);
           this.add(row);

            windowLayout.putConstraint(SpringLayout.NORTH, row, SPACING + i * (LABEL_HEIGHT + BETWEEN_SPACING),
                    SpringLayout.NORTH, this);

            windowLayout.putConstraint(SpringLayout.WEST, row, SPACING, SpringLayout.WEST, this);

        }

    }

    // MODIFIES: row
    // EFFECTS: creates one row of label and text field
    public void renderOneRow(JPanel row, JLabel label, JTextField textField) {
        SpringLayout rowLayout = new SpringLayout();
        row.setLayout(rowLayout);
        row.setMinimumSize(new Dimension(ROW_WIDTH, LABEL_HEIGHT));
        row.setPreferredSize(new Dimension(ROW_WIDTH, LABEL_HEIGHT));

        row.add(label);
        row.add(textField);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, label, SPACING, SpringLayout.WEST, row);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, textField, BETWEEN_SPACING, SpringLayout.EAST, label);
    }

    // MODIFIES: this
    // EFFECTS: adds the button to the pop-up window
    public void renderButton() {
        this.add(addGameToWishlistButton);

        windowLayout.putConstraint(SpringLayout.NORTH, addGameToWishlistButton,
                SPACING + fieldLabels.size() * (LABEL_HEIGHT + BETWEEN_SPACING), SpringLayout.NORTH, this);

        windowLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addGameToWishlistButton, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
    }


    // MODIFIES: addGameToWishlistButton
    // EFFECTS: adds listener to the button
    public void addListener() {
        addGameToWishlistButton.addActionListener(new AddGameToWishlistListener());
    }

    private class AddGameToWishlistListener implements ActionListener {

        /*
        Represents the listener for the addGameToWishlistButton
         */

        // EFFECTS: creates and adds a game entry with the information provided by the user when the
        //          button is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {

            giveInputToGUI();
            gui.addGameEntry();
            dispose();
        }
    }

    // MODIFIES: gui
    // EFFECTS: gives the user inputs to the main GUI
    public void giveInputToGUI() {

        gui.titleInput = gameTitleField.getText();
        gui.yearInput = stringToInt(yearField);
        gui.publisherInput = publisherField.getText();
        gui.developerInput = developerField.getText();
        gui.platformInput = platformField.getText();
        gui.priceInput = new BigDecimal(priceField.getText());
        gui.firstGenreInput = firstGenreField.getText();
        gui.secondGenreInput = secondGenreField.getText();
        gui.thirdGenreInput = thirdGenreField.getText();
        gui.fourthGenreInput = fourthGenreField.getText();
        gui.fifthGenreInput = fifthGenreField.getText();
        gui.ratingInput = ratingField.getText();
    }

    // EFFECTS: returns the text from the given field as an int value
    public int stringToInt(JTextField field) {
        String text = field.getText();
        return Integer.parseInt(text);
    }


}
