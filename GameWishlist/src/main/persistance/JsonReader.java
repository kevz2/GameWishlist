package persistance;

import model.Game;
import model.GameWishlist;
import model.Genres;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
Represents a reader that reads GameWishlist from JSON data stored in file
 */

public class JsonReader {

    private String sourceGameWishlist;

    // EFFECTS: constructs a reader to read data from a source file
    public JsonReader(String savedData) {
        this.sourceGameWishlist = savedData;
    }

    // EFFECTS: reads saved data from the file and returns it in a GameWishlist object;
    // throws IOException if an error occurs when reading the data from file
    public GameWishlist readSavedData() throws IOException {
        String jsonData = readGameWishlistFile(sourceGameWishlist);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameWishlist(jsonObject);
    }

    // EFFECTS: reads the source file for all the game entries and returns them as strings
    public String readGameWishlistFile(String source) throws IOException {
        StringBuilder wishlistBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> wishlistBuilder.append(s));
        }

        return wishlistBuilder.toString();
    }

    // EFFECTS: parses the GameWishlist object and returns it
    public GameWishlist parseGameWishlist(JSONObject jsonObject) {
        GameWishlist list = new GameWishlist();
        addGames(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses games from JSON object and adds them to the given game wishlist input
    public void addGames(GameWishlist list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("games");
        for (Object o : array) {
            JSONObject nextGame = (JSONObject) o;
            addGame(list, nextGame);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses a game from JSON object and adds it to the given game wishlist input
    public void addGame(GameWishlist list, JSONObject o) {
        String title = o.getString("gameTitle");
        int year = o.getInt("releasedYear");
        String publisher = o.getString("publisher");
        String developer = o.getString("developer");
        String platform = o.getString("platform");
        BigDecimal price = o.getBigDecimal("price");
        JSONObject savedGenres = o.getJSONObject("genres");
        Genres genres = new Genres();
        addGenres(genres, savedGenres);
        String rating = o.getString("esrbRating");

        Game game = new Game(title, year, publisher, developer, platform, price, genres, rating);
        list.addGame(game);

    }


    // MODIFIES: genres
    // EFFECTS: parses the genres from JSON object and adds them to the input list of genres
    public void addGenres(Genres genres, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("genres");
        for (Object o : array) {
            String nextGenre = o.toString();
            genres.addGenre(nextGenre);
        }
    }


















}
