import java.time.LocalDateTime;

public class Database implements MarioRepository{

    @Override
    public Iterable<Pizza> findSomePizza(int i) {
        return null;
    }

    @Override
    public Iterable<Pizza> findAllPizza() {
        return null;
    }

    @Override
    public Pizza createPizza(int pizzaID, int pizzaMenuID, double price, String name, String ingredients, LocalDateTime time) {
        return null;
    }
}

