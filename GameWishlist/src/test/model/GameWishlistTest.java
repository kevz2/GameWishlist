package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameWishlistTest {

    private GameWishlist wishlist1;
    private GameWishlist wishlist2;
    private GameWishlist wishlist3;

    private Game game1;
    private Game game2;
    private Game game3;
    private Game game4;
    private Game game5;
    private Game game6;
    private Game game7;

    private Genres genres1;
    private Genres genres2;
    private Genres genres3;

    @BeforeEach
    void runBefore() {
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
                "Xbox Series X|S", new BigDecimal(79.99), genres2, "M");

        game3 = new Game("Game3", 2019, "Publisher", "Developer", "PS5",
                new BigDecimal(89.99), genres3, "E10+");

        game4 = new Game("Game4", 2021, "Publisher", "Developer",
                "Xbox Series X|S", new BigDecimal(90.00), genres2, "M");

        game5 = new Game("Game4", 2021, "Publisher", "Developer", "PS5",
                new BigDecimal(90.00), genres2, "M");

        game6 = new Game("Game4", 2021, "Publisher", "Developer", "Switch",
                new BigDecimal(90.00), genres2, "M");

        game7 = new Game("Game5", 2022, "Publisher", "Developer", "PS5",
                new BigDecimal(109.99), genres1, "T");

        wishlist1 = new GameWishlist();
        wishlist2 = new GameWishlist();
        wishlist2.addGame(game1);
        wishlist2.addGame(game3);
        wishlist2.addGame(game6);
        wishlist3 = new GameWishlist();
        wishlist3.addGame(game1);
        wishlist3.addGame(game2);
        wishlist3.addGame(game3);
        wishlist3.addGame(game4);
        wishlist3.addGame(game5);
        wishlist3.addGame(game6);
        wishlist3.addGame(game7);
    }

    @Test
    void testReturnEntireListEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        ArrayList<Game> result = new ArrayList<>();
        assertEquals(result, wishlist1.returnEntireList());
    }


    @Test
    void testReturnEntireListNonEmptyList() {
        assertEquals(7, wishlist3.returnEntireList().size());
        ArrayList<Game> result = new ArrayList<>();
        result.add(game1);
        result.add(game2);
        result.add(game3);
        result.add(game4);
        result.add(game5);
        result.add(game6);
        result.add(game7);
        assertEquals(result, wishlist3.returnEntireList());
    }

    @Test
    void testAddGameSuccess() {
        assertEquals(3, wishlist2.returnEntireList().size());
        wishlist2.addGame(game2);
        assertEquals(4, wishlist2.returnEntireList().size());
        assertEquals(game2, wishlist2.findGame(game2.getGameTitle(), game2.getPlatform()));
    }

    @Test
    void testAddGameAlreadyAdded() {
        assertEquals(7, wishlist3.returnEntireList().size());
        assertEquals(game5, wishlist3.findGame(game5.getGameTitle(), game5.getPlatform()));
        wishlist3.addGame(game5);
        assertEquals(7, wishlist3.returnEntireList().size());
    }

    @Test
    void testRemoveGameSuccess() {
        assertEquals(7, wishlist3.returnEntireList().size());
        assertEquals(game1, wishlist3.findGame(game1.getGameTitle(), game1.getPlatform()));
        wishlist3.removeGame(game1);
        assertEquals(6, wishlist3.returnEntireList().size());
        assertEquals(null, wishlist3.findGame(game1.getGameTitle(), game1.getPlatform()));
    }

    @Test
    void testRemoveGameNotPresent() {
        assertEquals(3, wishlist2.returnEntireList().size());
        assertEquals(null, wishlist2.findGame(game2.getGameTitle(), game2.getPlatform()));
        wishlist2.removeGame(game2);
        assertEquals(3, wishlist2.returnEntireList().size());
    }

    @Test
    void testFindGameSuccess() {
        assertEquals(3, wishlist2.returnEntireList().size());
        assertEquals(game1, wishlist2.findGame(game1.getGameTitle(), game1.getPlatform()));
    }

    @Test
    void testFindGameNotPresent() {
        assertEquals(3, wishlist2.returnEntireList().size());
        assertEquals(null, wishlist2.findGame(game4.getGameTitle(), game4.getPlatform()));
    }

    @Test
    void testContainsGameSuccess() {
        assertEquals(7, wishlist3.returnEntireList().size());
        assertEquals(game3, wishlist3.findGame(game3.getGameTitle(), game3.getPlatform()));
        assertTrue(wishlist3.containsGame(game3));
    }

    @Test
    void testContainsGameNotPresent() {
        assertEquals(3, wishlist2.returnEntireList().size());
        assertEquals(null, wishlist2.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertFalse(wishlist2.containsGame(game2));
    }

    @Test
    void testFilterByTitleEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByTitle(game1.getGameTitle());
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByTitleEmptyOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByTitle("random title");
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByTitleMultipleOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByTitle(game5.getGameTitle());
        assertEquals(3, result.returnEntireList().size());
        assertEquals(game4, result.findGame(game5.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game5.getGameTitle(), game6.getPlatform()));
    }

    @Test
    void testFilterByYearEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByYear(game1.getReleasedYear());
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByYearEmptyOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByYear(1990);
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByYearMultipleOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByYear(game2.getReleasedYear());
        assertEquals(4, result.returnEntireList().size());
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertEquals(game4, result.findGame(game5.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game5.getGameTitle(), game6.getPlatform()));
    }



    @Test
    void testFilterByPlatformEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByTitle(game1.getPlatform());
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByPlatformEmptyOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByTitle("some platform");
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByPlatformMultipleOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByPlatform("PS5");
        assertEquals(3, result.returnEntireList().size());
        assertEquals(game3, result.findGame(game3.getGameTitle(), "PS5"));
        assertEquals(game5, result.findGame(game5.getGameTitle(), "PS5"));
        assertEquals(game7, result.findGame(game7.getGameTitle(), "PS5"));
    }

    @Test
    void testFilterByPriceRangeEmptyList() {
        BigDecimal value = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByPriceRange(game1.getPrice().subtract(value),
                game1.getPrice().add(value));
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByPriceRangeJustOutOfRange() {
        BigDecimal value = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByPriceRange(game1.getPrice().add(value),
                game2.getPrice().subtract(value));
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByPriceRangeMinBound() {
        BigDecimal value = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByPriceRange(game1.getPrice(),
                game2.getPrice().add(value));
        assertEquals(2, result.returnEntireList().size());
        assertEquals(game1, result.findGame(game1.getGameTitle(), game1.getPlatform()));
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
    }

    @Test
    void testFilterByPriceRangeMaxBound() {
        BigDecimal value = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByPriceRange(game3.getPrice().add(value),
                game7.getPrice().subtract(value));
        assertEquals(3, result.returnEntireList().size());
        assertEquals(game4, result.findGame(game4.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game6.getGameTitle(), game6.getPlatform()));
    }

    @Test
    void testFilterByPriceRangeMultiple() {
        BigDecimal value = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByPriceRange(game2.getPrice(),
                game7.getPrice().subtract(value));
        assertEquals(5, result.returnEntireList().size());
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertEquals(game3, result.findGame(game3.getGameTitle(), game3.getPlatform()));
        assertEquals(game4, result.findGame(game4.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game6.getGameTitle(), game6.getPlatform()));
    }

    @Test
    void testFilterByGenresEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByGenres(game1.getGenres());
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByGenresEmptyGenres() {
        Genres set = new Genres();
        assertEquals(3, wishlist2.returnEntireList().size());
        GameWishlist result = wishlist2.filterByGenres(set);
        assertEquals(3, result.returnEntireList().size());
        assertEquals(result, wishlist2);

    }

    @Test
    void testFilterByGenresEmptyOutput() {
        Genres set = new Genres();
        set.addGenre("some genre");
        assertEquals(1, set.getLength());
        assertEquals(3, wishlist2.returnEntireList().size());
        GameWishlist result = wishlist2.filterByGenres(set);
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByGenresOneGenre() {
        Genres set = new Genres();
        set.addGenre("Action");
        set.addGenre("");
        assertEquals(1, set.getLength());
        GameWishlist result = wishlist3.filterByGenres(set);
        assertEquals(5, result.returnEntireList().size());
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertEquals(game4, result.findGame(game4.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game6.getGameTitle(), game6.getPlatform()));
        assertEquals(game7, result.findGame(game7.getGameTitle(), game7.getPlatform()));
    }

    @Test
    void testFilterByGenresMultipleGenres() {
        Genres set = new Genres();
        set.addGenre("RPG");
        set.addGenre("Puzzle");
        set.addGenre("Action");
        assertEquals(3, set.getLength());
        GameWishlist result = wishlist3.filterByGenres(set);
        assertEquals(4, result.returnEntireList().size());
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertEquals(game4, result.findGame(game4.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game6.getGameTitle(), game6.getPlatform()));
    }

    @Test
    void testFilterByRatingEmptyList() {
        assertEquals(0, wishlist1.returnEntireList().size());
        GameWishlist result = wishlist1.filterByRating(game1.getEsrbRating());
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByRatingEmptyOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist1.filterByRating("A");
        assertEquals(0, result.returnEntireList().size());
    }

    @Test
    void testFilterByRatingMultipleOutput() {
        assertEquals(7, wishlist3.returnEntireList().size());
        GameWishlist result = wishlist3.filterByRating(game5.getEsrbRating());
        assertEquals(4, result.returnEntireList().size());
        assertEquals(game2, result.findGame(game2.getGameTitle(), game2.getPlatform()));
        assertEquals(game4, result.findGame(game4.getGameTitle(), game4.getPlatform()));
        assertEquals(game5, result.findGame(game5.getGameTitle(), game5.getPlatform()));
        assertEquals(game6, result.findGame(game6.getGameTitle(), game6.getPlatform()));
    }

}
