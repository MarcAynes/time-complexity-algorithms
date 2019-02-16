package Distribucion_Carga;

import users.User;

import java.util.ArrayList;

public class tipo {
    private ArrayList<User> users = new ArrayList<>();
    private double resultadoEquitividad;
    private double resultadoProximidad;

    public tipo(){

    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public double getResultadoEquitividad() {

        return resultadoEquitividad;
    }

    public double getResultadoProximidad() {

        return resultadoProximidad;
    }

    public void setUsers(ArrayList<User> users) {

        this.users = users;
    }

    public void addUser(User a){

        users.add(a);
    }

    public void setResultadoEquitividad(double resultadoEquitividad) {

        this.resultadoEquitividad = resultadoEquitividad;
    }

    public void setResultadoProximidad(double resultadoProximidad) {

        this.resultadoProximidad = resultadoProximidad;
    }
}
