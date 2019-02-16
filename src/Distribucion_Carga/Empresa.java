package Distribucion_Carga;

import users.User;

import java.util.ArrayList;

public class Empresa {
    ArrayList<Integer> buenos = new ArrayList<>();
    double distanciaTotal = 0.0;
    double costTotal = 0.0;

    public Empresa(){

    }

    public void Add(ArrayList<Integer> aux, double distancia, double cost){
        buenos = aux;
        this.distanciaTotal = distancia;
        this. costTotal = cost;
    }

    public double getCost(){
        return costTotal;
    }

    public double getDistancia(){
        return distanciaTotal;
    }

    public ArrayList<Integer> getBuenos(){
        return buenos;
    }


}
