package nodes;

import java.util.ArrayList;

public class Node {

    private Integer id;

    private Double reliability;

    private ArrayList<ConnectsTo> connectsTo = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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