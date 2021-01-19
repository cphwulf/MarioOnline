public interface PizzaRepository extends PizzaFactory {
    Iterable<Pizza> findAllPizzas();
    Pizza findPizzaById(int id) throws NoSuchPizzaException;
    void savePizzaToDB(Pizza pizza);
}
