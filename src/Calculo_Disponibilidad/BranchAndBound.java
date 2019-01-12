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


            for(int j = 0; j < candidates[(int) (a[(int) (servidor - 1)].getReachableFrom() - 1)].getConnectsTo().size(); j++) {
                TipoCola aux = new TipoCola(candidates[(int) (a[(int) (servidor - 1)].getReachableFrom() - 1)].getConnectsTo().get(j).getCost(), candidates[(int) (a[(int) (servidor - 1)].getReachableFrom() - 1)]);
                cola.enqueueNoOrdenate(aux);
            }
            cola.Ordenate();        //ordenacion de cola, a partir de aqui tenemos una cola ordenada

    }

    public void BranchAndBound(){

        while(!cola.Vacio()){
            
        }


    }


}
