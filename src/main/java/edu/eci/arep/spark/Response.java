package edu.eci.arep.spark;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Response {
    private static Response _instance = new Response();
    private String contentType;
    private String path;

    public Response(){}
    public static Response getInstance() {return _instance;}

    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: "+ getContentType() +"\r\n" +
                "\r\n";
    }

    public String getResponse() {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/" + getPath())), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getHeader() + content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
