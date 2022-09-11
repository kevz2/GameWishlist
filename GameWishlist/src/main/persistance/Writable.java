package persistance;

import org.json.JSONObject;

/*
An interface with the method toJson(), which can be used for saving an object
 */

public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
