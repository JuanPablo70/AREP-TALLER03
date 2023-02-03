package edu.eci.arep;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

/**
 * Client class that tests HttpServer functionalities
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.print("Type the movie you are looking for: ");
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        while (!input.equals("")) {
            JSONObject json = new JSONObject(HttpServer.searchMovie(input));
            if (json.get("Response").equals("False")) {
                System.out.println("Movie not found");
            } else {
                System.out.println("Server: " + json);
            }
            System.out.print("Type the movie you are looking for: ");
            input = obj.nextLine();
        }
    }
}
