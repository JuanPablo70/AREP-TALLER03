package edu.eci.arep.webapps;

import edu.eci.arep.app.RESTService;

public class HelloService implements RESTService {
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/html\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "Hello world"
                + "</body>"
                + "</html>";
    }
}
