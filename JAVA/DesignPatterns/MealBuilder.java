interface MealOptionsInterface {
    void addBurger(String options);
    void addColddrink(String options);
    void addFries(String options);
    Meal completeMeal();
}

class BurgerClass {
    String options;
    BurgerClass(String options) {
        this.options = options;
    }
}

class ColdDrink {
    String options;
    ColdDrink(String options) {
        this.options = options;
    }
}

class FriesClass {
    String options;
    FriesClass(String options) {
        this.options = options;
    }
}

class Meal {
    BurgerClass burger = null;
    ColdDrink coldDrink = null;
    FriesClass fries = null;

    Meal(String burgerOptions, String coldDrinkOptions, String friesOptions) {
        this.burger = new BurgerClass(burgerOptions);
        this.coldDrink = new ColdDrink(coldDrinkOptions);
        this.fries = new FriesClass(friesOptions);
    }

    @Override
    public String toString() {
        return "Meal [burger=" + burger.options + ", coldDrink=" + coldDrink.options + ", fries=" + fries.options + "]";
    }
}

public class MealBuilder implements MealOptionsInterface {
    private String burgerOptions = "";
    private String coldDrinkOptions = "";
    private String friesOptions = "";
    private Meal meal = null;

    @Override
    public void addBurger(String options) {
        this.burgerOptions = options;
    }

    @Override
    public void addColddrink(String options) {
        this.coldDrinkOptions = options;
    }

    @Override
    public void addFries(String options) {
        this.friesOptions = options;
    }

    @Override
    public Meal completeMeal() {
        meal = new Meal(burgerOptions, coldDrinkOptions, friesOptions);
        return meal;
    }
}

class MealBuilderClient {
    public static void main(String[] args) {
        MealOptionsInterface mealBuilder = new MealBuilder();
        mealBuilder.addBurger("adasdad");
        mealBuilder.addColddrink("asdada");
        mealBuilder.addFries("asdadfafsa");
        Meal meal = mealBuilder.completeMeal();
        System.out.println(meal);
    }
}
