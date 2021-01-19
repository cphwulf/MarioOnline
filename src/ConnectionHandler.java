import java.io.*;
import java.net.Socket;
import java.sql.Connection;

public class ConnectionHandler extends Thread{
    Socket socket;
    BufferedReader fromClient;
    PrintWriter toClient;
    String protokolIO;
    Pizza[] pizzas;

    public ConnectionHandler(Socket socket, Pizza[] pizzas) throws IOException {
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
                for(int i=0;i<pizzas.length;i++) {

                    if (pizzas[i]!=null && !picked) {
                        System.out.println("Sending " + pizzas[i].getName());
                        toClient.println(pizzas[i].getName() + " no. " + pizzas[i].getNo() + "," + pizzas[i].getCounter());

                        try {
                            int val = Integer.valueOf(protokolIO);
                            if (pizzas[i].getNo() == val) {
                                System.out.println("took pizza count " + pizzas[i].getCounter() + " no " + pizzas[i].getNo() + ", "+pizzas[i].getName());
                                pizzas[i] = null;
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
