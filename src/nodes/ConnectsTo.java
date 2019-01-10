package nodes;

public class ConnectsTo {

    private int to;

    private String name;

    private long cost;


    public void setTo(int to) {

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

    public int getTo() {

        return to;
    }

}