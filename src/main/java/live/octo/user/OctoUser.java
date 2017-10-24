package live.octo.user;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

public class OctoUser {
    private String id;
    private String username;

    public OctoUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OctoUser:["
                + "id=" + id + ","
                + "username=" + username
                + "]";
    }

    public static class Adapter extends TypeAdapter<OctoUser> {

        @Override
        public void write(JsonWriter json, OctoUser user) throws IOException {
            json.name("id").value(user.getId());
            json.name("username").value(user.getUsername());
            json.close();
        }

        @Override
        public OctoUser read(JsonReader json) throws IOException {
            UUID id = UUID.randomUUID();
            String username = null;

            json.beginObject();
            while (json.hasNext()) {
                switch (json.nextName()) {
                    case "username":
                        username = json.nextString();
                        break;
                }
            }
            json.endObject();
            return new OctoUser(id.toString(), username);
        }
    }
}
