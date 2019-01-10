package users;


public class Connection {

    private String username;

    private long since;

    private long visits;

    private long likes;

    private long comments;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public Long getSince() {

        return since;
    }

    public void setSince(long since) {

        this.since = since;
    }

    public long getVisits() {

        return visits;
    }

    public void setVisits(long visits) {

        this.visits = visits;
    }

    public long getLikes() {

        return likes;
    }

    public void setLikes(long likes) {

        this.likes = likes;
    }

    public long getComments() {

        return comments;
    }

    public void setComments(long comments) {

        this.comments = comments;
    }

}