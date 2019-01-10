package Calculo_Disponibilidad;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Disponibilidad {
    private Solution best;
    private Solution aux;
    private boolean [] visitado;
    private int n;

    public Disponibilidad(int n){
        best = new Solution();
        aux = new Solution();
        visitado = new boolean[n];
        Arrays.fill(visitado, false);
        this.n = n;
    }


    public Solution backtracking_D(){
        if (aux.isSolution(n)){

        }
        return best;
    }
}
