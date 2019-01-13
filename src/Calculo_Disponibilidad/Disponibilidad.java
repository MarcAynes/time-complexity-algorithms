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
    private boolean inicio;

    public Disponibilidad(int n){
        bestCoste = new Solution();
        bestFiabilidad = new Solution();
        aux = new Solution();
        visitado = new boolean[n];
        Arrays.fill(visitado, false);
        this.n = n;
        inicio = false;
    }


        public void backtracking_D(Node[] nodes, int [] origin, int [] destination, int current, int conection_index){
        int index, size;
        boolean solucion = false;

        if (!inicio){
            for(int i = 0; i < origin.length; i++){
                for (int m = 0; m < destination.length; m++) {
                    if (origin[i] + 1 == destination[m]) {
                        bestCoste.addNodo(nodes[origin[i] - 1]);
                        bestCoste.setFiabilidad(nodes[origin[i] - 1].getReliability());
                        bestFiabilidad.addNodo(nodes[origin[i] - 1]);
                        bestFiabilidad.setFiabilidad(nodes[origin[i] - 1].getReliability());
                        break;
                    }
                }

                if (aux.getNodos().size() == 0) {
                    aux.addNodo(nodes[origin[i] - 1]);
                    aux.setFiabilidad(nodes[origin[i] - 1].getReliability());
                    aux.setCoste(0);
                    visitado[origin[i] - 1] = true;
                    size = nodes[origin[i] - 1].getConnectsTo().size();

                    for (int k = 0; k < size; k++) {
                        inicio = true;
                        backtracking_D(nodes, origin, destination, nodes[origin[i] - 1].getConnectsTo().get(k).getTo(), k);
                        inicio = false;
                    }

                    visitado[origin[i] - 1] = false;
                    aux.setFiabilidad(0);
                    aux.getNodos().clear();

                }
            }

        } else {
            aux.addNodo(nodes[current - 1]);
            aux.sumaValores(aux, conection_index);
            visitado[current - 1] = true;

            solucion = false;

            for (int y = 0; y < destination.length; y++){
                if (aux.isSolution(destination[y])) {
                    solucion = true;
                    if (aux.getCoste() < bestCoste.getCoste()) {
                        bestCoste.clonar(aux);
                    }

                    if (aux.getFiabilidad() > bestFiabilidad.getFiabilidad()) {
                        bestFiabilidad.clonar(aux);
                    }
                }

            }

            if(!solucion){
                size = nodes[current - 1].getConnectsTo().size();

                for(int i = 0; i < size; i++) {
                    index = nodes[current - 1].getConnectsTo().get(i).getTo();

                    if (!(visitado[index - 1])) {
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
            System.out.println(bestCoste.getNodos().get(i).getId());
        }
        System.out.println("\n");

    }

    public void getBestFiabilidad() {
        int size = bestFiabilidad.getNodos().size();
        System.out.println("Camino de nodos mejor fiabilidad:");
        for(int i = 0; i < size; i++){
            System.out.println(bestFiabilidad.getNodos().get(i).getId());
        }

        System.out.println("\n");
    }

}
