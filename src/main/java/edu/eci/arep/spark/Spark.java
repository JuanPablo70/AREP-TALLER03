package edu.eci.arep.spark;

import java.util.HashMap;
import java.util.Map;

public class Spark {

    private static Spark _instance = new Spark();
    private Map<String, String> getServices = new HashMap<>();
    private Map<String, String> postServices = new HashMap<>();

    public Spark(){};

    public static Spark getInstance() {return _instance;}

    public void get(String path, Route route) {
        Response res = Response.getInstance();
        Request req = Request.getInstance();
        String rout = route.handle(req, res);
        getServices.put(path, rout);
    }

    public void post(String path, Route route) {
        Response re = Response.getInstance();
        Request rq = Request.getInstance();
        String rt = route.handle(rq, re);
        postServices.put(path, rt);
    }

    public String getGetService(String path) {
        return getServices.get(path);
    }

    public String getPostService(String path) {
        return postServices.get(path);
    }

}
