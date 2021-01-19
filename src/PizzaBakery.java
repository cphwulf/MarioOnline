import java.io.*;
import java.net.Socket;
import java.util.List;

public class PizzaBakery extends Thread {
    Socket socket;
    List<Pizza> menu;
    Pizza[] pizzas;
    BufferedReader bakerRead;
    PrintWriter bakerWrite;
    Database db;


    public PizzaBakery(Socket socket, Pizza[] pizzas, Database db) {
        this.pizzas = pizzas;
        this.menu = menu;
        this.db = db;
        try {
            bakerRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bakerWrite = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPizzaToQueue(Pizza pizza) {
        for(int i = 0; i < pizzas.length; i++) {
            if(pizzas[i] == null) {
                pizzas[i] = pizza;
                break;
            }
        }
    }

    @Override
    public void run() {
        String bakerLine = "";
        Pizza tmpPizza = null;
        try {
            while(!(bakerLine = bakerRead.readLine()).equals("bye")) {
                int inputVal = Integer.valueOf(bakerLine);

                tmpPizza = db.findPizzaById(inputVal);
                Pizza pizza = new Pizza(tmpPizza);
                    addPizzaToQueue(pizza);
                    System.out.println("Pizza " + pizza.getCounter() + " added to queue " + pizza.getName());
                System.out.println(bakerLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done baking");
        try {
            bakerRead.close();
            bakerWrite.close();
            System.out.println("The server is shut down!");
        } catch (IOException e) {
            System.out.println("err");
            e.printStackTrace();
        }
    }
}
