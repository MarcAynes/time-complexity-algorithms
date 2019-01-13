package Server;

import java.util.ArrayList;

public class Server {


    private int id;

    private String country;

    private ArrayList<Double> location = null;

    private int [] reachable_from;


    public int getId() {

        return id;
    }

    public String getCountry() {

        return country;
    }

    public ArrayList<Double> getLocation() {

        return location;
    }

    public int[] getReachableFrom() {

        return reachable_from;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public void setLocation(ArrayList<Double> location) {

        this.location = location;
    }

    public void setReachableFrom(int[] reachableFrom) {

        this.reachable_from = reachableFrom;
    }

}