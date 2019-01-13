package Calculo_Disponibilidad;

import Calculo_Disponibilidad.Cola.TipoCola;
import nodes.ConnectsTo;
import nodes.Node;

import java.util.ArrayList;

public class Greedy {
    Node[] candidates;
    ArrayList<Node> Solution = new ArrayList<>();
    boolean found = Boolean.FALSE;
    long servidor;
    long destino;
    int printa;

    public Greedy(Node[] candidates, long servidor, long destino, int i){

        this.candidates = candidates;
        this.servidor = servidor;
        this.destino = destino;
        this.printa = i;
    }

    public TipoCola calculateGreedy(){
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
       if(printa == 1) {
           if (found != Boolean.FALSE) {
               System.out.println("El cami amb el cost més baix es:");
               for (int i = 0; i < Solution.size(); i++) {
                   System.out.println(Solution.get(i).getId());
               }
               long Coste = 0;
               for (int i = 0; i < Solution.size() - 1; i++) {
                   for (int j = 0; Solution.get(i).getConnectsTo().size() > j; j++) {
                       if (Solution.get(i).getConnectsTo().get(j).getTo() == Solution.get(i + 1).getId()) {
                           Coste += Solution.get(i).getConnectsTo().get(j).getCost();
                       }
                   }
               }
               System.out.println("El coste total es: " + Coste);
               TipoCola returned = new TipoCola(Coste, Solution, 1.0);
               return returned;

           } else {
               System.out.println("Error, no se ha encontrado con Greedy una solucion por coste");
               TipoCola returned = new TipoCola();
               return returned;
           }
       }else{
           if(found){
               long Coste = 0;
               for (int i = 0; i < Solution.size() - 1; i++) {
                   for (int j = 0; Solution.get(i).getConnectsTo().size() > j; j++) {
                       if (Solution.get(i).getConnectsTo().get(j).getTo() == Solution.get(i + 1).getId()) {
                           Coste += Solution.get(i).getConnectsTo().get(j).getCost();
                       }
                   }
               }
               TipoCola returned = new TipoCola(Coste, Solution, 1.0);
               return returned;
           }else{
               TipoCola returned = new TipoCola();
               return returned;
           }
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

    public TipoCola calculateGreedyFiable(){
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

        if(printa == 1) {
            if(found != Boolean.FALSE) {
                System.out.println("El cami més fiable es:");
                for(int i = 0; i < Solution.size(); i++){
                    System.out.println(Solution.get(i).getId());
                }
                double fiabilidad = 1;
                for(int i = 0; i < Solution.size() - 1; i++) {

                    fiabilidad = fiabilidad*Solution.get(i).getReliability();
                }
                System.out.println("La fiabilidad total es: " + fiabilidad);
                TipoCola returned = new TipoCola(0, Solution, fiabilidad);
                return returned;

            }else{
                System.out.println("Error, no se ha encontrado con Greedy una solucion al buscar un camino por fiabilidad");
                TipoCola returned = new TipoCola();
                return returned;
            }
        }else{
            if(found){
                double fiabilidad = 1;
                for(int i = 0; i < Solution.size() - 1; i++) {

                    fiabilidad = fiabilidad*Solution.get(i).getReliability();
                }
                TipoCola returned = new TipoCola(0, Solution, fiabilidad);
                return returned;
            }else{
                TipoCola returned = new TipoCola();
                return returned;
            }
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

    public void ReseteaGreedy(){

        Solution.clear();
        found = Boolean.FALSE;
    }




}
