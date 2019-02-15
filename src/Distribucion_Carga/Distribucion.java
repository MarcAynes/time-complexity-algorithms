package Distribucion_Carga;

import Server.Server;
import users.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Distribucion_Carga.Haversiano.calcularDistancia;

public class Distribucion {
    private Solution2 best, aux2;
    private List<Solution2> posiblesBest;
    private boolean [] visitado;
    int sizeUser, sizeServer;

    public Distribucion(int sizeServer, int sizeUser) {

        best = new Solution2(sizeServer);
        posiblesBest = new ArrayList<>();
        aux2 = new Solution2(sizeServer);
        this.sizeServer = sizeServer;
        this.sizeUser = sizeUser;

        visitado = new boolean[sizeUser];
        Arrays.fill(visitado, false);


    }

    public void greedy_D(Server[] servers, User [] users){
        int i, j;
        Solution2 mejorActual = new Solution2(sizeServer);

        for(i = 0; i < sizeUser; i++){
            for(j = 0; j < sizeServer; j++){
                if(j == 0){
                    mejorActual.clonar(best);
                    aux2.clonar(best);
                    mejorActual.getServidores()[j].getUsuarios().add(users[i]);
                    mejorActual.getServidores()[j].sumarValores(users[i], servers[j]);
                    mejorActual.equitividadCarga();
                    mejorActual.cercaniaUsuarios();
                }

                aux2.getServidores()[j].getUsuarios().add(users[i]);
                aux2.getServidores()[j].sumarValores(users[i], servers[j]);
                aux2.equitividadCarga();
                aux2.cercaniaUsuarios();

                if (aux2.getResultadoEquitividad() < mejorActual.getResultadoEquitividad()){
                    mejorActual.clonar(aux2);
                }
                else if(aux2.getResultadoEquitividad() == mejorActual.getResultadoEquitividad()){
                    if(aux2.getResultadoProximidad() < mejorActual.getResultadoProximidad()){
                        mejorActual.clonar(aux2);
                    }
                }

                aux2.getServidores()[j].getUsuarios().remove(users[i]);
                aux2.getServidores()[j].quitarValores(users[i], servers[j]);
            }

            best.clonar(mejorActual);

        }
    }

    public void backtracking_D(Server[] servers, User [] users, double tolerancia, int actual){
        int index;

        if (aux2.isSolution(users.length, actual)){
            aux2.equitividadCarga();
            aux2.cercaniaUsuarios();

            if(aux2.getResultadoEquitividad() < best.getResultadoEquitividad()){
                if (best.getResultadoEquitividad() < aux2.getResultadoEquitividad() + tolerancia) {
                    Solution2 aux = new Solution2(sizeServer);
                    aux.clonar(best);
                    posiblesBest.add(aux);
                }
                best.clonar(aux2);

                for (int i = 0; i < posiblesBest.size();) {
                    if (posiblesBest.get(i).getResultadoEquitividad() > best.getResultadoEquitividad() + tolerancia) {
                        posiblesBest.remove(i);
                        i--;
                    }
                    i++;
                }
            }

            else {
                if (aux2.getResultadoEquitividad() < best.getResultadoEquitividad() + tolerancia && aux2.getResultadoProximidad() < best.getResultadoProximidad()) {
                    Solution2 aux = new Solution2(sizeServer);
                    aux.clonar(aux2);
                    posiblesBest.add(aux);
                }
            }
        }
        else {
            for (index = 0; index < sizeServer; index++) {
                aux2.getServidores()[index].getUsuarios().add(users[actual]);
                aux2.getServidores()[index].sumarValores(users[actual], servers[index]);
                backtracking_D(servers, users, tolerancia, actual + 1);
                aux2.getServidores()[index].getUsuarios().remove(users[actual]);
                aux2.getServidores()[index].quitarValores(users[actual], servers[index]);
            }

            /*for(int i = 0; i < sizeUser; i++) {
                if (!(visitado[i])) {
                    for (index = 0; index < sizeServer; index++) {
                        aux2.getServidores()[index].getUsuarios().add(users[i]);
                        aux2.getServidores()[index].sumarValores(users[i], servers[index]);
                        visitado[i] = true;
                        backtracking_D(servers, users, tolerancia, contador + 1);
                        aux2.getServidores()[index].getUsuarios().remove(users[i]);
                        aux2.getServidores()[index].quitarValores(users[i], servers[index]);
                        visitado[i] = false;
                    }
                }
            }*/

        }
    }

    public void getSolucion(){
        if (posiblesBest.size() == 0){
            System.out.println("Distribucion servidores:");
            for(int i = 0; i < sizeServer; i++){
                System.out.println("\nServidor " + i + ":");
                int tam = best.getServidores()[i].getUsuarios().size();
                for(int j = 0; j < tam; j++)
                    System.out.println(best.getServidores()[i].getUsuarios().get(j).getUsername());
            }
        }
        else {
            int longitud = posiblesBest.size();
            Solution2 solucion = new Solution2(1);  // Inicializacion prescindible para que el programa no de error de inicializacion
            for(int k = 0; k < longitud; k++){
                if(k == 0){
                    solucion = posiblesBest.get(k);
                }
                else {
                    if(posiblesBest.get(k).getResultadoProximidad() < solucion.getResultadoProximidad()){
                        solucion = posiblesBest.get(k);
                    }
                }
            }

            System.out.println("Distribucion servidores:");
            for(int i = 0; i < sizeServer; i++){
                System.out.println("\nServidor " + i + ":");
                int tam = solucion.getServidores()[i].getUsuarios().size();
                for(int j = 0; j < tam; j++)
                    System.out.println(solucion.getServidores()[i].getUsuarios().get(j).getUsername());
            }

        }
    }

}
