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

        if (origin == destination){
            bestCoste.addNodo(nodes[origin]);
            bestCoste.setFiabilidad(nodes[origin].getReliability());
            bestFiabilidad.addNodo(nodes[origin]);
            bestFiabilidad.setFiabilidad(nodes[origin].getReliability());
        }
        else if (aux.getNodos().size() == 0){
            aux.addNodo(nodes[origin]);
            aux.setFiabilidad(nodes[origin].getReliability());
            size = nodes[origin].getConnectsTo().size();
            for (int i = 0; i < size; i++){
                backtracking_D(nodes, origin, destination, nodes[origin].getConnectsTo().get(i).getTo(), i);
            }
        }
        else {
            aux.sumaValores(aux, conection_index);
            aux.addNodo(nodes[current]);

            if (aux.isSolution(destination)){
                if(aux.getCoste() < bestCoste.getCoste()){
                    bestCoste = aux;
                }

                if(aux.getFiabilidad() > bestFiabilidad.getFiabilidad()){
                    bestFiabilidad = aux;
                }
            }
            else {
                size = nodes[current].getConnectsTo().size();
                for(int i = 0; i < n; i++){

                    for(int j = 0; i < size; i++) {
                        index = nodes[i].getConnectsTo().get(j).getTo();

                        if (!(visitado[i])) {
                            aux.addNodo(nodes[index]);
                        }
                    }
                }


            }
            aux.getNodos().remove(aux.getNodos().size() - 1);
        }
    }

    public Solution getBestCoste() {
        return bestCoste;
    }

    public Solution getBestFiabilidad() {
        return bestFiabilidad;
    }
}
