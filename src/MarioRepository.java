public interface MarioRepository extends PizzaFactoryI{
    Iterable<Pizza> findSomePizza(int i);
    Iterable<Pizza> findAllPizza();
}
