import Pantalla.Menu;
import Server.Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nodes.Node;
import users.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        Gson gson = new GsonBuilder().create();

        BufferedReader nodesDataset = null;
        BufferedReader serversDataset = null;
        BufferedReader usersDataset = null;


        nodesDataset = new BufferedReader(new FileReader("Datasets/nodes.json"));
        Node[] leerNode = gson.fromJson(nodesDataset, Node[].class);

        serversDataset = new BufferedReader(new FileReader("Datasets/servers.json"));
        Server[] leerServer = gson.fromJson(serversDataset, Server[].class);

        usersDataset = new BufferedReader(new FileReader("Datasets/users.json"));
        User[] leerUser = gson.fromJson(usersDataset, User[].class);

        menu.menu();


    }
}
