import javax.sound.midi.Soundbank;
import java.util.List;

public class PizzaProducer implements Runnable {

    List<Pizza> pizzaList = null;
    final int LIMIT = 12;
    private int pizzaNo;

    public PizzaProducer(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public void orderPizza() throws InterruptedException {
        synchronized (pizzaList) {
            while(pizzaList.size() == LIMIT) {
                System.out.println("Too many pizzas waiting .. ");
                pizzaList.wait();
            }
        }
        synchronized (pizzaList) {
            pizzaList.add(new Pizza(3,"Vesuvio",54,"Løg,Ost"));
            pizzaList.notifyAll();
        }
    }

    @Override
    public void run() {
        while(true) {

            try {
                orderPizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
