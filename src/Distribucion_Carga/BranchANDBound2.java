package Distribucion_Carga;

import Distribucion_Carga.Cola2.Cola2;
import Server.Server;
import users.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchANDBound2 {
    Empresa best, x;
    ArrayList<Empresa> posiblesBest;


    public BranchANDBound2(int sizeServer, int sizeUser, User[] users){
        best = new Empresa(sizeServer);
        posiblesBest = new ArrayList<>();

        visitado = new boolean[sizeUser];
        Arrays.fill(visitado, false);
        live_nodes = new Cola2();
        for(int i = 0; users.length > i; i++){
            live_nodes.enqueueNoOrdenate(users[i]);
        }
        live_nodes.Ordenate();
        best.SetDiferenciaActividad(999999999);


    }

    public void Branch(Server[] servers, User[] users){


        while(!live_nodes.Vacio()){
            aux = live_nodes.dequeue();
            if(){

            }else{

            }

        }

    }
}
