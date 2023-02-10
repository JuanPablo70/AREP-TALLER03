package edu.eci.arep.webapps;

import edu.eci.arep.app.RESTService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CssService implements RESTService {
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/css\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/style.css")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
