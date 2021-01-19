import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class ConnectionHandler extends Thread{
    Socket socket;
    BufferedReader fromClient;
    PrintWriter toClient;
    String protokolIO;
    //Pizza[] pizzas;
    ArrayList<Pizza> pizzas;

    public ConnectionHandler(Socket socket, ArrayList<Pizza> pizzas) throws IOException {
        this.protokolIO = "";
        this.socket = socket;
        this.pizzas = pizzas;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            while (!((protokolIO = fromClient.readLine()).equalsIgnoreCase("bye"))) {
                boolean picked = false;
                Pizza tmpPizza = null;
                for(int i=0;i<pizzas.size();i++) {

                    if (pizzas.get(i) != null && !picked) {
                        toClient.println(pizzas.get(i).getName() + " no. " + pizzas.get(i).getNo() + "," + pizzas.get(i).getCounter());

                        try {
                            int val = Integer.valueOf(protokolIO);
                            if (pizzas.get(i).getNo() == val) {
                                System.out.println("took pizza count " + pizzas.get(i).getCounter() + " no " + pizzas.get(i).getNo() + ", "+pizzas.get(i).getName());
                                pizzas.add(null);
                                picked = true;
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                            System.out.println("Wron ...");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Got " + protokolIO);
    }
}
