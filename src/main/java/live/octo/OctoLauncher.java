package live.octo;

import static spark.Spark.*;

/**
 * The program starts here.
 */
public class OctoLauncher {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(80);
        init();
        System.out.println("Server is up my dude");
    }
}
