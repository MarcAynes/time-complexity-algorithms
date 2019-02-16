package Distribucion_Carga;

import users.User;

import java.util.ArrayList;

public class Server {

    private ArrayList<User> users = new ArrayList<>();
    private Double activitat = 0.0;

    public Server(){


    }

    public void AddUser(User u){
        users.add(u);
        activitat += u.getActivity();

    }

    public Double GetActivity(){

        return activitat;
    }

}
