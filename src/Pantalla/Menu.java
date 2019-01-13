package Pantalla;
import Calculo_Disponibilidad.Disponibilidad;
import Calculo_Disponibilidad.Greedy;
import Distribucion_Carga.Distribucion;
import Server.Server;
import nodes.Node;
import users.User;

import java.util.Scanner;

public class Menu {

    public static void menu(Node[] node, Server[] server, User[] user) {
        Scanner sc = new Scanner(System.in);
        char opcion, opcionM;
        double tolerancia = 0;

        int size = user.length;

        for(int i = 0; i < size; i++){
            tolerancia += user[i].getActivity();
        }

        tolerancia /= 5;

        System.out.println("Este es el menu principal de InstaSalle!");

        do {
            System.out.println("\nElija La funcionalidad que usted prefiera:");
            System.out.println("1. Disponibilidad");
            System.out.println("2. Distribucion de carrega");
            System.out.println("3. Salir");
            opcion = sc.next().charAt(0);


            switch (opcion) {
                case '1':
                    System.out.println("A quin servidor es troba en aquest moment?");
                    int servidor = Integer.parseInt(sc.next());
                    System.out.println("A que servidor se quiere conectar?");
                    int destino = Integer.parseInt(sc.next());

                    do {
                        System.out.println("\nElija el algoritmo para conseguir la solucion:");
                        System.out.println("1. Backtracking");
                        System.out.println("2. Branch & Bound");
                        System.out.println("3. Greedy");
                        System.out.println("4. Greedy + Backtracking");
                        System.out.println("5. Greedy + Branch & Bound");
                        System.out.println("6. Salir");
                        opcionM = sc.next().charAt(0);
                    } while (opcionM < '1' || opcionM > '6');

                    long nodeServerActual = server[servidor - 1].getReachableFrom() - 1;
                    long nodeServerFinal = server[destino - 1].getReachableFrom();
                    Disponibilidad disponibilidad = new Disponibilidad(node.length);

                    switch (opcionM) {
                        case '1':
                            disponibilidad.backtracking_D(node, (int) nodeServerActual, (int) nodeServerFinal, 0, 0);
                            disponibilidad.getBestCoste();
                            disponibilidad.getBestFiabilidad();
                            break;

                        case '2':

                            break;

                        case '3':
                            long StartTime = System.nanoTime();
                            Greedy g = new Greedy(node, nodeServerActual, nodeServerFinal);
                            g.calculateGreedy();
                            g.ReseteaGreedy();
                            g.calculateGreedyFiable();
                            long EndTime = System.nanoTime();
                            System.out.println("Tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");

                            break;

                        case '4':

                            break;

                        case '5':

                            break;

                        case '6':

                            break;

                        default:

                            break;
                    }
                    break;

                case '2':
                    Distribucion distribucion = new Distribucion(server.length, user.length);

                    do {
                        System.out.println("\nElija el algoritmo para conseguir lasolucion:");
                        System.out.println("1. Backtracking");
                        System.out.println("2. Branch & Bound");
                        System.out.println("3. Greedy");
                        System.out.println("4. Greedy + Backtracking");
                        System.out.println("5. Greedy + Branch & Bound");
                        System.out.println("6. Salir");
                        opcionM = sc.next().charAt(0);
                    } while (opcionM < '1' || opcionM > '6');

                    switch (opcionM) {
                        case '1':
                            distribucion.backtracking_D(server, user, tolerancia);
                            distribucion.getSolucion();
                            break;

                        case '2':

                            break;

                        case '3':

                            break;

                        case '4':

                            break;

                        case '5':

                            break;

                        case '6':

                            break;

                        default:

                            break;

                    }
                    break;

                case '3':

                    break;

                default:

                    break;
            }

        } while (opcion != '3');

    }
}

