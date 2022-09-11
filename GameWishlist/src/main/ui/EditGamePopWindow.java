package ui;

import model.Game;
import model.Genres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
Represents the pop-up window to edit the details of a game entry
 */

public class EditGamePopWindow extends JDialog {

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
    private static final String SAVE_CHANGES_STRING = "Save changes";

    private GameWishlistGUI gui;
    private Game game;
    private int index;
    private SpringLayout windowLayout;
    private JButton saveChangesButton;

    private JTextField gameTitleField;
    private JTextField yearField;
    private JTextField publisherField;
    private JTextField developerField;
    private JTextField platformField;
    private JTextField priceField;
    private JTextField firstGenreField;
    private JTextField secondGenreField;
    private JTextField thirdGenreField;
    private JTextField fourthGenreField;
    private JTextField fifthGenreField;
    private JTextField ratingField;
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

    protected String title;
    protected int year;
    protected String publisher;
    protected String developer;
    protected String platform;
    protected BigDecimal price;
    protected Genres genres;
    protected String firstGenre;
    protected String secondGenre;
    protected String thirdGenre;
    protected String fourthGenre;
    protected String fifthGenre;
    protected String rating;


    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<JLabel> fieldLabels = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to edit the details of a game entry
    public EditGamePopWindow(GameWishlistGUI gui, Game g, int index) {
        initialize(gui, g, index);
        customize();
        renderAllRows();
        renderButton();
        addListener();
        pack();
        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: initialize the fields, labels and text fields needed for the pop-up window
    public void initialize(GameWishlistGUI gui, Game g, int index) {
        this.gui = gui;
        this.game = g;
        this.index = index;
        windowLayout = new SpringLayout();
        saveChangesButton = new JButton(SAVE_CHANGES_STRING);
        getInfo();
        initializeLabels();
        initializeTextFields();
        addLabelsToList();
        addTextFieldsToList();
    }

    // MODIFIES: this
    // EFFECTS: constructs labels with specific texts
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
    // EFFECTS: adds all the labels to the list of labels
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
    // EFFECTS: constructs all the text fields with the appropriate prompt message in them;
    public void initializeTextFields() {
        gameTitleField = new JTextField(title);
        yearField = new JTextField(String.valueOf(year));
        publisherField = new JTextField(publisher);
        developerField = new JTextField(developer);
        platformField = new JTextField(platform);
        priceField = new JTextField(price.toString());
        firstGenreField = new JTextField(firstGenre);
        secondGenreField = new JTextField(secondGenre);
        thirdGenreField = new JTextField(thirdGenre);
        fourthGenreField = new JTextField(fourthGenre);
        fifthGenreField = new JTextField(fifthGenre);
        ratingField = new JTextField(rating);
    }

    // MODIFIES: this
    // EFFECTS: assigns the necessary information to each field
    public void getInfo() {
        title = game.getGameTitle();
        year = game.getReleasedYear();
        publisher = game.getPublisher();
        developer = game.getDeveloper();
        platform = game.getPlatform();
        price = game.getPrice();
        genres = game.getGenres();
        getGenreInfo();
        rating = game.getEsrbRating();
    }

    // MODIFIES: this
    // EFFECTS: assigns the necessary information to each genre field, depending on the selected game's
    //          number of genres
    public void getGenreInfo() {
        int size = genres.getLength();
        switch (size) {
            case 0:
                leaveGenresEmpty();
                break;
            case 1:
                addOneGenre();
                break;
            case 2:
                addTwoGenres();
                break;
            case 3:
                addThreeGenres();
                break;
            case 4:
                addFourGenres();
                break;
            case 5:
                addFiveGenres();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: assigns empty string to all five genres
    public void leaveGenresEmpty() {
        firstGenre = "";
        secondGenre = "";
        thirdGenre = "";
        fourthGenre = "";
        fifthGenre = "";
    }

    // MODIFIES: this
    // EFFECTS: assigns the one single genre from user input to firstGenre;
    //          assigns empty string to all others
    public void addOneGenre() {
        firstGenre = genres.getInfoAtIndex(0);
        secondGenre = "";
        thirdGenre = "";
        fourthGenre = "";
        fifthGenre = "";
    }

    // MODIFIES: this
    // EFFECTS: assigns one genre each, to firstGenre and secondGenre;
    //          assigns empty string to all others
    public void addTwoGenres() {
        firstGenre = genres.getInfoAtIndex(0);
        secondGenre = genres.getInfoAtIndex(1);
        thirdGenre = "";
        fourthGenre = "";
        fifthGenre = "";
    }

    // MODIFIES: this
    // EFFECTS: assigns one genre each, to firstGenre, secondGenre and thirdGenre;
    //          assigns empty string to all others
    public void addThreeGenres() {
        firstGenre = genres.getInfoAtIndex(0);
        secondGenre = genres.getInfoAtIndex(1);
        thirdGenre = genres.getInfoAtIndex(2);
        fourthGenre = "";
        fifthGenre = "";
    }

    // MODIFIES: this
    // EFFECTS: assigns one genre each, to firstGenre, secondGenre, thirdGenre and fourthGenre;
    //          assigns empty string to fifthGenre
    public void addFourGenres() {
        firstGenre = genres.getInfoAtIndex(0);
        secondGenre = genres.getInfoAtIndex(1);
        thirdGenre = genres.getInfoAtIndex(2);
        fourthGenre = genres.getInfoAtIndex(3);
        fifthGenre = "";
    }

    // MODIFIES: this
    // EFFECTS: assigns one genre each, to firstGenre, secondGenre, thirdGenre, fourthGenre and fifthGenre
    public void addFiveGenres() {
        firstGenre = genres.getInfoAtIndex(0);
        secondGenre = genres.getInfoAtIndex(1);
        thirdGenre = genres.getInfoAtIndex(2);
        fourthGenre = genres.getInfoAtIndex(3);
        fifthGenre = genres.getInfoAtIndex(4);
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
    // EFFECTS: customizes the components of the pop-up window
    public void customize() {

        this.setTitle("Edit Game Entry");
        this.setLayout(windowLayout);

        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        saveChangesButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        saveChangesButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        setLabelSize();
        setTextFieldSize();
    }


    // MODIFIES: this
    // EFFECTS: sets the size for all the labels
    public void setLabelSize() {
        for (JLabel label : fieldLabels) {
            label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the size for all the text fields
    public void setTextFieldSize() {
        for (JTextField field : textFields) {
            field.setMinimumSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            field.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }
    }

    // MODIFIES: this
    // EFFECTS: puts all the labels and text fields onto the pop-up window at their corresponding positions
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
    // EFFECTS: renders a single row containing a label and a text field
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
    // EFFECTS: adds the button onto the pop-up window
    public void renderButton() {
        this.add(saveChangesButton);

        windowLayout.putConstraint(SpringLayout.NORTH, saveChangesButton,
                SPACING + fieldLabels.size() * (LABEL_HEIGHT + BETWEEN_SPACING), SpringLayout.NORTH, this);

        windowLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, saveChangesButton, 0,
                SpringLayout.HORIZONTAL_CENTER, this);
    }


    // MODIFIES: saveChangesButton
    // EFFECTS: adds listener to saveChangeButton
    public void addListener() {
        saveChangesButton.addActionListener(new SaveChangesListener());
    }

    private class SaveChangesListener implements ActionListener {

        /*
        Represents the listener for saveChangeButton
         */

        // EFFECTS: saves the edited information of the selected game entry when saveChangeButton is clicked;
        //          then closes the pop-up window
        @Override
        public void actionPerformed(ActionEvent e) {

            giveInputToGUI();
            gui.editEntry(game, index);
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


    // EFFECTS: returns the string from the text field as an int value
    public int stringToInt(JTextField field) {
        String text = field.getText();
        return Integer.parseInt(text);
    }


}
