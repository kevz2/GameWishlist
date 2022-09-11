package model;

import org.json.JSONObject;
import persistance.Writable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Represents a game entry that can be added to a game wishlist.
 */

public class Game implements Writable {

    private String gameTitle;
    private int releasedYear;
    private String publisher;
    private String developer;
    private String platform;
    private BigDecimal price;
    private Genres genres;
    private String esrbRating;

    // EFFECTS: constructs a game object with all the given information
    public Game(String title, int year, String publisher, String developer,
                String platform, BigDecimal price, Genres genres, String rating) {

        gameTitle = title;
        releasedYear = year;
        this.publisher = publisher;
        this.developer = developer;
        this.platform = platform;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.genres = genres;
        esrbRating = rating;

    }

    // EFFECTS: returns the title of the game
    public String getGameTitle() {
        return gameTitle;
    }

    // MODIFIES: this
    // EFFECTS: changes the title of the game to the given title
    public void editGameTitle(String newTitle) {
        gameTitle = newTitle;
    }

    // EFFECTS: returns the released year of the game
    public int getReleasedYear() {
        return releasedYear;
    }

    // MODIFIES: this
    // EFFECTS: changes the released year of the game to the given year
    public void editReleasedYear(int newReleasedYear) {
        this.releasedYear = newReleasedYear;
    }

    // EFFECTS: returns the publisher of the game
    public String getPublisher() {
        return publisher;
    }

    // MODIFIES: this
    // EFFECTS: changes the publisher of the game to the given publisher
    public void editPublisher(String newPublisher) {
        this.publisher = newPublisher;
    }

    // EFFECTS: returns the developer of the game
    public String getDeveloper() {
        return developer;
    }

    // MODIFIES: this
    // EFFECTS: changes the developer of the game to the given developer
    public void editDeveloper(String newDeveloper) {
        this.developer = newDeveloper;
    }

    // EFFECTS: returns the platform of the game
    public String getPlatform() {
        return platform;
    }

    // MODIFIES: this
    // EFFECTS: changes the platform of the game to the given platform
    public void editPlatform(String newPlatform) {
        this.platform = newPlatform;
    }

    // EFFECTS: returns the price of the game
    public BigDecimal getPrice() {
        return price;
    }

    // MODIFIES: this
    // EFFECTS: changes the price of the game to the given price
    public void editPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    // EFFECTS: returns the list of genres of the game
    public Genres getGenres() {
        return genres;
    }

    // MODIFIES: this
    // EFFECTS: changes the genres of the game to the new list of genres
    public void editGenres(Genres newGenres) {
        this.genres = newGenres;
    }

    // EFFECTS: returns the ESRB rating of the game
    public String getEsrbRating() {
        return esrbRating;
    }

    // MODIFIES: this
    // EFFECTS: changes the ESRB rating of the game to the given rating
    public void editEsrbRating(String newEsrbRating) {
        this.esrbRating = newEsrbRating;
    }

    // MODIFIES: this
    // EFFECTS: changes the game entry's information to the given new information
    public void editGameEntry(String title, int year, String publisher, String developer,
                              String platform, BigDecimal price, Genres genres, String rating) {
        editGameTitle(title);
        editReleasedYear(year);
        editPublisher(publisher);
        editDeveloper(developer);
        editPlatform(platform);
        editPrice(price);
        editGenres(genres);
        editEsrbRating(rating);
    }

    // EFFECTS: returns the JSONObject representation of a game
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gameTitle", gameTitle);
        json.put("releasedYear", releasedYear);
        json.put("publisher", publisher);
        json.put("developer", developer);
        json.put("platform", platform);
        json.put("price", price);
        json.put("genres", genres.toJson());
        json.put("esrbRating", esrbRating);
        return json;
    }

    // EFFECTS: returns true if two objects being compared are the same, false otherwise
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (releasedYear != game.releasedYear) return false;
        if (gameTitle != null ? !gameTitle.equals(game.gameTitle) : game.gameTitle != null) return false;
        if (publisher != null ? !publisher.equals(game.publisher) : game.publisher != null) return false;
        if (developer != null ? !developer.equals(game.developer) : game.developer != null) return false;
        if (platform != null ? !platform.equals(game.platform) : game.platform != null) return false;
        if (price != null ? !price.equals(game.price) : game.price != null) return false;
        if (genres != null ? !genres.equals(game.genres) : game.genres != null) return false;
        return esrbRating != null ? esrbRating.equals(game.esrbRating) : game.esrbRating == null;
    }


}
