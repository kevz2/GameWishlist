package ui;

import model.Game;
import model.GameWishlist;
import model.Genres;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
Represents the graphical user interface for the Game Wishlist application
 */

public class GameWishlistGUI extends JFrame {

    private static final int SPACING = 10;
    private static final int MAIN_MENU_WIDTH = 900;
    private static final int MAIN_MENU_HEIGHT = 600;
    private static final int TOP_PANEL_WIDTH = MAIN_MENU_WIDTH - 2 * SPACING;
    private static final int TOP_PANEL_HEIGHT = 50;
    private static final int DISPLAY_PANE_WIDTH = MAIN_MENU_WIDTH / 3 * 2;
    private static final int DISPLAY_PANE_HEIGHT = MAIN_MENU_HEIGHT - 3 * SPACING - TOP_PANEL_HEIGHT;
    private static final int BUTTON_PANEL_WIDTH = MAIN_MENU_WIDTH - DISPLAY_PANE_WIDTH - 3 * SPACING;
    private static final int BUTTON_PANEL_HEIGHT = DISPLAY_PANE_HEIGHT;
    private static final int BUTTON_WIDTH = (MAIN_MENU_WIDTH - 4 * SPACING - DISPLAY_PANE_WIDTH) / 2;
    private static final int BUTTON_HEIGHT = (DISPLAY_PANE_HEIGHT - 6 * SPACING) / 6;
    private static final String ADD_GAME_STRING = "Add game";
    private static final String REMOVE_GAME_STRING = "Remove game";
    private static final String VIEW_GAME_STRING = "View game";
    private static final String EDIT_GAME_STRING = "Edit game";
    private static final String FILTER_TITLE_STRING = "Filter by title";
    private static final String FILTER_YEAR_STRING = "Filter by year";
    private static final String FILTER_PLATFORM_STRING = "Filter by platform";
    private static final String FILTER_PRICE_STRING = "Filter by price";
    private static final String FILTER_GENRES_STRING = "Filter by genres";
    private static final String FILTER_RATING_STRING = "Filter by rating";
    private static final String SAVE_DATA_STRING = "Save data";
    private static final String LOAD_DATA_STRING = "Load data";

    private SpringLayout buttonPanelLayout;
    private JButton addGameButton;
    private JButton removeGameButton;
    private JButton viewGameButton;
    private JButton editGameButton;
    private JButton filterByTitleButton;
    private JButton filterByYearButton;
    private JButton filterByPlatformButton;
    private JButton filterByPriceButton;
    private JButton filterByGenresButton;
    private JButton filterByRatingButton;
    private JButton saveDataButton;
    private JButton loadDataButton;

    private ArrayList<JButton> buttons = new ArrayList<>();

    private static final String DIRECTORY = "./data/";
    private static final String GUI_SAVED_GAME_WISHLIST = "GUIGameWishlist.json";
    private static final String GUI_GAME_WISHLIST_TO_WRITE = "temporary.json";

    protected GameWishlist wishlist;
    protected GameWishlist filteredWishlist;

    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    protected SpringLayout mainGUILayout;
    protected JPanel topPanel;
    protected JPanel displayPanel;
    protected JScrollPane wishlistPanel;
    protected ButtonPanel buttonPanel;

    protected BorderLayout scrollPaneLayout;
    protected JList gameWishlistJList;
    protected DefaultListModel gameWishlistListModel;

    protected String titleInput;
    protected int yearInput;
    protected String publisherInput;
    protected String developerInput;
    protected String platformInput;
    protected BigDecimal priceInput;
    protected Genres genresInput;
    protected String firstGenreInput;
    protected String secondGenreInput;
    protected String thirdGenreInput;
    protected String fourthGenreInput;
    protected String fifthGenreInput;
    protected String ratingInput;

    protected String titleFilterInput;
    protected int yearFilterInput;
    protected String platformFilterInput;
    protected BigDecimal minPriceFilterInput;
    protected BigDecimal maxPriceFilterInput;
    protected Genres genresFilterInput;
    protected String firstGenreFilterInput;
    protected String secondGenreFilterInput;
    protected String thirdGenreFilterInput;
    protected String fourthGenreFilterInput;
    protected String fifthGenreFilterInput;
    protected String ratingFilterInput;

    // MODIFIES: this
    // EFFECTS: constructs the main graphical user interface window of the application and displays it
    public GameWishlistGUI() {

        wishlist = new GameWishlist();

        mainGUILayout = new SpringLayout();
        scrollPaneLayout = new BorderLayout();

        displayGUI();
    }

    // MODIFIES: this
    // EFFECTS: adds the window title;
    //          adds components to the gui and lays them out accordingly;
    //          displays the gui
    public void displayGUI() {
        this.setTitle("Game Wishlist Builder");
        setLayout(mainGUILayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setPreferredSize(new Dimension(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT));

        createTopPanel();
        this.add(topPanel);
        mainGUILayout.putConstraint(SpringLayout.NORTH, topPanel, 10,
                SpringLayout.NORTH, this);
        mainGUILayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, topPanel, 0,
                SpringLayout.HORIZONTAL_CENTER, this);


        displayPanel = new DisplayPanel();
        this.add(displayPanel);
        mainGUILayout.putConstraint(SpringLayout.NORTH, displayPanel, 2 * SPACING + TOP_PANEL_HEIGHT,
                SpringLayout.NORTH, this);
        mainGUILayout.putConstraint(SpringLayout.WEST, displayPanel, SPACING,
                SpringLayout.WEST, this);


        buttonPanel = new ButtonPanel();
        this.add(buttonPanel);
        mainGUILayout.putConstraint(SpringLayout.NORTH, buttonPanel, 2 * SPACING + TOP_PANEL_HEIGHT,
                SpringLayout.NORTH, this);
        mainGUILayout.putConstraint(SpringLayout.WEST, buttonPanel, SPACING,
                SpringLayout.EAST, displayPanel);


        pack();
        setVisible(true);
    }


    private class DisplayPanel extends JPanel implements ListSelectionListener {
        /*
        Represents the panel that contains the games in the main GUI window
         */

        // MODIFIES: displayPanel
        // EFFECTS: creates the display panel for the main GUI
        public DisplayPanel() {
            initialize();

            gameWishlistListModel = new DefaultListModel();
            gameWishlistJList = new JList(gameWishlistListModel);

            gameWishlistJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            gameWishlistJList.setSelectedIndex(0);
            gameWishlistJList.addListSelectionListener(this);
            gameWishlistJList.setVisibleRowCount(10);

            wishlistPanel = new JScrollPane(gameWishlistJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            wishlistPanel.setPreferredSize(new Dimension(DISPLAY_PANE_WIDTH, DISPLAY_PANE_HEIGHT));
            wishlistPanel.getViewport().getView().setFont(new Font("Dialog", Font.PLAIN, 16));

            this.add(wishlistPanel);
        }

        // MODIFIES: displayPanel
        // EFFECTS: sets the layout and preferred sizes of the display panel
        public void initialize() {
            SpringLayout l = new SpringLayout();
            this.setLayout(l);
            this.setPreferredSize(new Dimension(DISPLAY_PANE_WIDTH, DISPLAY_PANE_HEIGHT));
        }

        // MODIFIES: removeGameButton
        // EFFECTS: enables the remove button if a valid game entry on the display panel is selected,
        //          disables the remove button otherwise
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (gameWishlistJList.getSelectedIndex() == -1) {
                    buttonPanel.getRemoveGameButton().setEnabled(false);
                } else {
                    buttonPanel.getRemoveGameButton().setEnabled(true);
                }
            }
        }
    }

    private class ButtonPanel extends JPanel {

        /*
        Represents the button panel on the main GUI window
         */

        // MODIFIES: buttonPanel
        // EFFECTS: creates the button panel for the main GUI window
        public ButtonPanel() {
            initialize();
            addButtonsToList();
            createPanel();
            addListener();
        }

        // MODIFIES: buttonPanel
        // EFFECTS: sets up the button panel for the main GUI window
        public void initialize() {
            buttonPanelLayout = new SpringLayout();
            addGameButton = new JButton(ADD_GAME_STRING);
            removeGameButton = new JButton(REMOVE_GAME_STRING);
            removeGameButton.setEnabled(false);
            viewGameButton = new JButton(VIEW_GAME_STRING);
            editGameButton = new JButton(EDIT_GAME_STRING);
            filterByTitleButton = new JButton(FILTER_TITLE_STRING);
            filterByYearButton = new JButton(FILTER_YEAR_STRING);
            filterByPlatformButton = new JButton(FILTER_PLATFORM_STRING);
            filterByPriceButton = new JButton(FILTER_PRICE_STRING);
            filterByGenresButton = new JButton(FILTER_GENRES_STRING);
            filterByRatingButton = new JButton(FILTER_RATING_STRING);
            saveDataButton = new JButton(SAVE_DATA_STRING);
            loadDataButton = new JButton(LOAD_DATA_STRING);
        }

        // MODIFIES: buttons
        // EFFECTS: adds the buttons to the buttons list
        public void addButtonsToList() {
            buttons.add(addGameButton);
            buttons.add(removeGameButton);
            buttons.add(viewGameButton);
            buttons.add(editGameButton);
            buttons.add(filterByTitleButton);
            buttons.add(filterByYearButton);
            buttons.add(filterByPlatformButton);
            buttons.add(filterByPriceButton);
            buttons.add(filterByGenresButton);
            buttons.add(filterByRatingButton);
            buttons.add(saveDataButton);
            buttons.add(loadDataButton);
        }

        // MODIFIES: buttonPanel, addGameButton, removeGameButton, viewGameButton, editGameButton,
        //           filterByTitleButton, filterByYearButton, filterByPlatformButton, filterByPriceButton,
        //           filterByGenresButton, filterByRatingButton, saveDataButton, loadDataButton
        // EFFECTS: sets the button panel size and button sizes;
        //          adds the buttons to the button panel
        public void createPanel() {
            this.setPreferredSize(new Dimension(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT));
            this.setLayout(buttonPanelLayout);
            setButtonSize();
            addButtonsToPanel();
        }

        // MODIFIES: addGameButton, removeGameButton, viewGameButton, editGameButton,
        //           filterByTitleButton, filterByYearButton, filterByPlatformButton, filterByPriceButton,
        //           filterByGenresButton, filterByRatingButton, saveDataButton, loadDataButton
        // EFFECTS: sets the button sizes
        public void setButtonSize() {
            for (JButton button : buttons) {
                button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            }
        }

        // MODIFIES: buttonPanel
        // EFFECTS: adds the buttons to the button panel
        public void addButtonsToPanel() {
            for (int i = 0; i < buttons.size(); i++) {
                JButton b = buttons.get(i);
                this.add(b);
                if (i % 2 == 0) {
                    buttonPanelLayout.putConstraint(SpringLayout.WEST, b, 0,
                            SpringLayout.WEST, this);
                    buttonPanelLayout.putConstraint(SpringLayout.NORTH, b, (i / 2) * (SPACING + BUTTON_HEIGHT),
                            SpringLayout.NORTH, this);
                } else {
                    buttonPanelLayout.putConstraint(SpringLayout.WEST, b, BUTTON_WIDTH + SPACING,
                            SpringLayout.WEST, this);
                    buttonPanelLayout.putConstraint(SpringLayout.NORTH, b, ((i - 1) / 2) * (SPACING + BUTTON_HEIGHT),
                            SpringLayout.NORTH, this);
                }
            }
        }

        // MODIFIES: addGameButton, removeGameButton, viewGameButton, editGameButton,
        //           filterByTitleButton, filterByYearButton, filterByPlatformButton, filterByPriceButton,
        //           filterByGenresButton, filterByRatingButton, saveDataButton, loadDataButton
        // EFFECTS: adds listener to all the buttons
        public void addListener() {
            addGameButtonAddListener();
            removeGameButtonAddListener();
            viewGameButtonAddListener();
            editGameButtonAddListener();
            filterByTitleButtonAddListener();
            filterByYearButtonAddListener();
            filterByPlatformButtonAddListener();
            filterByPriceButtonAddListener();
            filterByGenresButtonAddListener();
            filterByRatingButtonAddListener();
            saveDataButtonAddListener();
            loadDataButtonAddListener();
        }

        // MODIFIES: addGameButton
        // EFFECTS: adds listener to the addGameButton
        private void addGameButtonAddListener() {
            addGameButton.addActionListener(new AddGameListener());
        }

        private class AddGameListener implements ActionListener {
            /*
            Represents the listener for addGameButton
             */

            // EFFECTS: displays a pop-up window to add a game entry when addGameButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                makeAddGamePopWindow();
            }
        }

        // EFFECTS: returns the removeGameButton
        public JButton getRemoveGameButton() {
            return removeGameButton;
        }

        // MODIFIES: removeGameButton
        // EFFECTS: adds listener to the removeGameButton
        private void removeGameButtonAddListener() {
            removeGameButton.addActionListener(new RemoveGameListener());
        }

        private class RemoveGameListener implements ActionListener {

            /*
            Represents the listener for removeGameButton
             */

            // EFFECTS: removes the selected game entry from the display panel, the list model and the
            //          actual game wishlist;
            //          disables the removeGameButton if there is nothing left to display afterwards
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = gameWishlistJList.getSelectedIndex();
                String info = gameWishlistListModel.get(index).toString();
                String[] strings = info.split(", ");
                Game g = wishlist.findGame(strings[0], strings[1]);

                gameWishlistJList.remove(index);
                gameWishlistListModel.remove(index);
                wishlist.removeGame(g);

                if (gameWishlistListModel.getSize() == 0) {
                    removeGameButton.setEnabled(false);
                } else {
                    index = index - 1;
                    gameWishlistJList.setSelectedIndex(index);
                    gameWishlistJList.ensureIndexIsVisible(index);
                }

            }
        }

        // MODIFIES: viewGameButton
        // EFFECTS: adds listener to the viewGameButton
        private void viewGameButtonAddListener() {
            viewGameButton.addActionListener(new ViewGameListener());
        }

        private class ViewGameListener implements ActionListener {

            /*
            Represents the listener for the viewGameButton
             */

            // EFFECTS: displays a pop-up window that shows the details of the selected game entry when
            //          viewGameButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                makeViewGameEntryPopWindow();
            }
        }

        // MODIFIES: editGameButton
        // EFFECTS: adds listener to the editGameButton
        private void editGameButtonAddListener() {
            editGameButton.addActionListener(new EditGameListener());
        }

        private class EditGameListener implements ActionListener {

            /*
            Represents the listener for the editGameButton
             */

            // EFFECTS: displays a pop-up window to edit the details of the selected game entry when
            //          editGameButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                makeEditGameEntryPopWindow();
            }
        }

        // MODIFIES: filterByTitleButton
        // EFFECTS: adds listener to filterByTitleButton
        private void filterByTitleButtonAddListener() {
            filterByTitleButton.addActionListener(new FilterByTitleListener());
        }

        private class FilterByTitleListener implements ActionListener {

            /*
            Represents the listener for filterByTitleButton
             */

            // EFFECTS: displays a pop-up window where user can enter the game title to filter the wishlist
            //          when filterByTitleButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                createFilterByTitleWindow();
            }
        }

        // MODIFIES: filterByYearButton
        // EFFECTS: adds listener to filterByYearButton
        private void filterByYearButtonAddListener() {

            filterByYearButton.addActionListener(new FilterByYearListener());
        }

        private class FilterByYearListener implements ActionListener {

            /*
            Represents the listener for filterByYearButton
             */

            // EFFECTS: displays a pop-up window where user can enter the released year to filter the wishlist
            //          when filterByYearButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                createFilterByYearWindow();
            }
        }

        // MODIFIES: filterByPlatformButton
        // EFFECTS: adds listener to filterByPlatformButton
        private void filterByPlatformButtonAddListener() {
            filterByPlatformButton.addActionListener(new FilterByPlatformListener());
        }

        private class FilterByPlatformListener implements ActionListener {

            /*
            Represents the listener for filterByPlatformButton
             */

            // EFFECTS: displays a pop-up window where user can enter the platform information to filter
            //          the wishlist when filterByPlatformButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                createFilterByPlatformWindow();
            }
        }

        // MODIFIES: filterByPriceButton
        // EFFECTS: adds listener to filterByPriceButton
        private void filterByPriceButtonAddListener() {
            filterByPriceButton.addActionListener(new FilterByPriceListener());
        }

        private class FilterByPriceListener implements ActionListener {

            /*
            Represents the listener for filterByPriceButton
             */

            // EFFECTS: displays a pop-up window where user can enter the price range to filter the wishlist
            //          when filterByPriceButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                createFilterByPriceWindow();
            }
        }

        // MODIFIES: filterByGenresButton
        // EFFECTS: adds listener to filterByGenresButton
        private void filterByGenresButtonAddListener() {
            filterByGenresButton.addActionListener(new FilterByGenresListener());
        }

        private class FilterByGenresListener implements ActionListener {

            /*
            Represents the listener for filterByGenresButton
             */

            // EFFECTS: displays a pop-up window where user can enter the genre information to filter the
            //          wishlist when filterByGenresButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                createFilterByGenresWindow();
            }
        }

        // MODIFIES: filterByRatingButton
        // EFFECTS: adds listener to filterByRatingButton
        private void filterByRatingButtonAddListener() {
            filterByRatingButton.addActionListener(new FilterByRatingListener());
        }

        private class FilterByRatingListener implements ActionListener {

            /*
            Represents the listener for filterByRatingButton
             */

            // EFFECTS: displays a pop-up window where user can enter the ESRB rating to filter the wishlist
            //          when filterByRatingButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {

                createFilterByRatingWindow();
            }
        }

        // MODIFIES: saveDataButton
        // EFFECTS: adds listener to saveDataButton
        private void saveDataButtonAddListener() {
            saveDataButton.addActionListener(new SaveDataListener());
        }

        private class SaveDataListener implements ActionListener {

            /*
            Represents the listener to saveDataButton
             */

            // EFFECTS: saves the data when saveDataButton is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        }

        // MODIFIES: loadDataButton
        // EFFECTS: adds listener to loadDataButton
        private void loadDataButtonAddListener() {
            loadDataButton.addActionListener(new LoadDataListener());
        }

        private class LoadDataListener implements ActionListener {

            /*
            Represents the listener for loadDataButton
             */

            // EFFECTS: loads the data when loadDataButton is called
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        }

    }



    // MODIFIES: this
    // EFFECTS: creates the top panel for the main GUI window
    public void createTopPanel() {
        topPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        topPanel.setLayout(layout);
        topPanel.setPreferredSize(new Dimension(TOP_PANEL_WIDTH, TOP_PANEL_HEIGHT));

        JLabel message = new JLabel("<html>Welcome to your game wishlist!</html>");
        message.setFont(new Font("Dialog", Font.BOLD, 26));

        topPanel.add(message);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0,
                SpringLayout.HORIZONTAL_CENTER, topPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0,
                SpringLayout.VERTICAL_CENTER, topPanel);
    }


    // EFFECTS: creates a new pop-up window for adding a new game entry
    public void makeAddGamePopWindow() {
        new AddGamePopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: creates a new game entry;
    //          adds it to wishlist, gameWishlistJList, and gameWishlistListModel;
    //          displays it on the wishlistPanel
    public void addGameEntry() {
        int index = gameWishlistListModel.getSize();

        makeGenres(firstGenreInput, secondGenreInput, thirdGenreInput,
                fourthGenreInput, fifthGenreInput);

        Game game = new Game(titleInput, yearInput, publisherInput, developerInput, platformInput,
                priceInput, genresInput, ratingInput);

        wishlist.addGame(game);

        String entry = game.getGameTitle() + ", " + game.getPlatform();

        gameWishlistListModel.insertElementAt(entry, index);
        gameWishlistJList.setSelectedIndex(index);
        gameWishlistJList.ensureIndexIsVisible(index);
    }

    // MODIFIES: this
    // EFFECTS: adds up to five genre inputs when making a new game entry
    public void makeGenres(String firstGenreInput, String secondGenreInput, String thirdGenreInput,
                           String fourthGenreInput, String fifthGenreInput) {

        genresInput = new Genres();

        genresInput.addGenre(firstGenreInput);
        if (!secondGenreInput.equals("")) {
            genresInput.addGenre(secondGenreInput);
        }
        if (!thirdGenreInput.equals("")) {
            genresInput.addGenre(thirdGenreInput);
        }
        if (!fourthGenreInput.equals("")) {
            genresInput.addGenre(fourthGenreInput);
        }
        if (!fifthGenreInput.equals("")) {
            genresInput.addGenre(fifthGenreInput);
        }

    }

    // EFFECTS: creates a pop-up window for checking the details of the selected game entry
    public void makeViewGameEntryPopWindow() {
        int index = gameWishlistJList.getSelectedIndex();
        String info = gameWishlistListModel.get(index).toString();
        String[] strings = info.split(", ");
        Game g = wishlist.findGame(strings[0], strings[1]);
        new ViewGamePopWindow(g);
    }

    // EFFECTS: creates a pop-up window for editing the details of the selected game entry
    public void makeEditGameEntryPopWindow() {
        int index = gameWishlistJList.getSelectedIndex();
        String info = gameWishlistListModel.get(index).toString();
        String[] strings = info.split(", ");
        Game g = wishlist.findGame(strings[0], strings[1]);
        new EditGamePopWindow(this, g, index);
    }

    // MODIFIES: g, this
    // EFFECTS: edits the detail of the selected game entry
    public void editEntry(Game g, int index) {
        makeGenres(firstGenreInput, secondGenreInput, thirdGenreInput,
                fourthGenreInput, fifthGenreInput);

        g.editGameEntry(titleInput, yearInput, publisherInput, developerInput,
                platformInput, priceInput, genresInput, ratingInput);

        String entry = titleInput + ", " + platformInput;

        gameWishlistListModel.set(index, entry);
        gameWishlistJList.setSelectedIndex(index);
        gameWishlistJList.ensureIndexIsVisible(index);
    }

    // EFFECTS: creates a pop-up window to filter the wishlist by game title
    public void createFilterByTitleWindow() {
        new FilterByTitlePopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the list of game entries by the given game title;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByTitle() {
        filteredWishlist = new GameWishlist();
        filteredWishlist = wishlist.filterByTitle(titleFilterInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

                String name = g.getGameTitle();
                String platform = g.getPlatform();

                String entry = name + ", " + platform;

                gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }

    }

    // EFFECTS: creates a pop-up window to filter the wishlist by released year
    public void createFilterByYearWindow() {
        new FilterByYearPopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the wishlist by released year;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByYear() {
        filteredWishlist = new GameWishlist();
        filteredWishlist = wishlist.filterByYear(yearFilterInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

            String name = g.getGameTitle();
            String platform = g.getPlatform();

            String entry = name + ", " + platform;

            gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }
    }

    // EFFECTS: creates a pop-up window to filter the wishlist by platform
    public void createFilterByPlatformWindow() {
        new FilterByPlatformPopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the wishlist by platform;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByPlatform() {
        filteredWishlist = new GameWishlist();
        filteredWishlist = wishlist.filterByPlatform(platformInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

            String name = g.getGameTitle();
            String platform = g.getPlatform();

            String entry = name + ", " + platform;

            gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }
    }

    // EFFECTS: creates a pop-up window to filter the wishlist by price range
    public void createFilterByPriceWindow() {
        new FilterByPricePopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the wishlist by price;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByPrice() {
        filteredWishlist = new GameWishlist();
        filteredWishlist = wishlist.filterByPriceRange(minPriceFilterInput, maxPriceFilterInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

            String name = g.getGameTitle();
            String platform = g.getPlatform();

            String entry = name + ", " + platform;

            gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }
    }

    // EFFECTS: creates a pop-up window to filter the wishlist by genres
    public void createFilterByGenresWindow() {
        new FilterByGenresPopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the wishlist by genres;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByGenres() {
        genresFilterInput = new Genres();
        genresFilterInput.addGenre(firstGenreFilterInput);
        genresFilterInput.addGenre(secondGenreFilterInput);
        genresFilterInput.addGenre(thirdGenreFilterInput);
        genresFilterInput.addGenre(fourthGenreFilterInput);
        genresFilterInput.addGenre(fifthGenreFilterInput);

        filteredWishlist = wishlist.filterByGenres(genresFilterInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

            String name = g.getGameTitle();
            String platform = g.getPlatform();

            String entry = name + ", " + platform;

            gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }

    }

    // EFFECTS: creates a pop-up window to filter the wishlist by ESRB rating
    public void createFilterByRatingWindow() {
        new FilterByRatingPopWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: filters the wishlist by ESRB rating;
    //          displays the filtered wishlist;
    //          selects the first game entry in the filtered list if there is at least one game entry after filtering
    public void filterByRating() {
        filteredWishlist = new GameWishlist();
        filteredWishlist = wishlist.filterByRating(ratingFilterInput);
        gameWishlistListModel.clear();

        for (Game g : filteredWishlist.returnEntireList()) {

            String name = g.getGameTitle();
            String platform = g.getPlatform();

            String entry = name + ", " + platform;

            gameWishlistListModel.addElement(entry);
        }

        if (!(gameWishlistListModel.getSize() == 0)) {
            gameWishlistJList.setSelectedIndex(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the wishlist data to the designated file;
    //          creates a pop-up window containing a warning message if any error is encounterd
    public void saveData() {
        try {
            jsonWriter = new JsonWriter(DIRECTORY, GUI_SAVED_GAME_WISHLIST, GUI_GAME_WISHLIST_TO_WRITE);
            jsonWriter.openWishlistWriter();
            jsonWriter.writeWishlist(wishlist);
            jsonWriter.closeWishlistWriter();
        } catch (IOException e) {
            new WarningWindow(new JLabel("<html>Error encountered when saving the data.</html>"));
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the data from the saved file;
    //          creates a pop-up window containing a warning message if the saved file cannot be found
    public void loadData() {
        try {
            jsonReader = new JsonReader(DIRECTORY + GUI_SAVED_GAME_WISHLIST);
            gameWishlistListModel.clear();
            wishlist = jsonReader.readSavedData();
            for (Game g : wishlist.returnEntireList()) {

                String name = g.getGameTitle();
                String platform = g.getPlatform();

                String entry = name + ", " + platform;

                gameWishlistListModel.addElement(entry);
            }
            gameWishlistJList.setModel(gameWishlistListModel);
            if (!(gameWishlistListModel.getSize() == 0)) {
                gameWishlistJList.setSelectedIndex(0);
            }
        } catch (IOException e) {
            new WarningWindow(new JLabel("<html>File containing the saved data cannot be found.</html>"));
        }
    }


}

