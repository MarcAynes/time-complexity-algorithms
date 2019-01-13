package Calculo_Disponibilidad.Cola;

import nodes.ConnectsTo;
import nodes.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TipoCola {
    ArrayList<Node> node;
    long cost;
    Double Fiability;

    public TipoCola(){

        cost = 0;
        Fiability = 0.0;
        node = new ArrayList<>();
        Node aux = new Node();
        aux.setId(-1);
        node.add(aux);
        ArrayList<ConnectsTo> auxiliar = new ArrayList<>();
        ConnectsTo a = new ConnectsTo();
        auxiliar.add(a);
        node.get(0).setConnectsTo(auxiliar);
    }

    public TipoCola(long c, Node n){

        node = new ArrayList<>();
        cost = c;
        Fiability = 1.0;
        node.add(n);

    }

    public TipoCola(long c, TipoCola n, Node nodeAux){
        node = new ArrayList<>();
        cost = c;
        Fiability = 1.0;
        this.node.addAll(n.getArray());// n.getArray().;
        this.node.add(nodeAux);
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

    public double getFiability(){

        return Fiability;
    }

    public void setFiability(double a){

        Fiability = a;
    }
}
