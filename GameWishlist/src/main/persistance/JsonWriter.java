package persistance;

import model.GameWishlist;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/*
Represents a writer that writes JSON representation of GameWishlist data to file
 */

public class JsonWriter {
    private static final int TAB = 4;
    private String directory;
    private File file1;
    private File file2;
    private String fileName1;
    private String fileName2;
    private PrintWriter wishlistWriter;

    // EFFECTS: constructs a writer to write the data of a GameWishlist object to the destination file
    public JsonWriter(String directory, String fileName1, String fileName2) {
        this.directory = directory;
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
    }

    // MODIFIES: this
    // EFFECTS: creates a temporary file where data will be saved to and opens a writer for that file;
    // throws IOException if destination file cannot be opened for writing
    public void openWishlistWriter() throws IOException {
        file1 = new File(directory, fileName1);
        file2 = new File(directory, fileName2);
        file2.createNewFile();
        wishlistWriter = new PrintWriter(file2);

    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of the Game Wishlist object to the file
    public void writeWishlist(GameWishlist wishlist) {
        JSONObject wishlistJson = wishlist.toJson();
        saveWishlistToFile(wishlistJson.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void closeWishlistWriter() {

        wishlistWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: deletes the original file, renames the temporary file to be the final file that will contain
    // the saved data, and write the data to it
    public void saveWishlistToFile(String wishlistJson) {

        file1.delete();
        file2.renameTo(new File(directory, fileName1));
        wishlistWriter.print(wishlistJson);

    }

}
