package users;


import java.util.ArrayList;

public class User {

    private String username;

    private int followers;

    private int follows;

    private Double activity;

    private ArrayList<Connection> connections = null;

    private ArrayList<Post> posts = null;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public int getFollowers() {

        return followers;
    }

    public void setFollowers(int followers) {

        this.followers = followers;
    }

    public int getFollows() {

        return follows;
    }

    public void setFollows(int follows) {

        this.follows = follows;
    }

    public Double getActivity() {

        return activity;
    }

    public void setActivity(Double activity) {

        this.activity = activity;
    }

    public ArrayList<Connection> getConnections() {

        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {

        this.connections = connections;
    }

    public ArrayList<Post> getPosts() {

        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {

        this.posts = posts;
    }

}