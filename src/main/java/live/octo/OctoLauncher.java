package live.octo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import live.octo.user.OctoUser;

import static live.octo.util.ResponseCodes.OK;
import static live.octo.util.ResponseCodes.UNSUPPORTED_MEDIA;
import static spark.Spark.*;

/**
 * The program starts here.
 */
public class OctoLauncher {
    private static Gson gson;

    public static void main(String[] args) {
        // JSON stuff
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(OctoUser.class, new OctoUser.Adapter());
        gson = builder.create();

        // Server
        staticFiles.location("/public");
        port(80);
        init();

        // Routes
        post("/accounts/create", (request, response) -> {
            if (!request.contentType().equals("application/json")) {
                response.status(UNSUPPORTED_MEDIA);
                return "Content-Type must be application/json";
            }
            OctoUser user = gson.fromJson(request.body(), OctoUser.class);
            response.status(OK);
            return user.toString();
        });
        System.out.println("Server is up my dude");
    }
}
