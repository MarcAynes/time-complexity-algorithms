package Distribucion_Carga;

import Server.Server;
import users.User;

import java.util.ArrayList;
import java.util.List;

import static Distribucion_Carga.Haversiano.calcularDistancia;

public class Usuarios_Servidor {
    private List<User> usuarios;
    private double cargaActividad;
    private double diferenciaProximidad;

    public Usuarios_Servidor(){
        usuarios = new ArrayList<>();
        cargaActividad = 0;
        diferenciaProximidad = 0;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public double getCargaActividad() {
        return cargaActividad;
    }

    public double getDiferenciaProximidad() {
        return diferenciaProximidad;
    }

    public void sumarValores(User user, Server server){
        cargaActividad += user.getActivity();
        int size = user.getPosts().size();

        for (int i = 0; i < size; i++){
            diferenciaProximidad += calcularDistancia(user.getPosts().get(i).getLocation().get(0), user.getPosts().get(i).getLocation().get(1), server.getLocation().get(0), server.getLocation().get(1));
        }

    }

    public void quitarValores(User user, Server server){
        cargaActividad -= user.getActivity();
        int size = user.getPosts().size();

        for (int i = 0; i < size; i++){
            diferenciaProximidad -= calcularDistancia(user.getPosts().get(i).getLocation().get(0), user.getPosts().get(i).getLocation().get(1), server.getLocation().get(0), server.getLocation().get(1));
        }

    }


}
