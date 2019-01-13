package Calculo_Disponibilidad;

import nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Node> nodos;
    private double coste;
    private double fiabilidad;

    public Solution(){
        nodos = new ArrayList<>();
        coste = Double.POSITIVE_INFINITY;
        fiabilidad = 0;
    }

    public boolean isSolution(int destination){
        return nodos.get(nodos.size() - 1).getId() == destination;
    }

    public List<Node> getNodos() {
        return nodos;
    }

    public void setNodos(List<Node> nodos) {
        this.nodos = nodos;
    }

    public void addNodo(Node nodo) {
        nodos.add(nodo);

    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public double getFiabilidad() {
        return fiabilidad;
    }

    public void setFiabilidad(double fiabilidad) {
        this.fiabilidad = fiabilidad;
    }

    public void sumaValores(Solution aux, int index){
        int last = aux.getNodos().size() - 1;

        coste += aux.getNodos().get(last - 1).getConnectsTo().get(index).getCost();
        fiabilidad *= aux.getNodos().get(last).getReliability();
    }

    public void quitarValores(Solution aux, int index){
        int last = aux.getNodos().size() - 1;

        coste-= aux.getNodos().get(last - 1).getConnectsTo().get(index).getCost();
        fiabilidad /= aux.getNodos().get(last).getReliability();
    }

    public void clonar (Solution solution){
        int size = solution.getNodos().size();
        nodos.clear();

        for(int i = 0; i < size; i++){
            this.nodos.add(solution.nodos.get(i));
        }

        this.coste = solution.getCoste();
        this.fiabilidad = solution.getFiabilidad();
    }
}
