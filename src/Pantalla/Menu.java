package Pantalla;
import Calculo_Disponibilidad.BranchAndBound;
import Calculo_Disponibilidad.Cola.TipoCola;
import Calculo_Disponibilidad.Disponibilidad;
import Calculo_Disponibilidad.Greedy;
import Server.Server;
import nodes.Node;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void menu(Node[] node, Server[] server) {
        Scanner sc = new Scanner(System.in);
        char opcion, opcionM;

        System.out.println("Este es el menu principal de InstaSalle!");

        do {
            System.out.println("\nElija La funcionalidad que usted prefiera:");
            System.out.println("1. Disponibilidad");
            System.out.println("2. Distribucion de carrega");
            System.out.println("3. Salir");
            opcion = sc.next().charAt(0);


            switch (opcion) {
                case '1':
                    int servidor, destino;
                    do{
                        System.out.println("A quin servidor es troba en aquest moment?");
                        servidor = Integer.parseInt(sc.next());
                        if(servidor > server.length || servidor < 1){
                            System.out.println("Error! Opcion fuera de los limites\n");
                        }
                    } while (servidor > server.length || servidor < 1);

                    do {
                        System.out.println("A que servidor se quiere conectar?");
                        destino = Integer.parseInt(sc.next());
                        if(destino > server.length || destino < 1){
                            System.out.println("Error! Opcion fuera de los limites\n");
                        }
                    } while (destino > server.length || destino < 1);

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

                    ArrayList<Long> nodeServerActual = server[servidor - 1].getReachableFrom();
                    ArrayList<Long> nodeServerFinal = server[destino - 1].getReachableFrom();
                    Disponibilidad disponibilidad = new Disponibilidad(node.length);
                    long StartTime;
                    long EndTime;

                    switch (opcionM) {
                        case '1':
                            StartTime = System.nanoTime();
                            disponibilidad.backtracking_D(node, nodeServerActual, nodeServerFinal, 0, 0);
                            disponibilidad.getBestCoste();
                            disponibilidad.getBestFiabilidad();
                            EndTime = System.nanoTime();
                            System.out.println("tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");
                            break;

                        case '2':
                            StartTime = System.nanoTime();
                            BranchAndBound Branch = new BranchAndBound(node, server, servidor - 1, destino - 1);
                            Branch.BranchAndBound(server);

                            EndTime = System.nanoTime();
                            System.out.println("tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");
                            break;

                        case '3':
                            StartTime = System.nanoTime();
                            Greedy g = new Greedy(node, servidor - 1, destino - 1, 1);
                            g.calculateGreedy(server);
                            g.ReseteaGreedy();
                            g.calculateGreedyFiable(server);
                            EndTime = System.nanoTime();
                            System.out.println("tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");

                            break;

                        case '4':
                            StartTime = System.nanoTime();
                            TipoCola BestcB;
                            TipoCola BestFB;
                            Greedy a = new Greedy(node, servidor - 1, destino - 1, 0);
                            BestcB = a.calculateGreedy(server);
                            a.ReseteaGreedy();
                            BestFB = a.calculateGreedyFiable(server);
                            disponibilidad.setBestCoste(BestcB);
                            disponibilidad.setBestFiabilidad(BestFB);
                            disponibilidad.backtracking_D(node, nodeServerActual, nodeServerFinal, 0, 0);
                            disponibilidad.getBestCoste();
                            disponibilidad.getBestFiabilidad();
                            EndTime = System.nanoTime();
                            System.out.println("tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");

                            break;

                        case '5':
                            StartTime = System.nanoTime();
                            TipoCola Bestc;
                            TipoCola BestF;
                            Greedy c = new Greedy(node, servidor - 1, destino - 1, 0);
                            Bestc = c.calculateGreedy(server);
                            c.ReseteaGreedy();
                            BestF = c.calculateGreedyFiable(server);
                            BranchAndBound BranchG = new BranchAndBound(node, server, servidor - 1, destino - 1);
                            BranchG.BranchAndBoundGreedy(server, Bestc, BestF);
                            EndTime = System.nanoTime();
                            System.out.println("tiempo de ejecucion: " + ((float) (EndTime - StartTime))/1000000 + "ms");
                            break;

                        case '6':

                            break;

                        default:

                            break;
                    }
                    break;

                case '2':
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

