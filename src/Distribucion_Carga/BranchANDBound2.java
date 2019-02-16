package Distribucion_Carga;

import Distribucion_Carga.Cola2.Cola2;
import Server.Server;
import users.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Distribucion_Carga.Haversiano.calcularDistancia;

public class BranchANDBound2 {
    ArrayList<Empresa> posibles = new ArrayList<>();
    ArrayList<Integer> best, x;
    //ArrayList<Empresa> posiblesBest;
    Cola2 live_nodes = new Cola2();
    int sizeUser;
    int sizeServer;
    double cost;
    double tolerancia;

    public BranchANDBound2(int sizeServer, int sizeUser, User[] users, double tolerancia){
        best = new ArrayList<>();
        //posiblesBest = new ArrayList<>();
        cost = 999999999.0;
        this.tolerancia = tolerancia;
        //visitado = new boolean[sizeUser];
        //Arrays.fill(visitado, false);
        this.sizeUser = sizeUser;
        this.sizeServer = sizeServer;

        for(int i = 0; i < sizeServer; i++){
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(i);
            live_nodes.enqueue(aux);
        }
        best.add(-1);


    }

    public void Branch(Server[] servers, User[] users){


        while(!live_nodes.Vacio()){
            x = live_nodes.dequeue();
            if(x.size() == sizeUser){
                double max, min, auxiliar;
                max = 0.0;
                min = 999999999.0;
                for (int y = 0; y < sizeServer; y++){
                    double aux = 0.0;
                    for (int i = 0; i < sizeUser;i++){
                        if (x.get(i) == y){
                            aux += users[i].getActivity();
                        }
                    }
                    max = (aux > max) ? aux:max;
                    min = (aux < min) ? aux:min;
                }
                auxiliar = max - min;

                if (best.get(0) == -1){
                    best = x;
                    cost = auxiliar;

                }else{
                    if (auxiliar < cost){
                        double distancia = 0.0;
                        for (int i = 0; i < sizeUser; i++) {
                            double distanciaAux = 0.0;
                            for (int j = 0; j < users[i].getPosts().size(); j++) {
                                distanciaAux += calcularDistancia(users[i].getPosts().get(j).getLocation().get(0), users[i].getPosts().get(j).getLocation().get(1), servers[best.get(i)].getLocation().get(0), servers[best.get(i)].getLocation().get(1));
                            }
                            distancia += distanciaAux/users[i].getPosts().size();
                        }
                        Empresa a = new Empresa();
                        a.Add(best, distancia, cost);
                        posibles.add(a);
                        best = x;
                        cost = auxiliar;
                    }else{
                        if (auxiliar - tolerancia < cost){
                            double distancia = 0.0;
                            for (int i = 0; i < sizeUser; i++){
                                double distanciaAux = 0.0;
                                for (int j = 0; j < users[i].getPosts().size(); j++) {
                                    distanciaAux += calcularDistancia(users[i].getPosts().get(j).getLocation().get(0), users[i].getPosts().get(j).getLocation().get(1), servers[x.get(i)].getLocation().get(0), servers[x.get(i)].getLocation().get(1));
                                }

                                distancia += distanciaAux/users[i].getPosts().size();
                            }
                            Empresa a = new Empresa();
                            a.Add(x, distancia, cost);
                            posibles.add(a);
                        }
                    }
                }

            }else{
                for (Integer i = 0; i < sizeServer; i++){
                    ArrayList<Integer> aux1;
                    aux1 = (ArrayList<Integer>) x.clone();
                    aux1.add(i);

                    double max, min, auxiliar;
                    max = 0.0;
                    min = 999999999.0;
                    for (int y = 0; y < sizeServer; y++){                           //
                        double aux = 0.0;
                        for (int j = 0; j < aux1.size(); j++){
                            if (aux1.get(j) == y){
                                aux += users[j].getActivity();
                            }
                        }
                        max = (aux > max) ? aux:max;
                        min = ((aux < min)) ? aux:min;
                    }
                    auxiliar = max - min;
                    double totalRestant = 0.0;
                    for (int j = aux1.size(); j < sizeUser; j++){
                        totalRestant += users[j].getActivity();
                    }
                    if ((auxiliar - totalRestant - tolerancia) < cost){
                        live_nodes.enqueue(aux1);
                    }
                }

            }

        }

        double distancia = 0.0;
        for (int i = 0; i < sizeUser; i++){
            double distanciaAux = 0.0;
            for (int j = 0; j < users[i].getPosts().size(); j++) {
                distanciaAux += calcularDistancia(users[i].getPosts().get(j).getLocation().get(0), users[i].getPosts().get(j).getLocation().get(1), servers[best.get(i)].getLocation().get(0), servers[best.get(i)].getLocation().get(1));
            }
            distancia += distanciaAux/users[i].getPosts().size();
        }

        for (int i = 0; i < posibles.size(); i++){
            if(posibles.get(i).getCost() - tolerancia < cost && posibles.get(i).getDistancia() < distancia){
                best = posibles.get(i).getBuenos();
                distancia = posibles.get(i).getDistancia();
            }
        }

        for (int i = 0; i < sizeServer; i++){
            System.out.println("Servidor " + (i + 1));
            for (int j = 0; j < best.size(); j++){
                if (best.get(j) == i){
                    System.out.println(users[j].getUsername());
                }

            }
            System.out.println("---------------------------------------------------");
        }
        System.out.println(tolerancia);

    }

    public void setBest(ArrayList<Integer> best, User[] users){
        this.best = best;
        double max, min;
        max = 0.0;
        min = 999999999.0;
        for (int y = 0; y < sizeServer; y++){
            double aux = 0.0;
            for (int i = 0; i < sizeUser;i++){
                if (best.get(i) == y){
                    aux += users[i].getActivity();
                }
            }
            max = (aux > max) ? aux:max;
            min = (aux < min) ? aux:min;
        }
        this.cost = max - min;

    }
}
