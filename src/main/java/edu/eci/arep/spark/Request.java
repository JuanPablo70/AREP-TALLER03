package edu.eci.arep.spark;

public class Request {
    private static Request _instance = new Request();

    public Request(){}

    public static Request getInstance() {return _instance;}
}
