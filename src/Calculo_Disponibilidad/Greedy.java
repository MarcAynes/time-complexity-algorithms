package Calculo_Disponibilidad;

import nodes.ConnectsTo;
import nodes.Node;

import java.util.ArrayList;

public class Greedy {
    Node[] candidates;
    ArrayList<Node> Solution = new ArrayList<>();
    boolean found = Boolean.FALSE;
    long servidor;
    long destino;

    public Greedy(Node[] candidates, long servidor, long destino){

        this.candidates = candidates;
        this.servidor = servidor;
        this.destino = destino;

    }

    public void calculateGreedy(){
        Node c = candidates[(int) servidor];
        int distancia = candidates.length;

        Solution.add(c);
        distancia--;
        while(!found && distancia!= 0){
            c = selecciona(c);      //selecciono el siguiente node que tiene menos coste y que no he pasado por el.
            if(c.getId() == -1){
                break;
            }
            Solution.add(c);
            if(c.getId() == destino){
                found = Boolean.TRUE;
                break;
            }

       }
       if(found != Boolean.FALSE) {
           System.out.println("El cami amb el cost més baix es:");
           for(int i = 0; i < Solution.size(); i++){
               System.out.println(Solution.get(i).getId());
           }
           System.out.println();
       }else{
           System.out.println("Error, no se ha encontrado con Greedy una solucion por coste");
       }

    }

    private Node selecciona(Node c){

        ArrayList<ConnectsTo> coneccion =  c.getConnectsTo();
        ConnectsTo ca = new ConnectsTo();

        for(int i = 0; coneccion.size() > i; i++){
            if(ca.getCost() > coneccion.get(i).getCost() && !Solution.contains(candidates[coneccion.get(i).getTo() - 1])){
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

    public void calculateGreedyFiable(){
        Node c = candidates[(int) servidor];
        int distancia = candidates.length;

        Solution.add(c);
        distancia--;
        while(!found && distancia!= 0){
            c = seleccionaFiable(c);      //selecciono el siguiente node que tiene mas fiablidad y que no he pasado por el.
            if(c.getId() == -1){
                break;
            }
            Solution.add(c);
            if(c.getId() == destino){
                found = Boolean.TRUE;
                break;
            }

        }
        if(found != Boolean.FALSE) {
            System.out.println("El cami més fiable es:");
            for(int i = 0; i < Solution.size(); i++){
                System.out.println(Solution.get(i).getId());
            }
            System.out.println();
        }else{
            System.out.println("Error, no se ha encontrado con Greedy una solucion al buscar un camino por fiabilidad");
        }

    }

    private Node seleccionaFiable(Node c){

        ArrayList<ConnectsTo> coneccion =  c.getConnectsTo();
        Node ca = new Node();
        ca.setId(-1);

        for(int i = 0; coneccion.size() > i; i++){
            if(candidates[c.getConnectsTo().get(i).getTo() - 1].getReliability() > ca.getReliability() && !Solution.contains(candidates[c.getConnectsTo().get(i).getTo() - 1])){
                ca = candidates[c.getConnectsTo().get(i).getTo() - 1];
            }
        }
        if(ca.getId() == -1){
            Node a = new Node();
            a.setId(-1);
            return a;
        }else{
            return ca;
        }

    }

    public void ReseteaGreey(){

        Solution.clear();
        found = Boolean.FALSE;
    }




}
