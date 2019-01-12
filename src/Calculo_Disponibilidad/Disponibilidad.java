package Calculo_Disponibilidad;

import nodes.Node;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Disponibilidad {
    private Solution bestCoste;
    private Solution bestFiabilidad;
    private Solution aux;
    private boolean [] visitado;
    private int n;

    public Disponibilidad(int n){
        bestCoste = new Solution();
        bestFiabilidad = new Solution();
        aux = new Solution();
        visitado = new boolean[n];
        Arrays.fill(visitado, false);
        this.n = n;
    }


    public void backtracking_D(Node[] nodes, int origin, int destination, int current, int conection_index){
        int index, size;

        if (origin + 1 == destination){
            bestCoste.addNodo(nodes[origin]);
            bestCoste.setFiabilidad(nodes[origin].getReliability());
            bestFiabilidad.addNodo(nodes[origin]);
            bestFiabilidad.setFiabilidad(nodes[origin].getReliability());
        }
        else if (aux.getNodos().size() == 0){
            aux.addNodo(nodes[origin]);
            aux.setFiabilidad(nodes[origin].getReliability());
            aux.setCoste(0);
            visitado[origin] = true;
            size = nodes[origin].getConnectsTo().size();

            for (int i = 0; i < size; i++){
                backtracking_D(nodes, origin, destination, nodes[origin].getConnectsTo().get(i).getTo(), i);
            }
        }
        else {
            aux.addNodo(nodes[current - 1]);
            aux.sumaValores(aux, conection_index);
            visitado[current - 1] = true;

            if (aux.isSolution(destination)){
                if(aux.getCoste() < bestCoste.getCoste()){
                    bestCoste = aux;
                }

                if(aux.getFiabilidad() > bestFiabilidad.getFiabilidad()){
                    bestFiabilidad = aux;
                }
            }
            else {
                size = nodes[current - 1].getConnectsTo().size();


                for(int i = 0; i < size; i++) {
                    index = nodes[current - 1].getConnectsTo().get(i).getTo();

                    if (!(visitado[index])) {
                        backtracking_D(nodes, origin, destination, index, i);
                    }
                }

            }
            aux.quitarValores(aux, conection_index);
            aux.getNodos().remove(aux.getNodos().size() - 1);
            visitado[current - 1] = false;
        }
    }

    public void getBestCoste() {
        int size = bestCoste.getNodos().size();
        System.out.println("Camino de nodos mejor coste:");
        for(int i = 0; i < size; i++){
            System.out.println(bestCoste.getNodos().get(i));
        }
        System.out.println("\n");

    }

    public void getBestFiabilidad() {
        int size = bestFiabilidad.getNodos().size();
        System.out.println("Camino de nodos mejor fiabilidad:");
        for(int i = 0; i < size; i++){
            System.out.println(bestFiabilidad.getNodos().get(i));
        }

        System.out.println("\n");
    }

}
