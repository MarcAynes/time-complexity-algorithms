package nodes;

public class ConnectsTo {

    private long to;

    private String name;

    private long cost;


    public void setTo(long to) {

        this.to = to;
    }

    public void setCost(long cost) {

        this.cost = cost;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }


    public long getCost() {

        return cost;
    }

    public long getTo() {

        return to;
    }

}