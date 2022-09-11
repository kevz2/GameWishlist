package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game1;
    private Genres genres1;
    private Genres genres2;



    @BeforeEach
    void runBefore() {
        genres1 = new Genres();
        genres1.addGenre("RPG");
        genres1.addGenre("Action");
        genres1.addGenre("Stealth");
        genres1.addGenre("Horror");
        genres1.addGenre("Puzzle");

        genres2 = new Genres();
        genres2.addGenre("Action");
        genres2.addGenre("RPG");
        genres2.addGenre("Horror");

        game1 = new Game("Game1", 2021, "A", "B",
                "some platform", new BigDecimal(99.99), genres1, "M");
    }

    @Test
    void testGetGameTitle() {
        assertEquals("Game1", game1.getGameTitle());
    }

    @Test
    void testEditGameTitle() {
        game1.editGameTitle("GameOne");
        assertEquals("GameOne", game1.getGameTitle());
    }

    @Test
    void testGetReleasedYear() {
        assertEquals(2021, game1.getReleasedYear());
    }

    @Test
    void testEditReleasedYear() {
        game1.editReleasedYear(2022);
        assertEquals(2022, game1.getReleasedYear());
    }

    @Test
    void testGetPublisher() {
        assertEquals("A", game1.getPublisher());
    }

    @Test
    void testEditPublisher() {
        game1.editPublisher("a");
        assertEquals("a", game1.getPublisher());
    }

    @Test
    void testGetDeveloper() {
        assertEquals("B", game1.getDeveloper());
    }

    @Test
    void testEditDeveloper() {
        game1.editDeveloper("b");
        assertEquals("b", game1.getDeveloper());
    }

    @Test
    void testGetPlatform() {
        assertEquals("some platform", game1.getPlatform());
    }

    @Test
    void testEditPlatform() {
        game1.editPlatform("some other platform");
        assertEquals("some other platform", game1.getPlatform());
    }

    @Test
    void testGetPrice() {
        assertEquals(new BigDecimal(99.99).setScale(2, RoundingMode.CEILING), game1.getPrice());
    }

    @Test
    void testEditPrice() {
        game1.editPrice(new BigDecimal(100.00).setScale(2, RoundingMode.CEILING));
        assertEquals(new BigDecimal(100.00).setScale(2, RoundingMode.CEILING), game1.getPrice());
    }

    @Test
    void testGetGenres() {
        assertEquals(genres1, game1.getGenres());
    }

    @Test
    void testEditGenres() {
        game1.editGenres(genres2);
        assertEquals(genres2, game1.getGenres());
    }

    @Test
    void testGetEsrbRating() {
        assertEquals("M", game1.getEsrbRating());
    }

    @Test
    void testEditEsrbRating() {
        game1.editEsrbRating("T");
        assertEquals("T", game1.getEsrbRating());
    }

    @Test
    void testEditGameEntry() {
        game1.editGameEntry("GameOne", 2022, "a", "b", "some other platform",
                new BigDecimal(100.00), genres2, "T");
        assertEquals("GameOne", game1.getGameTitle());
        assertEquals(2022, game1.getReleasedYear());
        assertEquals("a", game1.getPublisher());
        assertEquals("b", game1.getDeveloper());
        assertEquals("some other platform", game1.getPlatform());
        assertEquals(new BigDecimal(100.00), game1.getPrice());
        assertEquals(genres2, game1.getGenres());
        assertEquals("T", game1.getEsrbRating());
    }

}
