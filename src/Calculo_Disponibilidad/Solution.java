package Calculo_Disponibilidad;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> nodos;
    float coste;
    float fiabilidad;

    public Solution(){
        nodos = new ArrayList<>();
        coste = 0;
        fiabilidad = 0;
    }

    public boolean isSolution(int n){
        return nodos.size() == n;
    }

}
