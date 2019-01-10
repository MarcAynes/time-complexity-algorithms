package users;

import java.util.ArrayList;

public class Post {

    private int id;

    private int published;

    private ArrayList<Double> location = null;

    private String category;

    private ArrayList<String> likedBy = null;

    private ArrayList<String> commentedBy = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public ArrayList<Double> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Double> location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(ArrayList<String> likedBy) {
        this.likedBy = likedBy;
    }

    public ArrayList<String> getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(ArrayList<String> commentedBy) {
        this.commentedBy = commentedBy;
    }

}