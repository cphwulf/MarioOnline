import java.time.LocalDateTime;

public interface PizzaFactoryI {
        Pizza createPizza(int pizzaID, int pizzaMenuID, double price, String name, String ingredients, LocalDateTime time);
    }
