package Calculo_Disponibilidad;

import Calculo_Disponibilidad.Cola.TipoCola;
import Calculo_Disponibilidad.Solution;
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


        public void backtracking_D(Node[] nodes, ArrayList<Long> origin, ArrayList<Long> destination, int current, int conection_index){
        int index, size;
        boolean solucion = false;

        if (!inicio){
            for(int i = 0; i < origin.size(); i++){
                for (int m = 0; m < destination.size(); m++) {
                    if (  origin.get(i) + 1 ==   destination.get(m).intValue()) {
                        bestCoste.addNodo(nodes[  origin.get(i).intValue() - 1]);
                        bestCoste.setFiabilidad(nodes[  origin.get(i).intValue() - 1].getReliability());
                        bestFiabilidad.addNodo(nodes[  (origin.get(i).intValue()) - 1]);
                        bestFiabilidad.setFiabilidad(nodes[  origin.get(i).intValue() - 1].getReliability());
                        break;
                    }
                }

                if (aux.getNodos().size() == 0) {
                    aux.addNodo(nodes[  origin.get(i).intValue() - 1]);
                    aux.setFiabilidad(nodes[  origin.get(i).intValue() - 1].getReliability());
                    aux.setCoste(0);
                    visitado[  origin.get(i).intValue() - 1] = true;
                    size = nodes[  origin.get(i).intValue() - 1].getConnectsTo().size();

                    for (int k = 0; k < size; k++) {
                        inicio = true;
                        backtracking_D(nodes, origin, destination, nodes[  origin.get(i).intValue() - 1].getConnectsTo().get(k).getTo(), k);
                        inicio = false;
                    }

                    visitado[  origin.get(i).intValue() - 1] = false;
                    aux.setFiabilidad(0);
                    aux.getNodos().clear();

                }
            }

        } else {
            aux.addNodo(nodes[current - 1]);
            aux.sumaValores(aux, conection_index);
            visitado[current - 1] = true;

            solucion = false;

            for (int y = 0; y < destination.size(); y++){
                if (aux.isSolution( destination.get(y).intValue())) {
                    solucion = true;
                    if (aux.getCoste() < bestCoste.getCoste()) {
                        bestCoste.clonar(aux);
                    }

                    if (aux.getFiabilidad() > bestFiabilidad.getFiabilidad()) {
                        bestFiabilidad.clonar(aux);
                    }
                }

            }

            if(!solucion && (aux.getCoste() < bestCoste.getCoste() || aux.getFiabilidad() > bestFiabilidad.getFiabilidad())){
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

    public void setBestCoste(TipoCola Bestc){
        this.bestCoste.setCoste((double) Bestc.getCost());
        this.bestCoste.setNodos(Bestc.getArray());

    }

    public void setBestFiabilidad(TipoCola Bestf){
        this.bestFiabilidad.setFiabilidad(Bestf.getFiability());
        this.bestFiabilidad.setNodos(Bestf.getArray());

    }

}
