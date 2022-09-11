package ui;

import model.Game;
import model.Genres;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
Represents the pop-up window used to look at the details of a game entry in the game wishlist
 */

public class ViewGamePopWindow extends JDialog {

    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_HEIGHT = 600;
    private static final int ROW_WIDTH = WINDOW_WIDTH - 50;
    private static final int LABEL_WIDTH = ROW_WIDTH / 5;
    private static final int LABEL_HEIGHT = WINDOW_HEIGHT / 15;
    private static final int INFO_LABEL_WIDTH = ROW_WIDTH / 5 * 4;
    private static final int INFO_LABEL_HEIGHT = WINDOW_HEIGHT / 15;
    private static final int SPACING = 10;
    private static final int BETWEEN_SPACING = 5;

    private SpringLayout windowLayout;

    private JLabel gameTitleInfo;
    private JLabel yearInfo;
    private JLabel publisherInfo;
    private JLabel developerInfo;
    private JLabel platformInfo;
    private JLabel priceInfo;
    private JLabel firstGenreInfo;
    private JLabel secondGenreInfo;
    private JLabel thirdGenreInfo;
    private JLabel fourthGenreInfo;
    private JLabel fifthGenreInfo;
    private JLabel ratingInfo;
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


    private ArrayList<JLabel> infoList = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: constructs the pop-up window to check the details of a game entry
    public ViewGamePopWindow(Game g) {
        initialize(g);
        addLabelsToList();
        addInfoToInfoList();
        customize();
        renderAllRows();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes some fields and all the labels
    public void initialize(Game g) {
        windowLayout = new SpringLayout();
        initializeLabels();
        initializeInfo(g);
    }

    // MODIFIES: this
    // EFFECTS: initializes the labels with appropriate texts
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
        labels.add(gameTitleLabel);
        labels.add(yearLabel);
        labels.add(publisherLabel);
        labels.add(developerLabel);
        labels.add(platformLabel);
        labels.add(priceLabel);
        labels.add(firstGenreLabel);
        labels.add(secondGenreLabel);
        labels.add(thirdGenreLabel);
        labels.add(fourthGenreLabel);
        labels.add(fifthGenreLabel);
        labels.add(ratingLabel);
    }

    // MODIFIES: this
    // EFFECTS: sets the sizes for all the labels
    public void setLabelSize() {
        for (JLabel label : labels) {
            label.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        }
    }

    // EFFECTS: initializes the labels containing the details of the game
    public void initializeInfo(Game g) {
        getInfo(g);
        addInfo();
    }

    // MODIFIES: title, year, publisher, developer, platform, price, genres, rating
    // EFFECTS: gives the information regarding that particular case to the appropriate links
    public void getInfo(Game g) {
        title = g.getGameTitle();
        year = g.getReleasedYear();
        publisher = g.getPublisher();
        developer = g.getDeveloper();
        platform = g.getPlatform();
        price = g.getPrice();
        genres = g.getGenres();
        getGenreInfo();
        rating = g.getEsrbRating();
    }

    // MODIFIES: this
    // EFFECTS: constructs all the labels with the appropriate texts
    public void addInfo() {
        gameTitleInfo = new JLabel(title);
        yearInfo = new JLabel(String.valueOf(year));
        publisherInfo = new JLabel(publisher);
        developerInfo = new JLabel(developer);
        platformInfo = new JLabel(platform);
        priceInfo = new JLabel(price.toString());
        firstGenreInfo = new JLabel(firstGenre);
        secondGenreInfo = new JLabel(secondGenre);
        thirdGenreInfo = new JLabel(thirdGenre);
        fourthGenreInfo = new JLabel(fourthGenre);
        fifthGenreInfo = new JLabel(fifthGenre);
        ratingInfo = new JLabel(rating);
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
    // EFFECTS: adds all information to the list
    public void addInfoToInfoList() {
        infoList.add(gameTitleInfo);
        infoList.add(yearInfo);
        infoList.add(publisherInfo);
        infoList.add(developerInfo);
        infoList.add(platformInfo);
        infoList.add(priceInfo);
        infoList.add(firstGenreInfo);
        infoList.add(secondGenreInfo);
        infoList.add(thirdGenreInfo);
        infoList.add(fourthGenreInfo);
        infoList.add(fifthGenreInfo);
        infoList.add(ratingInfo);
    }

    // MODIFIES: this
    // EFFECTS: sets the sizes of all information labels
    public void setInfoLabelSize() {
        for (JLabel info : infoList) {
            info.setMinimumSize(new Dimension(INFO_LABEL_WIDTH, INFO_LABEL_HEIGHT));
            info.setPreferredSize(new Dimension(INFO_LABEL_WIDTH, INFO_LABEL_HEIGHT));
        }
    }


    // MODIFIES: this
    // EFFECTS: sets the sizes for the button
    public void customizeInfoLabels() {

        for (JLabel info : infoList) {
            info.setPreferredSize(new Dimension(INFO_LABEL_WIDTH, INFO_LABEL_HEIGHT));
            info.setFont(new Font("Dialog", Font.PLAIN, 16));
            info.setBackground(Color.WHITE);
            info.setOpaque(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: customizes the components of the pop-up window
    public void customize() {
        this.setTitle("View Game Information");
        this.setLayout(windowLayout);

        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLabelSize();
        setInfoLabelSize();
        customizeInfoLabels();
    }


    // MODIFIES: this
    // EFFECTS: adds all labels onto the pop-up window at their corresponding positions
    public void renderAllRows() {

        for (int i = 0; i < labels.size(); i++) {

            JLabel label = labels.get(i);
            JLabel info = infoList.get(i);
            JPanel row = new JPanel();

            renderOneRow(row, label, info);
            this.add(row);

            windowLayout.putConstraint(SpringLayout.NORTH, row, SPACING + i * (LABEL_HEIGHT + BETWEEN_SPACING),
                    SpringLayout.NORTH, this);

            windowLayout.putConstraint(SpringLayout.WEST, row, SPACING, SpringLayout.WEST, this);

        }

    }

    // MODIFIES: row
    // EFFECTS: makes one row that has two labels, one describing what the information is about and the other
    //          containing the actual information regarding an aspect of the game entry
    public void renderOneRow(JPanel row, JLabel label, JLabel info) {
        SpringLayout rowLayout = new SpringLayout();
        row.setLayout(rowLayout);
        row.setMinimumSize(new Dimension(ROW_WIDTH, LABEL_HEIGHT));
        row.setPreferredSize(new Dimension(ROW_WIDTH, LABEL_HEIGHT));

        row.add(label);
        row.add(info);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, label, SPACING, SpringLayout.WEST, row);

        rowLayout.putConstraint(SpringLayout.VERTICAL_CENTER, info, 0, SpringLayout.VERTICAL_CENTER, row);

        rowLayout.putConstraint(SpringLayout.WEST, info, BETWEEN_SPACING, SpringLayout.EAST, label);
    }



}
