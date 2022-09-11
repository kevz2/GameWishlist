package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.math.BigDecimal;
import java.util.ArrayList;

/*
Represents a game wishlist
 */

public class GameWishlist implements Writable {

    private ArrayList<Game> wishlist;


    // EFFECTS: constructs an empty game wishlist
    public GameWishlist() {
        wishlist = new ArrayList<>();
    }

    // EFFECTS: returns the entire list of games in the GameWishlist object
    public ArrayList<Game> returnEntireList() {
        return wishlist;
    }

    // MODIFIES: this
    // EFFECTS: adds a new game to the game wishlist if it has not been added yet
    public void addGame(Game g) {

        if (!wishlist.contains(g)) {
            wishlist.add(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the given game from the game wishlist if it exists
    public void removeGame(Game g) {
        if (wishlist.contains(g)) {
            wishlist.remove(g);
        }
    }

    // EFFECTS: returns the game with the given title and platform in the game wishlist if it exists, otherwise
    //          returns null
    public Game findGame(String title, String platform) {
        Game result = null;

        for (Game g : wishlist) {
            if (g.getGameTitle().equals(title) && g.getPlatform().equals(platform)) {
                result = g;
                break;
            }
        }
        return result;
    }

    // EFFECTS: returns true if the game wishlist contains the given game
    public boolean containsGame(Game g) {
        return wishlist.contains(g);
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose titles match the given input
    public GameWishlist filterByTitle(String target) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            if (g.getGameTitle().equals(target)) {
                list.addGame(g);
            }
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose released years match the given input
    public GameWishlist filterByYear(int target) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            if (g.getReleasedYear() == target) {
                list.addGame(g);
            }
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose platforms match the given input
    public GameWishlist filterByPlatform(String target) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            if (g.getPlatform().equals(target)) {
                list.addGame(g);
            }
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose prices fall within the given
    // price range, including the minimum and maximum prices of the given range
    public GameWishlist filterByPriceRange(BigDecimal min, BigDecimal max) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            BigDecimal price = g.getPrice();
            if (price.compareTo(min) >= 0 && price.compareTo(max) <= 0) {
                list.addGame(g);
            }
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose own genres have all the genres
    // from the given input
    public GameWishlist filterByGenres(Genres targetGenres) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            Genres genres = g.getGenres();
            if (genres.containsGenres(targetGenres)) {
                list.addGame(g);
            }
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: returns a filtered game wishlist only containing the games whose ESRB ratings match the given input
    public GameWishlist filterByRating(String target) {
        GameWishlist list = new GameWishlist();
        for (Game g : wishlist) {
            if (g.getEsrbRating().equals(target)) {
                list.addGame(g);
            }
        }
        return list;
    }

    // EFFECTS: returns the JSON representation of a Game Wishlist object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesToJson());
        return json;
    }


    // EFFECTS: returns all the games in the GameWishlist object as a JSON array
    public JSONArray gamesToJson() {
        JSONArray array = new JSONArray();

        for (Game g : wishlist) {
            array.put(g.toJson());
        }

        return array;
    }

    // EFFECTS: returns true if two objects being compared are the same, false otherwise
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameWishlist that = (GameWishlist) o;

        return wishlist != null ? wishlist.equals(that.wishlist) : that.wishlist == null;
    }

}
