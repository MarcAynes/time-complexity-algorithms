package Calculo_Disponibilidad;

import nodes.ConnectsTo;
import nodes.Node;

import java.util.ArrayList;

public class Greedy {
    Node[] candidates;
    ArrayList<Node> Solution = new ArrayList<>();
    boolean found = Boolean.FALSE;

    public Greedy(Node[] candidates){
        this.candidates = candidates;
    }

    public void calculateGreedy(){
        Node c = new Node();
        int distancia = candidates.length;
        for(int i = 0; i < distancia; i++){
            if(c.getReliability() < candidates[i].getReliability()){
                c = candidates[i];
            }
        }

        Solution.add(c);
        distancia--;
        while(!found && distancia!= 0){
            c = selecciona(c);      //selecciono el siguiente node que tiene menos coste y que no he pasado por el.
            if(c.getId() == -1){
                break;
            }

       }
       if(found != Boolean.FALSE) {
           System.out.println("El nodo con id " + Solution.get(0).getId() + "tiene la fiabilidad mas alta");
           System.out.println();
       }else{
           System.out.println("Error, no se ha encontrado con Greedy una solucion");
       }
    }

    private Node selecciona(Node c){

        ArrayList<ConnectsTo> coneccion =  c.getConnectsTo();
        ConnectsTo ca = new ConnectsTo();

        for(int i = 0; coneccion.size() > i; i++){
            if(ca.getCost() > coneccion.get(i).getCost() && !Solution.contains(coneccion.get(i).getTo())){
                ca = coneccion.get(i);
            }
        }
        if(ca.getTo() == -1){
            Node a = new Node();
            a.setId(-1);
            return a;
        }else{
            return candidates[(int) (ca.getTo() - 1)];
        }

    }




}
