package nodes;

import java.util.ArrayList;

public class Node {

    private long id;

    private Double reliability;

    private ArrayList<ConnectsTo> connectsTo = new ArrayList<>();

    public Node(){
        id = 0;
        reliability = 0.0;

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Double getReliability() {

        return reliability;
    }

    public void setReliability(Double reliability) {

        this.reliability = reliability;
    }

    public ArrayList<ConnectsTo> getConnectsTo() {

        return connectsTo;
    }

    public void setConnectsTo(ArrayList<ConnectsTo> connectsTo) {

        this.connectsTo = connectsTo;
    }

}