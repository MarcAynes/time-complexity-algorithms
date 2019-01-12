package Calculo_Disponibilidad;

import Calculo_Disponibilidad.Cola.Cola;
import Calculo_Disponibilidad.Cola.TipoCola;
import Server.Server;
import nodes.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BranchAndBound {

    Node[] candidates;
    ArrayList<Node> SolutionCost = new ArrayList<>();
    ArrayList<Node> SolutionFiability = new ArrayList<>();
    boolean found = Boolean.FALSE;
    long servidor;
    long destino;
    long costTotal = 999999999;
    long fiabilidadTotal = 0;
    Cola cola = new Cola();

    public BranchAndBound(Node[] candidates, Server[] a, long servidor, long destino){

        this.candidates = candidates;
        this.servidor = servidor;
        this.destino = destino;


            for(int j = 0; j < a[(int) (servidor)].getReachableFrom(); j++) {
                TipoCola aux = new TipoCola(0, candidates[(int) (a[(int) (servidor )].getReachableFrom() - 1)]);
                cola.enqueueNoOrdenate(aux);
            }
            cola.Ordenate();        //ordenacion de cola, a partir de aqui tenemos una cola ordenada

    }

    public void BranchAndBound(Server[] server){
        TipoCola Best = new TipoCola();
        Best.setCost(999999999);
        while(!cola.Vacio()){
            TipoCola x = cola.dequeue();
            ArrayList<TipoCola> Options = new ArrayList<>();
            for(int i = 0; x.getLastNode().getConnectsTo().size() > i && !contiene(x, Options, i); i++){        //expand
                TipoCola aux = new TipoCola(x.getCost() + x.getLastNode().getConnectsTo().get(i).getCost(), x, candidates[x.getLastNode().getConnectsTo().get(i).getTo() - 1]);
                Options.add(aux);
            }
            for(int i = 0; i < Options.size();i++){

                if(Options.get(i).getLastNode().getId() == server[(int) (destino - 1)].getId() && Options.get(i).getCost() < Best.getCost()){  //if its solution
                    Best = Options.get(i);
                }else{
                    if(Options.get(i).getCost() < Best.getCost()){
                        cola.enqueue(Options.get(i));
                    }
                }

            }

        }
        if(Best.getArray().get(0).getId() != -1) {
            System.out.println("El camino con menor coste es:");
            for (int i = 0; Best.getArray().size() > i; i++) {
                System.out.println(Best.getArray().get(i).getId());
            }
            System.out.println("El coste es de: " + Best.getCost());
        }else{
            System.out.println("Error, No hay camino posible que conecte los servidores");
        }


    }

    private boolean contiene(TipoCola x, ArrayList<TipoCola> options, int j){

        boolean contiene = Boolean.FALSE;

        for(int i = 0; i < x.getArray().size() - 1; i++){
            if(x.getArray().get(i).getId() == x.getLastNode().getId() && x.getArray().size() != 1){ //suposo que en un node no apunta a ell mateix
                contiene = Boolean.TRUE;
                break;
            }
        }
        return contiene;
    }


}
