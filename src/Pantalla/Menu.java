package Pantalla;
import java.util.Scanner;

public class Menu {

    private static void menu() {
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

