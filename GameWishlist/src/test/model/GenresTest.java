package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GenresTest {

    private Genres genres1;
    private Genres genres2;
    private Genres genres3;



    @BeforeEach
    void runBefore() {
        genres1 = new Genres();

        genres2 = new Genres();
        genres2.addGenre("RPG");
        genres2.addGenre("Action");
        genres2.addGenre("Horror");

        genres3 = new Genres();
        genres3.addGenre("RPG");
        genres3.addGenre("Action");
        genres3.addGenre("Horror");
        genres3.addGenre("Puzzle");
        genres3.addGenre("Adventure");
    }


    @Test
    void testGetLengthMin() {
        assertEquals(0, genres1.getLength());
    }

    @Test
    void testGetLengthMiddle() {
        assertEquals(3, genres2.getLength());
    }

    @Test
    void testGetLengthMax() {
        assertEquals(5, genres3.getLength());
    }

    @Test
    void testGetInfo() {
        ArrayList<String> list = new ArrayList<>();
        list.add("RPG");
        list.add("Action");
        list.add("Horror");
        assertEquals(list, genres2.getInfo());
    }

    @Test
    void testGetInfoAtIndexNull() {
        assertEquals(null, genres2.getInfoAtIndex(5));
    }

    @Test
    void testGetInfoAtIndexFirst() {
        assertEquals("RPG", genres2.getInfoAtIndex(0));
    }

    @Test
    void testGetInfoAtIndexLast() {
        assertEquals("Adventure", genres3.getInfoAtIndex(4));
    }

    @Test
    void testGetInfoAtIndexMiddle() {
        assertEquals("Horror", genres3.getInfoAtIndex(2));
    }

    @Test
    void testAddGenreSuccess() {
        genres1.addGenre("RPG");
        assertEquals(1, genres1.getLength());
        assertEquals("RPG", genres1.getInfoAtIndex(0));
    }

    @Test
    void testAddGenreAlreadyAdded() {
        Genres set = new Genres();
        set.addGenre("RPG");
        assertTrue(genres2.containsGenres(set));
        assertEquals(3, genres2.getLength());
        genres2.addGenre("RPG");
        assertTrue(genres2.containsGenres(set));
        assertEquals(3, genres2.getLength());

    }

    @Test
    void removeGenreSuccess() {
        Genres set = new Genres();
        set.addGenre("Puzzle");
        assertTrue(genres3.containsGenres(set));
        assertEquals(5, genres3.getLength());
        genres3.removeGenre("Puzzle");
        assertFalse(genres3.containsGenres(set));
        assertEquals(4, genres3.getLength());
    }

    @Test
    void removeGenreNotPresent() {
        Genres set = new Genres();
        set.addGenre("Some Genre");
        assertFalse(genres3.containsGenres(set));
        assertEquals(5, genres3.getLength());
        genres3.removeGenre("Some Genre");
        assertFalse(genres3.containsGenres(set));
        assertEquals(5, genres3.getLength());
    }

    @Test
    void testContainsGenresMultipleYes() {
        Genres set = new Genres();
        set.addGenre("Action");
        set.addGenre("Puzzle");
        set.addGenre("Adventure");
        assertTrue(genres3.containsGenres(set));
    }

    @Test
    void testContainsGenresMultipleNo() {
        Genres set = new Genres();
        set.addGenre("Action");
        set.addGenre("Puzzle");
        set.addGenre("Adventure");
        assertFalse(genres2.containsGenres(set));
    }

}
