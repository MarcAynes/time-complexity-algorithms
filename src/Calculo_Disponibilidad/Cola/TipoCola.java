package Calculo_Disponibilidad.Cola;

import nodes.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TipoCola {
    ArrayList<Node> node;
    long cost;

    public TipoCola(){

        cost = 0;
        node = new ArrayList<>();
        Node aux = new Node();
        aux.setId(-1);
        node.add(aux);

    }

    public TipoCola(long c, Node n){

        node = new ArrayList<>();
        cost = c;
        node.add(n);

    }

    public void setNode(Node a){

        node.set(0, a);
    }

    public void setCost(long cost){

        this.cost = cost;
    }

    public long getCost(){

        return cost;
    }

    public Node getNode(){

        return node.get(0);
    }

    public ArrayList<Node> getArray(){

        return node;
    }

    public void setToArray(Node aux){

        node.add(aux);
    }

    public Node getLastNode(){

        return node.get(node.size() - 1);
    }
}
