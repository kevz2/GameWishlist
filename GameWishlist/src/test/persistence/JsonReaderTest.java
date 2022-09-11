package persistence;

import model.Game;
import model.GameWishlist;
import model.Genres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    private static final String TEST_READER_DIRECTORY = "./data/";
    private static final String TEST_READER_TEMPORARY = "testReaderTemporary.json";
    private static final String TEST_READER_EMPTY_WISHLIST = "testReaderEmptyWishlist.json";
    private static final String TEST_READER_NON_EMPTY_WISHLIST = "testReaderNonEmptyWishlist.json";

    private GameWishlist wishlist1;
    private GameWishlist wishlist2;
    private Game game1;
    private Game game2;
    private Game game3;
    private Game game4;
    private Game game5;
    private Game game6;
    private Genres genres1;
    private Genres genres2;
    private Genres genres3;


    @BeforeEach
    public void runBefore() {
        genres1 = new Genres();
        genres1.addGenre("Action");
        genres1.addGenre("RPG");
        genres1.addGenre("Stealth");

        genres2 = new Genres();
        genres2.addGenre("RPG");
        genres2.addGenre("Action");
        genres2.addGenre("Horror");
        genres2.addGenre("Stealth");
        genres2.addGenre("Puzzle");

        genres3 = new Genres();
        genres3.addGenre("RPG");

        game1 = new Game("Game1", 2000, "Publisher", "Developer", "PS4",
                new BigDecimal(50.00), genres3, "E10+");

        game2 = new Game("Game2", 2021, "Publisher", "Developer",
                "Xbox Series X|S", new BigDecimal(79.99), genres1, "M");

        game3 = new Game("Game3", 2019, "Publisher", "Developer", "PS5",
                new BigDecimal(89.99), genres2, "E10+");

        game4 = new Game("Game4", 2021, "Publisher", "Developer",
                "Xbox Series X|S", new BigDecimal(90.00), genres2, "M");

        game5 = new Game("Game4", 2021, "Publisher", "Developer", "PS5",
                new BigDecimal(90.00), genres2, "M");

        game6 = new Game("Game5", 2021, "Publisher", "Developer", "PS5",
                new BigDecimal(109.99), genres1, "T");

        wishlist1 = new GameWishlist();
        wishlist2 = new GameWishlist();
        wishlist2.addGame(game1);
        wishlist2.addGame(game2);
        wishlist2.addGame(game3);
        wishlist2.addGame(game4);
        wishlist2.addGame(game5);
        wishlist2.addGame(game6);
    }

    @Test
    void testJsonReaderNonExistentFile() {

        try {
            JsonReader reader = new JsonReader("./data/something");
            GameWishlist loadedList = reader.readSavedData();
            fail("IOException expected for loadedList");
        } catch (IOException e) {
            // pass
        }

    }

    @Test
    void testJsonReaderEmptyWishlist() {
        try {
            JsonWriter writer = new JsonWriter(TEST_READER_DIRECTORY,
                    TEST_READER_EMPTY_WISHLIST, TEST_READER_TEMPORARY);
            writer.openWishlistWriter();
            writer.writeWishlist(wishlist1);
            writer.closeWishlistWriter();
        } catch (IOException e1) {
            fail("Should be able to save");
        }

        JsonReader reader = new JsonReader(TEST_READER_DIRECTORY + TEST_READER_EMPTY_WISHLIST);
        try {
            GameWishlist loadedList = reader.readSavedData();
            assertEquals(0, loadedList.returnEntireList().size());
        } catch (IOException e2) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testJsonReaderNonEmptyWishlist() {
        try {
            assertEquals(6, wishlist2.returnEntireList().size());

            JsonWriter writer = new JsonWriter(TEST_READER_DIRECTORY,
                    TEST_READER_NON_EMPTY_WISHLIST, TEST_READER_TEMPORARY);
            writer.openWishlistWriter();
            writer.writeWishlist(wishlist2);
            writer.closeWishlistWriter();

            JsonReader reader = new JsonReader(TEST_READER_DIRECTORY + TEST_READER_NON_EMPTY_WISHLIST);
            GameWishlist loadedList = reader.readSavedData();

            assertEquals(6, loadedList.returnEntireList().size());
            assertEquals(game1, loadedList.findGame(game1.getGameTitle(), game1.getPlatform()));
            assertEquals(game2, loadedList.findGame(game2.getGameTitle(), game2.getPlatform()));
            assertEquals(game3, loadedList.findGame(game3.getGameTitle(), game3.getPlatform()));
            assertEquals(game4, loadedList.findGame(game4.getGameTitle(), game4.getPlatform()));
            assertEquals(game5, loadedList.findGame(game5.getGameTitle(), game5.getPlatform()));
            assertEquals(game6, loadedList.findGame(game6.getGameTitle(), game6.getPlatform()));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}
