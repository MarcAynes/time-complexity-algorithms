package Distribucion_Carga;

import Server.Server;
import users.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Distribucion {
    private Solution2 best, aux2;
    private List<Solution2> posiblesBest;
    private int contador;
    private boolean [] visitado;
    int sizeUser, sizeServer;

    public Distribucion(int sizeServer, int sizeUser) {

        best = new Solution2(sizeServer);
        posiblesBest = new ArrayList<>();
        aux2 = new Solution2(sizeServer);
        contador = 0;
        this.sizeServer = sizeServer;
        this.sizeUser = sizeUser;

        visitado = new boolean[sizeUser];
        Arrays.fill(visitado, false);


    }

    public void backtracking_D(Server[] servers, User [] users, double tolerancia){
        int index, size, size2;

        size = users.length;
        size2 = servers.length;


            if (aux2.isSolution(users.length, contador)){
                aux2.equitividadCarga();
                aux2.cercaniaUsuarios();

                if(aux2.getResultadoEquitividad() < best.getResultadoEquitividad()){
                    if (best.getResultadoEquitividad() < aux2.getResultadoEquitividad() + tolerancia) {
                        posiblesBest.add(best);
                    }
                    best.clonar(aux2);

                    for (int i = 0; i < posiblesBest.size(); i++) {
                        if (posiblesBest.get(i).getResultadoEquitividad() > best.getResultadoEquitividad() + tolerancia) {
                            posiblesBest.remove(i);
                            i--;
                        }
                    }
                }

                else {
                    if (aux2.getResultadoEquitividad() < best.getResultadoEquitividad() + tolerancia && aux2.getResultadoProximidad() < best.getResultadoProximidad()) {
                        posiblesBest.add(aux2);
                    }
                }
            }
            else {
                for(int i = 0; i < size; i++) {
                    if (!(visitado[i])) {
                        for (index = 0; index < size2; index++) {
                            aux2.getServidores()[index].getUsuarios().add(users[i]);
                            aux2.getServidores()[index].sumarValores(users[i], servers[index]);
                            visitado[i] = true;
                            contador++;
                            backtracking_D(servers, users, tolerancia);
                            aux2.getServidores()[index].getUsuarios().remove(users[i]);
                            aux2.getServidores()[index].quitarValores(users[i], servers[index]);
                            visitado[i] = false;
                            contador--;
                        }
                    }
                }

            }

    }

    public void getSolucion(){
        if (posiblesBest.size() == 0){
            System.out.println("Distribucion servidores:");
            for(int i = 0; i < sizeServer; i++){
                System.out.println("\nServidor " + i + ":");
                for(int j = 0; j < sizeServer; j++)
                    System.out.println(best.getServidores()[i].getUsuarios().get(j).getUsername());
            }
        }
        else {
            int longitud = posiblesBest.size();
            Solution2 solucion = new Solution2(1);
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
                for(int j = 0; j < sizeServer; j++)
                    System.out.println(solucion.getServidores()[i].getUsuarios().get(j).getUsername());
            }

        }
    }

}
