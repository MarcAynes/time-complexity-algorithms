package Distribucion_Carga;

import users.User;

public class Empresa {
    private Server[] servidores;
    private double diferenciaActividad = 0.0;
    int serverMax = -1;
    int serverMin = 0;


    public Empresa(int i){

        servidores = new Server[i];

    }

    public void addUser(int servidor, User u){

        servidores[servidor].AddUser(u);
        if(serverMax == -1){
            serverMax = servidor;
        }else{
            if(servidores[servidor].GetActivity() > servidores[serverMax].GetActivity()){
                serverMax = servidor;
            }
            if(servidores[servidor].GetActivity() < servidores[serverMin].GetActivity()){
                serverMin = servidor;
            }
            diferenciaActividad = servidores[serverMax].GetActivity() - servidores[serverMin].GetActivity();
        }
    }

    public Double GetDiferenciaActividad(){

        return diferenciaActividad;
    }
    public void SetDiferenciaActividad(double activity){

        diferenciaActividad = activity;
    }

}
