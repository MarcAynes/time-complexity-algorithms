package Calculo_Disponibilidad;

import Calculo_Disponibilidad.Cola.Cola;
import Calculo_Disponibilidad.Cola.TipoCola;
import Server.Server;
import nodes.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

public class BranchAndBound {

    Node[] candidates;
    ArrayList<Node> SolutionCost = new ArrayList<>();
    ArrayList<Node> SolutionFiability = new ArrayList<>();
    boolean found = Boolean.FALSE;
    long servidor;
    long destino;
    long costTotal = 999999999;
    long fiabilidadTotal = 0;
    Cola cola = new Cola();
    Cola colaF = new Cola();

    public BranchAndBound(Node[] candidates, Server[] a, long servidor, long destino){

        this.candidates = candidates;
        this.servidor = servidor;
        this.destino = destino;

            for (int i = 0; i < a[(int) (servidor)].getReachableFrom().size(); i++) {

                    TipoCola aux = new TipoCola(0, candidates[(int) (a[(int) (servidor)].getReachableFrom().get(i) - 1)]);
                    cola.enqueueNoOrdenate(aux);
                    colaF.enqueueNoOrdenate(aux);
            }
            cola.Ordenate();        //ordenacion de cola, a partir de aqui tenemos una cola ordenada
            colaF.Ordenate();
    }

    public void BranchAndBound(Server[] server){
        TipoCola Best = new TipoCola();
        Best.setCost(999999999);


        while(!cola.Vacio()){
            TipoCola x;
            ArrayList<TipoCola> Options = new ArrayList<>();
            if(!cola.Vacio()) {
                x = cola.dequeue();
            }else{
                x = new TipoCola();

            }

            for(int i = 0; (x.getLastNode().getConnectsTo().size() > i && !contiene(x, Options, i)); i++){        //expand
                    if (x.getLastNode().getConnectsTo().size() > i && !contiene(x, Options, i)) {
                        TipoCola aux = new TipoCola(x.getCost() + x.getLastNode().getConnectsTo().get(i).getCost(), x, candidates[x.getLastNode().getConnectsTo().get(i).getTo() - 1]);
                        Options.add(aux);
                    }
            }
            for(int i = 0; i < Options.size(); i++){
                    if ((server[(int) destino].getReachableFrom().contains(Options.get(i).getLastNode().getId())&& Options.get(i).getCost() < Best.getCost()) && Options.size() > i) {  //if its solution
                        Best = Options.get(i);
                    } else {
                        if (Options.get(i).getCost() < Best.getCost()) {
                            cola.enqueue(Options.get(i));
                        }
                    }


            }

        }
        if(Best.getArray().get(0).getId() != -1) {
            System.out.println("El camino con menor coste es:");
            for (int i = 0; Best.getArray().size() > i; i++) {
                System.out.println(Best.getArray().get(i).getId());
            }
            System.out.println("El coste es de: " + Best.getCost());
        }else{
            System.out.println("Error, No hay camino posible que conecte los servidores");
        }


    }


    public void BranchAndBounfF(Server[] server){
        TipoCola BestF = new TipoCola();

        while(!colaF.Vacio()) {

            TipoCola y;
            ArrayList<TipoCola> OptionsF = new ArrayList<>();
            if (!colaF.Vacio()) {
                y = colaF.dequeue();

            } else {
                y = new TipoCola();
            }

            for (int i = 0; (y.getLastNode().getConnectsTo().size() > i && !contiene(y, OptionsF, i)); i++) {
                if (y.getLastNode().getConnectsTo().size() > i && !contiene(y, OptionsF, i)) {
                    TipoCola auxF = new TipoCola(0, y, candidates[y.getLastNode().getConnectsTo().get(i).getTo() - 1]);
                    auxF.setFiability(y.getFiability() * candidates[y.getLastNode().getConnectsTo().get(i).getTo() - 1].getReliability());
                    OptionsF.add(auxF);
                }

            }

            for (int i = 0; i < OptionsF.size(); i++) {
                if ((server[(int) destino].getReachableFrom().contains(OptionsF.get(i).getLastNode().getId()) && OptionsF.get(i).getFiability() > BestF.getFiability()) && OptionsF.size() > i) {
                    BestF = OptionsF.get(i);
                } else {
                    if (OptionsF.get(i).getFiability() > BestF.getFiability()) {
                        colaF.enquueueF(OptionsF.get(i));
                    }
                }

            }

        }

            if (BestF.getArray().get(0).getId() != -1){
                System.out.println("El camino con mas fiablidad es:");
                for (int i = 0; BestF.getArray().size() > i; i++){
                    System.out.println((BestF.getArray().get(i).getId()));
                }
                System.out.println("La fiabilidad es de: " + BestF.getFiability());
            }else{
                System.out.println("Error, no hay camino posible que conecte los servidores ");
            }








    }

    private boolean contiene(TipoCola x, ArrayList<TipoCola> options, int j){

        boolean contiene = Boolean.FALSE;

        for(int i = 0; i < x.getArray().size() - 1; i++){
            if(x.getArray().get(i).getId() == x.getLastNode().getId() && x.getArray().size() != 1){ //suposo que en un node no apunta a ell mateix
                contiene = Boolean.TRUE;
                break;
            }
        }
        return contiene;
    }

    public void BranchAndBoundGreedy(Server[] server, TipoCola Best, TipoCola BestF){

        if(Best.getLastNode().getId() == -1) {
            Best.setCost(999999999);
        }

        while(!cola.Vacio() || !colaF.Vacio()){
            TipoCola x;
            ArrayList<TipoCola> Options = new ArrayList<>();
            if(!cola.Vacio()) {
                x = cola.dequeue();
            }else{
                x = new TipoCola();

            }

            TipoCola y;
            ArrayList<TipoCola> OptionsF = new ArrayList<>();
            if(!colaF.Vacio()){
                y = colaF.dequeue();

            }else{
                y = new TipoCola();
            }

            for(int i = 0; (x.getLastNode().getConnectsTo().size() > i && !contiene(x, Options, i)) || (y.getLastNode().getConnectsTo().size() > i && !contiene(y, OptionsF, i)); i++){        //expand
                try {

                    if (x.getLastNode().getConnectsTo().size() > i && !contiene(x, Options, i)) {
                        TipoCola aux = new TipoCola(x.getCost() + x.getLastNode().getConnectsTo().get(i).getCost(), x, candidates[x.getLastNode().getConnectsTo().get(i).getTo() - 1]);
                        Options.add(aux);
                    }
                }catch (ArrayIndexOutOfBoundsException e){

                }

                try {
                    if (y.getLastNode().getConnectsTo().size() > i && !contiene(y, OptionsF, i)) {
                        TipoCola auxF = new TipoCola(0, y, candidates[y.getLastNode().getConnectsTo().get(i).getTo() - 1]);
                        auxF.setFiability(y.getFiability() * candidates[y.getLastNode().getConnectsTo().get(i).getTo() - 1].getReliability());
                        OptionsF.add(auxF);
                    }
                }catch(ArrayIndexOutOfBoundsException e){

                }
            }
            for(int i = 0; i < Options.size() || i < OptionsF.size(); i++){
                try {
                    if ((Options.get(i).getLastNode().getId() == server[(int) (destino - 1)].getId() && Options.get(i).getCost() < Best.getCost()) && Options.size() > i) {  //if its solution
                        Best = Options.get(i);
                    } else {
                        if (Options.get(i).getCost() < Best.getCost()) {
                            cola.enqueue(Options.get(i));
                        }
                    }
                }catch(IndexOutOfBoundsException e){

                }
                try {
                    if ((OptionsF.get(i).getLastNode().getId() == server[(int) (destino - 1)].getId() && OptionsF.get(i).getFiability() > BestF.getFiability()) && OptionsF.size() > i) {
                        BestF = OptionsF.get(i);
                    } else {
                        if (OptionsF.get(i).getFiability() > BestF.getFiability()) {
                            colaF.enquueueF(OptionsF.get(i));
                        }
                    }
                }catch(IndexOutOfBoundsException e){

                }

            }

        }
        if(Best.getArray().get(0).getId() != -1) {
            System.out.println("El camino con menor coste es:");
            for (int i = 0; Best.getArray().size() > i; i++) {
                System.out.println(Best.getArray().get(i).getId());
            }
            System.out.println("El coste es de: " + Best.getCost());
        }else{
            System.out.println("Error, No hay camino posible que conecte los servidores");
        }
        if (BestF.getArray().get(0).getId() != -1){
            System.out.println("El camino con mas fiablidad es:");
            for (int i = 0; BestF.getArray().size() > i; i++){
                System.out.println((BestF.getArray().get(i).getId()));
            }
            System.out.println("La fiabilidad es de: " + BestF.getFiability());
        }else{
            System.out.println("Error, no hay camino posible que conecte los servidores ");
        }


    }


}
