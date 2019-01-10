package Server;

import java.util.ArrayList;

public class Server {


    private String id;

    private String country;

    private ArrayList<Double> location = null;

    private int reachableFrom;


    public String getId() {

        return id;
    }

    public String getCountry() {

        return country;
    }

    public ArrayList<Double> getLocation() {

        return location;
    }

    public int getReachableFrom() {

        return reachableFrom;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public void setLocation(ArrayList<Double> location) {

        this.location = location;
    }

    public void setReachableFrom(int reachableFrom) {

        this.reachableFrom = reachableFrom;
    }

}