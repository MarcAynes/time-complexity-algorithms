package Calculo_Disponibilidad.Cola;

import nodes.Node;

public class TipoCola {
    Node node;
    long cost;

    public TipoCola(){

        cost = 0;
        node = new Node();

    }

    public TipoCola(long c, Node n){

        cost = c;
        node = n;

    }

    public void setNode(Node a){

        node = a;
    }

    public void setCost(long cost){

        this.cost = cost;
    }

    public long getCost(){

        return cost;
    }

    public Node getNode(){

        return node;
    }
}
