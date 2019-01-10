package nodes;

public class ConnectsTo {

    private long to;

    private String name;

    private long cost;

    public ConnectsTo(){
        to = -1;
        name = " ";
        cost = 999999999;
    }


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