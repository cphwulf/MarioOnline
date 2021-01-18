import javax.sound.midi.Soundbank;
import java.util.List;

public class PizzaConsumer implements Runnable {

    List<Pizza> pizzaList = null;

    public PizzaConsumer(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public void getPizza() throws InterruptedException {
        synchronized (pizzaList) {
            while(pizzaList.isEmpty()) {
                System.out.println("No more .. ");
                pizzaList.wait();
            }
        }
        synchronized (pizzaList) {
            System.out.println("Getting pizza");
            Pizza pizza = pizzaList.get(0);
            pizzaList.notifyAll();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                getPizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
