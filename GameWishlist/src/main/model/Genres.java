package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;

/*
Represents a list of genres that form part of a game's information
 */

public class Genres implements Writable {

    // EFFECTS: constructs an empty list of genres
    private ArrayList<String> genres;

    public Genres() {
        genres = new ArrayList<>();
    }

    // EFFECTS: returns the number of genres in the list
    public int getLength() {
        return genres.size();
    }

    // EFFECTS: returns all the genres in the list
    public ArrayList<String> getInfo() {
        return genres;
    }

    // EFFECTS: returns the genre at the given index in the list, else returns null
    public String getInfoAtIndex(int index) {
        if (index <= genres.size()) {
            return genres.get(index);
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a genre to the list if it has not been added yet, is not null and is not empty string
    public void addGenre(String genre) {

        if (!genres.contains(genre) && !(genre == null) && !(genre.equals(""))) {
            genres.add(genre);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a genre if it exists in the list
    public void removeGenre(String genre) {

        if (genres.contains(genre)) {
            genres.remove(genre);
        }
    }

    // EFFECTS: checks whether the list contains all the genres in the input and returns true if that is the
    //          case, false otherwise
    public boolean containsGenres(Genres set) {
        boolean answer = true;
        for (String s : set.getInfo()) {
            if (genres.contains(s) && answer) {
                answer = genres.contains(s);
            } else {
                answer = false;
                break;
            }
        }
        return answer;
    }


    // EFFECTS: returns json representation of a list of genres
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("genres", genresToJson());
        return json;
    }

    // EFFECTS: returns all the genres in the list as a JSON array
    public JSONArray genresToJson() {
        JSONArray array = new JSONArray();

        for (int i = 0; i < genres.size(); i++) {
            array.put(genres.get(i));
        }

        return array;
    }


    // EFFECTS: returns true if two objects being compared are the same, false otherwise
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genres genres1 = (Genres) o;

        return genres != null ? genres.equals(genres1.genres) : genres1.genres == null;
    }

}
