package edu.jhu.cs.pl.lights_out;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.JavalinEvent;
import io.javalin.staticfiles.Location;
import org.postgresql.ds.PGSimpleDataSource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Server {
    private static ObjectMapper json = new ObjectMapper();

    public static void main(String[] args) throws SQLException {
        Javalin.create()

                .enableStaticFiles("/public")
                .enableStaticFiles(System.getProperty("user.dir") + "/src/main/resources/public", Location.EXTERNAL)

                .start(System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 7000);
    }

    public static ObjectMapper getJson() {
        return json;
    }
}
