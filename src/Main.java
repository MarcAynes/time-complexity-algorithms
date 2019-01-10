import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();

        BufferedReader nodesDataset = null;
        BufferedReader serversDataset = null;
        BufferedReader usersDataset = null;


        nodesDataset = new BufferedReader(new FileReader("Datasets/nodes.json"));

        serversDataset = new BufferedReader(new FileReader("Datasets/servers.json"));

        usersDataset = new BufferedReader(new FileReader("Datasets/users.json"));




    }
}
