package edu.eci.arep;

import java.net.*;
import java.io.*;
import java.util.HashMap;

/**
 * Web Server that uses an external API to find information about a specific movie
 */
public class HttpServer {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "c2d09dcc";
    private static final String GET_URL = "http://www.omdbapi.com/?t=%1$s&apikey=%2$s";
    private static final HashMap<String, String> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean status = true;
            String query = "";
            while ((inputLine = in.readLine()) != null) {
                if (status) {
                    query = inputLine.split(" ")[1];
                    status = false;
                }
                System.out.println("Received: " + inputLine);
                //System.out.println("-------" + request + "----------");
                if (!in.ready()) {
                    break;
                }
            }

            if (query.startsWith("/movie")) {
                String movieName = query.split("name=")[1];
                outputLine = htmlSimple(movieName);
            } else {
                outputLine = htmlGetForm();
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Method that finds the movie the user is looking for
     * @param movieName movie's name
     * @return String information of the movie in a Json format
     * @throws IOException
     */
    public static String searchMovie(String movieName) throws IOException {
        String movieJson = "";
        if (cache.containsKey(movieName)) {
            movieJson = cache.get(movieName);
            System.out.println("Pelicula en cache");
        } else {
            String url = String.format(GET_URL, movieName, API_KEY);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                movieJson = response.toString();
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
            cache.put(movieName, movieJson);
            System.out.println("Guardada en cache");
        }
        return movieJson;
    }

    /**
     * Method that displays in a json format the movie the user typed in the form
     * @param movie movie name
     * @return String according to the movie
     * @throws IOException
     */
    public static String htmlSimple(String movie) throws IOException {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: application/json\r\n" +
                "\r\n" +
                searchMovie(movie);
    }

    /**
     * Method that displays a get form
     * @return String
     */
    public static String htmlGetForm() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/html\r\n" +
                "\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Form with GET</h1>\n" +
                "        <form action=\"/movie\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/movie?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +/*
                "        <h1>Form with POST</h1>\n" +
                "        <form action=\"/hellopost\">\n" +
                "            <label for=\"postname\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n" +
                "        </form>\n" +
                "        \n" +
                "        <div id=\"postrespmsg\"></div>\n" +
                "        \n" +
                "        <script>\n" +
                "            function loadPostMsg(name){\n" +
                "                let url = \"/hellopost?name=\" + name.value;\n" +
                "\n" +
                "                fetch (url, {method: 'POST'})\n" +
                "                    .then(x => x.text())\n" +
                "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n" +
                "            }\n" +
                "        </script>\n" +*/
                "    </body>\n" +
                "</html>";
    }
}